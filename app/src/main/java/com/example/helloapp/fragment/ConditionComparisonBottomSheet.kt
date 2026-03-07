package com.example.helloapp.fragment

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.helloapp.ArticleDetailActivity
import com.example.helloapp.R
import com.example.helloapp.data.Article
import com.example.helloapp.viewmodel.ArticleViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ConditionComparisonBottomSheet : BottomSheetDialogFragment() {

    companion object {
        private const val ARG_ARTICLES = "arg_articles"
        private const val ARG_CONFIDENCES = "arg_confidences"
        private const val ARG_URGENCIES = "arg_urgencies"
        private const val ARG_SYMPTOMS = "arg_symptoms"

        fun newInstance(
            results: List<ArticleViewModel.SymptomMatchResult>
        ): ConditionComparisonBottomSheet {
            val articles = ArrayList(results.map { it.article })
            val confidences = ArrayList(results.map { it.confidence.name })
            val urgencies = ArrayList(results.map { it.urgency.name })
            val symptoms = ArrayList(results.map { it.matchedSymptoms.joinToString("|") })
            return ConditionComparisonBottomSheet().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_ARTICLES, articles)
                    putStringArrayList(ARG_CONFIDENCES, confidences)
                    putStringArrayList(ARG_URGENCIES, urgencies)
                    putStringArrayList(ARG_SYMPTOMS, symptoms)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_condition_comparison, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articles = arguments?.getParcelableArrayList<Article>(ARG_ARTICLES) ?: return
        val urgencies = arguments?.getStringArrayList(ARG_URGENCIES) ?: return
        val symptomsList = arguments?.getStringArrayList(ARG_SYMPTOMS) ?: return

        val container = view.findViewById<LinearLayout>(R.id.conditionCardsContainer)
        val inflater = LayoutInflater.from(requireContext())

        articles.forEachIndexed { index, article ->
            val cardView = inflater.inflate(R.layout.item_condition_comparison_card, container, false)

            val urgencyEnum = runCatching {
                ArticleViewModel.ConditionUrgency.valueOf(urgencies[index])
            }.getOrDefault(ArticleViewModel.ConditionUrgency.HOME_CARE)

            val ctx = requireContext()

            // Urgency row
            val (urgencyText, urgencyColorRes) = when (urgencyEnum) {
                ArticleViewModel.ConditionUrgency.EMERGENCY ->
                    getString(R.string.urgency_emergency) to R.color.urgency_emergency
                ArticleViewModel.ConditionUrgency.SEE_DOCTOR ->
                    getString(R.string.urgency_see_doctor) to R.color.urgency_see_doctor
                ArticleViewModel.ConditionUrgency.HOME_CARE ->
                    getString(R.string.urgency_home_care) to R.color.urgency_home_care
            }
            val urgencyColor = ContextCompat.getColor(ctx, urgencyColorRes)
            cardView.findViewById<View>(R.id.comparisonUrgencyDot)
                .backgroundTintList = ColorStateList.valueOf(urgencyColor)
            cardView.findViewById<TextView>(R.id.comparisonUrgencyLabel).apply {
                text = urgencyText
                setTextColor(urgencyColor)
            }

            // Title
            cardView.findViewById<TextView>(R.id.comparisonConditionTitle).text = article.title

            // Matched symptom chips (up to 3)
            val chipGroup = cardView.findViewById<ChipGroup>(R.id.comparisonSymptomsChips)
            val teal50 = ContextCompat.getColor(ctx, R.color.teal_50)
            val teal700 = ContextCompat.getColor(ctx, R.color.teal_700)
            val matchedSymptoms = if (index < symptomsList.size && symptomsList[index].isNotBlank())
                symptomsList[index].split("|") else emptyList()
            matchedSymptoms.take(3).forEach { symptom ->
                val chip = Chip(ctx).apply {
                    text = symptom
                    isClickable = false
                    isFocusable = false
                    chipBackgroundColor = ColorStateList.valueOf(teal50)
                    setTextColor(teal700)
                    textSize = 11f
                }
                chipGroup.addView(chip)
            }
            if (matchedSymptoms.size > 3) {
                val moreChip = Chip(ctx).apply {
                    text = getString(R.string.symptom_more_count, matchedSymptoms.size - 3)
                    isClickable = false
                    isFocusable = false
                    chipBackgroundColor = ColorStateList.valueOf(teal50)
                    setTextColor(teal700)
                    textSize = 11f
                }
                chipGroup.addView(moreChip)
            }

            // Read article button
            cardView.findViewById<MaterialButton>(R.id.comparisonReadArticleButton)
                .setOnClickListener {
                    dismiss()
                    val intent = Intent(requireContext(), ArticleDetailActivity::class.java)
                    intent.putExtra("article", article)
                    startActivity(intent)
                }

            container.addView(cardView)
        }
    }
}
