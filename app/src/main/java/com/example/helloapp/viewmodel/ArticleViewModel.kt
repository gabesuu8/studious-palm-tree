package com.example.helloapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.helloapp.data.AppDatabase
import com.example.helloapp.repository.ArticleRepository
import com.example.helloapp.service.ArticleFetcher
import kotlinx.coroutines.launch

class ArticleViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val repository = ArticleRepository(database.articleDao())
    private val articleFetcher = ArticleFetcher()
    
    val allArticles = repository.allArticles
    
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
