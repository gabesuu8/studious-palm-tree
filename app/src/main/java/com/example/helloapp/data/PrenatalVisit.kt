package com.example.helloapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "prenatal_visits",
    foreignKeys = [
        ForeignKey(
            entity = Pregnancy::class,
            parentColumns = ["id"],
            childColumns = ["pregnancyId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("pregnancyId")]
)
data class PrenatalVisit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val pregnancyId: Long,
    val visitDate: Long,
    val weekOfPregnancy: Int,
    val weightKg: Float? = null,
    val bloodPressure: String? = null, // e.g., "120/80"
    val fetalHeartRate: Int? = null, // beats per minute
    val notes: String? = null,
    val nextVisitDate: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable
