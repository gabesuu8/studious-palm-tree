package com.example.helloapp.fragment

import android.app.DatePickerDialog
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

class PregnancyTrackerFragment : Fragment() {

    private var viewModel: PregnancyViewModel? = null
    
    private var pregnancySpinner: Spinner? = null
    private var btnAddPregnancy: Button? = null
    private var progressCard: View? = null
    private var dangerSignsCard: View? = null
    private var visitsCard: View? = null
    private var emptyState: View? = null
    
    private var txtMotherName: TextView? = null
    private var txtCurrentWeek: TextView? = null
    private var txtTrimester: TextView? = null
    private var txtDaysRemaining: TextView? = null
    private var txtDueDate: TextView? = null
    private var progressBar: ProgressBar? = null
    private var txtRecommendedNextVisit: TextView? = null
    private var btnAddVisit: Button? = null
    private var btnViewDangerSigns: Button? = null
    private var visitsRecyclerView: RecyclerView? = null
    private var txtNoVisits: TextView? = null
    
    private var adapter: PrenatalVisitAdapter? = null
    private var pregnancies: List<Pregnancy> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pregnancy_tracker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Setup toolbar menu click listener (menu is defined in XML)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
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
        pregnancySpinner = view.findViewById(R.id.pregnancySpinner)
        btnAddPregnancy = view.findViewById(R.id.btnAddPregnancy)
        progressCard = view.findViewById(R.id.progressCard)
        dangerSignsCard = view.findViewById(R.id.dangerSignsCard)
        visitsCard = view.findViewById(R.id.visitsCard)
        emptyState = view.findViewById(R.id.emptyState)
        
        txtMotherName = view.findViewById(R.id.txtMotherName)
        txtCurrentWeek = view.findViewById(R.id.txtCurrentWeek)
        txtTrimester = view.findViewById(R.id.txtTrimester)
        txtDaysRemaining = view.findViewById(R.id.txtDaysRemaining)
        txtDueDate = view.findViewById(R.id.txtDueDate)
        progressBar = view.findViewById(R.id.progressBar)
        txtRecommendedNextVisit = view.findViewById(R.id.txtRecommendedNextVisit)
        btnAddVisit = view.findViewById(R.id.btnAddVisit)
        btnViewDangerSigns = view.findViewById(R.id.btnViewDangerSigns)
        visitsRecyclerView = view.findViewById(R.id.visitsRecyclerView)
        txtNoVisits = view.findViewById(R.id.txtNoVisits)
        
        visitsRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(
            requireActivity(),
            PregnancyViewModel.Factory(requireActivity().application)
        )[PregnancyViewModel::class.java]
        
        // Setup listeners
        btnAddPregnancy?.setOnClickListener { showAddPregnancyDialog() }
        btnAddVisit?.setOnClickListener { showAddVisitDialog() }
        btnViewDangerSigns?.setOnClickListener { showDangerSignsDialog() }
        
        pregnancySpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (pregnancies.isNotEmpty() && position < pregnancies.size) {
                    viewModel?.selectPregnancy(pregnancies[position])
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel?.selectPregnancy(null)
            }
        }
        
        // Observe data
        observeData()
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
            viewModel?.activePregnancies?.collect { pregnancyList ->
                pregnancies = pregnancyList
                updatePregnancySpinner(pregnancyList)
                
                if (pregnancyList.isEmpty()) {
                    emptyState?.visibility = View.VISIBLE
                    progressCard?.visibility = View.GONE
                    dangerSignsCard?.visibility = View.GONE
                    visitsCard?.visibility = View.GONE
                } else {
                    emptyState?.visibility = View.GONE
                }
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            val vm = viewModel ?: return@launch
            combine(
                vm.selectedPregnancy,
                vm.prenatalVisits
            ) { pregnancy, visits ->
                Pair(pregnancy, visits)
            }.collect { (pregnancy, visits) ->
                if (pregnancy != null) {
                    progressCard?.visibility = View.VISIBLE
                    dangerSignsCard?.visibility = View.VISIBLE
                    visitsCard?.visibility = View.VISIBLE
                    
                    updatePregnancyDisplay(pregnancy)
                    
                    adapter = PrenatalVisitAdapter { visit ->
                        showVisitDetails(visit)
                    }
                    visitsRecyclerView?.adapter = adapter
                    adapter?.submitList(visits)
                    
                    if (visits.isEmpty()) {
                        txtNoVisits?.visibility = View.VISIBLE
                        visitsRecyclerView?.visibility = View.GONE
                    } else {
                        txtNoVisits?.visibility = View.GONE
                        visitsRecyclerView?.visibility = View.VISIBLE
                    }
                } else {
                    progressCard?.visibility = View.GONE
                    dangerSignsCard?.visibility = View.GONE
                    visitsCard?.visibility = View.GONE
                }
            }
        }
    }
    
    private fun updatePregnancySpinner(pregnancies: List<Pregnancy>) {
        val ctx = context ?: return
        val names = pregnancies.map { it.motherName }
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, names)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pregnancySpinner?.adapter = spinnerAdapter
    }
    
    private fun updatePregnancyDisplay(pregnancy: Pregnancy) {
        val vm = viewModel ?: return
        txtMotherName?.text = pregnancy.motherName
        
        val currentWeek = vm.getCurrentWeek(pregnancy)
        val trimester = vm.getTrimester(currentWeek)
        val daysRemaining = vm.getDaysRemaining(pregnancy)
        
        txtCurrentWeek?.text = currentWeek.toString()
        txtTrimester?.text = trimester.toString()
        txtDaysRemaining?.text = daysRemaining.toString()
        
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        txtDueDate?.text = getString(R.string.expected_due_date, dateFormat.format(Date(pregnancy.expectedDueDate)))
        
        val nextVisitWeek = vm.getRecommendedNextVisitWeek(currentWeek)
        if (nextVisitWeek != null) {
            txtRecommendedNextVisit?.visibility = View.VISIBLE
            txtRecommendedNextVisit?.text = getString(R.string.recommended_next_visit_week, nextVisitWeek)
        } else {
            txtRecommendedNextVisit?.visibility = View.GONE
        }
        
        progressBar?.progress = currentWeek.coerceIn(0, 40)
    }
    
    private fun showAddPregnancyDialog() {
        val ctx = context ?: return
        val dialogView = LayoutInflater.from(ctx).inflate(R.layout.dialog_add_pregnancy, null)
        val editName = dialogView.findViewById<TextInputEditText>(R.id.editMotherName)
        val btnSelectLMP = dialogView.findViewById<Button>(R.id.btnSelectLMP)
        val editNotes = dialogView.findViewById<TextInputEditText>(R.id.editNotes)
        
        var selectedLMP: Long? = null
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        
        btnSelectLMP.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                ctx,
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
        
        AlertDialog.Builder(ctx)
            .setTitle(R.string.add_pregnancy)
            .setView(dialogView)
            .setPositiveButton(R.string.save) { _, _ ->
                val name = editName.text?.toString()?.trim()
                val lmp = selectedLMP
                val notes = editNotes.text?.toString()?.trim()
                
                if (!name.isNullOrEmpty() && lmp != null) {
                    viewModel?.addPregnancy(name, lmp, notes?.takeIf { it.isNotBlank() })
                } else {
                    Toast.makeText(ctx, R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
    
    private fun showAddVisitDialog() {
        val ctx = context ?: return
        val pregnancy = viewModel?.selectedPregnancy?.value ?: return
        
        val dialogView = LayoutInflater.from(ctx).inflate(R.layout.dialog_add_visit, null)
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
                show()
            }
        }
        
        btnSelectNext.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                ctx,
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
        
        AlertDialog.Builder(ctx)
            .setTitle(R.string.add_prenatal_visit)
            .setView(dialogView)
            .setPositiveButton(R.string.save) { _, _ ->
                val weight = editWeight.text?.toString()?.toFloatOrNull()
                val bp = editBP.text?.toString()?.trim()
                val fhr = editFHR.text?.toString()?.toIntOrNull()
                val notes = editNotes.text?.toString()?.trim()
                
                val weekOfPregnancy = Pregnancy.calculateCurrentWeek(pregnancy.lastMenstrualPeriod)
                
                viewModel?.addPrenatalVisit(
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
        val ctx = context ?: return
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
        
        AlertDialog.Builder(ctx)
            .setTitle(R.string.visit_details)
            .setMessage(message.toString())
            .setPositiveButton(android.R.string.ok, null)
            .setNegativeButton(R.string.delete) { _, _ ->
                viewModel?.deleteVisit(visit)
            }
            .show()
    }
    
    private fun showDangerSignsDialog() {
        val ctx = context ?: return
        val dangerSigns = viewModel?.getDangerSigns() ?: return
        val isFrench = LanguageHelper.getLanguage(ctx) == LanguageHelper.FRENCH
        
        val message = StringBuilder()
        message.append(getString(R.string.seek_immediate_care))
        message.append("\n\n")
        
        dangerSigns.forEach { sign ->
            val name = if (isFrench) sign.nameFr else sign.name
            val description = if (isFrench) sign.descriptionFr else sign.description
            message.append("• $name\n")
            message.append("  $description\n\n")
        }
        
        AlertDialog.Builder(ctx)
            .setTitle(R.string.danger_signs_title)
            .setMessage(message.toString())
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        pregnancySpinner = null
        btnAddPregnancy = null
        progressCard = null
        dangerSignsCard = null
        visitsCard = null
        emptyState = null
        txtMotherName = null
        txtCurrentWeek = null
        txtTrimester = null
        txtDaysRemaining = null
        txtDueDate = null
        progressBar = null
        btnAddVisit = null
        btnViewDangerSigns = null
        visitsRecyclerView = null
        txtNoVisits = null
        adapter = null
    }
}
