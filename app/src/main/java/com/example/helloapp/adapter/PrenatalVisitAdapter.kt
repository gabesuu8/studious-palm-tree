package com.example.helloapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.R
import com.example.helloapp.data.PrenatalVisit
import java.text.SimpleDateFormat
import java.util.*

class PrenatalVisitAdapter(
    private val onItemClick: (PrenatalVisit) -> Unit
) : ListAdapter<PrenatalVisit, PrenatalVisitAdapter.VisitViewHolder>(VisitDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prenatal_visit, parent, false)
        return VisitViewHolder(view)
    }

    override fun onBindViewHolder(holder: VisitViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    class VisitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtVisitDate: TextView = itemView.findViewById(R.id.txtVisitDate)
        private val txtVisitWeek: TextView = itemView.findViewById(R.id.txtVisitWeek)
        private val txtVisitWeight: TextView = itemView.findViewById(R.id.txtVisitWeight)
        private val txtVisitBP: TextView = itemView.findViewById(R.id.txtVisitBP)
        private val txtVisitNotes: TextView = itemView.findViewById(R.id.txtVisitNotes)

        fun bind(visit: PrenatalVisit, onItemClick: (PrenatalVisit) -> Unit) {
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            txtVisitDate.text = dateFormat.format(Date(visit.visitDate))
            txtVisitWeek.text = itemView.context.getString(R.string.week_number, visit.weekOfPregnancy)
            
            if (visit.weightKg != null) {
                txtVisitWeight.text = String.format("%.1f kg", visit.weightKg)
                txtVisitWeight.visibility = View.VISIBLE
            } else {
                txtVisitWeight.visibility = View.GONE
            }
            
            if (!visit.bloodPressure.isNullOrBlank()) {
                txtVisitBP.text = "BP: ${visit.bloodPressure}"
                txtVisitBP.visibility = View.VISIBLE
            } else {
                txtVisitBP.visibility = View.GONE
            }
            
            if (!visit.notes.isNullOrBlank()) {
                txtVisitNotes.text = visit.notes
                txtVisitNotes.visibility = View.VISIBLE
            } else {
                txtVisitNotes.visibility = View.GONE
            }
            
            itemView.setOnClickListener { onItemClick(visit) }
        }
    }

    class VisitDiffCallback : DiffUtil.ItemCallback<PrenatalVisit>() {
        override fun areItemsTheSame(oldItem: PrenatalVisit, newItem: PrenatalVisit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PrenatalVisit, newItem: PrenatalVisit): Boolean {
            return oldItem == newItem
        }
    }
}
