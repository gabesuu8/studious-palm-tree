package com.example.helloapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloapp.adapter.ArticleAdapter
import com.example.helloapp.data.Article
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.viewmodel.ArticleViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
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
    private lateinit var categoryChipGroup: ChipGroup
    private lateinit var chipAll: Chip

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageHelper.applyLanguage(newBase))
    }

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
            // Setup toolbar with menu
            val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
            
            Log.d(TAG, "Initializing views")
            recyclerView = findViewById(R.id.articlesRecyclerView)
            emptyStateText = findViewById(R.id.emptyStateText)
            searchEditText = findViewById(R.id.searchEditText)
            clearSearchButton = findViewById(R.id.clearSearchButton)
            categoryChipGroup = findViewById(R.id.categoryChipGroup)
            chipAll = findViewById(R.id.chipAll)
            
            adapter = ArticleAdapter(
                onItemClick = { article -> openArticleDetail(article) },
                onFavoriteClick = { article -> toggleFavorite(article) }
            )
            
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
            
            // Setup category filter chips
            setupCategoryChips()

            Log.d(TAG, "Starting article observation")
            // Observe filtered articles (responds to search and category filter)
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
                                    emptyStateText.text = getString(R.string.no_search_results, searchQuery)
                                } else {
                                    emptyStateText.text = getString(R.string.no_articles)
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
                        emptyStateText.text = getString(R.string.error_loading)
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
    
    private fun setupCategoryChips() {
        // Handle "All" chip click
        chipAll.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.setSelectedCategory(null)
            }
        }
        
        // Observe categories and create chips dynamically
        lifecycleScope.launch {
            viewModel.allCategories.collect { categories ->
                // Remove all chips except "All"
                val chipsToRemove = mutableListOf<View>()
                for (i in 0 until categoryChipGroup.childCount) {
                    val chip = categoryChipGroup.getChildAt(i)
                    if (chip.id != R.id.chipAll) {
                        chipsToRemove.add(chip)
                    }
                }
                chipsToRemove.forEach { categoryChipGroup.removeView(it) }
                
                // Add category chips
                categories.forEach { category ->
                    val chip = Chip(this@MainActivity).apply {
                        text = category
                        isCheckable = true
                        isCheckedIconVisible = false
                        setChipBackgroundColorResource(R.color.chip_background_color)
                        setOnCheckedChangeListener { _, isChecked ->
                            if (isChecked) {
                                viewModel.setSelectedCategory(category)
                                chipAll.isChecked = false
                            }
                        }
                    }
                    categoryChipGroup.addView(chip)
                }
            }
        }
    }

    private fun openArticleDetail(article: Article) {
        val intent = Intent(this, ArticleDetailActivity::class.java)
        intent.putExtra("article", article)
        startActivity(intent)
    }
    
    private fun toggleFavorite(article: Article) {
        viewModel.toggleFavorite(article)
        val message = if (article.isFavorite) {
            getString(R.string.removed_from_favorites)
        } else {
            getString(R.string.added_to_favorites)
        }
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorites -> {
                startActivity(Intent(this, FavoritesActivity::class.java))
                true
            }
            R.id.menu_language -> {
                showLanguageDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLanguageDialog() {
        val languages = arrayOf(getString(R.string.english), getString(R.string.french))
        val languageCodes = arrayOf(LanguageHelper.ENGLISH, LanguageHelper.FRENCH)
        val currentLanguage = LanguageHelper.getLanguage(this)
        val currentIndex = languageCodes.indexOf(currentLanguage)

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.language))
            .setSingleChoiceItems(languages, currentIndex) { dialog, which ->
                val selectedCode = languageCodes[which]
                if (currentLanguage != selectedCode) {
                    // Set the new language preference
                    LanguageHelper.setLanguage(this, selectedCode)
                    dialog.dismiss()
                    // Fetch articles in the new language (clears old and loads new)
                    viewModel.fetchAndSaveArticles()
                    // Recreate the activity to apply new language to UI
                    recreate()
                } else {
                    dialog.dismiss()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

}
