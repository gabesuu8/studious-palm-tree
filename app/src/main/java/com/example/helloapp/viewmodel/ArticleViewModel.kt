package com.example.helloapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.helloapp.data.AppDatabase
import com.example.helloapp.data.Article
import com.example.helloapp.repository.ArticleRepository
import com.example.helloapp.service.ArticleFetcher
import com.example.helloapp.util.LanguageHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ArticleViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val database: AppDatabase by lazy {
        try {
            android.util.Log.d("ArticleViewModel", "Initializing database")
            AppDatabase.getDatabase(application)
        } catch (e: Exception) {
            android.util.Log.e("ArticleViewModel", "Error initializing database", e)
            throw e
        }
    }
    
    private val repository: ArticleRepository by lazy {
        try {
            android.util.Log.d("ArticleViewModel", "Initializing repository")
            ArticleRepository(database.articleDao())
        } catch (e: Exception) {
            android.util.Log.e("ArticleViewModel", "Error initializing repository", e)
            throw e
        }
    }
    
    private val articleFetcher = ArticleFetcher()
    
    // Search query state
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery
    
    // Category filter state
    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory
    
    val allArticles: Flow<List<Article>> by lazy {
        try {
            android.util.Log.d("ArticleViewModel", "Getting allArticles flow")
            repository.allArticles
        } catch (e: Exception) {
            android.util.Log.e("ArticleViewModel", "Error getting allArticles", e)
            throw e
        }
    }
    
    val favoriteArticles: Flow<List<Article>> by lazy {
        repository.favoriteArticles
    }
    
    val allCategories: Flow<List<String>> by lazy {
        repository.allCategories
    }
    
    // Filtered articles based on search query and category
    val filteredArticles: Flow<List<Article>> by lazy {
        combine(allArticles, _searchQuery, _selectedCategory) { articles, query, category ->
            var filtered = articles
            
            // Filter by category first
            if (category != null) {
                filtered = filtered.filter { it.category == category }
            }
            
            // Then filter by search query
            if (query.isBlank()) {
                filtered
            } else {
                val queryLower = query.lowercase().trim()
                val keywords = queryLower.split(" ").filter { it.isNotBlank() }
                
                filtered.filter { article ->
                    keywords.any { keyword ->
                        article.title.lowercase().contains(keyword) ||
                        article.summary.lowercase().contains(keyword) ||
                        article.content.lowercase().contains(keyword) ||
                        article.category.lowercase().contains(keyword)
                    }
                }.sortedByDescending { article ->
                    // Score articles by relevance - title matches score higher
                    var score = 0
                    keywords.forEach { keyword ->
                        if (article.title.lowercase().contains(keyword)) score += 10
                        if (article.summary.lowercase().contains(keyword)) score += 5
                        if (article.category.lowercase().contains(keyword)) score += 3
                        if (article.content.lowercase().contains(keyword)) score += 1
                    }
                    score
                }
            }
        }
    }
    
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
    
    fun setSelectedCategory(category: String?) {
        _selectedCategory.value = category
    }
    
    fun toggleFavorite(article: Article) {
        viewModelScope.launch {
            repository.toggleFavorite(article.id, !article.isFavorite)
        }
    }
    
    fun fetchAndSaveArticles() {
        viewModelScope.launch {
            try {
                val languageCode = LanguageHelper.getLanguage(context)
                val articles = articleFetcher.fetchHealthcareArticles(languageCode)
                // Clear existing articles and insert new ones for the new language
                repository.deleteAllArticles()
                repository.insertArticles(articles)
            } catch (e: Exception) {
                // Handle error - articles will fall back to sample articles
                android.util.Log.e("ArticleViewModel", "Error fetching articles", e)
            }
        }
    }
    
    fun getArticleById(id: Long) = viewModelScope.launch {
        repository.getArticleById(id)
    }
    
    class AndroidViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArticleViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        
        companion object {
            @Volatile
            private var INSTANCE: AndroidViewModelFactory? = null
            
            fun getInstance(application: Application): AndroidViewModelFactory {
                return INSTANCE ?: synchronized(this) {
                    INSTANCE ?: AndroidViewModelFactory(application).also { INSTANCE = it }
                }
            }
        }
    }
}
