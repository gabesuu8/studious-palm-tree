package com.example.helloapp.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.FavoritesActivity
import com.example.helloapp.R
import com.example.helloapp.RapidTestTimerActivity
import com.example.helloapp.adapter.ClinicAdapter
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.viewmodel.ClinicViewModel
import com.example.helloapp.viewmodel.ClinicWithDistance
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class ClinicsFragment : Fragment(), OnMapReadyCallback {

    private var viewModel: ClinicViewModel? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null
    
    private var recyclerView: RecyclerView? = null
    private var emptyState: View? = null
    private var mapContainer: View? = null
    private var locationStatusBar: View? = null
    private var txtLocationStatus: TextView? = null
    private var btnEnableLocation: Button? = null
    private var chipEmergency: Chip? = null
    private var spinnerCountry: Spinner? = null
    private var fabToggleView: FloatingActionButton? = null
    
    private var adapter: ClinicAdapter? = null
    private var googleMap: GoogleMap? = null
    private var isMapView = false
    private var currentClinics: List<ClinicWithDistance> = emptyList()
    
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true -> {
                getCurrentLocation()
            }
            else -> {
                updateLocationUI(false)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_clinics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Setup toolbar
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_options -> {
                    showOptionsDialog()
                    true
                }
                else -> false
            }
        }
        
        // Initialize views
        recyclerView = view.findViewById(R.id.clinicsRecyclerView)
        emptyState = view.findViewById(R.id.emptyState)
        mapContainer = view.findViewById(R.id.mapContainer)
        locationStatusBar = view.findViewById(R.id.locationStatusBar)
        txtLocationStatus = view.findViewById(R.id.txtLocationStatus)
        btnEnableLocation = view.findViewById(R.id.btnEnableLocation)
        chipEmergency = view.findViewById(R.id.chipEmergency)
        spinnerCountry = view.findViewById(R.id.spinnerCountry)
        fabToggleView = view.findViewById(R.id.fabToggleView)
        
        // Setup RecyclerView
        adapter = ClinicAdapter { clinicWithDistance ->
            showClinicDetails(clinicWithDistance)
        }
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = adapter
        
        // Initialize location client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(
            requireActivity(),
            ClinicViewModel.Factory(requireActivity().application)
        )[ClinicViewModel::class.java]
        
        // Setup listeners
        setupListeners()
        
        // Check location permission
        checkLocationPermission()
        
        // Setup map if available
        setupMap()
        
        // Observe data
        observeData()
    }
    
    private fun setupListeners() {
        btnEnableLocation?.setOnClickListener {
            requestLocationPermission()
        }
        
        chipEmergency?.setOnCheckedChangeListener { _, isChecked ->
            viewModel?.setEmergencyFilter(isChecked)
        }
        
        fabToggleView?.setOnClickListener {
            toggleView()
        }
    }
    
    private fun setupMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }
    
    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        
        // Check if we have location permission
        if (hasLocationPermission()) {
            try {
                map.isMyLocationEnabled = true
            } catch (e: SecurityException) {
                // Permission not granted
            }
        }
        
        // Center map on Lomé, Togo (B.P. 1413 area) by default
        val lomeCenter = LatLng(ClinicViewModel.DEFAULT_LOME_LAT, ClinicViewModel.DEFAULT_LOME_LON)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lomeCenter, 12f))
        
        // Update markers with current clinics
        updateMapMarkers()
    }
    
    private fun observeData() {
        // Observe filtered clinics
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.filteredClinics?.collect { clinics ->
                currentClinics = clinics
                
                if (clinics.isEmpty()) {
                    recyclerView?.visibility = View.GONE
                    emptyState?.visibility = View.VISIBLE
                } else {
                    recyclerView?.visibility = View.VISIBLE
                    emptyState?.visibility = View.GONE
                    adapter?.submitList(clinics)
                }
                
                // Update map markers
                updateMapMarkers()
            }
        }
        
        // Observe countries for spinner
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.allCountries?.collect { countries ->
                setupCountrySpinner(countries)
            }
        }
    }
    
    private fun setupCountrySpinner(countries: List<String>) {
        val ctx = context ?: return
        val allOption = getString(R.string.all_countries)
        val items = listOf(allOption) + countries
        
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, items)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCountry?.adapter = spinnerAdapter
        
        spinnerCountry?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selected = if (position == 0) null else countries[position - 1]
                viewModel?.setSelectedCountry(selected)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    
    private fun toggleView() {
        isMapView = !isMapView
        
        if (isMapView && isNetworkAvailable()) {
            recyclerView?.visibility = View.GONE
            emptyState?.visibility = View.GONE
            mapContainer?.visibility = View.VISIBLE
            fabToggleView?.setImageResource(R.drawable.ic_list)
            updateMapMarkers()
        } else {
            if (isMapView && !isNetworkAvailable()) {
                Toast.makeText(context, R.string.map_requires_internet, Toast.LENGTH_SHORT).show()
                isMapView = false
            }
            mapContainer?.visibility = View.GONE
            if (currentClinics.isEmpty()) {
                recyclerView?.visibility = View.GONE
                emptyState?.visibility = View.VISIBLE
            } else {
                recyclerView?.visibility = View.VISIBLE
                emptyState?.visibility = View.GONE
            }
            fabToggleView?.setImageResource(R.drawable.ic_map)
        }
    }
    
    private fun updateMapMarkers() {
        val map = googleMap ?: return
        map.clear()
        
        // Always include Lomé center (B.P. 1413) in bounds so map is centered on the area
        val lomeCenter = LatLng(ClinicViewModel.DEFAULT_LOME_LAT, ClinicViewModel.DEFAULT_LOME_LON)
        val boundsBuilder = LatLngBounds.Builder().include(lomeCenter)
        
        currentClinics.forEach { clinicWithDistance ->
            val clinic = clinicWithDistance.clinic
            val position = LatLng(clinic.latitude, clinic.longitude)
            
            map.addMarker(
                MarkerOptions()
                    .position(position)
                    .title(clinic.name)
                    .snippet(clinic.address)
            )
            
            boundsBuilder.include(position)
        }
        
        if (currentClinics.isEmpty()) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(lomeCenter, 12f))
            return
        }
        
        try {
            val bounds = boundsBuilder.build()
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
        } catch (e: Exception) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(lomeCenter, 12f))
        }
    }
    
    private fun checkLocationPermission() {
        if (hasLocationPermission()) {
            getCurrentLocation()
        } else {
            updateLocationUI(false)
        }
    }
    
    private fun hasLocationPermission(): Boolean {
        val ctx = context ?: return false
        return ContextCompat.checkSelfPermission(
            ctx, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(
            ctx, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    private fun requestLocationPermission() {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
    
    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        if (!hasLocationPermission()) return
        
        val cancellationToken = CancellationTokenSource()
        
        fusedLocationClient?.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            cancellationToken.token
        )?.addOnSuccessListener { location: Location? ->
            if (location != null) {
                viewModel?.setUserLocation(location)
                updateLocationUI(true, location)
            } else {
                // Try to get last known location
                fusedLocationClient?.lastLocation?.addOnSuccessListener { lastLocation ->
                    if (lastLocation != null) {
                        viewModel?.setUserLocation(lastLocation)
                        updateLocationUI(true, lastLocation)
                    } else {
                        updateLocationUI(false)
                    }
                }
            }
        }?.addOnFailureListener {
            updateLocationUI(false)
        }
    }
    
    private fun updateLocationUI(hasLocation: Boolean, location: Location? = null) {
        if (hasLocation && location != null) {
            locationStatusBar?.visibility = View.GONE
        } else {
            locationStatusBar?.visibility = View.VISIBLE
            txtLocationStatus?.text = getString(R.string.enable_location)
        }
    }
    
    private fun isNetworkAvailable(): Boolean {
        val ctx = context ?: return false
        val connectivityManager = ctx.getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
    
    private fun showClinicDetails(clinicWithDistance: ClinicWithDistance) {
        val ctx = context ?: return
        val clinic = clinicWithDistance.clinic
        
        val message = StringBuilder()
        message.append("${clinic.address}, ${clinic.city}, ${clinic.country}\n\n")
        
        if (!clinic.phone.isNullOrBlank()) {
            message.append("${getString(R.string.phone)}: ${clinic.phone}\n\n")
        }
        
        if (!clinic.services.isNullOrBlank()) {
            message.append("${getString(R.string.services)}: ${clinic.services}\n\n")
        }
        
        if (!clinic.openingHours.isNullOrBlank()) {
            message.append("${getString(R.string.hours)}: ${clinic.openingHours}\n")
        }
        
        val distance = clinicWithDistance.getFormattedDistance()
        if (distance.isNotEmpty()) {
            message.append("\n${getString(R.string.distance)}: $distance")
        }
        
        AlertDialog.Builder(ctx)
            .setTitle(clinic.name)
            .setMessage(message.toString())
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
    
    private fun showOptionsDialog() {
        val ctx = context ?: return
        val items = arrayOf(
            getString(R.string.language),
            getString(R.string.favorites),
            getString(R.string.rapid_test_timer_menu)
        )
        AlertDialog.Builder(ctx)
            .setTitle(getString(R.string.menu_options))
            .setItems(items) { _, which ->
                when (which) {
                    0 -> showLanguageDialog()
                    1 -> startActivity(Intent(requireContext(), FavoritesActivity::class.java))
                    2 -> startActivity(Intent(requireContext(), RapidTestTimerActivity::class.java))
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun showLanguageDialog() {
        val ctx = context ?: return
        val languages = arrayOf(getString(R.string.english), getString(R.string.french))
        val languageCodes = arrayOf(LanguageHelper.ENGLISH, LanguageHelper.FRENCH)
        val currentLanguage = LanguageHelper.getLanguage(ctx)
        val currentIndex = languageCodes.indexOf(currentLanguage)

        AlertDialog.Builder(ctx)
            .setTitle(getString(R.string.language))
            .setSingleChoiceItems(languages, currentIndex) { dialog, which ->
                val selectedCode = languageCodes[which]
                if (currentLanguage != selectedCode) {
                    LanguageHelper.setLanguage(ctx, selectedCode)
                    dialog.dismiss()
                    activity?.recreate()
                } else {
                    dialog.dismiss()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView = null
        emptyState = null
        mapContainer = null
        locationStatusBar = null
        txtLocationStatus = null
        btnEnableLocation = null
        chipEmergency = null
        spinnerCountry = null
        fabToggleView = null
        adapter = null
        googleMap = null
    }
}
