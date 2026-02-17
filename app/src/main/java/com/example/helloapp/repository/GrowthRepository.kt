package com.example.helloapp.repository

import com.example.helloapp.data.Child
import com.example.helloapp.data.GrowthDao
import com.example.helloapp.data.GrowthRecord
import kotlinx.coroutines.flow.Flow

class GrowthRepository(private val growthDao: GrowthDao) {
    
    val allChildren: Flow<List<Child>> = growthDao.getAllChildren()
    
    fun getGrowthRecordsForChild(childId: Long): Flow<List<GrowthRecord>> =
        growthDao.getGrowthRecordsForChild(childId)
    
    fun getGrowthRecordsForChildAsc(childId: Long): Flow<List<GrowthRecord>> =
        growthDao.getGrowthRecordsForChildAsc(childId)
    
    suspend fun getChildById(childId: Long): Child? = growthDao.getChildById(childId)
    
    suspend fun getLatestGrowthRecord(childId: Long): GrowthRecord? =
        growthDao.getLatestGrowthRecord(childId)
    
    suspend fun insertChild(child: Child): Long = growthDao.insertChild(child)
    
    suspend fun updateChild(child: Child) = growthDao.updateChild(child)
    
    suspend fun deleteChild(child: Child) = growthDao.deleteChild(child)
    
    suspend fun insertGrowthRecord(record: GrowthRecord): Long = 
        growthDao.insertGrowthRecord(record)
    
    suspend fun updateGrowthRecord(record: GrowthRecord) = 
        growthDao.updateGrowthRecord(record)
    
    suspend fun deleteGrowthRecord(record: GrowthRecord) = 
        growthDao.deleteGrowthRecord(record)
    
    suspend fun getGrowthRecordCount(childId: Long): Int = 
        growthDao.getGrowthRecordCount(childId)
}
