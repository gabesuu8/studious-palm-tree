package com.example.helloapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.helloapp.data.AppDatabase
import com.example.helloapp.data.Child
import com.example.helloapp.data.GrowthRecord
import com.example.helloapp.repository.GrowthRepository
import com.example.helloapp.repository.VaccinationRepository
import com.example.helloapp.util.WHOGrowthStandards
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GrowthViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = GrowthRepository(database.growthDao())
    private val vaccinationRepository = VaccinationRepository(database.vaccinationDao())
    
    val allChildren: StateFlow<List<Child>> = repository.allChildren
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    
    private val _selectedChild = MutableStateFlow<Child?>(null)
    val selectedChild: StateFlow<Child?> = _selectedChild
    
    val growthRecords: StateFlow<List<GrowthRecord>> = _selectedChild.flatMapLatest { child ->
        if (child != null) {
            repository.getGrowthRecordsForChild(child.id)
        } else {
            flowOf(emptyList())
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    
    fun selectChild(child: Child?) {
        _selectedChild.value = child
    }
    
    fun addChild(name: String, dateOfBirth: Long, gender: String) {
        viewModelScope.launch {
            val child = Child(
                name = name,
                dateOfBirth = dateOfBirth,
                gender = gender
            )
            val childId = repository.insertChild(child)
            // Create vaccination schedule for this child (same as Growth tracker children)
            vaccinationRepository.copyTemplatesToChild(childId)
            // Select the newly added child
            repository.getChildById(childId)?.let { selectChild(it) }
        }
    }
    
    fun updateChild(child: Child) {
        viewModelScope.launch {
            repository.updateChild(child)
        }
    }
    
    fun deleteChild(child: Child) {
        viewModelScope.launch {
            vaccinationRepository.deleteAllForChild(child.id)
            repository.deleteChild(child)
            if (_selectedChild.value?.id == child.id) {
                _selectedChild.value = null
            }
        }
    }
    
    fun addGrowthRecord(
        childId: Long,
        date: Long,
        weightKg: Float,
        heightCm: Float,
        headCircumferenceCm: Float? = null,
        notes: String? = null
    ) {
        viewModelScope.launch {
            val record = GrowthRecord(
                childId = childId,
                date = date,
                weightKg = weightKg,
                heightCm = heightCm,
                headCircumferenceCm = headCircumferenceCm,
                notes = notes
            )
            repository.insertGrowthRecord(record)
        }
    }
    
    fun deleteGrowthRecord(record: GrowthRecord) {
        viewModelScope.launch {
            repository.deleteGrowthRecord(record)
        }
    }
    
    fun assessGrowth(
        record: GrowthRecord,
        child: Child
    ): WHOGrowthStandards.GrowthAssessment {
        return WHOGrowthStandards.assessGrowth(
            weightKg = record.weightKg,
            heightCm = record.heightCm,
            birthDate = child.dateOfBirth,
            measurementDate = record.date,
            isMale = child.gender == "male"
        )
    }
    
    fun getChildAgeMonths(child: Child, measurementDate: Long = System.currentTimeMillis()): Int {
        return WHOGrowthStandards.calculateAgeMonths(child.dateOfBirth, measurementDate)
    }
    
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GrowthViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return GrowthViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
