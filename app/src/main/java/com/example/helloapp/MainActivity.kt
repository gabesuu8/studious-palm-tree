package com.example.helloapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.adapter.ArticleAdapter
import com.example.helloapp.data.Article
import com.example.helloapp.viewmodel.ArticleViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var viewModel: ArticleViewModel
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyStateText: TextView
    private lateinit var adapter: ArticleAdapter
    private lateinit var searchEditText: EditText
    private lateinit var clearSearchButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate started")
        
        try {
            setContentView(R.layout.activity_main)
            Log.d(TAG, "setContentView successful")
        } catch (e: Exception) {
            Log.e(TAG, "Error in setContentView", e)
            e.printStackTrace()
            throw e
        }

        try {
            Log.d(TAG, "Initializing views")
            recyclerView = findViewById(R.id.articlesRecyclerView)
            emptyStateText = findViewById(R.id.emptyStateText)
            searchEditText = findViewById(R.id.searchEditText)
            clearSearchButton = findViewById(R.id.clearSearchButton)
            
            adapter = ArticleAdapter { article ->
                openArticleDetail(article)
            }
            
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter

            Log.d(TAG, "Initializing ViewModel")
            try {
                viewModel = ViewModelProvider(
                    this,
                    ArticleViewModel.AndroidViewModelFactory.getInstance(application)
                )[ArticleViewModel::class.java]
                Log.d(TAG, "ViewModel initialized successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to initialize ViewModel", e)
                // Show error and return - app can't function without ViewModel
                emptyStateText.visibility = View.VISIBLE
                emptyStateText.text = "Error initializing app: ${e.message}\n\nPlease check Logcat for details."
                recyclerView.visibility = View.GONE
                return
            }

            // Setup search functionality
            setupSearch()

            findViewById<FloatingActionButton>(R.id.fabRefresh).setOnClickListener {
                viewModel.fetchAndSaveArticles()
                Snackbar.make(it, "Fetching articles...", Snackbar.LENGTH_SHORT).show()
            }

            Log.d(TAG, "Starting article observation")
            // Observe filtered articles (responds to search)
            lifecycleScope.launch {
                try {
                    var isFirstLoad = true
                    viewModel.filteredArticles.collect { articles ->
                        try {
                            Log.d(TAG, "Received articles: ${articles.size}")
                            val searchQuery = viewModel.searchQuery.value
                            
                            if (articles.isEmpty()) {
                                recyclerView.visibility = View.GONE
                                emptyStateText.visibility = View.VISIBLE
                                
                                if (searchQuery.isNotBlank()) {
                                    emptyStateText.text = "No articles found for \"$searchQuery\".\nTry different keywords."
                                } else {
                                    emptyStateText.text = "No articles available.\nTap the refresh button to load articles."
                                    // Load articles on first launch if empty
                                    if (isFirstLoad) {
                                        isFirstLoad = false
                                        Log.d(TAG, "First load - fetching articles")
                                        viewModel.fetchAndSaveArticles()
                                    }
                                }
                            } else {
                                recyclerView.visibility = View.VISIBLE
                                emptyStateText.visibility = View.GONE
                                adapter.submitList(articles)
                                isFirstLoad = false
                            }
                        } catch (e: Exception) {
                            Log.e(TAG, "Error in article collection", e)
                            e.printStackTrace()
                        }
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error in lifecycleScope", e)
                    e.printStackTrace()
                    // Show error message
                    try {
                        emptyStateText.visibility = View.VISIBLE
                        emptyStateText.text = "Error loading articles. Please try again."
                        recyclerView.visibility = View.GONE
                    } catch (ex: Exception) {
                        Log.e(TAG, "Error showing error message", ex)
                    }
                }
            }
            Log.d(TAG, "onCreate completed successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Critical error in onCreate", e)
            e.printStackTrace()
            // If there's a critical error, show a simple message
            try {
                findViewById<TextView>(R.id.emptyStateText)?.apply {
                    visibility = View.VISIBLE
                    text = "App initialization error: ${e.message}. Please check Logcat."
                }
            } catch (ex: Exception) {
                Log.e(TAG, "Could not show error message", ex)
            }
        }
    }

    private fun setupSearch() {
        // Text change listener for real-time search
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString() ?: ""
                viewModel.setSearchQuery(query)
                adapter.setSearchKeywords(query)
                clearSearchButton.isVisible = query.isNotEmpty()
            }
            
            override fun afterTextChanged(s: Editable?) {}
        })
        
        // Handle search action on keyboard
        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Hide keyboard
                searchEditText.clearFocus()
                true
            } else {
                false
            }
        }
        
        // Clear search button
        clearSearchButton.setOnClickListener {
            searchEditText.text.clear()
            viewModel.setSearchQuery("")
            adapter.setSearchKeywords("")
            clearSearchButton.visibility = View.GONE
        }
    }

    private fun openArticleDetail(article: Article) {
        val intent = Intent(this, ArticleDetailActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }
}
