package com.example.helloapp.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.R
import com.example.helloapp.data.Article
import com.example.helloapp.viewmodel.ArticleViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

data class SymptomChipColors(val fillColor: Int, val accentColor: Int, val checkedColor: Int)

class SymptomResultAdapter(
    private val onReadArticle: (Article) -> Unit
) : RecyclerView.Adapter<SymptomResultAdapter.ViewHolder>() {

    private var results: List<ArticleViewModel.SymptomMatchResult> = emptyList()
    private var symptomColorMap: Map<String, SymptomChipColors> = emptyMap()

    fun setSymptomColorMap(map: Map<String, SymptomChipColors>) {
        symptomColorMap = map
    }

    fun submitList(newResults: List<ArticleViewModel.SymptomMatchResult>) {
        results = newResults
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_symptom_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount() = results.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val urgencyLabel: TextView = itemView.findViewById(R.id.urgencyLabel)
        private val urgencyDot: View = itemView.findViewById(R.id.urgencyDot)
        private val confidenceChip: Chip = itemView.findViewById(R.id.confidenceChip)
        private val conditionTitle: TextView = itemView.findViewById(R.id.conditionTitle)
        private val matchedSymptomsChips: ChipGroup = itemView.findViewById(R.id.matchedSymptomsChips)
        private val differentiatingTipLayout: View = itemView.findViewById(R.id.differentiatingTipLayout)
        private val differentiatingChips: ChipGroup = itemView.findViewById(R.id.differentiatingChips)
        private val readArticleButton: MaterialButton = itemView.findViewById(R.id.readArticleButton)

        fun bind(result: ArticleViewModel.SymptomMatchResult) {
            val ctx = itemView.context

            // Urgency row
            val (urgencyText, urgencyColorRes) = when (result.urgency) {
                ArticleViewModel.ConditionUrgency.EMERGENCY ->
                    ctx.getString(R.string.urgency_emergency) to R.color.urgency_emergency
                ArticleViewModel.ConditionUrgency.SEE_DOCTOR ->
                    ctx.getString(R.string.urgency_see_doctor) to R.color.urgency_see_doctor
                ArticleViewModel.ConditionUrgency.HOME_CARE ->
                    ctx.getString(R.string.urgency_home_care) to R.color.urgency_home_care
                else -> ctx.getString(R.string.urgency_home_care) to R.color.urgency_home_care
            }
            val urgencyColor = ContextCompat.getColor(ctx, urgencyColorRes)
            urgencyLabel.text = urgencyText
            urgencyLabel.setTextColor(urgencyColor)
            urgencyDot.backgroundTintList = ColorStateList.valueOf(urgencyColor)

            // Confidence chip
            val (confText, confColorRes) = when (result.confidence) {
                ArticleViewModel.MatchConfidence.HIGH ->
                    ctx.getString(R.string.confidence_high) to R.color.confidence_high
                ArticleViewModel.MatchConfidence.MODERATE ->
                    ctx.getString(R.string.confidence_moderate) to R.color.confidence_moderate
                ArticleViewModel.MatchConfidence.LOW ->
                    ctx.getString(R.string.confidence_low) to R.color.confidence_low
                else -> ctx.getString(R.string.confidence_low) to R.color.confidence_low
            }
            val confColor = ContextCompat.getColor(ctx, confColorRes)
            confidenceChip.text = confText
            confidenceChip.setTextColor(confColor)
            confidenceChip.chipBackgroundColor = ColorStateList.valueOf(
                Color.argb(30, Color.red(confColor), Color.green(confColor), Color.blue(confColor))
            )
            confidenceChip.chipStrokeColor = ColorStateList.valueOf(confColor)
            confidenceChip.chipStrokeWidth = 1f

            // Title
            conditionTitle.text = result.article.title

            // Matched symptom chips (up to 4) — styled to match the selected symptom bubbles
            matchedSymptomsChips.removeAllViews()
            val density = ctx.resources.displayMetrics.density
            val defaultColors = SymptomChipColors(0xFFB2DFDB.toInt(), 0xFF00695C.toInt(), 0xFF00897B.toInt())
            val radius = 20f * density

            result.matchedSymptoms.take(4).forEach { symptom ->
                val colors = symptomColorMap[symptom] ?: defaultColors
                val chip = Chip(ctx).apply {
                    text = symptom.replaceFirstChar { it.uppercase() }
                    isClickable = false
                    isFocusable = false
                    chipBackgroundColor = ColorStateList.valueOf(colors.fillColor)
                    setTextColor(colors.accentColor)
                    chipStrokeColor = ColorStateList.valueOf(colors.accentColor)
                    chipStrokeWidth = (1f * density)
                    chipCornerRadius = radius
                    chipMinHeight = (40f * density)
                    textSize = 14f
                    chipEndPadding = (10f * density)
                    chipStartPadding = (10f * density)
                    textEndPadding = (2f * density)
                    textStartPadding = (2f * density)
                    setEnsureMinTouchTargetSize(false)
                }
                matchedSymptomsChips.addView(chip)
            }
            if (result.matchedSymptoms.size > 4) {
                val moreChip = Chip(ctx).apply {
                    text = ctx.getString(R.string.symptom_more_count, result.matchedSymptoms.size - 4)
                    isClickable = false
                    isFocusable = false
                    chipBackgroundColor = ColorStateList.valueOf(defaultColors.fillColor)
                    setTextColor(defaultColors.accentColor)
                    chipStrokeColor = ColorStateList.valueOf(defaultColors.accentColor)
                    chipStrokeWidth = (1f * density)
                    chipCornerRadius = radius
                    chipMinHeight = (40f * density)
                    textSize = 14f
                    chipEndPadding = (10f * density)
                    chipStartPadding = (10f * density)
                    textEndPadding = (2f * density)
                    textStartPadding = (2f * density)
                    setEnsureMinTouchTargetSize(false)
                }
                matchedSymptomsChips.addView(moreChip)
            }

            // Differentiating tip — color-coded chips
            differentiatingChips.removeAllViews()
            if (result.differentiatingSymptoms.isNotEmpty()) {
                result.differentiatingSymptoms.forEach { symptom ->
                    val colors = symptomColorMap[symptom] ?: defaultColors
                    val chip = Chip(ctx).apply {
                        text = symptom.replaceFirstChar { it.uppercase() }
                        isClickable = false
                        isFocusable = false
                        chipBackgroundColor = ColorStateList.valueOf(colors.fillColor)
                        setTextColor(colors.accentColor)
                        chipStrokeColor = ColorStateList.valueOf(colors.accentColor)
                        chipStrokeWidth = (1f * density)
                        chipCornerRadius = radius
                        chipMinHeight = (40f * density)
                        textSize = 14f
                        chipEndPadding = (10f * density)
                        chipStartPadding = (10f * density)
                        textEndPadding = (2f * density)
                        textStartPadding = (2f * density)
                        setEnsureMinTouchTargetSize(false)
                    }
                    differentiatingChips.addView(chip)
                }
                differentiatingTipLayout.visibility = View.VISIBLE
            } else {
                differentiatingTipLayout.visibility = View.GONE
            }

            // Read article button
            readArticleButton.setOnClickListener { onReadArticle(result.article) }
        }
    }
}
