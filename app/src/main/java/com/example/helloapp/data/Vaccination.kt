package com.example.helloapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaccinations")
data class Vaccination(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val recommendedAge: String,
    val category: String, // e.g., "Birth", "6 Weeks", "9 Months", etc.
    val isCompleted: Boolean = false,
    val dateCompleted: Long? = null, // Timestamp when marked complete
    /** 0 = template (schedule definition); >0 = Child.id from Growth tracker */
    val childId: Long = 0
)
