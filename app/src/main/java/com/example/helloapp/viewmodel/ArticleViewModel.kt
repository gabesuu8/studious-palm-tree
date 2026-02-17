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
import com.example.helloapp.util.suggestClosestMatch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
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
    
    /** Suggested search when no results (spelling / "did you mean"). */
    val suggestedSearchQuery: Flow<String?> by lazy {
        combine(allArticles, _searchQuery, _selectedCategory) { articles, query, category ->
            if (query.isBlank()) return@combine null
            var filtered = articles
            if (category != null) filtered = filtered.filter { it.category == category }
            val queryLower = query.lowercase().trim()
            val keywords = queryLower.split(" ").filter { it.isNotBlank() }
            if (keywords.isEmpty()) return@combine null
            filtered = filtered.filter { article ->
                keywords.any { keyword ->
                    article.title.lowercase().contains(keyword) ||
                    article.summary.lowercase().contains(keyword) ||
                    article.content.lowercase().contains(keyword) ||
                    article.category.lowercase().contains(keyword)
                }
            }
            if (filtered.isNotEmpty()) return@combine null
            suggestClosestMatch(query, articles.map { it.title })
        }
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

    /**
     * Symptom keywords per condition. Each pair: (symptom keywords, title substrings to match article).
     * Title substrings can be EN or FR so we match article.title.contains(any).
     */
    private val symptomMap: List<Pair<List<String>, List<String>>> = listOf(
        listOf("fever", "headache", "chills", "sweating", "vomiting", "fatigue", "jaundice", "convulsions") to listOf("Malaria", "Paludisme"),
        listOf("diarrhoea", "diarrhea", "dehydration", "thirst", "sunken", "ors", "stool", "loose") to listOf("Diarrhoeal", "diarrhée", "Diarrhea"),
        listOf("fever", "headache", "muscle", "joint", "rash", "nausea", "bleeding", "vomiting") to listOf("Dengue"),
        listOf("fever", "headache", "abdominal", "constipation", "rash", "weakness", "typhoid") to listOf("Typhoid", "typhoïde"),
        listOf("fever", "jaundice", "yellow", "bleeding", "vomiting", "kidney", "liver") to listOf("Yellow fever", "Fièvre jaune", "jaune"),
        listOf("cough", "blood", "weight", "night sweats", "chest", "tuberculosis", "tb") to listOf("Tuberculosis", "Tuberculose"),
        listOf("urine", "blood", "water", "swim", "bilharzia", "schisto", "bladder") to listOf("Schistosomiasis", "Bilharzia", "bilharziose"),
        listOf("thin", "wasting", "swollen", "belly", "edema", "malnutrition", "stunting") to listOf("Malnutrition", "malnutrition"),
        listOf("sneezing", "runny nose", "itchy", "rash", "hives", "swelling", "anaphylaxis", "allergy") to listOf("Allergies", "Allergies"),
        listOf("headache", "migraine", "nausea", "light", "aura", "throbbing") to listOf("Migraine", "Migraines"),
        listOf("sugar", "thirst", "urination", "diabetes", "blood glucose", "insulin", "tired", "blurred") to listOf("Diabetes", "Diabète"),
        listOf("bleeding", "pressure", "wound", "cut", "burn", "choking", "unconscious") to listOf("First aid", "Premiers secours"),
        listOf("fracture", "sprain", "swelling", "pain", "bone", "ankle", "wrist", "rice") to listOf("Fracture", "fractures", "Entorse", "sprains"),
        listOf("snake", "bite", "venom", "swelling", "antivenom", "fang") to listOf("Snake", "serpent", "Morsures"),
        listOf("cholera", "watery", "rice water", "dehydration", "vomiting", "leg cramp") to listOf("Cholera", "Choléra")
    )

    /** Match articles by symptom keywords; returns list sorted by relevance (most matches first). */
    fun getSymptomMatches(articles: List<Article>, symptomText: String): List<Article> {
        if (symptomText.isBlank()) return emptyList()
        val words = symptomText.lowercase().trim().split(" ").filter { it.length >= 2 }
        if (words.isEmpty()) return emptyList()
        val scored = articles.mapNotNull { article ->
            var bestScore = 0
            for ((keywords, titleSubstrings) in symptomMap) {
                if (!titleSubstrings.any { article.title.contains(it, ignoreCase = true) }) continue
                val score = words.count { w -> keywords.any { k -> k.contains(w) || w.contains(k) } }
                if (score > bestScore) bestScore = score
            }
            if (bestScore > 0) article to bestScore else null
        }
        return scored.sortedByDescending { it.second }.map { it.first }.distinct()
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
