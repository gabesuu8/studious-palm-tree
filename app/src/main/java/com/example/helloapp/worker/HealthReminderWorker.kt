package com.example.helloapp.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.helloapp.data.AppDatabase
import com.example.helloapp.util.NotificationHelper
import kotlinx.coroutines.flow.first

class HealthReminderWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val db = AppDatabase.getDatabase(context)
        val now = System.currentTimeMillis()

        // --- Vaccination reminders ---
        val vaccinationDao = db.vaccinationDao()
        val children = db.growthDao().getAllChildren().first()
        for (child in children) {
            // Vaccines are only copied to a child when the Vaccination UI is opened for them.
            // The worker must do the same check so it works even if the user never visited that tab.
            if (vaccinationDao.getVaccinationCountForChild(child.id) == 0) {
                val templates = vaccinationDao.getTemplateVaccinationsOnce()
                if (templates.isNotEmpty()) {
                    vaccinationDao.insertAll(templates.map { t ->
                        t.copy(id = 0, childId = child.id, isCompleted = false, dateCompleted = null)
                    })
                }
            }

            val pending = vaccinationDao.getVaccinationsForChild(child.id).first()
                .filter { !it.isCompleted }

            if (pending.isEmpty()) continue

            // Collect all vaccines that are overdue or due within 7 days, sorted by urgency
            val dueVaccines = pending
                .map { v ->
                    val days = parseRecommendedAgeToDays(v.recommendedAge)
                    val dueMs = child.dateOfBirth + days * 24 * 60 * 60 * 1000L
                    val daysUntilDue = (dueMs - now) / (24 * 60 * 60 * 1000L)
                    Pair(v.name, daysUntilDue)
                }
                .filter { (_, daysUntilDue) -> daysUntilDue <= 7 }
                .sortedBy { (_, daysUntilDue) -> daysUntilDue }

            if (dueVaccines.isNotEmpty()) {
                NotificationHelper.postVaccinationNotification(
                    context,
                    childId = child.id,
                    childName = child.name,
                    vaccines = dueVaccines
                )
            }
        }

        // --- Prenatal visit reminders ---
        val pregnancies = db.pregnancyDao().getActivePregnancies().first()
        val twoDaysMs = 2 * 24 * 60 * 60 * 1000L
        for (pregnancy in pregnancies) {
            val latestVisit = db.pregnancyDao().getLatestVisit(pregnancy.id) ?: continue
            val nextVisitDate = latestVisit.nextVisitDate ?: continue

            val daysUntilVisit = (nextVisitDate - now) / (24 * 60 * 60 * 1000L)
            // Notify if visit is today or within 2 days
            if (daysUntilVisit in 0..2) {
                NotificationHelper.postPrenatalNotification(
                    context,
                    pregnancyId = pregnancy.id,
                    motherName = pregnancy.motherName,
                    daysUntilVisit = daysUntilVisit
                )
            }
        }

        return Result.success()
    }

    /** Parse recommendedAge string to days from birth. Mirrors VaccinationViewModel logic. */
    private fun parseRecommendedAgeToDays(recommendedAge: String): Long {
        val s = recommendedAge.lowercase()
        when {
            s.contains("birth") || s.contains("naissance") -> return 0
            s.contains("week") || s.contains("semaine") -> {
                val num = Regex("(\\d+)").find(s)?.groupValues?.get(1)?.toLongOrNull() ?: 0
                return num * 7
            }
            s.contains("month") || s.contains("mois") -> {
                val num = Regex("(\\d+)").find(s)?.groupValues?.get(1)?.toLongOrNull() ?: 0
                return num * 30
            }
            s.contains("year") || s.contains("an") -> {
                val num = Regex("(\\d+)").find(s)?.groupValues?.get(1)?.toLongOrNull() ?: 2
                return num * 365
            }
        }
        return 0
    }

    companion object {
        const val WORK_NAME = "health_reminder"
    }
}
