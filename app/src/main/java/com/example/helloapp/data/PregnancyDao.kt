package com.example.helloapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PregnancyDao {
    
    // Pregnancy operations
    @Query("SELECT * FROM pregnancies WHERE isActive = 1 ORDER BY expectedDueDate ASC")
    fun getActivePregnancies(): Flow<List<Pregnancy>>
    
    @Query("SELECT * FROM pregnancies ORDER BY createdAt DESC")
    fun getAllPregnancies(): Flow<List<Pregnancy>>
    
    @Query("SELECT * FROM pregnancies WHERE id = :pregnancyId")
    suspend fun getPregnancyById(pregnancyId: Long): Pregnancy?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPregnancy(pregnancy: Pregnancy): Long
    
    @Update
    suspend fun updatePregnancy(pregnancy: Pregnancy)
    
    @Delete
    suspend fun deletePregnancy(pregnancy: Pregnancy)
    
    @Query("UPDATE pregnancies SET isActive = 0 WHERE id = :pregnancyId")
    suspend fun markPregnancyComplete(pregnancyId: Long)
    
    @Query("DELETE FROM pregnancies")
    suspend fun deleteAllPregnancies()
    
    // Prenatal visit operations
    @Query("SELECT * FROM prenatal_visits WHERE pregnancyId = :pregnancyId ORDER BY visitDate DESC")
    fun getVisitsForPregnancy(pregnancyId: Long): Flow<List<PrenatalVisit>>
    
    @Query("SELECT * FROM prenatal_visits WHERE pregnancyId = :pregnancyId ORDER BY visitDate ASC")
    fun getVisitsForPregnancyAsc(pregnancyId: Long): Flow<List<PrenatalVisit>>
    
    @Query("SELECT * FROM prenatal_visits WHERE id = :visitId")
    suspend fun getVisitById(visitId: Long): PrenatalVisit?
    
    @Query("SELECT * FROM prenatal_visits WHERE pregnancyId = :pregnancyId ORDER BY visitDate DESC LIMIT 1")
    suspend fun getLatestVisit(pregnancyId: Long): PrenatalVisit?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVisit(visit: PrenatalVisit): Long
    
    @Update
    suspend fun updateVisit(visit: PrenatalVisit)
    
    @Delete
    suspend fun deleteVisit(visit: PrenatalVisit)
    
    @Query("SELECT COUNT(*) FROM prenatal_visits WHERE pregnancyId = :pregnancyId")
    suspend fun getVisitCount(pregnancyId: Long): Int
}
