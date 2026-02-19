package com.example.helloapp.fragment

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.R
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

class GrowthTrackerFragment : Fragment() {

    private var viewModel: GrowthViewModel? = null
    
    private var childSpinner: Spinner? = null
    private var btnAddChild: Button? = null
    private var btnDeleteChild: Button? = null
    private var statusCard: View? = null
    private var historyCard: View? = null
    private var emptyState: View? = null
    
    private var txtWeight: TextView? = null
    private var txtHeight: TextView? = null
    private var txtBmi: TextView? = null
    private var txtNutritionalStatus: TextView? = null
    private var txtWeightZScore: TextView? = null
    private var txtHeightZScore: TextView? = null
    private var btnAddMeasurement: Button? = null
    private var recordsRecyclerView: RecyclerView? = null
    
    private var adapter: GrowthRecordAdapter? = null
    private var children: List<Child> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_growth_tracker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        val isEmbedded = arguments?.getBoolean(VaccinationGrowthFragment.ARG_EMBEDDED, false) == true
        if (isEmbedded) toolbar.visibility = View.GONE
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_language -> {
                    showLanguageDialog()
                    true
                }
                else -> false
            }
        }
        
        // Initialize views
        childSpinner = view.findViewById(R.id.childSpinner)
        btnAddChild = view.findViewById(R.id.btnAddChild)
        btnDeleteChild = view.findViewById(R.id.btnDeleteChild)
        statusCard = view.findViewById(R.id.statusCard)
        historyCard = view.findViewById(R.id.historyCard)
        emptyState = view.findViewById(R.id.emptyState)
        
        txtWeight = view.findViewById(R.id.txtWeight)
        txtHeight = view.findViewById(R.id.txtHeight)
        txtBmi = view.findViewById(R.id.txtBmi)
        txtNutritionalStatus = view.findViewById(R.id.txtNutritionalStatus)
        txtWeightZScore = view.findViewById(R.id.txtWeightZScore)
        txtHeightZScore = view.findViewById(R.id.txtHeightZScore)
        btnAddMeasurement = view.findViewById(R.id.btnAddMeasurement)
        recordsRecyclerView = view.findViewById(R.id.recordsRecyclerView)
        
        recordsRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(
            requireActivity(),
            GrowthViewModel.Factory(requireActivity().application)
        )[GrowthViewModel::class.java]
        
        // Setup listeners
        btnAddChild?.setOnClickListener { showAddChildDialog() }
        btnDeleteChild?.setOnClickListener { confirmDeleteChild() }
        btnAddMeasurement?.setOnClickListener { showAddMeasurementDialog() }
        
        childSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (children.isNotEmpty() && position < children.size) {
                    viewModel?.selectChild(children[position])
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel?.selectChild(null)
            }
        }
        
        // Observe data
        observeData()
    }
    
    private fun confirmDeleteChild() {
        val ctx = context ?: return
        val child = viewModel?.selectedChild?.value ?: return
        AlertDialog.Builder(ctx)
            .setTitle(getString(R.string.delete_child))
            .setMessage(getString(R.string.delete_child_confirm))
            .setPositiveButton(android.R.string.ok) { _, _ ->
                viewModel?.deleteChild(child)
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
                    // Recreate activity to apply language change
                    activity?.recreate()
                } else {
                    dialog.dismiss()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
    
    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.allChildren?.collect { childList ->
                children = childList
                updateChildSpinner(childList)
                
                if (childList.isEmpty()) {
                    emptyState?.visibility = View.VISIBLE
                    statusCard?.visibility = View.GONE
                    historyCard?.visibility = View.GONE
                } else {
                    emptyState?.visibility = View.GONE
                }
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            val vm = viewModel ?: return@launch
            combine(
                vm.selectedChild,
                vm.growthRecords
            ) { child, records ->
                Pair(child, records)
            }.collect { (child, records) ->
                btnDeleteChild?.visibility = if (child != null) View.VISIBLE else View.GONE
                if (child != null) {
                    statusCard?.visibility = View.VISIBLE
                    historyCard?.visibility = View.VISIBLE
                    
                    adapter = GrowthRecordAdapter(child) { record ->
                        showRecordDetails(record, child)
                    }
                    recordsRecyclerView?.adapter = adapter
                    adapter?.submitList(records)
                    
                    if (records.isNotEmpty()) {
                        val latestRecord = records.first()
                        updateStatusDisplay(latestRecord, child)
                    } else {
                        clearStatusDisplay()
                    }
                } else {
                    statusCard?.visibility = View.GONE
                    historyCard?.visibility = View.GONE
                }
            }
        }
    }
    
    private fun updateChildSpinner(children: List<Child>) {
        val ctx = context ?: return
        val names = children.map { it.name }
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, names)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        childSpinner?.adapter = spinnerAdapter
    }
    
    private fun updateStatusDisplay(record: GrowthRecord, child: Child) {
        txtWeight?.text = String.format("%.1f kg", record.weightKg)
        txtHeight?.text = String.format("%.1f cm", record.heightCm)
        
        val heightM = record.heightCm / 100
        val bmi = record.weightKg / (heightM * heightM)
        txtBmi?.text = String.format("%.1f", bmi)
        
        val assessment = viewModel?.assessGrowth(record, child) ?: return
        
        val isFrench = LanguageHelper.getLanguage(requireContext()) == LanguageHelper.FRENCH
        val statusText = if (isFrench) {
            assessment.weightStatus.descriptionFr
        } else {
            assessment.weightStatus.description
        }
        txtNutritionalStatus?.text = statusText
        txtNutritionalStatus?.background?.setTint(Color.parseColor(assessment.weightStatus.colorCode))
        
        txtWeightZScore?.text = getString(R.string.weight_for_age_z, 
            String.format("%.2f", assessment.weightForAgeZScore))
        txtHeightZScore?.text = getString(R.string.height_for_age_z, 
            String.format("%.2f", assessment.heightForAgeZScore))
    }
    
    private fun clearStatusDisplay() {
        txtWeight?.text = "--"
        txtHeight?.text = "--"
        txtBmi?.text = "--"
        txtNutritionalStatus?.text = getString(R.string.no_data)
        txtNutritionalStatus?.background?.setTint(Color.GRAY)
        txtWeightZScore?.text = getString(R.string.weight_for_age_z, "--")
        txtHeightZScore?.text = getString(R.string.height_for_age_z, "--")
    }
    
    private fun showAddChildDialog() {
        val ctx = context ?: return
        val dialogView = LayoutInflater.from(ctx).inflate(R.layout.dialog_add_child, null)
        val editName = dialogView.findViewById<TextInputEditText>(R.id.editChildName)
        val btnSelectDate = dialogView.findViewById<Button>(R.id.btnSelectBirthDate)
        val genderGroup = dialogView.findViewById<RadioGroup>(R.id.genderRadioGroup)
        
        var selectedBirthDate: Long? = null
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        
        btnSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                ctx,
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
        
        AlertDialog.Builder(ctx)
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
                    viewModel?.addChild(name, birthDate, gender)
                } else {
                    Toast.makeText(ctx, R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
    
    private fun showAddMeasurementDialog() {
        val ctx = context ?: return
        val child = viewModel?.selectedChild?.value ?: return
        
        val dialogView = LayoutInflater.from(ctx).inflate(R.layout.dialog_add_measurement, null)
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
                ctx,
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
        
        AlertDialog.Builder(ctx)
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
                    viewModel?.addGrowthRecord(
                        childId = child.id,
                        date = selectedDate,
                        weightKg = weight,
                        heightCm = height,
                        headCircumferenceCm = head,
                        notes = notes?.takeIf { it.isNotBlank() }
                    )
                } else {
                    Toast.makeText(ctx, R.string.enter_weight_height, Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
    
    private fun showRecordDetails(record: GrowthRecord, child: Child) {
        val ctx = context ?: return
        val assessment = viewModel?.assessGrowth(record, child) ?: return
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val ageMonths = WHOGrowthStandards.calculateAgeMonths(child.dateOfBirth, record.date)
        
        val isFrench = LanguageHelper.getLanguage(ctx) == LanguageHelper.FRENCH
        
        val message = StringBuilder()
        message.append("${getString(R.string.date)}: ${dateFormat.format(Date(record.date))}\n")
        message.append("${getString(R.string.age)}: $ageMonths ${getString(R.string.months)}\n\n")
        message.append("${getString(R.string.weight)}: ${String.format("%.1f", record.weightKg)} kg\n")
        message.append("${getString(R.string.height)}: ${String.format("%.1f", record.heightCm)} cm\n")
        
        if (record.headCircumferenceCm != null) {
            message.append("${getString(R.string.head_circumference)}: ${String.format("%.1f", record.headCircumferenceCm)} cm\n")
        }
        
        val heightM = record.heightCm / 100
        val bmi = record.weightKg / (heightM * heightM)
        message.append("${getString(R.string.bmi)}: ${String.format("%.1f", bmi)}\n\n")
        
        val statusText = if (isFrench) assessment.weightStatus.descriptionFr else assessment.weightStatus.description
        message.append("${getString(R.string.nutritional_status)}: $statusText\n\n")
        
        message.append("${getString(R.string.who_z_scores)}:\n")
        message.append("• ${getString(R.string.weight_for_age)}: ${String.format("%.2f", assessment.weightForAgeZScore)}\n")
        message.append("• ${getString(R.string.height_for_age)}: ${String.format("%.2f", assessment.heightForAgeZScore)}\n")
        message.append("• ${getString(R.string.weight_for_height)}: ${String.format("%.2f", assessment.weightForHeightZScore)}\n")
        
        if (!record.notes.isNullOrBlank()) {
            message.append("\n${getString(R.string.notes)}: ${record.notes}")
        }
        
        AlertDialog.Builder(ctx)
            .setTitle(R.string.measurement_details)
            .setMessage(message.toString())
            .setPositiveButton(android.R.string.ok, null)
            .setNegativeButton(R.string.delete) { _, _ ->
                viewModel?.deleteGrowthRecord(record)
            }
            .show()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        childSpinner = null
        btnAddChild = null
        statusCard = null
        historyCard = null
        emptyState = null
        txtWeight = null
        txtHeight = null
        txtBmi = null
        txtNutritionalStatus = null
        txtWeightZScore = null
        txtHeightZScore = null
        btnAddMeasurement = null
        recordsRecyclerView = null
        adapter = null
    }
}
