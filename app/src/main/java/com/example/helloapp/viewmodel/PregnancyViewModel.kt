package com.example.helloapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.helloapp.data.AppDatabase
import com.example.helloapp.data.Pregnancy
import com.example.helloapp.data.PrenatalVisit
import com.example.helloapp.repository.PregnancyRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PregnancyViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = PregnancyRepository(database.pregnancyDao())
    
    val activePregnancies: StateFlow<List<Pregnancy>> = repository.activePregnancies
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    
    val allPregnancies: StateFlow<List<Pregnancy>> = repository.allPregnancies
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    
    private val _selectedPregnancy = MutableStateFlow<Pregnancy?>(null)
    val selectedPregnancy: StateFlow<Pregnancy?> = _selectedPregnancy
    
    val prenatalVisits: StateFlow<List<PrenatalVisit>> = _selectedPregnancy.flatMapLatest { pregnancy ->
        if (pregnancy != null) {
            repository.getVisitsForPregnancy(pregnancy.id)
        } else {
            flowOf(emptyList())
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    
    fun selectPregnancy(pregnancy: Pregnancy?) {
        _selectedPregnancy.value = pregnancy
    }
    
    fun addPregnancy(motherName: String, lastMenstrualPeriod: Long, notes: String? = null) {
        viewModelScope.launch {
            val pregnancy = Pregnancy(
                motherName = motherName,
                lastMenstrualPeriod = lastMenstrualPeriod,
                expectedDueDate = Pregnancy.calculateDueDate(lastMenstrualPeriod),
                notes = notes
            )
            val pregnancyId = repository.insertPregnancy(pregnancy)
            // Select the newly added pregnancy
            repository.getPregnancyById(pregnancyId)?.let { selectPregnancy(it) }
        }
    }
    
    fun updatePregnancy(pregnancy: Pregnancy) {
        viewModelScope.launch {
            repository.updatePregnancy(pregnancy)
        }
    }
    
    fun markPregnancyComplete(pregnancyId: Long) {
        viewModelScope.launch {
            repository.markPregnancyComplete(pregnancyId)
            if (_selectedPregnancy.value?.id == pregnancyId) {
                _selectedPregnancy.value = null
            }
        }
    }
    
    fun deletePregnancy(pregnancy: Pregnancy) {
        viewModelScope.launch {
            repository.deletePregnancy(pregnancy)
            if (_selectedPregnancy.value?.id == pregnancy.id) {
                _selectedPregnancy.value = null
            }
        }
    }
    
    fun addPrenatalVisit(
        pregnancyId: Long,
        visitDate: Long,
        weekOfPregnancy: Int,
        weightKg: Float? = null,
        bloodPressure: String? = null,
        fetalHeartRate: Int? = null,
        notes: String? = null,
        nextVisitDate: Long? = null
    ) {
        viewModelScope.launch {
            val visit = PrenatalVisit(
                pregnancyId = pregnancyId,
                visitDate = visitDate,
                weekOfPregnancy = weekOfPregnancy,
                weightKg = weightKg,
                bloodPressure = bloodPressure,
                fetalHeartRate = fetalHeartRate,
                notes = notes,
                nextVisitDate = nextVisitDate
            )
            repository.insertVisit(visit)
        }
    }
    
    fun deleteVisit(visit: PrenatalVisit) {
        viewModelScope.launch {
            repository.deleteVisit(visit)
        }
    }
    
    fun getCurrentWeek(pregnancy: Pregnancy): Int {
        return Pregnancy.calculateCurrentWeek(pregnancy.lastMenstrualPeriod)
    }
    
    fun getDaysRemaining(pregnancy: Pregnancy): Int {
        return Pregnancy.calculateDaysRemaining(pregnancy.expectedDueDate)
    }
    
    fun getTrimester(weekOfPregnancy: Int): Int {
        return when {
            weekOfPregnancy <= 13 -> 1
            weekOfPregnancy <= 26 -> 2
            else -> 3
        }
    }

    /** WHO-recommended antenatal visit weeks (8 contacts). Returns next recommended week after current, or null if past all. */
    private val recommendedVisitWeeks = listOf(8, 12, 20, 26, 30, 34, 36, 38)

    fun getRecommendedNextVisitWeek(currentWeek: Int): Int? =
        recommendedVisitWeeks.firstOrNull { it > currentWeek }

    // Danger signs that require immediate medical attention
    data class DangerSign(
        val name: String,
        val nameFr: String,
        val description: String,
        val descriptionFr: String,
        val isUrgent: Boolean = true
    )
    
    fun getDangerSigns(): List<DangerSign> = listOf(
        DangerSign(
            "Vaginal Bleeding",
            "Saignement vaginal",
            "Any bleeding during pregnancy needs immediate evaluation",
            "Tout saignement pendant la grossesse nécessite une évaluation immédiate",
            true
        ),
        DangerSign(
            "Severe Headache",
            "Maux de tête sévères",
            "Persistent headache that doesn't improve with rest or paracetamol",
            "Maux de tête persistants qui ne s'améliorent pas avec le repos ou le paracétamol",
            true
        ),
        DangerSign(
            "Blurred Vision",
            "Vision floue",
            "Seeing spots, flashing lights, or blurry vision",
            "Voir des taches, des lumières clignotantes ou une vision floue",
            true
        ),
        DangerSign(
            "Severe Abdominal Pain",
            "Douleurs abdominales sévères",
            "Continuous or severe pain in the abdomen",
            "Douleur continue ou sévère dans l'abdomen",
            true
        ),
        DangerSign(
            "High Fever",
            "Forte fièvre",
            "Temperature above 38°C (100.4°F)",
            "Température supérieure à 38°C",
            true
        ),
        DangerSign(
            "Difficulty Breathing",
            "Difficulté à respirer",
            "Shortness of breath or rapid breathing",
            "Essoufflement ou respiration rapide",
            true
        ),
        DangerSign(
            "Swelling of Face/Hands",
            "Gonflement du visage/mains",
            "Sudden swelling of face, hands, or severe swelling of feet",
            "Gonflement soudain du visage, des mains ou gonflement sévère des pieds",
            true
        ),
        DangerSign(
            "Reduced Fetal Movement",
            "Diminution des mouvements fœtaux",
            "Baby moving less than usual (after 28 weeks)",
            "Bébé bougeant moins que d'habitude (après 28 semaines)",
            true
        ),
        DangerSign(
            "Water Breaking",
            "Rupture de la poche des eaux",
            "Leaking fluid from vagina before 37 weeks",
            "Écoulement de liquide du vagin avant 37 semaines",
            true
        ),
        DangerSign(
            "Convulsions/Fits",
            "Convulsions",
            "Any seizure or loss of consciousness",
            "Toute crise ou perte de conscience",
            true
        )
    )
    
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PregnancyViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PregnancyViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
