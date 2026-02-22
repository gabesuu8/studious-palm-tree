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
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.ChipGroup
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
    private var chartCard: View? = null
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

    private var growthLineChart: LineChart? = null
    private var chartMetricChipGroup: ChipGroup? = null
    private var showWeightChart = true

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
                R.id.menu_language -> { showLanguageDialog(); true }
                else -> false
            }
        }

        childSpinner = view.findViewById(R.id.childSpinner)
        btnAddChild = view.findViewById(R.id.btnAddChild)
        btnDeleteChild = view.findViewById(R.id.btnDeleteChild)
        statusCard = view.findViewById(R.id.statusCard)
        chartCard = view.findViewById(R.id.chartCard)
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

        growthLineChart = view.findViewById(R.id.growthLineChart)
        chartMetricChipGroup = view.findViewById(R.id.chartMetricChipGroup)

        recordsRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        growthLineChart?.let { setupChartAppearance(it) }

        chartMetricChipGroup?.setOnCheckedStateChangeListener { _, checkedIds ->
            showWeightChart = checkedIds.contains(R.id.chipWeight)
            val child = viewModel?.selectedChild?.value ?: return@setOnCheckedStateChangeListener
            val records = viewModel?.growthRecords?.value ?: return@setOnCheckedStateChangeListener
            updateGrowthChart(records, child)
        }

        viewModel = ViewModelProvider(
            requireActivity(),
            GrowthViewModel.Factory(requireActivity().application)
        )[GrowthViewModel::class.java]

        btnAddChild?.setOnClickListener { showAddChildDialog() }
        btnDeleteChild?.setOnClickListener { confirmDeleteChild() }
        btnAddMeasurement?.setOnClickListener { showAddMeasurementDialog() }

        childSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (children.isNotEmpty() && position < children.size) {
                    viewModel?.selectChild(children[position])
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) { viewModel?.selectChild(null) }
        }

        observeData()
    }

    // ── Chart ─────────────────────────────────────────────────────────────────

    private fun setupChartAppearance(chart: LineChart) {
        chart.description.isEnabled = false
        chart.setTouchEnabled(true)
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        chart.setPinchZoom(true)
        chart.setDrawGridBackground(false)
        chart.legend.isEnabled = false

        chart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(true)
            granularity = 3f
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float) = value.toInt().toString()
            }
            textSize = 10f
        }
        chart.axisLeft.apply {
            setDrawGridLines(true)
            axisMinimum = 0f
            textSize = 10f
        }
        chart.axisRight.isEnabled = false
    }

    private fun updateGrowthChart(records: List<GrowthRecord>, child: Child) {
        val chart = growthLineChart ?: return
        val sortedRecords = records.sortedBy { it.date }
        val isMale = child.gender == "male"

        val actualEntries = sortedRecords.mapNotNull { record ->
            val ageMonths = WHOGrowthStandards.calculateAgeMonths(child.dateOfBirth, record.date)
            if (ageMonths < 0) return@mapNotNull null
            val value = if (showWeightChart) record.weightKg else record.heightCm
            Entry(ageMonths.toFloat(), value)
        }

        val label = if (showWeightChart) getString(R.string.weight_kg) else getString(R.string.height_cm)
        val actualSet = LineDataSet(actualEntries, label).apply {
            color = Color.parseColor("#009688")
            setCircleColor(Color.parseColor("#009688"))
            lineWidth = 2.5f
            circleRadius = 4f
            circleHoleRadius = 2f
            setDrawValues(false)
            mode = LineDataSet.Mode.LINEAR
        }

        val dataSets = mutableListOf<ILineDataSet>(actualSet)
        dataSets.addAll(buildWHOReferenceLines(isMale))

        chart.data = LineData(dataSets)

        val maxAge = sortedRecords.maxOfOrNull { record ->
            WHOGrowthStandards.calculateAgeMonths(child.dateOfBirth, record.date)
        } ?: 60
        chart.xAxis.axisMaximum = (maxOf(maxAge, 12) + 3).toFloat()
        chart.xAxis.axisMinimum = 0f
        chart.invalidate()
    }

    private fun buildWHOReferenceLines(isMale: Boolean): List<ILineDataSet> {
        data class RefLine(val zScore: Double, val color: Int, val label: String)
        val lines = listOf(
            RefLine(0.0,  Color.parseColor("#4CAF50"), getString(R.string.who_median)),
            RefLine(-2.0, Color.parseColor("#FF8F00"), getString(R.string.who_minus2sd)),
            RefLine(-3.0, Color.parseColor("#D32F2F"), getString(R.string.who_minus3sd))
        )
        return lines.map { (zScore, color, label) ->
            val entries = WHOGrowthStandards.referenceAges.map { ageMonths ->
                val value = if (showWeightChart) {
                    WHOGrowthStandards.getWeightForAgeAtZScore(ageMonths, zScore, isMale)
                } else {
                    WHOGrowthStandards.getHeightForAgeAtZScore(ageMonths, zScore, isMale)
                }
                Entry(ageMonths.toFloat(), value)
            }
            LineDataSet(entries, label).apply {
                this.color = color
                lineWidth = 1.2f
                setDrawCircles(false)
                setDrawValues(false)
                enableDashedLine(10f, 6f, 0f)
            }
        }
    }

    // ── Data observation ──────────────────────────────────────────────────────

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.allChildren?.collect { childList ->
                children = childList
                updateChildSpinner(childList)
                if (childList.isEmpty()) {
                    emptyState?.visibility = View.VISIBLE
                    statusCard?.visibility = View.GONE
                    chartCard?.visibility = View.GONE
                    historyCard?.visibility = View.GONE
                } else {
                    emptyState?.visibility = View.GONE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val vm = viewModel ?: return@launch
            combine(vm.selectedChild, vm.growthRecords) { child, records ->
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
                        updateStatusDisplay(records.first(), child)
                        chartCard?.visibility = View.VISIBLE
                        updateGrowthChart(records, child)
                    } else {
                        clearStatusDisplay()
                        chartCard?.visibility = View.GONE
                    }
                } else {
                    statusCard?.visibility = View.GONE
                    chartCard?.visibility = View.GONE
                    historyCard?.visibility = View.GONE
                }
            }
        }
    }

    // ── UI helpers ────────────────────────────────────────────────────────────

    private fun updateChildSpinner(children: List<Child>) {
        val ctx = context ?: return
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, children.map { it.name })
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        childSpinner?.adapter = spinnerAdapter
    }

    private fun updateStatusDisplay(record: GrowthRecord, child: Child) {
        txtWeight?.text = String.format("%.1f kg", record.weightKg)
        txtHeight?.text = String.format("%.1f cm", record.heightCm)
        val heightM = record.heightCm / 100
        txtBmi?.text = String.format("%.1f", record.weightKg / (heightM * heightM))

        val assessment = viewModel?.assessGrowth(record, child) ?: return
        val isFrench = LanguageHelper.getLanguage(requireContext()) == LanguageHelper.FRENCH
        txtNutritionalStatus?.text = if (isFrench) assessment.weightStatus.descriptionFr else assessment.weightStatus.description
        txtNutritionalStatus?.background?.setTint(Color.parseColor(assessment.weightStatus.colorCode))
        txtWeightZScore?.text = getString(R.string.weight_for_age_z, String.format("%.2f", assessment.weightForAgeZScore))
        txtHeightZScore?.text = getString(R.string.height_for_age_z, String.format("%.2f", assessment.heightForAgeZScore))
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

    // ── Dialogs ───────────────────────────────────────────────────────────────

    private fun confirmDeleteChild() {
        val ctx = context ?: return
        val child = viewModel?.selectedChild?.value ?: return
        AlertDialog.Builder(ctx)
            .setTitle(getString(R.string.delete_child))
            .setMessage(getString(R.string.delete_child_confirm))
            .setPositiveButton(android.R.string.ok) { _, _ -> viewModel?.deleteChild(child) }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun showLanguageDialog() {
        val ctx = context ?: return
        val languages = arrayOf(getString(R.string.english), getString(R.string.french))
        val languageCodes = arrayOf(LanguageHelper.ENGLISH, LanguageHelper.FRENCH)
        val currentLanguage = LanguageHelper.getLanguage(ctx)
        AlertDialog.Builder(ctx)
            .setTitle(getString(R.string.language))
            .setSingleChoiceItems(languages, languageCodes.indexOf(currentLanguage)) { dialog, which ->
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
            DatePickerDialog(ctx, { _, year, month, day ->
                calendar.set(year, month, day)
                selectedBirthDate = calendar.timeInMillis
                btnSelectDate.text = dateFormat.format(calendar.time)
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                .apply { datePicker.maxDate = System.currentTimeMillis(); show() }
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
        val editMuac = dialogView.findViewById<TextInputEditText>(R.id.editMuac)
        val editNotes = dialogView.findViewById<TextInputEditText>(R.id.editNotes)

        var selectedDate: Long = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        btnSelectDate.text = dateFormat.format(Date(selectedDate))

        btnSelectDate.setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(ctx, { _, year, month, day ->
                cal.set(year, month, day)
                selectedDate = cal.timeInMillis
                btnSelectDate.text = dateFormat.format(cal.time)
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
                .apply {
                    datePicker.maxDate = System.currentTimeMillis()
                    datePicker.minDate = child.dateOfBirth
                    show()
                }
        }

        AlertDialog.Builder(ctx)
            .setTitle(R.string.add_measurement)
            .setView(dialogView)
            .setPositiveButton(R.string.save) { _, _ ->
                val weight = editWeight.text?.toString()?.toFloatOrNull()
                val height = editHeight.text?.toString()?.toFloatOrNull()
                val head = editHeadCircumference.text?.toString()?.toFloatOrNull()
                val muac = editMuac.text?.toString()?.toFloatOrNull()
                val notes = editNotes.text?.toString()?.takeIf { it.isNotBlank() }
                if (weight != null && height != null) {
                    viewModel?.addGrowthRecord(
                        childId = child.id,
                        date = selectedDate,
                        weightKg = weight,
                        heightCm = height,
                        headCircumferenceCm = head,
                        muacCm = muac,
                        notes = notes
                    )
                } else {
                    Toast.makeText(ctx, R.string.enter_weight_height, Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun showEditMeasurementDialog(record: GrowthRecord, child: Child) {
        val ctx = context ?: return
        val dialogView = LayoutInflater.from(ctx).inflate(R.layout.dialog_add_measurement, null)
        val btnSelectDate = dialogView.findViewById<Button>(R.id.btnSelectDate)
        val editWeight = dialogView.findViewById<TextInputEditText>(R.id.editWeight)
        val editHeight = dialogView.findViewById<TextInputEditText>(R.id.editHeight)
        val editHeadCircumference = dialogView.findViewById<TextInputEditText>(R.id.editHeadCircumference)
        val editMuac = dialogView.findViewById<TextInputEditText>(R.id.editMuac)
        val editNotes = dialogView.findViewById<TextInputEditText>(R.id.editNotes)

        // Pre-populate all fields with existing values
        var selectedDate: Long = record.date
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        btnSelectDate.text = dateFormat.format(Date(selectedDate))
        editWeight.setText(String.format("%.1f", record.weightKg))
        editHeight.setText(String.format("%.1f", record.heightCm))
        record.headCircumferenceCm?.let { editHeadCircumference.setText(String.format("%.1f", it)) }
        record.muacCm?.let { editMuac.setText(String.format("%.1f", it)) }
        record.notes?.let { editNotes.setText(it) }

        btnSelectDate.setOnClickListener {
            val cal = Calendar.getInstance().apply { timeInMillis = selectedDate }
            DatePickerDialog(ctx, { _, year, month, day ->
                cal.set(year, month, day)
                selectedDate = cal.timeInMillis
                btnSelectDate.text = dateFormat.format(cal.time)
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
                .apply {
                    datePicker.maxDate = System.currentTimeMillis()
                    datePicker.minDate = child.dateOfBirth
                    show()
                }
        }

        AlertDialog.Builder(ctx)
            .setTitle(R.string.edit_measurement)
            .setView(dialogView)
            .setPositiveButton(R.string.save) { _, _ ->
                val weight = editWeight.text?.toString()?.toFloatOrNull()
                val height = editHeight.text?.toString()?.toFloatOrNull()
                val head = editHeadCircumference.text?.toString()?.toFloatOrNull()
                val muac = editMuac.text?.toString()?.toFloatOrNull()
                val notes = editNotes.text?.toString()?.takeIf { it.isNotBlank() }
                if (weight != null && height != null) {
                    viewModel?.updateGrowthRecord(
                        record.copy(
                            date = selectedDate,
                            weightKg = weight,
                            heightCm = height,
                            headCircumferenceCm = head,
                            muacCm = muac,
                            notes = notes
                        )
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

        // Head circumference with WHO normal range
        if (record.headCircumferenceCm != null) {
            val (hcMin, hcMax) = WHOGrowthStandards.getHeadCircumferenceNormalRange(ageMonths)
            message.append("${getString(R.string.head_circumference)}: ${String.format("%.1f", record.headCircumferenceCm)} cm")
            message.append("  (${getString(R.string.head_circ_normal_range, hcMin, hcMax)})\n")
        }

        // MUAC with WHO status
        if (record.muacCm != null) {
            val muacStatus = WHOGrowthStandards.interpretMuac(record.muacCm)
            val statusLabel = if (isFrench) muacStatus.descriptionFr else muacStatus.description
            message.append("${getString(R.string.muac)}: ${String.format("%.1f", record.muacCm)} cm  → $statusLabel\n")
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
            .setPositiveButton(R.string.edit) { _, _ -> showEditMeasurementDialog(record, child) }
            .setNeutralButton(android.R.string.ok, null)
            .setNegativeButton(R.string.delete) { _, _ ->
                AlertDialog.Builder(ctx)
                    .setTitle(R.string.delete)
                    .setMessage(getString(R.string.delete_child_confirm))
                    .setPositiveButton(android.R.string.ok) { _, _ -> viewModel?.deleteGrowthRecord(record) }
                    .setNegativeButton(android.R.string.cancel, null)
                    .show()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        childSpinner = null
        btnAddChild = null
        statusCard = null
        chartCard = null
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
        growthLineChart = null
        chartMetricChipGroup = null
        adapter = null
    }
}
