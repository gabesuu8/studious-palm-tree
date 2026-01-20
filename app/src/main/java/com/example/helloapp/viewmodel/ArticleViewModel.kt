package com.example.helloapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.helloapp.data.AppDatabase
import com.example.helloapp.data.Article
import com.example.helloapp.repository.ArticleRepository
import com.example.helloapp.service.ArticleFetcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ArticleViewModel(application: Application) : AndroidViewModel(application) {
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
    
    val allArticles: Flow<List<Article>> by lazy {
        try {
            android.util.Log.d("ArticleViewModel", "Getting allArticles flow")
            repository.allArticles
        } catch (e: Exception) {
            android.util.Log.e("ArticleViewModel", "Error getting allArticles", e)
            throw e
        }
    }
    
    // Filtered articles based on search query
    val filteredArticles: Flow<List<Article>> by lazy {
        combine(allArticles, _searchQuery) { articles, query ->
            if (query.isBlank()) {
                articles
            } else {
                val queryLower = query.lowercase().trim()
                val keywords = queryLower.split(" ").filter { it.isNotBlank() }
                
                articles.filter { article ->
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
    
    fun fetchAndSaveArticles() {
        viewModelScope.launch {
            try {
                val articles = articleFetcher.fetchHealthcareArticles()
                repository.insertArticles(articles)
            } catch (e: Exception) {
                // Handle error - articles will fall back to sample articles
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
