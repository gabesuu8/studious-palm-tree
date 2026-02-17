package com.example.helloapp

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.adapter.VaccinationAdapter
import com.example.helloapp.data.Vaccination
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.viewmodel.VaccinationViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class VaccinationActivity : AppCompatActivity() {
    
    private lateinit var viewModel: VaccinationViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyStateText: TextView
    private lateinit var progressCount: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: VaccinationAdapter
    
    private lateinit var chipAll: Chip
    private lateinit var chipCompleted: Chip
    
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageHelper.applyLanguage(newBase))
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccinations)
        
        // Setup toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
        
        // Initialize views
        recyclerView = findViewById(R.id.vaccinationsRecyclerView)
        emptyStateText = findViewById(R.id.emptyStateText)
        progressCount = findViewById(R.id.progressCount)
        progressBar = findViewById(R.id.progressBar)
        chipAll = findViewById(R.id.chipAll)
        chipCompleted = findViewById(R.id.chipCompleted)
        
        // Setup adapter
        adapter = VaccinationAdapter(
            onCheckChanged = { vaccination -> toggleVaccination(vaccination) },
            onItemClick = { vaccination -> showVaccinationDetails(vaccination) }
        )
        
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(
            this,
            VaccinationViewModel.Factory(application)
        )[VaccinationViewModel::class.java]
        
        // Setup filter chips
        setupFilterChips()
        
        // Observe vaccinations
        lifecycleScope.launch {
            viewModel.filteredVaccinations.collect { vaccinations ->
                if (vaccinations.isEmpty()) {
                    recyclerView.visibility = View.GONE
                    emptyStateText.visibility = View.VISIBLE
                } else {
                    recyclerView.visibility = View.VISIBLE
                    emptyStateText.visibility = View.GONE
                    adapter.submitVaccinations(vaccinations)
                }
            }
        }
        
        // Update progress
        lifecycleScope.launch {
            viewModel.allVaccinations.collect { vaccinations ->
                updateProgress(vaccinations)
            }
        }
    }
    
    private fun setupFilterChips() {
        chipAll.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) viewModel.setFilter("all")
        }
        chipCompleted.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) viewModel.setFilter("completed")
        }
    }
    
    private fun toggleVaccination(vaccination: Vaccination) {
        viewModel.toggleVaccinationStatus(vaccination)
        
        val message = if (!vaccination.isCompleted) {
            getString(R.string.vaccination_marked_complete, vaccination.name)
        } else {
            getString(R.string.vaccination_marked_incomplete, vaccination.name)
        }
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show()
    }
    
    private fun showVaccinationDetails(vaccination: Vaccination) {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val completionInfo = if (vaccination.isCompleted && vaccination.dateCompleted != null) {
            "\n\n${getString(R.string.completed_on)}: ${dateFormat.format(Date(vaccination.dateCompleted))}"
        } else {
            ""
        }
        
        val dialog = AlertDialog.Builder(this)
            .setTitle(vaccination.name)
            .setMessage("${vaccination.description}\n\n${getString(R.string.recommended_age)}: ${vaccination.recommendedAge}$completionInfo")
        
        if (!vaccination.isCompleted) {
            dialog.setPositiveButton(getString(R.string.mark_as_done)) { _, _ ->
                showDatePicker(vaccination)
            }
        } else {
            dialog.setPositiveButton(getString(R.string.mark_as_not_done)) { _, _ ->
                viewModel.toggleVaccinationStatus(vaccination)
            }
        }
        
        dialog.setNegativeButton(android.R.string.cancel, null)
        dialog.show()
    }
    
    private fun showDatePicker(vaccination: Vaccination) {
        val calendar = Calendar.getInstance()
        
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                viewModel.updateVaccinationDate(vaccination, calendar.timeInMillis)
                Snackbar.make(
                    recyclerView,
                    getString(R.string.vaccination_marked_complete, vaccination.name),
                    Snackbar.LENGTH_SHORT
                ).show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.maxDate = System.currentTimeMillis()
            setTitle(getString(R.string.select_date))
            show()
        }
    }
    
    private fun updateProgress(vaccinations: List<Vaccination>) {
        val completed = vaccinations.count { it.isCompleted }
        val total = vaccinations.size
        
        progressCount.text = "$completed/$total"
        
        if (total > 0) {
            val percentage = (completed * 100) / total
            progressBar.progress = percentage
        } else {
            progressBar.progress = 0
        }
    }
}
