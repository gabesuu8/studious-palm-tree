package com.example.helloapp.fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.FavoritesActivity
import com.example.helloapp.R
import com.example.helloapp.RapidTestTimerActivity
import com.example.helloapp.adapter.VaccinationAdapter
import com.example.helloapp.data.Child
import com.example.helloapp.data.Vaccination
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.viewmodel.VaccinationViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class VaccinationFragment : Fragment() {
    
    private var viewModel: VaccinationViewModel? = null
    private var recyclerView: RecyclerView? = null
    private var emptyStateText: TextView? = null
    private var noChildrenState: View? = null
    private var childSelectorCard: View? = null
    private var progressCard: View? = null
    private var progressCount: TextView? = null
    private var progressBar: ProgressBar? = null
    private var nextDueCard: View? = null
    private var nextDueText: TextView? = null
    private var childSpinner: Spinner? = null
    private var adapter: VaccinationAdapter? = null
    
    private var chipAll: Chip? = null
    private var chipCompleted: Chip? = null
    
    private var children: List<Child> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vaccinations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        val isEmbedded = arguments?.getBoolean(VaccinationGrowthFragment.ARG_EMBEDDED, false) == true
        if (isEmbedded) toolbar.visibility = View.GONE
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
        recyclerView = view.findViewById(R.id.vaccinationsRecyclerView)
        emptyStateText = view.findViewById(R.id.emptyStateText)
        noChildrenState = view.findViewById(R.id.noChildrenState)
        childSelectorCard = view.findViewById(R.id.childSelectorCard)
        progressCard = view.findViewById(R.id.progressCard)
        progressCount = view.findViewById(R.id.progressCount)
        progressBar = view.findViewById(R.id.progressBar)
        nextDueCard = view.findViewById(R.id.nextDueCard)
        nextDueText = view.findViewById(R.id.nextDueText)
        childSpinner = view.findViewById(R.id.childSpinner)
        chipAll = view.findViewById(R.id.chipAll)
        chipCompleted = view.findViewById(R.id.chipCompleted)
        
        // Setup adapter
        adapter = VaccinationAdapter(
            onCheckChanged = { vaccination -> toggleVaccination(vaccination) },
            onItemClick = { vaccination ->
                if (vaccination.totalDoses > 1) {
                    viewModel?.toggleExpanded(vaccination.id)
                } else {
                    showVaccinationDetails(vaccination)
                }
            },
            onRecordDose = { vaccination, doseIndex -> showDatePickerForDose(vaccination, doseIndex) },
            onUndoDose = { vaccination, doseIndex -> viewModel?.undoDose(vaccination, doseIndex) }
        )
        
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = adapter
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(
            requireActivity(),
            VaccinationViewModel.Factory(requireActivity().application)
        )[VaccinationViewModel::class.java]
        
        // Setup filter chips
        setupFilterChips()
        
        // Observe children (same as Growth tracker)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.allChildren?.collect { childList ->
                children = childList
                updateChildSpinner(childList)
                
                if (childList.isEmpty()) {
                    noChildrenState?.visibility = View.VISIBLE
                    childSelectorCard?.visibility = View.GONE
                    progressCard?.visibility = View.GONE
                    nextDueCard?.visibility = View.GONE
                    view.findViewById<View>(R.id.filterChipGroup)?.visibility = View.GONE
                    recyclerView?.visibility = View.GONE
                    emptyStateText?.visibility = View.GONE
                    viewModel?.setSelectedChild(null)
                } else {
                    noChildrenState?.visibility = View.GONE
                    childSelectorCard?.visibility = View.VISIBLE
                    progressCard?.visibility = View.VISIBLE
                    view.findViewById<View>(R.id.filterChipGroup)?.visibility = View.VISIBLE
                    if (viewModel?.selectedChild?.value == null) {
                        viewModel?.setSelectedChild(childList.first())
                    }
                }
            }
        }
        
        // Observe vaccinations for selected child
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.filteredVaccinations?.collect { vaccinations ->
                if (children.isEmpty()) return@collect
                if (vaccinations.isEmpty()) {
                    recyclerView?.visibility = View.GONE
                    emptyStateText?.visibility = View.VISIBLE
                } else {
                    recyclerView?.visibility = View.VISIBLE
                    emptyStateText?.visibility = View.GONE
                    adapter?.submitVaccinations(vaccinations)
                }
            }
        }
        
        // Update progress for selected child
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.allVaccinations?.collect { vaccinations ->
                updateProgress(vaccinations)
            }
        }

        // Next vaccine due
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.nextVaccineDue?.collect { next ->
                if (next != null) {
                    nextDueCard?.visibility = View.VISIBLE
                    val dateStr = SimpleDateFormat("d MMM yyyy", Locale.getDefault()).format(Date(next.dueDateMillis))
                    nextDueText?.text = if (next.isOverdue) {
                        getString(R.string.overdue_vaccine, next.name, dateStr)
                    } else {
                        getString(R.string.next_vaccine_due_by, next.name, dateStr)
                    }
                } else {
                    nextDueCard?.visibility = View.GONE
                }
            }
        }

        // Observe expansion state
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.expandedVaccineIds?.collect { ids ->
                adapter?.expandedIds = ids
            }
        }
    }
    
    private fun updateChildSpinner(childList: List<Child>) {
        val ctx = context ?: return
        val names = childList.map { it.name }
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, names)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        childSpinner?.adapter = spinnerAdapter
        
        childSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (childList.isNotEmpty() && position in childList.indices) {
                    viewModel?.setSelectedChild(childList[position])
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        
        // Restore selection to match current selected child
        viewModel?.selectedChild?.value?.let { selected ->
            val index = childList.indexOfFirst { it.id == selected.id }
            if (index >= 0) childSpinner?.setSelection(index)
        }
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
                    // Reinitialize vaccinations in the new language
                    viewModel?.reinitializeVaccinations()
                    // Recreate activity to apply language change
                    activity?.recreate()
                } else {
                    dialog.dismiss()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
    
    private fun setupFilterChips() {
        chipAll?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) viewModel?.setFilter("all")
        }
        chipCompleted?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) viewModel?.setFilter("completed")
        }
    }
    
    private fun toggleVaccination(vaccination: Vaccination) {
        viewModel?.recordNextDose(vaccination)

        val message = if (!vaccination.isFullyCompleted) {
            getString(R.string.vaccination_marked_complete, vaccination.name)
        } else {
            getString(R.string.vaccination_marked_incomplete, vaccination.name)
        }
        recyclerView?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }
    
    private fun showVaccinationDetails(vaccination: Vaccination) {
        val ctx = context ?: return
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

        val scheduleInfo = "\n\n${getString(R.string.recommended_age)}: ${vaccination.doseSchedule.replace("|", " → ")}"
        val progressInfo = if (vaccination.totalDoses > 1) {
            "\n${getString(R.string.dose_progress, vaccination.completedDoses, vaccination.totalDoses)}"
        } else ""
        val completionInfo = if (vaccination.isFullyCompleted && vaccination.lastDoseDate != null) {
            "\n\n${getString(R.string.completed_on)}: ${dateFormat.format(Date(vaccination.lastDoseDate))}"
        } else ""

        val dialog = AlertDialog.Builder(ctx)
            .setTitle(vaccination.name)
            .setMessage("${vaccination.description}$scheduleInfo$progressInfo$completionInfo")

        if (!vaccination.isFullyCompleted) {
            dialog.setPositiveButton(getString(R.string.record_next_dose)) { _, _ ->
                showDatePicker(vaccination)
            }
        } else {
            dialog.setPositiveButton(getString(R.string.undo_last_dose)) { _, _ ->
                viewModel?.undoLastDose(vaccination)
            }
        }

        dialog.setNegativeButton(android.R.string.cancel, null)
        dialog.show()
    }
    
    private fun showDatePickerForDose(vaccination: Vaccination, doseIndex: Int) {
        val ctx = context ?: return
        val calendar = Calendar.getInstance()

        DatePickerDialog(
            ctx,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                viewModel?.recordDoseWithDate(vaccination, doseIndex, calendar.timeInMillis)
                recyclerView?.let {
                    Snackbar.make(
                        it,
                        getString(R.string.vaccination_marked_complete, vaccination.name),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
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

    private fun showDatePicker(vaccination: Vaccination) {
        val nextIndex = vaccination.parsedDoseDates.indexOfFirst { it == null }
        if (nextIndex >= 0) {
            showDatePickerForDose(vaccination, nextIndex)
        }
    }
    
    private fun updateProgress(vaccinations: List<Vaccination>) {
        val completed = vaccinations.sumOf { it.completedDoses }
        val total = vaccinations.sumOf { it.totalDoses }

        progressCount?.text = "$completed/$total"

        if (total > 0) {
            val percentage = (completed * 100) / total
            progressBar?.progress = percentage
        } else {
            progressBar?.progress = 0
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView = null
        emptyStateText = null
        noChildrenState = null
        childSelectorCard = null
        progressCard = null
        progressCount = null
        progressBar = null
        nextDueCard = null
        nextDueText = null
        childSpinner = null
        chipAll = null
        chipCompleted = null
        adapter = null
    }
}
