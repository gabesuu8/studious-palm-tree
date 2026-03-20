package com.example.helloapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VaccinationDao {
    /** All vaccinations for a specific child (from Growth tracker). */
    @Query("SELECT * FROM vaccinations WHERE childId = :childId ORDER BY id ASC")
    fun getVaccinationsForChild(childId: Long): Flow<List<Vaccination>>
    
    /** Template schedule (childId = 0) - used to create records for new children. */
    @Query("SELECT * FROM vaccinations WHERE childId = 0 ORDER BY id ASC")
    fun getTemplateVaccinations(): Flow<List<Vaccination>>
    
    @Query("SELECT * FROM vaccinations WHERE childId = 0 ORDER BY id ASC")
    suspend fun getTemplateVaccinationsOnce(): List<Vaccination>
    
    @Query("SELECT COUNT(*) FROM vaccinations WHERE childId = 0")
    suspend fun getTemplateCount(): Int
    
    @Query("SELECT COUNT(*) FROM vaccinations WHERE childId = :childId")
    suspend fun getVaccinationCountForChild(childId: Long): Int
    
    @Query("SELECT * FROM vaccinations WHERE childId = :childId AND completedDoses >= totalDoses")
    fun getCompletedVaccinationsForChild(childId: Long): Flow<List<Vaccination>>
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVaccination(vaccination: Vaccination)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vaccinations: List<Vaccination>)
    
    @Update
    suspend fun updateVaccination(vaccination: Vaccination)
    
    @Delete
    suspend fun deleteVaccination(vaccination: Vaccination)
    
    @Query("DELETE FROM vaccinations WHERE childId = :childId")
    suspend fun deleteAllForChild(childId: Long)
    
    @Query("DELETE FROM vaccinations WHERE childId = 0")
    suspend fun deleteTemplates()
    
    @Query("DELETE FROM vaccinations")
    suspend fun deleteAll()

    @Query("DELETE FROM vaccinations WHERE id NOT IN (SELECT MIN(id) FROM vaccinations GROUP BY name, childId)")
    suspend fun deleteDuplicates()
}
