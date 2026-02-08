package com.example.helloapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ClinicDao {
    
    @Query("SELECT * FROM clinics ORDER BY name ASC")
    fun getAllClinics(): Flow<List<Clinic>>
    
    @Query("SELECT * FROM clinics WHERE country = :country ORDER BY name ASC")
    fun getClinicsByCountry(country: String): Flow<List<Clinic>>
    
    @Query("SELECT * FROM clinics WHERE city = :city ORDER BY name ASC")
    fun getClinicsByCity(city: String): Flow<List<Clinic>>
    
    @Query("SELECT * FROM clinics WHERE isEmergency = 1 ORDER BY name ASC")
    fun getEmergencyClinics(): Flow<List<Clinic>>
    
    @Query("SELECT * FROM clinics WHERE isHospital = 1 ORDER BY name ASC")
    fun getHospitals(): Flow<List<Clinic>>
    
    @Query("SELECT DISTINCT country FROM clinics ORDER BY country ASC")
    fun getAllCountries(): Flow<List<String>>
    
    @Query("SELECT DISTINCT city FROM clinics WHERE country = :country ORDER BY city ASC")
    fun getCitiesByCountry(country: String): Flow<List<String>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clinic: Clinic): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(clinics: List<Clinic>)
    
    @Update
    suspend fun update(clinic: Clinic)
    
    @Delete
    suspend fun delete(clinic: Clinic)
    
    @Query("DELETE FROM clinics")
    suspend fun deleteAll()
    
    @Query("SELECT COUNT(*) FROM clinics")
    suspend fun getClinicCount(): Int
}
