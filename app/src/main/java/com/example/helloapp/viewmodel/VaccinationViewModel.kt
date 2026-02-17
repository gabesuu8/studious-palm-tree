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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
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

    init {
        viewModelScope.launch {
            if (repository.getTemplateCount() == 0) {
                initializeDefaultVaccinations()
            }
        }
    }

    /** Vaccinations for the currently selected child. */
    val vaccinationsForSelectedChild: Flow<List<Vaccination>> = _selectedChild.flatMapLatest { child ->
        if (child != null) {
            flow {
                ensureChildHasVaccinationRecords(child.id)
                repository.getVaccinationsForChild(child.id).collect { emit(it) }
            }
        } else {
            flowOf(emptyList())
        }
    }

    private suspend fun ensureChildHasVaccinationRecords(childId: Long) {
        if (repository.getVaccinationCountForChild(childId) == 0) {
            repository.copyTemplatesToChild(childId)
        }
    }

    fun setSelectedChild(child: Child?) {
        _selectedChild.value = child
    }

    /** All vaccinations for selected child (for progress). */
    val allVaccinations: Flow<List<Vaccination>> = vaccinationsForSelectedChild

    /** Next vaccine due for selected child (name, due date ms, isOverdue). Null if no child or all complete. */
    data class NextVaccineDue(val name: String, val dueDateMillis: Long, val isOverdue: Boolean)
    val nextVaccineDue: Flow<NextVaccineDue?> = combine(
        vaccinationsForSelectedChild,
        _selectedChild
    ) { vaccinations, child ->
        if (child == null || vaccinations.isEmpty()) return@combine null
        val pending = vaccinations.filter { !it.isCompleted }
        if (pending.isEmpty()) return@combine null
        val now = System.currentTimeMillis()
        val dob = child.dateOfBirth
        val withDue = pending.map { v ->
            val days = parseRecommendedAgeToDays(v.recommendedAge)
            val dueMs = dob + days * 24L * 60 * 60 * 1000
            Triple(v, dueMs, dueMs < now)
        }
        val sorted = withDue.sortedBy { it.second }
        val next = sorted.first()
        NextVaccineDue(next.first.name, next.second, next.third)
    }

    /** Parse recommendedAge string to approximate days from birth for ordering. */
    private fun parseRecommendedAgeToDays(recommendedAge: String): Long {
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

    val filteredVaccinations: Flow<List<Vaccination>> = combine(
        vaccinationsForSelectedChild,
        _selectedFilter
    ) { vaccinations, filter ->
        when (filter) {
            "completed" -> vaccinations.filter { it.isCompleted }
            "pending" -> vaccinations.filter { !it.isCompleted }
            else -> vaccinations
        }
    }
    
    fun setFilter(filter: String) {
        _selectedFilter.value = filter
    }
    
    fun toggleVaccinationStatus(vaccination: Vaccination) {
        viewModelScope.launch {
            val updated = vaccination.copy(
                isCompleted = !vaccination.isCompleted,
                dateCompleted = if (!vaccination.isCompleted) System.currentTimeMillis() else null
            )
            repository.updateVaccination(updated)
        }
    }
    
    fun updateVaccinationDate(vaccination: Vaccination, date: Long) {
        viewModelScope.launch {
            val updated = vaccination.copy(
                isCompleted = true,
                dateCompleted = date
            )
            repository.updateVaccination(updated)
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
                recommendedAge = "At birth",
                category = "Birth"
            ),
            Vaccination(
                name = "OPV-0 (Oral Polio)",
                description = "First dose of oral polio vaccine. Protects against poliomyelitis.",
                recommendedAge = "At birth",
                category = "Birth"
            ),
            Vaccination(
                name = "Hepatitis B - Birth Dose",
                description = "First dose to prevent Hepatitis B infection. Should be given within 24 hours of birth.",
                recommendedAge = "At birth (within 24 hours)",
                category = "Birth"
            ),
            
            // 6 Weeks
            Vaccination(
                name = "DTP-HepB-Hib 1 (Pentavalent)",
                description = "Protects against Diphtheria, Tetanus, Pertussis, Hepatitis B, and Haemophilus influenzae type b.",
                recommendedAge = "6 weeks",
                category = "6 Weeks"
            ),
            Vaccination(
                name = "OPV-1",
                description = "Second dose of oral polio vaccine.",
                recommendedAge = "6 weeks",
                category = "6 Weeks"
            ),
            Vaccination(
                name = "Pneumococcal (PCV) 1",
                description = "First dose protecting against pneumococcal diseases including pneumonia and meningitis.",
                recommendedAge = "6 weeks",
                category = "6 Weeks"
            ),
            Vaccination(
                name = "Rotavirus 1",
                description = "First dose protecting against rotavirus, a common cause of severe diarrhea in children.",
                recommendedAge = "6 weeks",
                category = "6 Weeks"
            ),
            
            // 10 Weeks
            Vaccination(
                name = "DTP-HepB-Hib 2 (Pentavalent)",
                description = "Second dose of the pentavalent vaccine.",
                recommendedAge = "10 weeks",
                category = "10 Weeks"
            ),
            Vaccination(
                name = "OPV-2",
                description = "Third dose of oral polio vaccine.",
                recommendedAge = "10 weeks",
                category = "10 Weeks"
            ),
            Vaccination(
                name = "Pneumococcal (PCV) 2",
                description = "Second dose of pneumococcal vaccine.",
                recommendedAge = "10 weeks",
                category = "10 Weeks"
            ),
            Vaccination(
                name = "Rotavirus 2",
                description = "Second dose of rotavirus vaccine.",
                recommendedAge = "10 weeks",
                category = "10 Weeks"
            ),
            
            // 14 Weeks
            Vaccination(
                name = "DTP-HepB-Hib 3 (Pentavalent)",
                description = "Third and final dose of the pentavalent vaccine.",
                recommendedAge = "14 weeks",
                category = "14 Weeks"
            ),
            Vaccination(
                name = "OPV-3",
                description = "Fourth dose of oral polio vaccine.",
                recommendedAge = "14 weeks",
                category = "14 Weeks"
            ),
            Vaccination(
                name = "Pneumococcal (PCV) 3",
                description = "Third dose of pneumococcal vaccine.",
                recommendedAge = "14 weeks",
                category = "14 Weeks"
            ),
            Vaccination(
                name = "IPV (Inactivated Polio)",
                description = "Injectable polio vaccine for additional protection.",
                recommendedAge = "14 weeks",
                category = "14 Weeks"
            ),
            
            // 6 Months
            Vaccination(
                name = "Vitamin A - 1st Dose",
                description = "First dose of Vitamin A supplementation to prevent deficiency and boost immunity.",
                recommendedAge = "6 months",
                category = "6 Months"
            ),
            
            // 9 Months
            Vaccination(
                name = "Measles-Rubella 1 (MR)",
                description = "First dose protecting against measles and rubella.",
                recommendedAge = "9 months",
                category = "9 Months"
            ),
            Vaccination(
                name = "Yellow Fever",
                description = "Single dose vaccine protecting against yellow fever. Required in endemic areas.",
                recommendedAge = "9 months",
                category = "9 Months"
            ),
            Vaccination(
                name = "Meningococcal A",
                description = "Protects against meningococcal meningitis type A.",
                recommendedAge = "9 months",
                category = "9 Months"
            ),
            
            // 12 Months
            Vaccination(
                name = "Vitamin A - 2nd Dose",
                description = "Second dose of Vitamin A supplementation.",
                recommendedAge = "12 months",
                category = "12 Months"
            ),
            
            // 15-18 Months
            Vaccination(
                name = "Measles-Rubella 2 (MR)",
                description = "Second dose of measles-rubella vaccine for lasting protection.",
                recommendedAge = "15-18 months",
                category = "15-18 Months"
            ),
            Vaccination(
                name = "DTP Booster",
                description = "Booster dose for continued protection against diphtheria, tetanus, and pertussis.",
                recommendedAge = "15-18 months",
                category = "15-18 Months"
            ),
            Vaccination(
                name = "OPV Booster",
                description = "Booster dose of oral polio vaccine.",
                recommendedAge = "15-18 months",
                category = "15-18 Months"
            ),
            
            // Additional vaccines
            Vaccination(
                name = "Malaria Vaccine (RTS,S)",
                description = "Recommended in areas with moderate to high malaria transmission. Given in 4 doses.",
                recommendedAge = "5-17 months (series)",
                category = "Special Vaccines"
            ),
            Vaccination(
                name = "Typhoid",
                description = "Protects against typhoid fever. Recommended in endemic areas.",
                recommendedAge = "2 years and above",
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
                recommendedAge = "À la naissance",
                category = "Naissance"
            ),
            Vaccination(
                name = "VPO-0 (Polio oral)",
                description = "Première dose du vaccin antipoliomyélitique oral. Protège contre la poliomyélite.",
                recommendedAge = "À la naissance",
                category = "Naissance"
            ),
            Vaccination(
                name = "Hépatite B - Dose de naissance",
                description = "Première dose pour prévenir l'infection par l'hépatite B. Doit être administrée dans les 24 heures suivant la naissance.",
                recommendedAge = "À la naissance (dans les 24 heures)",
                category = "Naissance"
            ),
            
            // 6 Semaines
            Vaccination(
                name = "DTC-HépB-Hib 1 (Pentavalent)",
                description = "Protège contre la diphtérie, le tétanos, la coqueluche, l'hépatite B et Haemophilus influenzae type b.",
                recommendedAge = "6 semaines",
                category = "6 Semaines"
            ),
            Vaccination(
                name = "VPO-1",
                description = "Deuxième dose du vaccin antipoliomyélitique oral.",
                recommendedAge = "6 semaines",
                category = "6 Semaines"
            ),
            Vaccination(
                name = "Pneumocoque (PCV) 1",
                description = "Première dose protégeant contre les maladies pneumococciques, y compris la pneumonie et la méningite.",
                recommendedAge = "6 semaines",
                category = "6 Semaines"
            ),
            Vaccination(
                name = "Rotavirus 1",
                description = "Première dose protégeant contre le rotavirus, cause fréquente de diarrhée sévère chez les enfants.",
                recommendedAge = "6 semaines",
                category = "6 Semaines"
            ),
            
            // 10 Semaines
            Vaccination(
                name = "DTC-HépB-Hib 2 (Pentavalent)",
                description = "Deuxième dose du vaccin pentavalent.",
                recommendedAge = "10 semaines",
                category = "10 Semaines"
            ),
            Vaccination(
                name = "VPO-2",
                description = "Troisième dose du vaccin antipoliomyélitique oral.",
                recommendedAge = "10 semaines",
                category = "10 Semaines"
            ),
            Vaccination(
                name = "Pneumocoque (PCV) 2",
                description = "Deuxième dose du vaccin pneumococcique.",
                recommendedAge = "10 semaines",
                category = "10 Semaines"
            ),
            Vaccination(
                name = "Rotavirus 2",
                description = "Deuxième dose du vaccin contre le rotavirus.",
                recommendedAge = "10 semaines",
                category = "10 Semaines"
            ),
            
            // 14 Semaines
            Vaccination(
                name = "DTC-HépB-Hib 3 (Pentavalent)",
                description = "Troisième et dernière dose du vaccin pentavalent.",
                recommendedAge = "14 semaines",
                category = "14 Semaines"
            ),
            Vaccination(
                name = "VPO-3",
                description = "Quatrième dose du vaccin antipoliomyélitique oral.",
                recommendedAge = "14 semaines",
                category = "14 Semaines"
            ),
            Vaccination(
                name = "Pneumocoque (PCV) 3",
                description = "Troisième dose du vaccin pneumococcique.",
                recommendedAge = "14 semaines",
                category = "14 Semaines"
            ),
            Vaccination(
                name = "VPI (Polio inactivé)",
                description = "Vaccin antipoliomyélitique injectable pour une protection supplémentaire.",
                recommendedAge = "14 semaines",
                category = "14 Semaines"
            ),
            
            // 6 Mois
            Vaccination(
                name = "Vitamine A - 1ère dose",
                description = "Première dose de supplémentation en vitamine A pour prévenir les carences et renforcer l'immunité.",
                recommendedAge = "6 mois",
                category = "6 Mois"
            ),
            
            // 9 Mois
            Vaccination(
                name = "Rougeole-Rubéole 1 (RR)",
                description = "Première dose protégeant contre la rougeole et la rubéole.",
                recommendedAge = "9 mois",
                category = "9 Mois"
            ),
            Vaccination(
                name = "Fièvre jaune",
                description = "Vaccin à dose unique protégeant contre la fièvre jaune. Obligatoire dans les zones endémiques.",
                recommendedAge = "9 mois",
                category = "9 Mois"
            ),
            Vaccination(
                name = "Méningocoque A",
                description = "Protège contre la méningite à méningocoque de type A.",
                recommendedAge = "9 mois",
                category = "9 Mois"
            ),
            
            // 12 Mois
            Vaccination(
                name = "Vitamine A - 2ème dose",
                description = "Deuxième dose de supplémentation en vitamine A.",
                recommendedAge = "12 mois",
                category = "12 Mois"
            ),
            
            // 15-18 Mois
            Vaccination(
                name = "Rougeole-Rubéole 2 (RR)",
                description = "Deuxième dose du vaccin rougeole-rubéole pour une protection durable.",
                recommendedAge = "15-18 mois",
                category = "15-18 Mois"
            ),
            Vaccination(
                name = "DTC Rappel",
                description = "Dose de rappel pour une protection continue contre la diphtérie, le tétanos et la coqueluche.",
                recommendedAge = "15-18 mois",
                category = "15-18 Mois"
            ),
            Vaccination(
                name = "VPO Rappel",
                description = "Dose de rappel du vaccin antipoliomyélitique oral.",
                recommendedAge = "15-18 mois",
                category = "15-18 Mois"
            ),
            
            // Vaccins spéciaux
            Vaccination(
                name = "Vaccin antipaludique (RTS,S)",
                description = "Recommandé dans les zones à transmission modérée à élevée du paludisme. Administré en 4 doses.",
                recommendedAge = "5-17 mois (série)",
                category = "Vaccins Spéciaux"
            ),
            Vaccination(
                name = "Typhoïde",
                description = "Protège contre la fièvre typhoïde. Recommandé dans les zones endémiques.",
                recommendedAge = "2 ans et plus",
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
