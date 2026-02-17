package com.example.helloapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GrowthDao {
    
    // Child operations
    @Query("SELECT * FROM children ORDER BY name ASC")
    fun getAllChildren(): Flow<List<Child>>
    
    @Query("SELECT * FROM children WHERE id = :childId")
    suspend fun getChildById(childId: Long): Child?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChild(child: Child): Long
    
    @Update
    suspend fun updateChild(child: Child)
    
    @Delete
    suspend fun deleteChild(child: Child)
    
    @Query("DELETE FROM children")
    suspend fun deleteAllChildren()
    
    // Growth record operations
    @Query("SELECT * FROM growth_records WHERE childId = :childId ORDER BY date DESC")
    fun getGrowthRecordsForChild(childId: Long): Flow<List<GrowthRecord>>
    
    @Query("SELECT * FROM growth_records WHERE childId = :childId ORDER BY date ASC")
    fun getGrowthRecordsForChildAsc(childId: Long): Flow<List<GrowthRecord>>
    
    @Query("SELECT * FROM growth_records WHERE id = :recordId")
    suspend fun getGrowthRecordById(recordId: Long): GrowthRecord?
    
    @Query("SELECT * FROM growth_records WHERE childId = :childId ORDER BY date DESC LIMIT 1")
    suspend fun getLatestGrowthRecord(childId: Long): GrowthRecord?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrowthRecord(record: GrowthRecord): Long
    
    @Update
    suspend fun updateGrowthRecord(record: GrowthRecord)
    
    @Delete
    suspend fun deleteGrowthRecord(record: GrowthRecord)
    
    @Query("DELETE FROM growth_records WHERE childId = :childId")
    suspend fun deleteAllGrowthRecordsForChild(childId: Long)
    
    @Query("SELECT COUNT(*) FROM growth_records WHERE childId = :childId")
    suspend fun getGrowthRecordCount(childId: Long): Int
}
