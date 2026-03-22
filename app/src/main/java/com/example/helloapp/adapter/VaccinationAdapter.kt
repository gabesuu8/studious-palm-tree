package com.example.helloapp.adapter

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.R
import com.example.helloapp.data.Vaccination
import java.text.SimpleDateFormat
import java.util.*

class VaccinationAdapter(
    private val onCheckChanged: (Vaccination) -> Unit,
    private val onItemClick: (Vaccination) -> Unit,
    private val onInfoClick: (Vaccination) -> Unit,
    private val onRecordDose: (Vaccination, Int) -> Unit,
    private val onUndoDose: (Vaccination, Int) -> Unit
) : ListAdapter<VaccinationAdapter.ListItem, RecyclerView.ViewHolder>(VaccinationDiffCallback()) {

    sealed class ListItem {
        data class Header(val title: String) : ListItem()
        data class VaccinationItem(val vaccination: Vaccination) : ListItem()
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_VACCINATION = 1
        private val DATE_FORMAT = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    }

    var expandedIds: Set<Int> = emptySet()
        set(value) {
            val old = field
            field = value
            // Notify changed items for expansion toggle
            for (i in 0 until itemCount) {
                val item = getItem(i)
                if (item is ListItem.VaccinationItem) {
                    val id = item.vaccination.id
                    if (old.contains(id) != value.contains(id)) {
                        notifyItemChanged(i)
                    }
                }
            }
        }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ListItem.Header -> VIEW_TYPE_HEADER
            is ListItem.VaccinationItem -> VIEW_TYPE_VACCINATION
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_vaccination_header, parent, false)
                HeaderViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_vaccination, parent, false)
                VaccinationViewHolder(view, onCheckChanged, onItemClick, onInfoClick, onRecordDose, onUndoDose)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is ListItem.Header -> (holder as HeaderViewHolder).bind(item.title)
            is ListItem.VaccinationItem -> {
                val isExpanded = expandedIds.contains(item.vaccination.id)
                (holder as VaccinationViewHolder).bind(item.vaccination, isExpanded)
            }
        }
    }

    fun submitVaccinations(vaccinations: List<Vaccination>) {
        val items = mutableListOf<ListItem>()
        val grouped = vaccinations.groupBy { it.category }

        // Define the order of categories
        val categoryOrder = listOf(
            "Birth", "Naissance",
            "6 Weeks", "6 Semaines",
            "10 Weeks", "10 Semaines",
            "14 Weeks", "14 Semaines",
            "6 Months", "6 Mois",
            "9 Months", "9 Mois",
            "12 Months", "12 Mois",
            "15-18 Months", "15-18 Mois",
            "Special Vaccines", "Vaccins Spéciaux"
        )

        // Sort categories by the defined order
        val sortedCategories = grouped.keys.sortedBy { category ->
            val index = categoryOrder.indexOf(category)
            if (index == -1) Int.MAX_VALUE else index
        }

        for (category in sortedCategories) {
            items.add(ListItem.Header(category))
            grouped[category]?.forEach { vaccination ->
                items.add(ListItem.VaccinationItem(vaccination))
            }
        }

        submitList(items)
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerText: TextView = itemView.findViewById(R.id.headerText)
        private val headerIcon: ImageView = itemView.findViewById(R.id.headerIcon)

        fun bind(title: String) {
            headerText.text = title
            // Set icon based on category
            val iconRes = when {
                title.contains("Birth") || title.contains("Naissance") -> R.drawable.ic_baby
                title.contains("Weeks") || title.contains("Semaines") -> R.drawable.ic_calendar
                title.contains("Months") || title.contains("Mois") -> R.drawable.ic_calendar
                title.contains("Special") || title.contains("Spéciaux") -> R.drawable.ic_star_filled
                else -> R.drawable.ic_calendar
            }
            headerIcon.setImageResource(iconRes)
        }
    }

    class VaccinationViewHolder(
        itemView: View,
        private val onCheckChanged: (Vaccination) -> Unit,
        private val onItemClick: (Vaccination) -> Unit,
        private val onInfoClick: (Vaccination) -> Unit,
        private val onRecordDose: (Vaccination, Int) -> Unit,
        private val onUndoDose: (Vaccination, Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val checkbox: CheckBox = itemView.findViewById(R.id.vaccinationCheckbox)
        private val nameText: TextView = itemView.findViewById(R.id.vaccinationName)
        private val descText: TextView = itemView.findViewById(R.id.vaccinationDescription)
        private val ageText: TextView = itemView.findViewById(R.id.recommendedAge)
        private val dateText: TextView = itemView.findViewById(R.id.completionDate)
        private val expandIndicator: TextView = itemView.findViewById(R.id.expandIndicator)
        private val doseListContainer: LinearLayout = itemView.findViewById(R.id.doseListContainer)
        private val infoArrow: ImageView = itemView.findViewById(R.id.infoArrow)

        private var currentVaccination: Vaccination? = null

        init {
            checkbox.setOnClickListener {
                currentVaccination?.let { onCheckChanged(it) }
            }
            itemView.setOnClickListener {
                currentVaccination?.let { onItemClick(it) }
            }
            infoArrow.setOnClickListener {
                currentVaccination?.let { onInfoClick(it) }
            }
        }

        fun bind(vaccination: Vaccination, isExpanded: Boolean) {
            currentVaccination = vaccination
            nameText.text = vaccination.name
            descText.text = vaccination.description

            // Show next dose age or "Complete"
            val nextAge = vaccination.nextDoseAge
            ageText.text = nextAge ?: itemView.context.getString(R.string.completed_on).substringBefore(" ")

            // Set checkbox without triggering listener
            checkbox.setOnCheckedChangeListener(null)
            checkbox.isChecked = vaccination.isFullyCompleted
            checkbox.setOnCheckedChangeListener { _, _ ->
                currentVaccination?.let { onCheckChanged(it) }
            }

            // Show dose progress for multi-dose vaccines, or completion date for single-dose
            if (vaccination.totalDoses > 1) {
                dateText.text = "${vaccination.completedDoses}/${vaccination.totalDoses}"
                dateText.visibility = View.VISIBLE
                // Show expand indicator
                expandIndicator.text = if (isExpanded) "▲" else "▼"
                expandIndicator.visibility = View.VISIBLE
            } else if (vaccination.isFullyCompleted && vaccination.lastDoseDate != null) {
                dateText.text = "✓ ${DATE_FORMAT.format(Date(vaccination.lastDoseDate))}"
                dateText.visibility = View.VISIBLE
                expandIndicator.visibility = View.GONE
            } else {
                dateText.visibility = View.GONE
                expandIndicator.visibility = View.GONE
            }

            if (vaccination.isFullyCompleted) {
                nameText.alpha = 0.7f
                descText.alpha = 0.7f
            } else {
                nameText.alpha = 1.0f
                descText.alpha = 1.0f
            }

            // Handle expansion for multi-dose vaccines
            if (vaccination.totalDoses > 1 && isExpanded) {
                doseListContainer.visibility = View.VISIBLE
                populateDoseRows(vaccination)
            } else {
                doseListContainer.visibility = View.GONE
            }
        }

        private fun populateDoseRows(vaccination: Vaccination) {
            val context = itemView.context
            val inflater = LayoutInflater.from(context)
            val needed = vaccination.totalDoses
            val existing = doseListContainer.childCount

            // Remove extra views
            if (existing > needed) {
                doseListContainer.removeViews(needed, existing - needed)
            }
            // Add missing views
            for (i in existing until needed) {
                inflater.inflate(R.layout.item_dose_row, doseListContainer, true)
            }

            val doseDates = vaccination.parsedDoseDates
            val scheduleAges = vaccination.doseSchedule.split("|")

            for (i in 0 until needed) {
                val row = doseListContainer.getChildAt(i)
                val dot = row.findViewById<View>(R.id.doseStatusDot)
                val label = row.findViewById<TextView>(R.id.doseLabel)
                val action = row.findViewById<TextView>(R.id.doseAction)

                val scheduledAge = scheduleAges.getOrNull(i)?.trim() ?: ""
                val doseDate = doseDates.getOrNull(i)
                val isCompleted = doseDate != null

                // Status dot color
                val bg = dot.background
                if (bg is GradientDrawable) {
                    bg.setColor(
                        if (isCompleted) 0xFF4CAF50.toInt() else 0xFFBDBDBD.toInt()
                    )
                }

                // Label
                label.text = context.getString(R.string.dose_number_with_schedule, i + 1, scheduledAge)

                // Action text
                if (isCompleted) {
                    action.text = DATE_FORMAT.format(Date(doseDate!!))
                    action.setTextColor(0xFF4CAF50.toInt())
                    action.setOnClickListener {
                        currentVaccination?.let { v -> onUndoDose(v, i) }
                    }
                } else {
                    action.text = context.getString(R.string.dose_record)
                    action.setTextColor(ContextCompat.getColor(context, R.color.purple_700))
                    action.setOnClickListener {
                        currentVaccination?.let { v -> onRecordDose(v, i) }
                    }
                }
            }
        }
    }

    class VaccinationDiffCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return when {
                oldItem is ListItem.Header && newItem is ListItem.Header ->
                    oldItem.title == newItem.title
                oldItem is ListItem.VaccinationItem && newItem is ListItem.VaccinationItem ->
                    oldItem.vaccination.id == newItem.vaccination.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }
}
