package com.example.helloapp

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.adapter.PrenatalVisitAdapter
import com.example.helloapp.data.Pregnancy
import com.example.helloapp.data.PrenatalVisit
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.viewmodel.PregnancyViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class PregnancyTrackerActivity : AppCompatActivity() {

    private lateinit var viewModel: PregnancyViewModel
    
    private lateinit var pregnancySpinner: Spinner
    private lateinit var btnAddPregnancy: Button
    private lateinit var progressCard: View
    private lateinit var dangerSignsCard: View
    private lateinit var visitsCard: View
    private lateinit var emptyState: View
    
    private lateinit var txtMotherName: TextView
    private lateinit var txtCurrentWeek: TextView
    private lateinit var txtTrimester: TextView
    private lateinit var txtDaysRemaining: TextView
    private lateinit var txtDueDate: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnAddVisit: Button
    private lateinit var btnViewDangerSigns: Button
    private lateinit var visitsRecyclerView: RecyclerView
    private lateinit var txtNoVisits: TextView
    
    private var adapter: PrenatalVisitAdapter? = null
    private var pregnancies: List<Pregnancy> = emptyList()

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageHelper.applyLanguage(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregnancy_tracker)
        
        // Setup toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
        
        // Initialize views
        pregnancySpinner = findViewById(R.id.pregnancySpinner)
        btnAddPregnancy = findViewById(R.id.btnAddPregnancy)
        progressCard = findViewById(R.id.progressCard)
        dangerSignsCard = findViewById(R.id.dangerSignsCard)
        visitsCard = findViewById(R.id.visitsCard)
        emptyState = findViewById(R.id.emptyState)
        
        txtMotherName = findViewById(R.id.txtMotherName)
        txtCurrentWeek = findViewById(R.id.txtCurrentWeek)
        txtTrimester = findViewById(R.id.txtTrimester)
        txtDaysRemaining = findViewById(R.id.txtDaysRemaining)
        txtDueDate = findViewById(R.id.txtDueDate)
        progressBar = findViewById(R.id.progressBar)
        btnAddVisit = findViewById(R.id.btnAddVisit)
        btnViewDangerSigns = findViewById(R.id.btnViewDangerSigns)
        visitsRecyclerView = findViewById(R.id.visitsRecyclerView)
        txtNoVisits = findViewById(R.id.txtNoVisits)
        
        visitsRecyclerView.layoutManager = LinearLayoutManager(this)
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(
            this,
            PregnancyViewModel.Factory(application)
        )[PregnancyViewModel::class.java]
        
        // Setup listeners
        btnAddPregnancy.setOnClickListener { showAddPregnancyDialog() }
        btnAddVisit.setOnClickListener { showAddVisitDialog() }
        btnViewDangerSigns.setOnClickListener { showDangerSignsDialog() }
        
        pregnancySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (pregnancies.isNotEmpty() && position < pregnancies.size) {
                    viewModel.selectPregnancy(pregnancies[position])
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.selectPregnancy(null)
            }
        }
        
        // Observe data
        observeData()
    }
    
    private fun observeData() {
        lifecycleScope.launch {
            viewModel.activePregnancies.collect { pregnancyList ->
                pregnancies = pregnancyList
                updatePregnancySpinner(pregnancyList)
                
                if (pregnancyList.isEmpty()) {
                    emptyState.visibility = View.VISIBLE
                    progressCard.visibility = View.GONE
                    dangerSignsCard.visibility = View.GONE
                    visitsCard.visibility = View.GONE
                } else {
                    emptyState.visibility = View.GONE
                }
            }
        }
        
        lifecycleScope.launch {
            combine(
                viewModel.selectedPregnancy,
                viewModel.prenatalVisits
            ) { pregnancy, visits ->
                Pair(pregnancy, visits)
            }.collect { (pregnancy, visits) ->
                if (pregnancy != null) {
                    progressCard.visibility = View.VISIBLE
                    dangerSignsCard.visibility = View.VISIBLE
                    visitsCard.visibility = View.VISIBLE
                    
                    updatePregnancyDisplay(pregnancy)
                    
                    // Update visits
                    adapter = PrenatalVisitAdapter { visit ->
                        showVisitDetails(visit)
                    }
                    visitsRecyclerView.adapter = adapter
                    adapter?.submitList(visits)
                    
                    if (visits.isEmpty()) {
                        txtNoVisits.visibility = View.VISIBLE
                        visitsRecyclerView.visibility = View.GONE
                    } else {
                        txtNoVisits.visibility = View.GONE
                        visitsRecyclerView.visibility = View.VISIBLE
                    }
                } else {
                    progressCard.visibility = View.GONE
                    dangerSignsCard.visibility = View.GONE
                    visitsCard.visibility = View.GONE
                }
            }
        }
    }
    
    private fun updatePregnancySpinner(pregnancies: List<Pregnancy>) {
        val names = pregnancies.map { it.motherName }
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, names)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pregnancySpinner.adapter = spinnerAdapter
    }
    
    private fun updatePregnancyDisplay(pregnancy: Pregnancy) {
        txtMotherName.text = pregnancy.motherName
        
        val currentWeek = viewModel.getCurrentWeek(pregnancy)
        val trimester = viewModel.getTrimester(currentWeek)
        val daysRemaining = viewModel.getDaysRemaining(pregnancy)
        
        txtCurrentWeek.text = currentWeek.toString()
        txtTrimester.text = trimester.toString()
        txtDaysRemaining.text = daysRemaining.toString()
        
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        txtDueDate.text = getString(R.string.expected_due_date, dateFormat.format(Date(pregnancy.expectedDueDate)))
        
        progressBar.progress = currentWeek.coerceIn(0, 40)
    }
    
    private fun showAddPregnancyDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_pregnancy, null)
        val editName = dialogView.findViewById<TextInputEditText>(R.id.editMotherName)
        val btnSelectLMP = dialogView.findViewById<Button>(R.id.btnSelectLMP)
        val editNotes = dialogView.findViewById<TextInputEditText>(R.id.editNotes)
        
        var selectedLMP: Long? = null
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        
        btnSelectLMP.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    selectedLMP = calendar.timeInMillis
                    btnSelectLMP.text = dateFormat.format(calendar.time)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).apply {
                datePicker.maxDate = System.currentTimeMillis()
                show()
            }
        }
        
        AlertDialog.Builder(this)
            .setTitle(R.string.add_pregnancy)
            .setView(dialogView)
            .setPositiveButton(R.string.save) { _, _ ->
                val name = editName.text?.toString()?.trim()
                val lmp = selectedLMP
                val notes = editNotes.text?.toString()?.trim()
                
                if (!name.isNullOrEmpty() && lmp != null) {
                    viewModel.addPregnancy(name, lmp, notes?.takeIf { it.isNotBlank() })
                } else {
                    Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
    
    private fun showAddVisitDialog() {
        val pregnancy = viewModel.selectedPregnancy.value ?: return
        
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_visit, null)
        val btnSelectDate = dialogView.findViewById<Button>(R.id.btnSelectVisitDate)
        val editWeight = dialogView.findViewById<TextInputEditText>(R.id.editVisitWeight)
        val editBP = dialogView.findViewById<TextInputEditText>(R.id.editBloodPressure)
        val editFHR = dialogView.findViewById<TextInputEditText>(R.id.editFetalHeartRate)
        val editNotes = dialogView.findViewById<TextInputEditText>(R.id.editVisitNotes)
        val btnSelectNext = dialogView.findViewById<Button>(R.id.btnSelectNextVisit)
        
        var selectedDate: Long = System.currentTimeMillis()
        var nextVisitDate: Long? = null
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        btnSelectDate.text = dateFormat.format(Date(selectedDate))
        
        btnSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    selectedDate = calendar.timeInMillis
                    btnSelectDate.text = dateFormat.format(calendar.time)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).apply {
                datePicker.maxDate = System.currentTimeMillis()
                show()
            }
        }
        
        btnSelectNext.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    nextVisitDate = calendar.timeInMillis
                    btnSelectNext.text = dateFormat.format(calendar.time)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).apply {
                datePicker.minDate = System.currentTimeMillis()
                show()
            }
        }
        
        AlertDialog.Builder(this)
            .setTitle(R.string.add_prenatal_visit)
            .setView(dialogView)
            .setPositiveButton(R.string.save) { _, _ ->
                val weight = editWeight.text?.toString()?.toFloatOrNull()
                val bp = editBP.text?.toString()?.trim()
                val fhr = editFHR.text?.toString()?.toIntOrNull()
                val notes = editNotes.text?.toString()?.trim()
                
                val weekOfPregnancy = Pregnancy.calculateCurrentWeek(pregnancy.lastMenstrualPeriod)
                
                viewModel.addPrenatalVisit(
                    pregnancyId = pregnancy.id,
                    visitDate = selectedDate,
                    weekOfPregnancy = weekOfPregnancy,
                    weightKg = weight,
                    bloodPressure = bp?.takeIf { it.isNotBlank() },
                    fetalHeartRate = fhr,
                    notes = notes?.takeIf { it.isNotBlank() },
                    nextVisitDate = nextVisitDate
                )
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
    
    private fun showVisitDetails(visit: PrenatalVisit) {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        
        val message = StringBuilder()
        message.append("${getString(R.string.date)}: ${dateFormat.format(Date(visit.visitDate))}\n")
        message.append("${getString(R.string.week)}: ${visit.weekOfPregnancy}\n\n")
        
        if (visit.weightKg != null) {
            message.append("${getString(R.string.weight)}: ${String.format("%.1f", visit.weightKg)} kg\n")
        }
        
        if (!visit.bloodPressure.isNullOrBlank()) {
            message.append("${getString(R.string.blood_pressure)}: ${visit.bloodPressure}\n")
        }
        
        if (visit.fetalHeartRate != null) {
            message.append("${getString(R.string.fetal_heart_rate)}: ${visit.fetalHeartRate} bpm\n")
        }
        
        if (!visit.notes.isNullOrBlank()) {
            message.append("\n${getString(R.string.notes)}: ${visit.notes}")
        }
        
        if (visit.nextVisitDate != null) {
            message.append("\n\n${getString(R.string.next_visit)}: ${dateFormat.format(Date(visit.nextVisitDate))}")
        }
        
        AlertDialog.Builder(this)
            .setTitle(R.string.visit_details)
            .setMessage(message.toString())
            .setPositiveButton(android.R.string.ok, null)
            .setNegativeButton(R.string.delete) { _, _ ->
                viewModel.deleteVisit(visit)
            }
            .show()
    }
    
    private fun showDangerSignsDialog() {
        val dangerSigns = viewModel.getDangerSigns()
        val isFrench = LanguageHelper.getLanguage(this) == LanguageHelper.FRENCH
        
        val message = StringBuilder()
        message.append(getString(R.string.seek_immediate_care))
        message.append("\n\n")
        
        dangerSigns.forEach { sign ->
            val name = if (isFrench) sign.nameFr else sign.name
            val description = if (isFrench) sign.descriptionFr else sign.description
            message.append("• $name\n")
            message.append("  $description\n\n")
        }
        
        AlertDialog.Builder(this)
            .setTitle(R.string.danger_signs_title)
            .setMessage(message.toString())
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
}
