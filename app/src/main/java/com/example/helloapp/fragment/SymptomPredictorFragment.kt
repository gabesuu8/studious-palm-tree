package com.example.helloapp.fragment

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.helloapp.FavoritesActivity
import com.example.helloapp.R
import com.example.helloapp.RapidTestTimerActivity
import com.example.helloapp.SymptomResultsActivity
import com.example.helloapp.data.Article
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.util.SymptomCategories
import com.example.helloapp.viewmodel.ArticleViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SymptomPredictorFragment : Fragment() {

    private var viewModel: ArticleViewModel? = null
    private var btnSuggestCondition: Button? = null
    private var emptyStateText: TextView? = null
    private var clearChipsButton: TextView? = null
    private var symptomBubblesContainer: LinearLayout? = null
    private var allArticlesSnapshot: List<Article> = emptyList()

    // label → keyword; drives clearAllChips and the "Clear all" button visibility
    private val selectedChipKeywords = mutableMapOf<String, String>()
    // label → Chip view; used for programmatic uncheck in clearAllChips
    private val chipViews = mutableMapOf<String, Chip>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_symptom_predictor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialToolbar>(R.id.toolbar)?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_options -> { showOptionsDialog(); true }
                else -> false
            }
        }

        btnSuggestCondition     = view.findViewById(R.id.btnSuggestCondition)
        emptyStateText          = view.findViewById(R.id.emptyStateText)
        clearChipsButton        = view.findViewById(R.id.clearChipsButton)
        symptomBubblesContainer = view.findViewById(R.id.symptomBubblesContainer)

        viewModel = ViewModelProvider(
            requireActivity(),
            ArticleViewModel.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[ArticleViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.allArticles?.collect { list -> allArticlesSnapshot = list }
        }

        buildSymptomBubbles()

        clearChipsButton?.setOnClickListener { clearAllChips() }
        btnSuggestCondition?.setOnClickListener { showSymptomResults() }
    }

    // ──────────────────────────────────────────────────────────────
    // Chip bubble construction
    // ──────────────────────────────────────────────────────────────

    private fun buildSymptomBubbles() {
        val container = symptomBubblesContainer ?: return
        val ctx       = requireContext()
        val density   = ctx.resources.displayMetrics.density
        container.removeAllViews()
        chipViews.clear()

        val white = ContextCompat.getColor(ctx, R.color.white)
        val iconTint = ColorStateList.valueOf(white)
        val radius = 20f * density

        // Hint for long-press feature
        val hintText = TextView(ctx).apply {
            text = getString(R.string.symptom_long_press_hint)
            textSize = 12f
            setTextColor(ContextCompat.getColor(ctx, android.R.color.darker_gray))
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, (12 * density).toInt()) }
        }
        container.addView(hintText)

        SymptomCategories.symptomCategories.forEachIndexed { catIndex, (category, symptoms) ->

            val colors = SymptomCategories.categoryColorsList[catIndex]

            // State-based chip colors
            val bgColors = ColorStateList(
                arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf()),
                intArrayOf(colors.checkedColor, colors.fillColor)
            )
            val textColors = ColorStateList(
                arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf()),
                intArrayOf(white, colors.accentColor)
            )

            // ── Category header ──────────────────────────────────
            val header = TextView(ctx).apply {
                text = category.uppercase()
                textSize = 11f
                setTypeface(typeface, Typeface.BOLD)
                setTextColor(colors.accentColor)
                letterSpacing = 0.10f
                val topMargin = if (catIndex == 0) 0 else (16 * density).toInt()
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, topMargin, 0, (10 * density).toInt()) }
                layoutParams = params
            }
            container.addView(header)

            // ── Wrapping chip group ───────────────────────────────
            val chipGroup = ChipGroup(ctx).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                chipSpacingHorizontal = (8 * density).toInt()
                chipSpacingVertical   = (8 * density).toInt()
            }

            for ((label, keyword) in symptoms) {
                val chip = Chip(ctx).apply {
                    text                 = label
                    isCheckable          = true
                    isChecked            = selectedChipKeywords.containsKey(label)
                    chipBackgroundColor  = bgColors
                    setTextColor(textColors)
                    chipStrokeColor      = ColorStateList.valueOf(colors.accentColor)
                    chipStrokeWidth      = (1f * density)
                    chipCornerRadius     = radius
                    chipMinHeight        = (40f * density)
                    textSize             = 14f
                    isCheckedIconVisible = true
                    checkedIconTint      = iconTint
                    chipEndPadding  = (10f * density)
                    chipStartPadding = (10f * density)
                    textEndPadding  = (2f * density)
                    textStartPadding = (2f * density)
                    setEnsureMinTouchTargetSize(false)

                    setOnCheckedChangeListener { _, checked ->
                        if (checked) {
                            selectedChipKeywords[label] = keyword
                        } else {
                            selectedChipKeywords.remove(label)
                        }
                        clearChipsButton?.visibility =
                            if (selectedChipKeywords.isNotEmpty()) View.VISIBLE else View.GONE
                    }
                    setOnLongClickListener {
                        val defResId = SymptomCategories.symptomDefinitions[keyword]
                        if (defResId != null) {
                            AlertDialog.Builder(ctx)
                                .setTitle(label)
                                .setMessage(getString(defResId))
                                .setPositiveButton(android.R.string.ok, null)
                                .show()
                        }
                        true
                    }
                }
                chipGroup.addView(chip)
                chipViews[label] = chip
            }

            container.addView(chipGroup)
        }
    }

    private fun clearAllChips() {
        chipViews.values.forEach { it.isChecked = false }
        selectedChipKeywords.clear()
        clearChipsButton?.visibility = View.GONE
    }

    // ──────────────────────────────────────────────────────────────
    // Search logic
    // ──────────────────────────────────────────────────────────────

    private fun showSymptomResults() {
        val query = selectedChipKeywords.values.joinToString(" ")

        if (query.isBlank()) {
            Snackbar.make(
                view ?: return,
                getString(R.string.symptom_enter_first),
                Snackbar.LENGTH_SHORT
            ).show()
            return
        }

        val results = viewModel?.getSymptomMatchResults(allArticlesSnapshot, query) ?: emptyList()

        if (results.isEmpty()) {
            emptyStateText?.visibility = View.VISIBLE
        } else {
            emptyStateText?.visibility = View.GONE
            val intent = Intent(requireContext(), SymptomResultsActivity::class.java)
            intent.putExtra("query", query)
            startActivity(intent)
        }
    }

    // ──────────────────────────────────────────────────────────────
    // Navigation & dialogs
    // ──────────────────────────────────────────────────────────────

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
        val languages     = arrayOf(getString(R.string.english), getString(R.string.french))
        val languageCodes = arrayOf(LanguageHelper.ENGLISH, LanguageHelper.FRENCH)
        val currentLanguage = LanguageHelper.getLanguage(ctx)
        val currentIndex  = languageCodes.indexOf(currentLanguage)

        AlertDialog.Builder(ctx)
            .setTitle(getString(R.string.language))
            .setSingleChoiceItems(languages, currentIndex) { dialog, which ->
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

    override fun onDestroyView() {
        super.onDestroyView()
        btnSuggestCondition     = null
        emptyStateText          = null
        clearChipsButton        = null
        symptomBubblesContainer = null
    }
}
