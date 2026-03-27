package com.example.helloapp

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
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
import com.example.helloapp.adapter.GrowthRecordAdapter
import com.example.helloapp.data.Child
import com.example.helloapp.data.GrowthRecord
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.util.WHOGrowthStandards
import com.example.helloapp.viewmodel.GrowthViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class GrowthTrackerActivity : AppCompatActivity() {

    private lateinit var viewModel: GrowthViewModel
    
    private lateinit var childSpinner: Spinner
    private lateinit var btnAddChild: Button
    private lateinit var statusCard: View
    private lateinit var historyCard: View
    private lateinit var emptyState: View
    
    private lateinit var txtWeight: TextView
    private lateinit var txtHeight: TextView
    private lateinit var txtBmi: TextView
    private lateinit var txtNutritionalStatus: TextView
    private lateinit var txtWeightZScore: TextView
    private lateinit var txtHeightZScore: TextView
    private lateinit var btnAddMeasurement: Button
    private lateinit var recordsRecyclerView: RecyclerView
    
    private var adapter: GrowthRecordAdapter? = null
    private var children: List<Child> = emptyList()

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageHelper.applyLanguage(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_growth_tracker)
        
        // Setup toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
        
        // Initialize views
        childSpinner = findViewById(R.id.childSpinner)
        btnAddChild = findViewById(R.id.btnAddChild)
        statusCard = findViewById(R.id.statusCard)
        historyCard = findViewById(R.id.historyCard)
        emptyState = findViewById(R.id.emptyState)
        
        txtWeight = findViewById(R.id.txtWeight)
        txtHeight = findViewById(R.id.txtHeight)
        txtBmi = findViewById(R.id.txtBmi)
        txtNutritionalStatus = findViewById(R.id.txtNutritionalStatus)
        txtWeightZScore = findViewById(R.id.txtWeightZScore)
        txtHeightZScore = findViewById(R.id.txtHeightZScore)
        btnAddMeasurement = findViewById(R.id.btnAddMeasurement)
        recordsRecyclerView = findViewById(R.id.recordsRecyclerView)
        
        recordsRecyclerView.layoutManager = LinearLayoutManager(this)
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(
            this,
            GrowthViewModel.Factory(application)
        )[GrowthViewModel::class.java]
        
        // Setup listeners
        btnAddChild.setOnClickListener { showAddChildDialog() }
        btnAddMeasurement.setOnClickListener { showAddMeasurementDialog() }
        
        childSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (children.isNotEmpty() && position < children.size) {
                    viewModel.selectChild(children[position])
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.selectChild(null)
            }
        }
        
        // Observe data
        observeData()
    }
    
    private fun observeData() {
        lifecycleScope.launch {
            viewModel.allChildren.collect { childList ->
                children = childList
                updateChildSpinner(childList)
                
                if (childList.isEmpty()) {
                    emptyState.visibility = View.VISIBLE
                    statusCard.visibility = View.GONE
                    historyCard.visibility = View.GONE
                } else {
                    emptyState.visibility = View.GONE
                }
            }
        }
        
        lifecycleScope.launch {
            combine(
                viewModel.selectedChild,
                viewModel.growthRecords
            ) { child, records ->
                Pair(child, records)
            }.collect { (child, records) ->
                if (child != null) {
                    statusCard.visibility = View.VISIBLE
                    historyCard.visibility = View.VISIBLE
                    
                    // Update adapter
                    adapter = GrowthRecordAdapter(child) { record ->
                        showRecordDetails(record, child)
                    }
                    recordsRecyclerView.adapter = adapter
                    adapter?.submitList(records)
                    
                    // Update status with latest record
                    if (records.isNotEmpty()) {
                        val latestRecord = records.first()
                        updateStatusDisplay(latestRecord, child)
                    } else {
                        clearStatusDisplay()
                    }
                } else {
                    statusCard.visibility = View.GONE
                    historyCard.visibility = View.GONE
                }
            }
        }
    }
    
    private fun updateChildSpinner(children: List<Child>) {
        val names = children.map { it.name }
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, names)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        childSpinner.adapter = spinnerAdapter
    }
    
    private fun updateStatusDisplay(record: GrowthRecord, child: Child) {
        txtWeight.text = String.format("%.1f kg", record.weightKg)
        txtHeight.text = String.format("%.1f cm", record.heightCm)
        
        // Calculate BMI
        val heightM = record.heightCm / 100
        val bmi = record.weightKg / (heightM * heightM)
        txtBmi.text = String.format("%.1f", bmi)
        
        // Get assessment
        val assessment = viewModel.assessGrowth(record, child)
        
        // Update nutritional status
        val isFrench = LanguageHelper.getLanguage(this) == LanguageHelper.FRENCH
        val statusText = if (isFrench) {
            assessment.weightStatus.descriptionFr
        } else {
            assessment.weightStatus.description
        }
        txtNutritionalStatus.text = statusText
        txtNutritionalStatus.background.setTint(Color.parseColor(assessment.weightStatus.colorCode))
        
        // Update z-scores
        txtWeightZScore.text = getString(R.string.weight_for_age_z, 
            String.format("%.2f", assessment.weightForAgeZScore))
        txtHeightZScore.text = getString(R.string.height_for_age_z, 
            String.format("%.2f", assessment.heightForAgeZScore))
    }
    
    private fun clearStatusDisplay() {
        txtWeight.text = "--"
        txtHeight.text = "--"
        txtBmi.text = "--"
        txtNutritionalStatus.text = getString(R.string.no_data)
        txtNutritionalStatus.background.setTint(Color.GRAY)
        txtWeightZScore.text = getString(R.string.weight_for_age_z, "--")
        txtHeightZScore.text = getString(R.string.height_for_age_z, "--")
    }
    
    private fun showAddChildDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_child, null)
        val editName = dialogView.findViewById<TextInputEditText>(R.id.editChildName)
        val btnSelectDate = dialogView.findViewById<Button>(R.id.btnSelectBirthDate)
        val genderGroup = dialogView.findViewById<RadioGroup>(R.id.genderRadioGroup)
        
        var selectedBirthDate: Long? = null
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        
        btnSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    selectedBirthDate = calendar.timeInMillis
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
        
        AlertDialog.Builder(this)
            .setTitle(R.string.add_child)
            .setView(dialogView)
            .setPositiveButton(R.string.save) { _, _ ->
                val name = editName.text?.toString()?.trim()
                val birthDate = selectedBirthDate
                val gender = when (genderGroup.checkedRadioButtonId) {
                    R.id.radioMale -> "male"
                    R.id.radioFemale -> "female"
                    else -> null
                }
                
                if (!name.isNullOrEmpty() && birthDate != null && gender != null) {
                    viewModel.addChild(name, birthDate, gender)
                } else {
                    Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
    
    private fun showAddMeasurementDialog() {
        val child = viewModel.selectedChild.value ?: return
        
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_measurement, null)
        val btnSelectDate = dialogView.findViewById<Button>(R.id.btnSelectDate)
        val editWeight = dialogView.findViewById<TextInputEditText>(R.id.editWeight)
        val editHeight = dialogView.findViewById<TextInputEditText>(R.id.editHeight)
        val editHeadCircumference = dialogView.findViewById<TextInputEditText>(R.id.editHeadCircumference)
        val editNotes = dialogView.findViewById<TextInputEditText>(R.id.editNotes)
        
        var selectedDate: Long = System.currentTimeMillis()
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
                datePicker.minDate = child.dateOfBirth
                show()
            }
        }
        
        AlertDialog.Builder(this)
            .setTitle(R.string.add_measurement)
            .setView(dialogView)
            .setPositiveButton(R.string.save) { _, _ ->
                val weightStr = editWeight.text?.toString()
                val heightStr = editHeight.text?.toString()
                val headStr = editHeadCircumference.text?.toString()
                val notes = editNotes.text?.toString()
                
                val weight = weightStr?.toFloatOrNull()
                val height = heightStr?.toFloatOrNull()
                val head = headStr?.toFloatOrNull()
                
                if (weight != null && height != null) {
                    viewModel.addGrowthRecord(
                        childId = child.id,
                        date = selectedDate,
                        weightKg = weight,
                        heightCm = height,
                        headCircumferenceCm = head,
                        notes = notes?.takeIf { it.isNotBlank() }
                    )
                } else {
                    Toast.makeText(this, R.string.enter_weight_height, Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
    
    private fun showRecordDetails(record: GrowthRecord, child: Child) {
        val assessment = viewModel.assessGrowth(record, child)
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val ageMonths = WHOGrowthStandards.calculateAgeMonths(child.dateOfBirth, record.date)
        val isFrench = LanguageHelper.getLanguage(this) == LanguageHelper.FRENCH

        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_record_details, null)

        dialogView.findViewById<TextView>(R.id.detailDate).text = dateFormat.format(Date(record.date))
        dialogView.findViewById<TextView>(R.id.detailAge).text = "${getString(R.string.age)}: $ageMonths ${getString(R.string.months)}"

        val statusText = if (isFrench) assessment.weightStatus.descriptionFr else assessment.weightStatus.description
        val statusView = dialogView.findViewById<TextView>(R.id.detailStatus)
        statusView.text = statusText
        statusView.background.setTint(Color.parseColor(assessment.weightStatus.colorCode))

        dialogView.findViewById<TextView>(R.id.detailWeight).text = String.format("%.1f kg", record.weightKg)
        dialogView.findViewById<TextView>(R.id.detailHeight).text = String.format("%.1f cm", record.heightCm)
        dialogView.findViewById<TextView>(R.id.detailWeightZScore).text = "z: ${String.format("%.2f", assessment.weightForAgeZScore)}"
        dialogView.findViewById<TextView>(R.id.detailHeightZScore).text = "z: ${String.format("%.2f", assessment.heightForAgeZScore)}"

        val heightM = record.heightCm / 100
        val bmi = record.weightKg / (heightM * heightM)
        dialogView.findViewById<TextView>(R.id.detailBmi).text = String.format("%.1f", bmi)

        if (record.headCircumferenceCm != null) {
            val (hcMin, hcMax) = WHOGrowthStandards.getHeadCircumferenceNormalRange(ageMonths)
            dialogView.findViewById<View>(R.id.detailHeadCircSection).visibility = View.VISIBLE
            dialogView.findViewById<TextView>(R.id.detailHeadCirc).text =
                "${String.format("%.1f", record.headCircumferenceCm)} cm  (${getString(R.string.head_circ_normal_range, hcMin, hcMax)})"
        }

        if (record.muacCm != null) {
            val muacStatus = WHOGrowthStandards.interpretMuac(record.muacCm)
            val muacLabel = if (isFrench) muacStatus.descriptionFr else muacStatus.description
            dialogView.findViewById<View>(R.id.detailMuacSection).visibility = View.VISIBLE
            dialogView.findViewById<TextView>(R.id.detailMuac).text =
                "${String.format("%.1f", record.muacCm)} cm  \u2192 $muacLabel"
        }

        if (!record.notes.isNullOrBlank()) {
            dialogView.findViewById<View>(R.id.detailNotesSection).visibility = View.VISIBLE
            dialogView.findViewById<TextView>(R.id.detailNotes).text = record.notes
        }

        AlertDialog.Builder(this)
            .setTitle(R.string.measurement_details)
            .setView(dialogView)
            .setPositiveButton(android.R.string.ok, null)
            .setNegativeButton(R.string.delete) { _, _ ->
                viewModel.deleteGrowthRecord(record)
            }
            .show()
    }
}
