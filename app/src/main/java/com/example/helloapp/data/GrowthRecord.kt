package com.example.helloapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "growth_records",
    foreignKeys = [
        ForeignKey(
            entity = Child::class,
            parentColumns = ["id"],
            childColumns = ["childId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("childId")]
)
data class GrowthRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val childId: Long,
    val date: Long, // Measurement date timestamp
    val weightKg: Float, // Weight in kilograms
    val heightCm: Float, // Height in centimeters
    val headCircumferenceCm: Float? = null, // Optional head circumference
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable
