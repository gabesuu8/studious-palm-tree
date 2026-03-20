package com.example.helloapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.helloapp.data.AppDatabase
import com.example.helloapp.data.Child
import com.example.helloapp.data.Vaccination
import com.example.helloapp.repository.VaccinationRepository
import com.example.helloapp.util.LanguageHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VaccinationViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    private val repository = VaccinationRepository(database.vaccinationDao())

    /** Children from Growth tracker - same list as in Growth tab. */
    val allChildren: Flow<List<Child>> = database.growthDao().getAllChildren()

    private val _selectedChild = MutableStateFlow<Child?>(null)
    val selectedChild: StateFlow<Child?> = _selectedChild

    private val _selectedFilter = MutableStateFlow("all")
    val selectedFilter: StateFlow<String> = _selectedFilter

    private val _expandedVaccineIds = MutableStateFlow<Set<Int>>(emptySet())
    val expandedVaccineIds: StateFlow<Set<Int>> = _expandedVaccineIds

    fun toggleExpanded(vaccinationId: Int) {
        _expandedVaccineIds.value = _expandedVaccineIds.value.toMutableSet().apply {
            if (contains(vaccinationId)) remove(vaccinationId) else add(vaccinationId)
        }
    }

    init {
        viewModelScope.launch {
            database.vaccinationDao().deleteDuplicates()
            if (repository.getTemplateCount() == 0) {
                initializeDefaultVaccinations()
            }
        }
    }

    /** Vaccinations for the currently selected child. */
    val vaccinationsForSelectedChild: StateFlow<List<Vaccination>> = _selectedChild.flatMapLatest { child ->
        if (child != null) {
            flow {
                ensureChildHasVaccinationRecords(child.id)
                emitAll(repository.getVaccinationsForChild(child.id))
            }
        } else {
            flowOf(emptyList())
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private suspend fun ensureChildHasVaccinationRecords(childId: Long) {
        if (repository.getVaccinationCountForChild(childId) == 0) {
            // Ensure templates exist before copying (fixes race with init block)
            if (repository.getTemplateCount() == 0) {
                initializeDefaultVaccinations()
            }
            repository.copyTemplatesToChild(childId)
        }
    }

    fun setSelectedChild(child: Child?) {
        _selectedChild.value = child
    }

    /** All vaccinations for selected child (for progress). */
    val allVaccinations: StateFlow<List<Vaccination>> = vaccinationsForSelectedChild

    /** Next vaccine due for selected child (name, due date ms, isOverdue). Null if no child or all complete. */
    data class NextVaccineDue(val name: String, val dueDateMillis: Long, val isOverdue: Boolean)
    val nextVaccineDue: Flow<NextVaccineDue?> = combine(
        vaccinationsForSelectedChild,
        _selectedChild
    ) { vaccinations, child ->
        if (child == null || vaccinations.isEmpty()) return@combine null
        val pending = vaccinations.filter { !it.isFullyCompleted }
        if (pending.isEmpty()) return@combine null
        val now = System.currentTimeMillis()
        val dob = child.dateOfBirth
        val withDue = pending.mapNotNull { v ->
            val nextAge = v.nextDoseAge ?: return@mapNotNull null
            val days = parseRecommendedAgeToDays(nextAge)
            val dueMs = dob + days * 24L * 60 * 60 * 1000
            Triple(v, dueMs, dueMs < now)
        }
        if (withDue.isEmpty()) return@combine null
        val sorted = withDue.sortedBy { it.second }
        val next = sorted.first()
        NextVaccineDue(next.first.name, next.second, next.third)
    }

    companion object {
        /** Parse recommendedAge string to approximate days from birth for ordering. */
        @JvmStatic
        fun parseRecommendedAgeToDays(recommendedAge: String): Long {
            val s = recommendedAge.lowercase()
            when {
                s.contains("birth") || s.contains("naissance") -> return 0
                s.contains("week") || s.contains("semaine") -> {
                    val num = Regex("(\\d+)").find(s)?.groupValues?.get(1)?.toLongOrNull() ?: 0
                    return num * 7
                }
                s.contains("month") || s.contains("mois") -> {
                    val num = Regex("(\\d+)").find(s)?.groupValues?.get(1)?.toLongOrNull() ?: 0
                    return num * 30
                }
                s.contains("year") || s.contains("an") -> {
                    val num = Regex("(\\d+)").find(s)?.groupValues?.get(1)?.toLongOrNull() ?: 2
                    return num * 365
                }
            }
            return 0
        }
    }

    val filteredVaccinations: Flow<List<Vaccination>> = combine(
        vaccinationsForSelectedChild,
        _selectedFilter
    ) { vaccinations, filter ->
        when (filter) {
            "completed" -> vaccinations.filter { it.isFullyCompleted }
            "pending" -> vaccinations.filter { !it.isFullyCompleted }
            else -> vaccinations
        }
    }

    fun setFilter(filter: String) {
        _selectedFilter.value = filter
    }

    fun recordNextDose(vaccination: Vaccination) {
        viewModelScope.launch {
            if (vaccination.isFullyCompleted) {
                // Undo the last recorded dose
                val dates = vaccination.parsedDoseDates
                val lastIndex = dates.indexOfLast { it != null }
                if (lastIndex >= 0) {
                    repository.updateVaccination(vaccination.withDoseUndone(lastIndex))
                }
            } else {
                // Record next pending dose
                val dates = vaccination.parsedDoseDates
                val nextIndex = dates.indexOfFirst { it == null }
                if (nextIndex >= 0) {
                    repository.updateVaccination(vaccination.withDoseRecorded(nextIndex, System.currentTimeMillis()))
                }
            }
        }
    }

    fun recordDoseWithDate(vaccination: Vaccination, doseIndex: Int, date: Long) {
        viewModelScope.launch {
            val updated = vaccination.withDoseRecorded(doseIndex, date)
            if (updated !== vaccination) {
                repository.updateVaccination(updated)
            }
        }
    }

    fun undoDose(vaccination: Vaccination, doseIndex: Int) {
        viewModelScope.launch {
            val updated = vaccination.withDoseUndone(doseIndex)
            if (updated !== vaccination) {
                repository.updateVaccination(updated)
            }
        }
    }

    fun undoLastDose(vaccination: Vaccination) {
        viewModelScope.launch {
            if (vaccination.completedDoses > 0) {
                val dates = vaccination.parsedDoseDates
                val lastIndex = dates.indexOfLast { it != null }
                if (lastIndex >= 0) {
                    repository.updateVaccination(vaccination.withDoseUndone(lastIndex))
                } else {
                    val updated = vaccination.copy(
                        completedDoses = vaccination.completedDoses - 1,
                        lastDoseDate = null
                    )
                    repository.updateVaccination(updated)
                }
            }
        }
    }

    /** Reset vaccination progress for the selected child only. */
    fun resetVaccinationsForSelectedChild() {
        viewModelScope.launch {
            val child = _selectedChild.value ?: return@launch
            repository.deleteAllForChild(child.id)
            repository.copyTemplatesToChild(child.id)
        }
    }

    fun reinitializeVaccinations() {
        viewModelScope.launch {
            repository.deleteTemplates()
            initializeDefaultVaccinations()
        }
    }

    private suspend fun initializeDefaultVaccinations() {
        val languageCode = LanguageHelper.getLanguage(getApplication())
        val vaccinations = if (languageCode == "fr") {
            getFrenchVaccinations()
        } else {
            getEnglishVaccinations()
        }
        repository.insertAll(vaccinations)
    }

    private fun getEnglishVaccinations(): List<Vaccination> {
        return listOf(
            // At Birth
            Vaccination(
                name = "BCG",
                description = "Protects against tuberculosis (TB). Given as a single dose at birth.",
                totalDoses = 1,
                doseSchedule = "At birth",
                category = "Birth"
            ),
            Vaccination(
                name = "OPV (Oral Polio)",
                description = "Protects against poliomyelitis. Given in multiple doses from birth through 15-18 months.",
                totalDoses = 5,
                doseSchedule = "At birth|6 weeks|10 weeks|14 weeks|15-18 months",
                category = "Birth"
            ),
            Vaccination(
                name = "Hepatitis B",
                description = "Prevents Hepatitis B infection. Should be given within 24 hours of birth.",
                totalDoses = 1,
                doseSchedule = "At birth",
                category = "Birth"
            ),

            // 6 Weeks
            Vaccination(
                name = "Pentavalent (DTP-HepB-Hib)",
                description = "Protects against Diphtheria, Tetanus, Pertussis, Hepatitis B, and Haemophilus influenzae type b.",
                totalDoses = 3,
                doseSchedule = "6 weeks|10 weeks|14 weeks",
                category = "6 Weeks"
            ),
            Vaccination(
                name = "Pneumococcal (PCV)",
                description = "Protects against pneumococcal diseases including pneumonia and meningitis.",
                totalDoses = 3,
                doseSchedule = "6 weeks|10 weeks|14 weeks",
                category = "6 Weeks"
            ),
            Vaccination(
                name = "Rotavirus",
                description = "Protects against rotavirus, a common cause of severe diarrhea in children.",
                totalDoses = 2,
                doseSchedule = "6 weeks|10 weeks",
                category = "6 Weeks"
            ),

            // 14 Weeks
            Vaccination(
                name = "IPV (Inactivated Polio)",
                description = "Injectable polio vaccine for additional protection against poliomyelitis.",
                totalDoses = 1,
                doseSchedule = "14 weeks",
                category = "14 Weeks"
            ),

            // 6 Months
            Vaccination(
                name = "Vitamin A",
                description = "Vitamin A supplementation to prevent deficiency and boost immunity.",
                totalDoses = 2,
                doseSchedule = "6 months|12 months",
                category = "6 Months"
            ),

            // 9 Months
            Vaccination(
                name = "Measles-Rubella (MR)",
                description = "Protects against measles and rubella.",
                totalDoses = 2,
                doseSchedule = "9 months|15-18 months",
                category = "9 Months"
            ),
            Vaccination(
                name = "Yellow Fever",
                description = "Single dose vaccine protecting against yellow fever. Required in endemic areas.",
                totalDoses = 1,
                doseSchedule = "9 months",
                category = "9 Months"
            ),
            Vaccination(
                name = "Meningococcal A",
                description = "Protects against meningococcal meningitis type A.",
                totalDoses = 1,
                doseSchedule = "9 months",
                category = "9 Months"
            ),

            // 15-18 Months
            Vaccination(
                name = "DTP Booster",
                description = "Booster dose for continued protection against diphtheria, tetanus, and pertussis.",
                totalDoses = 1,
                doseSchedule = "15-18 months",
                category = "15-18 Months"
            ),

            // Special Vaccines
            Vaccination(
                name = "Malaria Vaccine (RTS,S)",
                description = "Recommended in areas with moderate to high malaria transmission. Given in 4 doses.",
                totalDoses = 4,
                doseSchedule = "5 months|6 months|7 months|17 months",
                category = "Special Vaccines"
            ),
            Vaccination(
                name = "Typhoid",
                description = "Protects against typhoid fever. Recommended in endemic areas.",
                totalDoses = 1,
                doseSchedule = "2 years and above",
                category = "Special Vaccines"
            )
        )
    }

    private fun getFrenchVaccinations(): List<Vaccination> {
        return listOf(
            // À la naissance
            Vaccination(
                name = "BCG",
                description = "Protège contre la tuberculose. Administré en dose unique à la naissance.",
                totalDoses = 1,
                doseSchedule = "À la naissance",
                category = "Naissance"
            ),
            Vaccination(
                name = "VPO (Polio oral)",
                description = "Protège contre la poliomyélite. Administré en plusieurs doses de la naissance à 15-18 mois.",
                totalDoses = 5,
                doseSchedule = "À la naissance|6 semaines|10 semaines|14 semaines|15-18 mois",
                category = "Naissance"
            ),
            Vaccination(
                name = "Hépatite B",
                description = "Prévient l'infection par l'hépatite B. Doit être administrée dans les 24 heures suivant la naissance.",
                totalDoses = 1,
                doseSchedule = "À la naissance",
                category = "Naissance"
            ),

            // 6 Semaines
            Vaccination(
                name = "Pentavalent (DTC-HépB-Hib)",
                description = "Protège contre la diphtérie, le tétanos, la coqueluche, l'hépatite B et Haemophilus influenzae type b.",
                totalDoses = 3,
                doseSchedule = "6 semaines|10 semaines|14 semaines",
                category = "6 Semaines"
            ),
            Vaccination(
                name = "Pneumocoque (PCV)",
                description = "Protège contre les maladies pneumococciques, y compris la pneumonie et la méningite.",
                totalDoses = 3,
                doseSchedule = "6 semaines|10 semaines|14 semaines",
                category = "6 Semaines"
            ),
            Vaccination(
                name = "Rotavirus",
                description = "Protège contre le rotavirus, cause fréquente de diarrhée sévère chez les enfants.",
                totalDoses = 2,
                doseSchedule = "6 semaines|10 semaines",
                category = "6 Semaines"
            ),

            // 14 Semaines
            Vaccination(
                name = "VPI (Polio inactivé)",
                description = "Vaccin antipoliomyélitique injectable pour une protection supplémentaire.",
                totalDoses = 1,
                doseSchedule = "14 semaines",
                category = "14 Semaines"
            ),

            // 6 Mois
            Vaccination(
                name = "Vitamine A",
                description = "Supplémentation en vitamine A pour prévenir les carences et renforcer l'immunité.",
                totalDoses = 2,
                doseSchedule = "6 mois|12 mois",
                category = "6 Mois"
            ),

            // 9 Mois
            Vaccination(
                name = "Rougeole-Rubéole (RR)",
                description = "Protège contre la rougeole et la rubéole.",
                totalDoses = 2,
                doseSchedule = "9 mois|15-18 mois",
                category = "9 Mois"
            ),
            Vaccination(
                name = "Fièvre jaune",
                description = "Vaccin à dose unique protégeant contre la fièvre jaune. Obligatoire dans les zones endémiques.",
                totalDoses = 1,
                doseSchedule = "9 mois",
                category = "9 Mois"
            ),
            Vaccination(
                name = "Méningocoque A",
                description = "Protège contre la méningite à méningocoque de type A.",
                totalDoses = 1,
                doseSchedule = "9 mois",
                category = "9 Mois"
            ),

            // 15-18 Mois
            Vaccination(
                name = "DTC Rappel",
                description = "Dose de rappel pour une protection continue contre la diphtérie, le tétanos et la coqueluche.",
                totalDoses = 1,
                doseSchedule = "15-18 mois",
                category = "15-18 Mois"
            ),

            // Vaccins spéciaux
            Vaccination(
                name = "Vaccin antipaludique (RTS,S)",
                description = "Recommandé dans les zones à transmission modérée à élevée du paludisme. Administré en 4 doses.",
                totalDoses = 4,
                doseSchedule = "5 mois|6 mois|7 mois|17 mois",
                category = "Vaccins Spéciaux"
            ),
            Vaccination(
                name = "Typhoïde",
                description = "Protège contre la fièvre typhoïde. Recommandé dans les zones endémiques.",
                totalDoses = 1,
                doseSchedule = "2 ans et plus",
                category = "Vaccins Spéciaux"
            )
        )
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VaccinationViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VaccinationViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
