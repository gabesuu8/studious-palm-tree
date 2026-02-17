package com.example.helloapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.R
import com.example.helloapp.data.Child
import com.example.helloapp.data.GrowthRecord
import com.example.helloapp.util.WHOGrowthStandards
import java.text.SimpleDateFormat
import java.util.*

class GrowthRecordAdapter(
    private val child: Child,
    private val onItemClick: (GrowthRecord) -> Unit
) : ListAdapter<GrowthRecord, GrowthRecordAdapter.GrowthRecordViewHolder>(GrowthRecordDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrowthRecordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_growth_record, parent, false)
        return GrowthRecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: GrowthRecordViewHolder, position: Int) {
        holder.bind(getItem(position), child, onItemClick)
    }

    class GrowthRecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtDate: TextView = itemView.findViewById(R.id.txtDate)
        private val txtAge: TextView = itemView.findViewById(R.id.txtAge)
        private val txtRecordWeight: TextView = itemView.findViewById(R.id.txtRecordWeight)
        private val txtRecordHeight: TextView = itemView.findViewById(R.id.txtRecordHeight)
        private val txtRecordStatus: TextView = itemView.findViewById(R.id.txtRecordStatus)

        fun bind(record: GrowthRecord, child: Child, onItemClick: (GrowthRecord) -> Unit) {
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            txtDate.text = dateFormat.format(Date(record.date))
            
            val ageMonths = WHOGrowthStandards.calculateAgeMonths(child.dateOfBirth, record.date)
            val ageText = if (ageMonths < 12) {
                itemView.context.getString(R.string.age_months, ageMonths)
            } else {
                val years = ageMonths / 12
                val months = ageMonths % 12
                if (months > 0) {
                    itemView.context.getString(R.string.age_years_months, years, months)
                } else {
                    itemView.context.getString(R.string.age_years, years)
                }
            }
            txtAge.text = ageText
            
            txtRecordWeight.text = String.format("%.1f kg", record.weightKg)
            txtRecordHeight.text = String.format("%.1f cm", record.heightCm)
            
            // Calculate and display status
            val assessment = WHOGrowthStandards.assessGrowth(
                weightKg = record.weightKg,
                heightCm = record.heightCm,
                birthDate = child.dateOfBirth,
                measurementDate = record.date,
                isMale = child.gender == "male"
            )
            
            txtRecordStatus.text = assessment.weightStatus.description
            txtRecordStatus.background.setTint(Color.parseColor(assessment.weightStatus.colorCode))
            
            itemView.setOnClickListener { onItemClick(record) }
        }
    }

    class GrowthRecordDiffCallback : DiffUtil.ItemCallback<GrowthRecord>() {
        override fun areItemsTheSame(oldItem: GrowthRecord, newItem: GrowthRecord): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GrowthRecord, newItem: GrowthRecord): Boolean {
            return oldItem == newItem
        }
    }
}
