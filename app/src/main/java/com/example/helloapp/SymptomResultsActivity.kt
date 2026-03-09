package com.example.helloapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.adapter.SymptomResultAdapter
import com.example.helloapp.data.Article
import com.example.helloapp.fragment.ConditionComparisonBottomSheet
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.util.SymptomCategories
import com.example.helloapp.viewmodel.ArticleViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SymptomResultsActivity : AppCompatActivity() {

    private var selectedResults: List<ArticleViewModel.SymptomMatchResult> = emptyList()

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageHelper.applyLanguage(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptom_results)

        val query = intent.getStringExtra("query") ?: run {
            finish()
            return
        }

        // Toolbar with back navigation
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val compareButton = findViewById<MaterialButton>(R.id.compareButton)
        val resultsRecyclerView = findViewById<RecyclerView>(R.id.resultsRecyclerView)
        val emptyStateText = findViewById<TextView>(R.id.emptyStateText)

        // Set up adapter
        val resultAdapter = SymptomResultAdapter(
            onReadArticle = { article -> openArticleDetail(article) },
            onSelectionChanged = { selected ->
                selectedResults = selected
                if (selected.isNotEmpty()) {
                    compareButton.text = getString(R.string.compare_conditions_count, selected.size)
                    compareButton.visibility = View.VISIBLE
                } else {
                    compareButton.visibility = View.GONE
                }
            }
        )
        resultAdapter.setSymptomColorMap(SymptomCategories.buildSymptomColorMap())

        resultsRecyclerView.layoutManager = LinearLayoutManager(this)
        resultsRecyclerView.adapter = resultAdapter

        // Compare button
        compareButton.setOnClickListener {
            if (selectedResults.isNotEmpty()) {
                ConditionComparisonBottomSheet.newInstance(selectedResults)
                    .show(supportFragmentManager, "compare")
            }
        }

        // Load articles and compute results
        val viewModel = ViewModelProvider(
            this,
            ArticleViewModel.AndroidViewModelFactory.getInstance(application)
        )[ArticleViewModel::class.java]

        lifecycleScope.launch {
            val articles = viewModel.allArticles.first()
            val results = viewModel.getSymptomMatchResults(articles, query)
            resultAdapter.submitList(results)

            if (results.isEmpty()) {
                resultsRecyclerView.visibility = View.GONE
                emptyStateText.visibility = View.VISIBLE
            } else {
                resultsRecyclerView.visibility = View.VISIBLE
                emptyStateText.visibility = View.GONE
            }
        }
    }

    private fun openArticleDetail(article: Article) {
        val intent = Intent(this, ArticleDetailActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }
}
