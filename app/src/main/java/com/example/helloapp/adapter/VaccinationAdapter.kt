package com.example.helloapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.R
import com.example.helloapp.data.Vaccination
import java.text.SimpleDateFormat
import java.util.*

class VaccinationAdapter(
    private val onCheckChanged: (Vaccination) -> Unit,
    private val onItemClick: (Vaccination) -> Unit
) : ListAdapter<VaccinationAdapter.ListItem, RecyclerView.ViewHolder>(VaccinationDiffCallback()) {
    
    sealed class ListItem {
        data class Header(val title: String) : ListItem()
        data class VaccinationItem(val vaccination: Vaccination) : ListItem()
    }
    
    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_VACCINATION = 1
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
                VaccinationViewHolder(view, onCheckChanged, onItemClick)
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is ListItem.Header -> (holder as HeaderViewHolder).bind(item.title)
            is ListItem.VaccinationItem -> (holder as VaccinationViewHolder).bind(item.vaccination)
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
        private val onItemClick: (Vaccination) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val checkbox: CheckBox = itemView.findViewById(R.id.vaccinationCheckbox)
        private val nameText: TextView = itemView.findViewById(R.id.vaccinationName)
        private val descText: TextView = itemView.findViewById(R.id.vaccinationDescription)
        private val ageText: TextView = itemView.findViewById(R.id.recommendedAge)
        private val dateText: TextView = itemView.findViewById(R.id.completionDate)
        
        private var currentVaccination: Vaccination? = null
        
        init {
            checkbox.setOnClickListener {
                currentVaccination?.let { onCheckChanged(it) }
            }
            itemView.setOnClickListener {
                currentVaccination?.let { onItemClick(it) }
            }
        }
        
        fun bind(vaccination: Vaccination) {
            currentVaccination = vaccination
            nameText.text = vaccination.name
            descText.text = vaccination.description
            ageText.text = vaccination.recommendedAge
            
            // Set checkbox without triggering listener
            checkbox.setOnCheckedChangeListener(null)
            checkbox.isChecked = vaccination.isCompleted
            checkbox.setOnCheckedChangeListener { _, _ ->
                currentVaccination?.let { onCheckChanged(it) }
            }
            
            // Show completion date if completed
            if (vaccination.isCompleted && vaccination.dateCompleted != null) {
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                dateText.text = "✓ ${dateFormat.format(Date(vaccination.dateCompleted))}"
                dateText.visibility = View.VISIBLE
                nameText.alpha = 0.7f
                descText.alpha = 0.7f
            } else {
                dateText.visibility = View.GONE
                nameText.alpha = 1.0f
                descText.alpha = 1.0f
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
