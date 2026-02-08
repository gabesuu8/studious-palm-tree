package com.example.helloapp.repository

import com.example.helloapp.data.Clinic
import com.example.helloapp.data.ClinicDao
import kotlinx.coroutines.flow.Flow

class ClinicRepository(private val clinicDao: ClinicDao) {
    
    val allClinics: Flow<List<Clinic>> = clinicDao.getAllClinics()
    
    fun getClinicsByCountry(country: String): Flow<List<Clinic>> = 
        clinicDao.getClinicsByCountry(country)
    
    fun getClinicsByCity(city: String): Flow<List<Clinic>> = 
        clinicDao.getClinicsByCity(city)
    
    fun getEmergencyClinics(): Flow<List<Clinic>> = 
        clinicDao.getEmergencyClinics()
    
    fun getHospitals(): Flow<List<Clinic>> = 
        clinicDao.getHospitals()
    
    fun getAllCountries(): Flow<List<String>> = 
        clinicDao.getAllCountries()
    
    fun getCitiesByCountry(country: String): Flow<List<String>> = 
        clinicDao.getCitiesByCountry(country)
    
    suspend fun insert(clinic: Clinic): Long = 
        clinicDao.insert(clinic)
    
    suspend fun insertAll(clinics: List<Clinic>) = 
        clinicDao.insertAll(clinics)
    
    suspend fun update(clinic: Clinic) = 
        clinicDao.update(clinic)
    
    suspend fun delete(clinic: Clinic) = 
        clinicDao.delete(clinic)
    
    suspend fun deleteAll() = 
        clinicDao.deleteAll()
    
    suspend fun getClinicCount(): Int = 
        clinicDao.getClinicCount()
}
