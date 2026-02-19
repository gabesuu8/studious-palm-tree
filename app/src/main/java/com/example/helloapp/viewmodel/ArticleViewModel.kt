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
import com.example.helloapp.util.matchesWithTypo
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
        listOf("cholera", "watery", "rice water", "dehydration", "vomiting", "leg cramp") to listOf("Cholera", "Choléra"),
        listOf("worm", "worms", "deworm", "helminth", "stomach", "belly pain") to listOf("Soil-Transmitted", "Helminths", "Vers intestinaux"),
        listOf("itch", "river", "blind", "eye", "blackfly", "ivermectin") to listOf("River Blindness", "Onchocerciasis", "Cécité des rivières"),
        listOf("swelling", "leg", "elephant", "filariasis", "lymphatic") to listOf("Lymphatic Filariasis", "Elephantiasis", "Filariose"),
        listOf("eye", "trachoma", "trichiasis", "eyelash", "blind") to listOf("Trachoma", "Trachome"),
        listOf("bleeding", "postpartum", "after birth", "haemorrhage", "hemorrhage") to listOf("Postpartum", "Hémorragie"),
        listOf("newborn", "baby", "first 24", "breastfeed", "skin to skin") to listOf("Newborn Care", "Soins du nouveau-né"),
        listOf("breastfeed", "breast milk", "exclusive", "lactation") to listOf("Exclusive Breastfeeding", "Allaitement"),
        listOf("complementary", "weaning", "solid food", "6 months") to listOf("Complementary Feeding", "Alimentation de complément"),
        listOf("vitamin a", "night blind", "vision", "supplement") to listOf("Vitamin A", "vitamine A"),
        listOf("anaemia", "anemia", "pale", "tired", "iron") to listOf("Anaemia", "Anémie"),
        listOf("hepatitis", "jaundice", "waterborne", "yellow") to listOf("Hepatitis A", "Hépatite"),
        listOf("meningitis", "stiff neck", "headache", "rash", "fontanelle") to listOf("Meningitis", "Méningite"),
        listOf("measles", "rash", "fever", "cough", "vaccine") to listOf("Measles", "Rougeole"),
        listOf("hiv", "aids", "test", "antiretroviral", "condom") to listOf("HIV", "VIH"),
        listOf("lassa", "rodent", "west africa", "bleeding") to listOf("Lassa", "Lassa"),
        listOf("ebola", "bleeding", "outbreak", "contact") to listOf("Ebola"),
        listOf("heat", "heatstroke", "exhaustion", "sun", "dehydrat") to listOf("Heat Exhaustion", "Épuisement", "chaleur"),
        listOf("burn", "scald", "blister") to listOf("Burns", "Brûlures"),
        listOf("cut", "wound", "stitch", "bleeding", "tetanus") to listOf("Cuts and Wounds", "Coupures"),
        listOf("fever", "child", "children", "convulsion", "seizure") to listOf("Fever in Children", "Fièvre chez l'enfant"),
        listOf("stress", "anxiety", "overwhelm", "cope") to listOf("Stress", "stress"),
        listOf("depression", "sad", "hopeless", "sadness") to listOf("Depression", "Dépression"),
        listOf("mental health", "mental", "sad", "anxious", "help") to listOf("Mental Health", "Santé Mentale"),
        listOf("bed net", "mosquito net", "net", "itn", "mii") to listOf("Bed Nets", "Moustiquaires"),
        listOf("vaccination", "vaccine", "epi", "pev", "schedule", "immunization") to listOf("Vaccination Schedule", "Calendrier vaccinal")
    )

    /** Informal/colloquial terms → formal symptom keywords (for expansion). */
    private val informalSymptomMap: Map<String, List<String>> = mapOf(
        "belly" to listOf("abdominal", "stomach"),
        "stomach" to listOf("abdominal"),
        "throwing up" to listOf("vomiting"),
        "puke" to listOf("vomiting"),
        "puking" to listOf("vomiting"),
        "sick" to listOf("vomiting", "nausea"),
        "pee" to listOf("urination", "urine"),
        "peeing" to listOf("urination", "urine"),
        "poop" to listOf("stool", "diarrhoea", "diarrhea"),
        "pooping" to listOf("stool", "diarrhoea", "diarrhea"),
        "runny nose" to listOf("sneezing"),
        "stuffy nose" to listOf("sneezing"),
        "sweating at night" to listOf("night sweats"),
        "night sweat" to listOf("night sweats"),
        "hurts" to listOf("pain"),
        "hurting" to listOf("pain"),
        "achy" to listOf("pain", "muscle"),
        "tummy" to listOf("abdominal", "stomach"),
        "tired" to listOf("fatigue"),
        "exhausted" to listOf("fatigue"),
        "weak" to listOf("weakness", "fatigue"),
        "skin yellow" to listOf("jaundice", "yellow"),
        "yellow skin" to listOf("jaundice", "yellow"),
        "coughing blood" to listOf("cough", "blood"),
        "blood in pee" to listOf("urine", "blood"),
        "blood in stool" to listOf("blood", "stool"),
        "loose stool" to listOf("diarrhoea", "diarrhea", "loose", "stool"),
        "watery stool" to listOf("watery", "diarrhoea", "diarrhea"),
        "rice water stool" to listOf("rice water", "cholera", "watery"),
        "leg cramps" to listOf("leg cramp"),
        "muscle pain" to listOf("muscle", "pain"),
        "joint pain" to listOf("joint", "pain"),
        "high sugar" to listOf("sugar", "diabetes"),
        "peeing a lot" to listOf("urination", "diabetes"),
        "very thirsty" to listOf("thirst", "dehydration"),
        "sunken eyes" to listOf("sunken", "dehydration"),
        "broken bone" to listOf("fracture", "bone"),
        "twisted ankle" to listOf("sprain", "ankle"),
        "snake bite" to listOf("snake", "bite"),
        "allergic" to listOf("allergy"),
        "itchy eyes" to listOf("itchy", "allergy"),
        "weight loss" to listOf("weight"),
        "losing weight" to listOf("weight"),
        "chest pain" to listOf("chest", "pain"),
        "blood glucose" to listOf("sugar", "diabetes")
    )

    /** All multi-word phrases from symptom keywords + informal keys, longest first for greedy tokenization. */
    private val symptomPhrases: List<String> by lazy {
        val fromMap = symptomMap.flatMap { it.first }.filter { " " in it }.distinct()
        val fromInformal = informalSymptomMap.keys.filter { " " in it }
        (fromMap + fromInformal).distinct().sortedByDescending { it.length }
    }

    /** Match articles by symptom keywords; supports multi-word phrases, informal expansion, and typo tolerance. */
    fun getSymptomMatches(articles: List<Article>, symptomText: String): List<Article> {
        if (symptomText.isBlank()) return emptyList()
        val inputLower = symptomText.trim().lowercase()
        val tokens = tokenizeSymptomInput(inputLower)
        if (tokens.isEmpty()) return emptyList()
        val expandedTerms = expandSymptomTokens(tokens)
        val scored = articles.mapNotNull { article ->
            var bestScore = 0
            for ((keywords, titleSubstrings) in symptomMap) {
                if (!titleSubstrings.any { article.title.contains(it, ignoreCase = true) }) continue
                val score = countKeywordMatches(keywords, tokens, expandedTerms)
                if (score > bestScore) bestScore = score
            }
            if (bestScore > 0) article to bestScore else null
        }
        return scored.sortedByDescending { it.second }.map { it.first }.distinct()
    }

    /** Extract phrases first (longest first), then single words (length >= 2). */
    private fun tokenizeSymptomInput(input: String): List<String> {
        var remaining = input
        val result = mutableListOf<String>()
        for (phrase in symptomPhrases) {
            while (remaining.contains(phrase)) {
                result.add(phrase)
                remaining = remaining.replaceFirst(phrase, " ").replace(Regex("\\s+"), " ").trim()
            }
        }
        result.addAll(remaining.split(" ").map { it.trim() }.filter { it.length >= 2 })
        return result
    }

    /** Expand tokens with informal synonyms for matching. */
    private fun expandSymptomTokens(tokens: List<String>): Set<String> {
        val set = mutableSetOf<String>()
        for (t in tokens) {
            set.add(t)
            informalSymptomMap[t]?.let { set.addAll(it) }
        }
        return set
    }

    /** Count how many of [keywords] match [tokens] or [expandedTerms] (exact or typo). */
    private fun countKeywordMatches(keywords: List<String>, tokens: List<String>, expandedTerms: Set<String>): Int {
        var count = 0
        for (k in keywords) {
            val kLower = k.lowercase()
            if (" " in k) {
                if (tokens.any { matchesWithTypo(it, kLower) } || expandedTerms.any { matchesWithTypo(it, kLower) }) count++
            } else {
                if (expandedTerms.any { it == kLower || matchesWithTypo(it, kLower) }) count++
            }
        }
        return count
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
