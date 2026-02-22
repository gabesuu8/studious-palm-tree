package com.example.helloapp.fragment

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.helloapp.ArticleDetailActivity
import com.example.helloapp.R
import com.example.helloapp.data.Article
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.viewmodel.ArticleViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SymptomPredictorFragment : Fragment() {

    private var viewModel: ArticleViewModel? = null
    private var symptomInputEditText: EditText? = null
    private var btnSuggestCondition: Button? = null
    private var allArticlesSnapshot: List<Article> = emptyList()

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
                R.id.menu_language -> {
                    showLanguageDialog()
                    true
                }
                else -> false
            }
        }

        symptomInputEditText = view.findViewById(R.id.symptomInputEditText)
        btnSuggestCondition = view.findViewById(R.id.btnSuggestCondition)

        viewModel = ViewModelProvider(
            requireActivity(),
            ArticleViewModel.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[ArticleViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.allArticles?.collect { list ->
                allArticlesSnapshot = list
            }
        }

        btnSuggestCondition?.setOnClickListener { showSymptomMatchDialog() }
    }

    private fun showSymptomMatchDialog() {
        val query = symptomInputEditText?.text?.toString()?.trim() ?: ""
        if (query.isBlank()) {
            symptomInputEditText?.requestFocus()
            Snackbar.make(
                symptomInputEditText ?: view ?: return,
                getString(R.string.symptom_enter_first),
                Snackbar.LENGTH_SHORT
            ).show()
            return
        }
        val results = viewModel?.getSymptomMatchResults(allArticlesSnapshot, query) ?: emptyList()
        val ctx = context ?: return
        if (results.isEmpty()) {
            AlertDialog.Builder(ctx)
                .setTitle(getString(R.string.possible_conditions))
                .setMessage(getString(R.string.no_conditions_match))
                .setPositiveButton(android.R.string.ok, null)
                .show()
            return
        }

        val dialogView = LayoutInflater.from(ctx).inflate(R.layout.dialog_symptom_results, null)
        val listView = dialogView.findViewById<ListView>(R.id.resultsListView)
        val tipView = dialogView.findViewById<TextView>(R.id.differentiatingTipText)

        val displayItems = results.map { result ->
            val confidenceLabel = when (result.confidence) {
                ArticleViewModel.MatchConfidence.HIGH -> getString(R.string.confidence_high)
                ArticleViewModel.MatchConfidence.MODERATE -> getString(R.string.confidence_moderate)
                ArticleViewModel.MatchConfidence.LOW -> getString(R.string.confidence_low)
            }
            val matchInfo = getString(R.string.symptoms_matched_count,
                result.matchedSymptoms.size)
            SpannableStringBuilder().apply {
                append(result.article.title)
                append("\n")
                val detailStart = length
                append("$confidenceLabel  ·  $matchInfo")
                val color = when (result.confidence) {
                    ArticleViewModel.MatchConfidence.HIGH -> ContextCompat.getColor(ctx, R.color.confidence_high)
                    ArticleViewModel.MatchConfidence.MODERATE -> ContextCompat.getColor(ctx, R.color.confidence_moderate)
                    ArticleViewModel.MatchConfidence.LOW -> ContextCompat.getColor(ctx, R.color.confidence_low)
                }
                setSpan(ForegroundColorSpan(color), detailStart, length, 0)
                setSpan(RelativeSizeSpan(0.85f), detailStart, length, 0)
            }
        }

        val adapter = ArrayAdapter(ctx, android.R.layout.simple_list_item_1, displayItems)
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            val article = results.getOrNull(position)?.article ?: return@setOnItemClickListener
            openArticleDetail(article)
        }

        val allDifferentiating = results
            .filter { it.confidence != ArticleViewModel.MatchConfidence.HIGH }
            .flatMap { r -> r.differentiatingSymptoms.map { it to r.article.title } }
            .distinctBy { it.first }
            .take(4)

        if (allDifferentiating.isNotEmpty() && results.size > 1) {
            val tips = allDifferentiating.joinToString("\n") { (symptom, condition) ->
                "• $symptom → $condition"
            }
            tipView.text = getString(R.string.narrow_down_tip, tips)
            tipView.visibility = View.VISIBLE
        } else {
            tipView.visibility = View.GONE
        }

        AlertDialog.Builder(ctx)
            .setTitle(getString(R.string.possible_conditions))
            .setView(dialogView)
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun openArticleDetail(article: Article) {
        val intent = Intent(requireContext(), ArticleDetailActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
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
        symptomInputEditText = null
        btnSuggestCondition = null
    }
}
