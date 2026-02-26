package com.example.helloapp.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.helloapp.MainActivity
import com.example.helloapp.R

object NotificationHelper {

    private const val CHANNEL_VACCINATION = "vaccination_reminders"
    private const val CHANNEL_PRENATAL = "prenatal_reminders"

    fun createChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_VACCINATION,
                    "Vaccination Reminders",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply { description = "Reminders for upcoming and overdue vaccinations" }
            )
            nm.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_PRENATAL,
                    "Prenatal Visit Reminders",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply { description = "Reminders for scheduled prenatal visits" }
            )
        }
    }

    /**
     * [vaccines] is a list of (vaccineName, daysUntilDue) pairs, sorted by urgency.
     * Negative daysUntilDue = overdue.
     */
    fun postVaccinationNotification(
        context: Context,
        childId: Long,
        childName: String,
        vaccines: List<Pair<String, Long>>
    ) {
        if (vaccines.isEmpty()) return

        val title = if (vaccines.size == 1) {
            "Vaccine reminder: $childName"
        } else {
            "${vaccines.size} vaccines due: $childName"
        }

        val lines = vaccines.joinToString("\n") { (name, days) ->
            when {
                days < 0 -> "• $name — overdue by ${-days} day(s)"
                days == 0L -> "• $name — due today"
                else -> "• $name — due in $days day(s)"
            }
        }
        val summary = vaccines.first().let { (name, days) ->
            when {
                days < 0 -> "$name overdue by ${-days}d"
                days == 0L -> "$name due today"
                else -> "$name due in ${days}d"
            }
        } + if (vaccines.size > 1) " (+${vaccines.size - 1} more)" else ""

        val notification = NotificationCompat.Builder(context, CHANNEL_VACCINATION)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(summary)
            .setStyle(NotificationCompat.BigTextStyle().bigText(lines))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(mainActivityIntent(context))
            .build()

        NotificationManagerCompat.from(context)
            .notify(CHANNEL_VACCINATION.hashCode() + childId.toInt(), notification)
    }

    fun postPrenatalNotification(
        context: Context,
        pregnancyId: Long,
        motherName: String,
        daysUntilVisit: Long
    ) {
        val text = when {
            daysUntilVisit == 0L -> "Your prenatal visit for $motherName is today"
            else -> "Prenatal visit for $motherName is in $daysUntilVisit day(s)"
        }
        val notification = NotificationCompat.Builder(context, CHANNEL_PRENATAL)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Prenatal visit reminder")
            .setContentText(text)
            .setStyle(NotificationCompat.BigTextStyle().bigText(text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(mainActivityIntent(context))
            .build()

        NotificationManagerCompat.from(context)
            .notify(CHANNEL_PRENATAL.hashCode() + pregnancyId.toInt(), notification)
    }

    private fun mainActivityIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}
