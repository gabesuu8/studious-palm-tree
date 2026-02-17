package com.example.helloapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "pregnancies")
data class Pregnancy(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val motherName: String,
    val lastMenstrualPeriod: Long, // LMP date timestamp
    val expectedDueDate: Long, // Calculated from LMP (LMP + 280 days)
    val notes: String? = null,
    val isActive: Boolean = true, // false after delivery
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable {
    
    companion object {
        const val PREGNANCY_DURATION_DAYS = 280 // 40 weeks
        
        fun calculateDueDate(lmpDate: Long): Long {
            return lmpDate + (PREGNANCY_DURATION_DAYS * 24 * 60 * 60 * 1000L)
        }
        
        fun calculateCurrentWeek(lmpDate: Long): Int {
            val daysSinceLmp = (System.currentTimeMillis() - lmpDate) / (24 * 60 * 60 * 1000L)
            return (daysSinceLmp / 7).toInt()
        }
        
        fun calculateDaysRemaining(dueDate: Long): Int {
            val remaining = (dueDate - System.currentTimeMillis()) / (24 * 60 * 60 * 1000L)
            return remaining.toInt().coerceAtLeast(0)
        }
    }
}
