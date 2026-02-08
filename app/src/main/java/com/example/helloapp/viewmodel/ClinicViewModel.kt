package com.example.helloapp.viewmodel

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.helloapp.data.AppDatabase
import com.example.helloapp.data.Clinic
import com.example.helloapp.repository.ClinicRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ClinicViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: ClinicRepository
    
    /** Default center: B.P. 1413, Lomé, Togo (city center) */
    private val defaultLomeLocation: Location = Location("default").apply {
        latitude = DEFAULT_LOME_LAT
        longitude = DEFAULT_LOME_LON
    }
    
    private val _userLocation = MutableStateFlow<Location?>(null)
    val userLocation: StateFlow<Location?> = _userLocation
    
    private val _selectedCountry = MutableStateFlow<String?>(null)
    val selectedCountry: StateFlow<String?> = _selectedCountry
    
    private val _filterEmergencyOnly = MutableStateFlow(false)
    val filterEmergencyOnly: StateFlow<Boolean> = _filterEmergencyOnly
    
    val allClinics: Flow<List<Clinic>>
    val allCountries: Flow<List<String>>
    
    init {
        val database = AppDatabase.getDatabase(application)
        repository = ClinicRepository(database.clinicDao())
        allClinics = repository.allClinics
        allCountries = repository.getAllCountries()
        
        // Initialize sample clinics if database is empty
        viewModelScope.launch {
            if (repository.getClinicCount() == 0) {
                initializeSampleClinics()
            }
        }
    }
    
    // Filtered clinics based on country and emergency filter
    // When user location is unknown, use Lomé (B.P. 1413) as reference for distance sorting
    val filteredClinics: Flow<List<ClinicWithDistance>> = combine(
        allClinics,
        _userLocation,
        _selectedCountry,
        _filterEmergencyOnly
    ) { clinics, location, country, emergencyOnly ->
        var filtered = clinics
        
        // Filter by country
        if (country != null) {
            filtered = filtered.filter { it.country == country }
        }
        
        // Filter emergency only
        if (emergencyOnly) {
            filtered = filtered.filter { it.isEmergency }
        }
        
        // Use user location or default to Lomé center (B.P. 1413 area) for distance
        val referenceLocation = location ?: defaultLomeLocation
        
        // Calculate distances and sort (nearest first)
        filtered.map { clinic ->
            val distance = calculateDistance(
                referenceLocation.latitude, referenceLocation.longitude,
                clinic.latitude, clinic.longitude
            )
            ClinicWithDistance(clinic, distance)
        }.sortedBy { it.distanceKm }
    }
    
    companion object {
        /** Lomé, Togo - B.P. 1413 area (city center) */
        const val DEFAULT_LOME_LAT = 6.1256
        const val DEFAULT_LOME_LON = 1.2254
    }
    
    fun setUserLocation(location: Location?) {
        _userLocation.value = location
    }
    
    fun setSelectedCountry(country: String?) {
        _selectedCountry.value = country
    }
    
    fun setEmergencyFilter(emergencyOnly: Boolean) {
        _filterEmergencyOnly.value = emergencyOnly
    }
    
    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val results = FloatArray(1)
        Location.distanceBetween(lat1, lon1, lat2, lon2, results)
        return results[0] / 1000.0 // Convert to kilometers
    }
    
    private suspend fun initializeSampleClinics() {
        val clinics = listOf(
            // Togo - Lomé
            Clinic(
                name = "CHU Sylvanus Olympio",
                address = "Boulevard du 13 Janvier",
                city = "Lomé",
                country = "Togo",
                latitude = 6.1319,
                longitude = 1.2228,
                phone = "+228 22 21 25 01",
                services = "Emergency, Surgery, Pediatrics, Maternity, Internal Medicine",
                openingHours = "24/7",
                isHospital = true,
                isEmergency = true
            ),
            Clinic(
                name = "Clinique Biasa",
                address = "Rue de la Gare, Tokoin",
                city = "Lomé",
                country = "Togo",
                latitude = 6.1380,
                longitude = 1.2150,
                phone = "+228 22 21 35 42",
                services = "General Medicine, Pediatrics, Laboratory",
                openingHours = "Mon-Sat: 7:00-19:00",
                isHospital = false,
                isEmergency = false
            ),
            Clinic(
                name = "Polyclinique Saint Joseph",
                address = "Avenue de la Libération",
                city = "Lomé",
                country = "Togo",
                latitude = 6.1256,
                longitude = 1.2310,
                phone = "+228 22 21 66 89",
                services = "Maternity, Pediatrics, Vaccination, Family Planning",
                openingHours = "Mon-Fri: 8:00-17:00, Sat: 8:00-12:00",
                isHospital = false,
                isEmergency = false
            ),
            Clinic(
                name = "Centre de Santé d'Adidogomé",
                address = "Adidogomé",
                city = "Lomé",
                country = "Togo",
                latitude = 6.1489,
                longitude = 1.1821,
                phone = "+228 22 50 12 34",
                services = "Primary Care, Vaccination, Prenatal Care",
                openingHours = "Mon-Fri: 7:30-15:30",
                isHospital = false,
                isEmergency = false
            ),
            
            // Togo - Kara
            Clinic(
                name = "CHR Kara",
                address = "Centre Hospitalier Régional",
                city = "Kara",
                country = "Togo",
                latitude = 9.5511,
                longitude = 1.1861,
                phone = "+228 26 60 01 23",
                services = "Emergency, Surgery, Maternity, Pediatrics",
                openingHours = "24/7",
                isHospital = true,
                isEmergency = true
            ),
            
            // Uganda - Kampala
            Clinic(
                name = "Mulago National Referral Hospital",
                address = "Upper Mulago Hill Road",
                city = "Kampala",
                country = "Uganda",
                latitude = 0.3420,
                longitude = 32.5760,
                phone = "+256 414 554 001",
                services = "Emergency, Surgery, Pediatrics, Maternity, Oncology, Cardiology",
                openingHours = "24/7",
                isHospital = true,
                isEmergency = true
            ),
            Clinic(
                name = "Nsambya Hospital",
                address = "Nsambya Hill",
                city = "Kampala",
                country = "Uganda",
                latitude = 0.2989,
                longitude = 32.5900,
                phone = "+256 414 267 011",
                services = "Emergency, Maternity, Surgery, Pediatrics",
                openingHours = "24/7",
                isHospital = true,
                isEmergency = true
            ),
            Clinic(
                name = "Mengo Hospital",
                address = "Albert Cook Road, Mengo",
                city = "Kampala",
                country = "Uganda",
                latitude = 0.3050,
                longitude = 32.5530,
                phone = "+256 414 270 222",
                services = "General Medicine, Maternity, Pediatrics, Eye Care",
                openingHours = "Mon-Fri: 8:00-17:00, Emergency 24/7",
                isHospital = true,
                isEmergency = true
            ),
            Clinic(
                name = "Kisugu Health Centre III",
                address = "Kisugu, Makindye",
                city = "Kampala",
                country = "Uganda",
                latitude = 0.2920,
                longitude = 32.6010,
                phone = "+256 414 123 456",
                services = "Primary Care, Vaccination, Prenatal Care, HIV Testing",
                openingHours = "Mon-Fri: 8:00-17:00",
                isHospital = false,
                isEmergency = false
            ),
            
            // Uganda - Jinja
            Clinic(
                name = "Jinja Regional Referral Hospital",
                address = "Hospital Road",
                city = "Jinja",
                country = "Uganda",
                latitude = 0.4244,
                longitude = 33.2041,
                phone = "+256 434 120 108",
                services = "Emergency, Surgery, Maternity, Pediatrics",
                openingHours = "24/7",
                isHospital = true,
                isEmergency = true
            ),
            
            // Uganda - Gulu
            Clinic(
                name = "Gulu Regional Referral Hospital",
                address = "Churchill Road",
                city = "Gulu",
                country = "Uganda",
                latitude = 2.7747,
                longitude = 32.2990,
                phone = "+256 471 432 098",
                services = "Emergency, Surgery, Maternity, Pediatrics, Mental Health",
                openingHours = "24/7",
                isHospital = true,
                isEmergency = true
            )
        )
        
        repository.insertAll(clinics)
    }
    
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ClinicViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ClinicViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

data class ClinicWithDistance(
    val clinic: Clinic,
    val distanceKm: Double?
) {
    fun getFormattedDistance(): String {
        return when {
            distanceKm == null -> ""
            distanceKm < 1 -> "${(distanceKm * 1000).toInt()} m"
            else -> String.format("%.1f km", distanceKm)
        }
    }
}
