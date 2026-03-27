package com.example.helloapp.fragment

import android.app.DatePickerDialog
import android.content.Intent
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
import com.example.helloapp.FavoritesActivity
import com.example.helloapp.R
import com.example.helloapp.RapidTestTimerActivity
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
    private var currentChildId: Long? = null
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
                R.id.menu_options -> { showOptionsDialog(); true }
                else -> false
            }
        }

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



    // ── Data observation ──────────────────────────────────────────────────────

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
            combine(vm.selectedChild, vm.growthRecords) { child, records ->
                Pair(child, records)
            }.collect { (child, records) ->
                btnDeleteChild?.visibility = if (child != null) View.VISIBLE else View.GONE
                if (child != null) {
                    statusCard?.visibility = View.VISIBLE
                    historyCard?.visibility = View.VISIBLE

                    if (child.id != currentChildId) {
                        currentChildId = child.id
                        adapter = GrowthRecordAdapter(child) { record ->
                            showRecordDetails(record, child)
                        }
                        recordsRecyclerView?.adapter = adapter
                    }
                    adapter?.submitList(records)

                    if (records.isNotEmpty()) {
                        updateStatusDisplay(records.first(), child)
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

        val dialogView = LayoutInflater.from(ctx).inflate(R.layout.dialog_record_details, null)

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

        AlertDialog.Builder(ctx)
            .setTitle(R.string.measurement_details)
            .setView(dialogView)
            .setPositiveButton(R.string.edit) { _, _ -> showEditMeasurementDialog(record, child) }
            .setNeutralButton(android.R.string.ok, null)
            .setNegativeButton(R.string.delete) { _, _ ->
                AlertDialog.Builder(ctx)
                    .setTitle(R.string.delete)
                    .setMessage(getString(R.string.delete_measurement_confirm))
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
        currentChildId = null
    }
}
