package com.example.helloapp.repository

import com.example.helloapp.data.Pregnancy
import com.example.helloapp.data.PregnancyDao
import com.example.helloapp.data.PrenatalVisit
import kotlinx.coroutines.flow.Flow

class PregnancyRepository(private val pregnancyDao: PregnancyDao) {
    
    val activePregnancies: Flow<List<Pregnancy>> = pregnancyDao.getActivePregnancies()
    val allPregnancies: Flow<List<Pregnancy>> = pregnancyDao.getAllPregnancies()
    
    fun getVisitsForPregnancy(pregnancyId: Long): Flow<List<PrenatalVisit>> =
        pregnancyDao.getVisitsForPregnancy(pregnancyId)
    
    fun getVisitsForPregnancyAsc(pregnancyId: Long): Flow<List<PrenatalVisit>> =
        pregnancyDao.getVisitsForPregnancyAsc(pregnancyId)
    
    suspend fun getPregnancyById(pregnancyId: Long): Pregnancy? =
        pregnancyDao.getPregnancyById(pregnancyId)
    
    suspend fun getLatestVisit(pregnancyId: Long): PrenatalVisit? =
        pregnancyDao.getLatestVisit(pregnancyId)
    
    suspend fun insertPregnancy(pregnancy: Pregnancy): Long =
        pregnancyDao.insertPregnancy(pregnancy)
    
    suspend fun updatePregnancy(pregnancy: Pregnancy) =
        pregnancyDao.updatePregnancy(pregnancy)
    
    suspend fun deletePregnancy(pregnancy: Pregnancy) =
        pregnancyDao.deletePregnancy(pregnancy)
    
    suspend fun markPregnancyComplete(pregnancyId: Long) =
        pregnancyDao.markPregnancyComplete(pregnancyId)
    
    suspend fun insertVisit(visit: PrenatalVisit): Long =
        pregnancyDao.insertVisit(visit)
    
    suspend fun updateVisit(visit: PrenatalVisit) =
        pregnancyDao.updateVisit(visit)
    
    suspend fun deleteVisit(visit: PrenatalVisit) =
        pregnancyDao.deleteVisit(visit)
    
    suspend fun getVisitCount(pregnancyId: Long): Int =
        pregnancyDao.getVisitCount(pregnancyId)
}
