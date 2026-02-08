package com.example.helloapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clinics")
data class Clinic(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val address: String,
    val city: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val phone: String? = null,
    val services: String? = null, // Comma-separated list of services
    val openingHours: String? = null,
    val isHospital: Boolean = false,
    val isEmergency: Boolean = false
)
