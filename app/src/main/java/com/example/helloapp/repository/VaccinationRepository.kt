package com.example.helloapp.repository

import com.example.helloapp.data.Vaccination
import com.example.helloapp.data.VaccinationDao
import kotlinx.coroutines.flow.Flow

class VaccinationRepository(private val vaccinationDao: VaccinationDao) {

    fun getVaccinationsForChild(childId: Long): Flow<List<Vaccination>> =
        vaccinationDao.getVaccinationsForChild(childId)

    fun getTemplateVaccinations(): Flow<List<Vaccination>> =
        vaccinationDao.getTemplateVaccinations()

    suspend fun getTemplateCount(): Int = vaccinationDao.getTemplateCount()

    suspend fun getVaccinationCountForChild(childId: Long): Int =
        vaccinationDao.getVaccinationCountForChild(childId)

    /** Copy template schedule to a new child (call when child is added in Growth tracker). */
    suspend fun copyTemplatesToChild(childId: Long) {
        val templates = vaccinationDao.getTemplateVaccinationsOnce()
        if (templates.isEmpty()) return
        val forChild = templates.map { t ->
            t.copy(id = 0, childId = childId, isCompleted = false, dateCompleted = null)
        }
        vaccinationDao.insertAll(forChild)
    }

    suspend fun insertVaccination(vaccination: Vaccination) =
        vaccinationDao.insertVaccination(vaccination)

    suspend fun insertAll(vaccinations: List<Vaccination>) =
        vaccinationDao.insertAll(vaccinations)

    suspend fun updateVaccination(vaccination: Vaccination) =
        vaccinationDao.updateVaccination(vaccination)

    suspend fun deleteVaccination(vaccination: Vaccination) =
        vaccinationDao.deleteVaccination(vaccination)

    suspend fun deleteAllForChild(childId: Long) =
        vaccinationDao.deleteAllForChild(childId)

    suspend fun deleteTemplates() = vaccinationDao.deleteTemplates()

    suspend fun deleteAll() = vaccinationDao.deleteAll()
}
