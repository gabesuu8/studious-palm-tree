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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.ArticleDetailActivity
import com.example.helloapp.R
import com.example.helloapp.adapter.SymptomChipColors
import com.example.helloapp.adapter.SymptomResultAdapter
import com.example.helloapp.data.Article
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.viewmodel.ArticleViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SymptomPredictorFragment : Fragment() {

    /**
     * Mayo Clinic-aligned symptom categories.
     * Pair: display label → keyword(s) fed to the ViewModel matcher.
     * The label is also appended to the EditText so the user sees what they selected.
     */
    private val symptomCategories: List<Pair<String, List<Pair<String, String>>>> = listOf(
        "General" to listOf(
            "Fever"       to "fever",
            "Fatigue"     to "fatigue tired",
            "Chills"      to "chills",
            "Sweating"    to "sweating",
            "Weight loss" to "weight",
            "Night sweats" to "night sweats"
        ),
        "Head & Neurological" to listOf(
            "Headache"       to "headache",
            "Stiff neck"     to "stiff neck",
            "Blurred vision" to "blurred vision",
            "Convulsions"    to "convulsions",
            "Dizziness"      to "dizziness"
        ),
        "Digestive" to listOf(
            "Nausea"         to "nausea",
            "Vomiting"       to "vomiting",
            "Diarrhea"       to "diarrhea",
            "Abdominal pain" to "abdominal",
            "Dehydration"    to "dehydration",
            "Thirst"         to "thirst"
        ),
        "Respiratory" to listOf(
            "Cough"               to "cough",
            "Chest pain"          to "chest",
            "Shortness of breath" to "breathing"
        ),
        "Skin" to listOf(
            "Rash"     to "rash",
            "Itching"  to "itchy",
            "Jaundice" to "jaundice",
            "Swelling" to "swelling",
            "Hives"    to "hives",
            "Bleeding" to "bleeding"
        ),
        "Muscles & Joints" to listOf(
            "Muscle pain" to "muscle pain",
            "Joint pain"  to "joint",
            "Weakness"    to "weakness"
        ),
        "Mental Health" to listOf(
            "Anxiety"     to "anxiety",
            "Sadness"     to "sad",
            "Stress"      to "stress",
            "Hopelessness" to "hopeless"
        )
    )

    private var viewModel: ArticleViewModel? = null
    private var btnSuggestCondition: Button? = null
    private var resultsRecyclerView: RecyclerView? = null
    private var compareButton: MaterialButton? = null
    private var emptyStateText: TextView? = null
    private var clearChipsButton: TextView? = null
    private var symptomBubblesContainer: LinearLayout? = null
    private var allArticlesSnapshot: List<Article> = emptyList()

    private lateinit var resultAdapter: SymptomResultAdapter
    private var selectedResults: List<ArticleViewModel.SymptomMatchResult> = emptyList()

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
                R.id.menu_language -> { showLanguageDialog(); true }
                else -> false
            }
        }

        btnSuggestCondition     = view.findViewById(R.id.btnSuggestCondition)
        resultsRecyclerView     = view.findViewById(R.id.resultsRecyclerView)
        compareButton           = view.findViewById(R.id.compareButton)
        emptyStateText          = view.findViewById(R.id.emptyStateText)
        clearChipsButton        = view.findViewById(R.id.clearChipsButton)
        symptomBubblesContainer = view.findViewById(R.id.symptomBubblesContainer)

        resultAdapter = SymptomResultAdapter(
            onReadArticle = { article -> openArticleDetail(article) },
            onSelectionChanged = { selected ->
                selectedResults = selected
                updateCompareButton(selected.size)
            }
        )

        // Build symptom → category color mapping for result chips
        // Maps both display labels ("Fever") and keywords ("fever", "fever tired") for matching
        val colorMap = mutableMapOf<String, SymptomChipColors>()
        symptomCategories.forEachIndexed { catIndex, (_, symptoms) ->
            val cc = categoryColorsList[catIndex]
            val chipColors = SymptomChipColors(cc.fillColor, cc.accentColor, cc.checkedColor)
            for ((label, keyword) in symptoms) {
                colorMap[label] = chipColors
                colorMap[label.lowercase()] = chipColors
                // Map each individual keyword token as well
                keyword.split(" ").forEach { token ->
                    colorMap[token] = chipColors
                }
            }
        }
        resultAdapter.setSymptomColorMap(colorMap)

        resultsRecyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = resultAdapter
        }

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
        compareButton?.setOnClickListener {
            if (selectedResults.isNotEmpty()) {
                ConditionComparisonBottomSheet.newInstance(selectedResults)
                    .show(childFragmentManager, "compare")
            }
        }
    }

    // ──────────────────────────────────────────────────────────────
    // Chip bubble construction
    // ──────────────────────────────────────────────────────────────

    // Per-category colors: unchecked fill, unchecked stroke/text, checked fill
    private data class CategoryColors(val fillColor: Int, val accentColor: Int, val checkedColor: Int)
    private val categoryColorsList = listOf(
        CategoryColors(0xFFB2DFDB.toInt(), 0xFF00695C.toInt(), 0xFF00897B.toInt()),  // General – teal
        CategoryColors(0xFFBBDEFB.toInt(), 0xFF1565C0.toInt(), 0xFF1976D2.toInt()),  // Head – blue
        CategoryColors(0xFFC8E6C9.toInt(), 0xFF2E7D32.toInt(), 0xFF388E3C.toInt()),  // Digestive – green
        CategoryColors(0xFFB2EBF2.toInt(), 0xFF00838F.toInt(), 0xFF0097A7.toInt()),  // Respiratory – cyan
        CategoryColors(0xFFF8BBD0.toInt(), 0xFFAD1457.toInt(), 0xFFC2185B.toInt()),  // Skin – pink
        CategoryColors(0xFFFFE0B2.toInt(), 0xFFE65100.toInt(), 0xFFF57C00.toInt()),  // Muscles – orange
        CategoryColors(0xFFE1BEE7.toInt(), 0xFF6A1B9A.toInt(), 0xFF7B1FA2.toInt())   // Mental – purple
    )

    private fun buildSymptomBubbles() {
        val container = symptomBubblesContainer ?: return
        val ctx       = requireContext()
        val density   = ctx.resources.displayMetrics.density
        container.removeAllViews()
        chipViews.clear()

        val white = ContextCompat.getColor(ctx, R.color.white)
        val iconTint = ColorStateList.valueOf(white)
        val radius = 20f * density

        symptomCategories.forEachIndexed { catIndex, (category, symptoms) ->

            val colors = categoryColorsList[catIndex]

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
        resultAdapter.submitList(results)

        if (results.isEmpty()) {
            resultsRecyclerView?.visibility = View.GONE
            emptyStateText?.visibility = View.VISIBLE
            compareButton?.visibility = View.GONE
        } else {
            emptyStateText?.visibility = View.GONE
            resultsRecyclerView?.visibility = View.VISIBLE
            // Scroll the results into view
            resultsRecyclerView?.post { resultsRecyclerView?.scrollToPosition(0) }
            // If only one result, open it directly
            if (results.size == 1) {
                openArticleDetail(results[0].article)
            }
        }
    }

    private fun updateCompareButton(count: Int) {
        val btn = compareButton ?: return
        if (count >= 1) {
            btn.text = getString(R.string.compare_conditions_count, count)
            btn.visibility = View.VISIBLE
        } else {
            btn.visibility = View.GONE
        }
    }

    // ──────────────────────────────────────────────────────────────
    // Navigation & dialogs
    // ──────────────────────────────────────────────────────────────

    private fun openArticleDetail(article: Article) {
        val intent = Intent(requireContext(), ArticleDetailActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
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
        resultsRecyclerView     = null
        compareButton           = null
        emptyStateText          = null
        clearChipsButton        = null
        symptomBubblesContainer = null
    }
}
