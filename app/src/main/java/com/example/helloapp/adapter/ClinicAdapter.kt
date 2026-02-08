package com.example.helloapp.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.R
import com.example.helloapp.viewmodel.ClinicWithDistance

class ClinicAdapter(
    private val onClinicClick: (ClinicWithDistance) -> Unit
) : ListAdapter<ClinicWithDistance, ClinicAdapter.ClinicViewHolder>(ClinicDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClinicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_clinic, parent, false)
        return ClinicViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClinicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ClinicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconType: ImageView = itemView.findViewById(R.id.iconType)
        private val txtName: TextView = itemView.findViewById(R.id.txtClinicName)
        private val txtAddress: TextView = itemView.findViewById(R.id.txtAddress)
        private val txtDistance: TextView = itemView.findViewById(R.id.txtDistance)
        private val txtServices: TextView = itemView.findViewById(R.id.txtServices)
        private val txtHours: TextView = itemView.findViewById(R.id.txtHours)
        private val emergencyBadge: View = itemView.findViewById(R.id.emergencyBadge)
        private val btnCall: Button = itemView.findViewById(R.id.btnCall)
        private val btnDirections: Button = itemView.findViewById(R.id.btnDirections)

        fun bind(clinicWithDistance: ClinicWithDistance) {
            val clinic = clinicWithDistance.clinic
            val context = itemView.context
            
            txtName.text = clinic.name
            txtAddress.text = "${clinic.address}, ${clinic.city}"
            
            // Distance
            val distance = clinicWithDistance.getFormattedDistance()
            if (distance.isNotEmpty()) {
                txtDistance.text = distance
                txtDistance.visibility = View.VISIBLE
            } else {
                txtDistance.visibility = View.GONE
            }
            
            // Services
            if (!clinic.services.isNullOrBlank()) {
                txtServices.text = clinic.services
                txtServices.visibility = View.VISIBLE
            } else {
                txtServices.visibility = View.GONE
            }
            
            // Hours
            if (!clinic.openingHours.isNullOrBlank()) {
                txtHours.text = clinic.openingHours
                txtHours.visibility = View.VISIBLE
            } else {
                txtHours.visibility = View.GONE
            }
            
            // Icon type
            iconType.setImageResource(
                if (clinic.isHospital) R.drawable.ic_hospital else R.drawable.ic_clinic
            )
            
            // Emergency badge
            emergencyBadge.visibility = if (clinic.isEmergency) View.VISIBLE else View.GONE
            
            // Call button
            if (!clinic.phone.isNullOrBlank()) {
                btnCall.visibility = View.VISIBLE
                btnCall.setOnClickListener {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:${clinic.phone}")
                    }
                    context.startActivity(intent)
                }
            } else {
                btnCall.visibility = View.GONE
            }
            
            // Directions button - opens Google Maps
            btnDirections.setOnClickListener {
                val uri = Uri.parse("geo:${clinic.latitude},${clinic.longitude}?q=${clinic.latitude},${clinic.longitude}(${Uri.encode(clinic.name)})")
                val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                    setPackage("com.google.android.apps.maps")
                }
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                } else {
                    // Fallback to browser
                    val browserUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${clinic.latitude},${clinic.longitude}")
                    context.startActivity(Intent(Intent.ACTION_VIEW, browserUri))
                }
            }
            
            itemView.setOnClickListener { onClinicClick(clinicWithDistance) }
        }
    }

    class ClinicDiffCallback : DiffUtil.ItemCallback<ClinicWithDistance>() {
        override fun areItemsTheSame(oldItem: ClinicWithDistance, newItem: ClinicWithDistance): Boolean {
            return oldItem.clinic.id == newItem.clinic.id
        }

        override fun areContentsTheSame(oldItem: ClinicWithDistance, newItem: ClinicWithDistance): Boolean {
            return oldItem == newItem
        }
    }
}
