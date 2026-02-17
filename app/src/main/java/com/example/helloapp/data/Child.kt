package com.example.helloapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "children")
data class Child(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val dateOfBirth: Long, // Timestamp
    val gender: String, // "male" or "female"
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable
