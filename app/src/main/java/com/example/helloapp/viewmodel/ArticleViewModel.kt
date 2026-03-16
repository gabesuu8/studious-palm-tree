package com.example.helloapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.helloapp.data.AppDatabase
import com.example.helloapp.data.Article
import com.example.helloapp.repository.ArticleRepository
import com.example.helloapp.service.ArticleFetcher
import com.example.helloapp.util.SymptomCategories
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.util.keywordMatchesText
import com.example.helloapp.util.matchesWithTypo
import com.example.helloapp.util.suggestClosestMatchFromTexts
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
    
    /** Suggested search when no results (spelling / "did you mean"). */
    val suggestedSearchQuery: Flow<String?> by lazy {
        combine(allArticles, _searchQuery, _selectedCategory) { articles, query, category ->
            if (query.isBlank()) return@combine null
            var filtered = articles
            if (category != null) filtered = filtered.filter { it.category == category }
            val queryLower = query.lowercase().trim()
            val keywords = queryLower.split(" ").filter { it.isNotBlank() }
            if (keywords.isEmpty()) return@combine null
            val categoryFiltered = filtered
            filtered = filtered.filter { article ->
                keywords.any { keyword ->
                    keywordMatchesText(keyword, article.title) ||
                    keywordMatchesText(keyword, article.summary) ||
                    keywordMatchesText(keyword, article.content) ||
                    keywordMatchesText(keyword, article.category)
                }
            }
            if (filtered.isNotEmpty()) return@combine null
            suggestClosestMatchFromTexts(query, categoryFiltered.map { it.title to (it.title + " " + it.summary) })
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
                        keywordMatchesText(keyword, article.title) ||
                        keywordMatchesText(keyword, article.summary) ||
                        keywordMatchesText(keyword, article.content) ||
                        keywordMatchesText(keyword, article.category)
                    }
                }.sortedByDescending { article ->
                    // Score by relevance: exact substring > fuzzy/prefix; title > summary > category > content
                    var score = 0
                    keywords.forEach { keyword ->
                        val t = article.title.lowercase()
                        val s = article.summary.lowercase()
                        val c = article.category.lowercase()
                        val b = article.content.lowercase()
                        if (t.contains(keyword)) score += 10 else if (keywordMatchesText(keyword, article.title)) score += 7
                        if (s.contains(keyword)) score += 5 else if (keywordMatchesText(keyword, article.summary)) score += 3
                        if (c.contains(keyword)) score += 3 else if (keywordMatchesText(keyword, article.category)) score += 2
                        if (b.contains(keyword)) score += 1 else if (keywordMatchesText(keyword, article.content)) score += 1
                    }
                    score
                }
            }
        }
    }
    
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private data class ConditionProfile(
        val titleSubstrings: List<String>,
        val symptoms: List<String>,
        val keySymptoms: List<String>,
        val minSymptoms: Int = 2,
        val defaultUrgency: ConditionUrgency = ConditionUrgency.SEE_DOCTOR
    )

    enum class ConditionUrgency { EMERGENCY, SEE_DOCTOR, HOME_CARE }

    data class SymptomMatchResult(
        val article: Article,
        val confidence: MatchConfidence,
        val urgency: ConditionUrgency,
        val score: Double,
        val matchedSymptoms: List<String>,
        val differentiatingSymptoms: List<String>
    )

    enum class MatchConfidence { HIGH, MODERATE, LOW }

    private val conditionProfiles: List<ConditionProfile> = listOf(
        ConditionProfile(listOf("Malaria", "Paludisme"),
            listOf("fever", "headache", "chills", "sweating", "vomiting", "fatigue", "jaundice", "convulsions"),
            listOf("chills", "sweating", "convulsions", "fever"),
            defaultUrgency = ConditionUrgency.EMERGENCY),
        ConditionProfile(listOf("Diarrhoeal", "diarrhée", "Diarrhea"),
            listOf("diarrhea", "dehydration", "thirst", "sunken", "ors", "stool", "loose"),
            listOf("diarrhea", "loose", "ors", "dehydration", "thirst"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Dengue"),
            listOf("fever", "headache", "muscle", "joint", "rash", "nausea", "bleeding", "vomiting"),
            listOf("joint", "muscle", "rash", "nausea"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Typhoid", "typhoïde"),
            listOf("fever", "headache", "abdominal", "constipation", "rash", "weakness", "typhoid"),
            listOf("constipation", "typhoid", "abdominal"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Yellow fever", "Fièvre jaune", "jaune"),
            listOf("fever", "jaundice", "yellow", "bleeding", "vomiting", "kidney", "liver"),
            listOf("jaundice", "yellow", "liver", "kidney"),
            defaultUrgency = ConditionUrgency.EMERGENCY),
        ConditionProfile(listOf("Tuberculosis", "Tuberculose"),
            listOf("cough", "blood", "weight", "night sweats", "chest", "tuberculosis", "tb", "breathing"),
            listOf("night sweats", "tb", "tuberculosis", "cough", "chest", "weight"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Schistosomiasis", "Bilharzia", "bilharziose"),
            listOf("urine", "blood", "water", "swim", "bilharzia", "schisto", "bladder"),
            listOf("bilharzia", "schisto", "swim", "bladder"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Malnutrition", "malnutrition"),
            listOf("thin", "wasting", "swollen", "belly", "edema", "malnutrition", "stunting"),
            listOf("wasting", "stunting", "edema", "malnutrition"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Allergies", "Allergies"),
            listOf("sneezing", "runny nose", "itchy", "rash", "hives", "swelling", "anaphylaxis", "allergy"),
            listOf("hives", "anaphylaxis", "itchy", "allergy", "swelling"),
            defaultUrgency = ConditionUrgency.HOME_CARE),
        ConditionProfile(listOf("Migraine", "Migraines"),
            listOf("headache", "migraine", "nausea", "light", "aura", "throbbing"),
            listOf("migraine", "aura", "throbbing", "headache"),
            defaultUrgency = ConditionUrgency.HOME_CARE),
        ConditionProfile(listOf("Diabetes", "Diabète"),
            listOf("sugar", "thirst", "urination", "diabetes", "blood glucose", "insulin", "tired", "blurred"),
            listOf("blood glucose", "insulin", "diabetes"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("First aid", "Premiers secours"),
            listOf("bleeding", "pressure", "wound", "cut", "burn", "choking", "unconscious"),
            listOf("choking", "unconscious", "bleeding"),
            defaultUrgency = ConditionUrgency.EMERGENCY),
        ConditionProfile(listOf("Fracture", "fractures", "Entorse", "sprains"),
            listOf("fracture", "sprain", "swelling", "pain", "bone", "ankle", "wrist", "rice"),
            listOf("fracture", "sprain", "bone"),
            defaultUrgency = ConditionUrgency.EMERGENCY),
        ConditionProfile(listOf("Snake", "serpent", "Morsures"),
            listOf("snake", "bite", "venom", "swelling", "antivenom", "fang"),
            listOf("snake", "venom", "antivenom", "fang"),
            defaultUrgency = ConditionUrgency.EMERGENCY),
        ConditionProfile(listOf("Cholera", "Choléra"),
            listOf("cholera", "watery", "rice water", "dehydration", "vomiting", "leg cramp"),
            listOf("cholera", "rice water", "leg cramp", "vomiting"),
            defaultUrgency = ConditionUrgency.EMERGENCY),
        ConditionProfile(listOf("Soil-Transmitted", "Helminths", "Vers intestinaux"),
            listOf("worm", "worms", "deworm", "helminth", "stomach", "belly pain"),
            listOf("worm", "worms", "deworm", "helminth"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("River Blindness", "Onchocerciasis", "Cécité des rivières"),
            listOf("itch", "river", "blind", "eye", "blackfly", "ivermectin"),
            listOf("blackfly", "ivermectin", "river"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Lymphatic Filariasis", "Elephantiasis", "Filariose"),
            listOf("swelling", "leg", "elephant", "filariasis", "lymphatic"),
            listOf("elephant", "filariasis", "lymphatic"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Trachoma", "Trachome"),
            listOf("eye", "trachoma", "trichiasis", "eyelash", "blind"),
            listOf("trachoma", "trichiasis", "eyelash"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Postpartum", "Hémorragie"),
            listOf("bleeding", "postpartum", "after birth", "haemorrhage", "hemorrhage"),
            listOf("postpartum", "after birth"),
            defaultUrgency = ConditionUrgency.EMERGENCY),
        ConditionProfile(listOf("Newborn Care", "Soins du nouveau-né"),
            listOf("newborn", "baby", "first 24", "breastfeed", "skin to skin"),
            listOf("newborn", "skin to skin", "first 24"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Exclusive Breastfeeding", "Allaitement"),
            listOf("breastfeed", "breast milk", "exclusive", "lactation"),
            listOf("breast milk", "lactation", "exclusive"),
            defaultUrgency = ConditionUrgency.HOME_CARE),
        ConditionProfile(listOf("Complementary Feeding", "Alimentation de complément"),
            listOf("complementary", "weaning", "solid food", "6 months"),
            listOf("weaning", "complementary", "solid food"),
            defaultUrgency = ConditionUrgency.HOME_CARE),
        ConditionProfile(listOf("Vitamin A", "vitamine A"),
            listOf("vitamin a", "night blind", "vision", "supplement"),
            listOf("vitamin a", "night blind", "vision"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Anaemia", "Anémie"),
            listOf("anaemia", "anemia", "pale", "tired", "iron", "fatigue", "weakness", "dizziness"),
            listOf("anaemia", "anemia", "iron", "fatigue", "weakness"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Hepatitis A", "Hépatite"),
            listOf("hepatitis", "jaundice", "waterborne", "yellow"),
            listOf("hepatitis", "waterborne"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Meningitis", "Méningite"),
            listOf("meningitis", "stiff neck", "headache", "rash", "fontanelle"),
            listOf("meningitis", "stiff neck", "fontanelle"),
            defaultUrgency = ConditionUrgency.EMERGENCY),
        ConditionProfile(listOf("Measles", "Rougeole"),
            listOf("measles", "rash", "fever", "cough", "vaccine"),
            listOf("measles", "rash"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Influenza", "Grippe", "Flu"),
            listOf("fever", "cough", "breathing", "fatigue", "headache", "muscle", "sore throat"),
            listOf("cough", "breathing"),
            defaultUrgency = ConditionUrgency.HOME_CARE),
        ConditionProfile(listOf("HIV", "VIH"),
            listOf("hiv", "aids", "test", "antiretroviral", "condom"),
            listOf("hiv", "aids", "antiretroviral"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Lassa", "Lassa"),
            listOf("lassa", "rodent", "west africa", "bleeding"),
            listOf("lassa", "rodent"),
            defaultUrgency = ConditionUrgency.EMERGENCY),
        ConditionProfile(listOf("Ebola"),
            listOf("ebola", "bleeding", "outbreak", "contact"),
            listOf("ebola", "outbreak"),
            defaultUrgency = ConditionUrgency.EMERGENCY),
        ConditionProfile(listOf("Heat Exhaustion", "Épuisement", "chaleur"),
            listOf("heat", "heatstroke", "exhaustion", "sun", "dehydrat", "dizziness"),
            listOf("heatstroke", "heat", "dizziness"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Burns", "Brûlures"),
            listOf("burn", "scald", "blister"),
            listOf("scald", "blister", "burn"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Cuts and Wounds", "Coupures"),
            listOf("cut", "wound", "stitch", "bleeding", "tetanus"),
            listOf("wound", "stitch", "tetanus"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Fever in Children", "Fièvre chez l'enfant"),
            listOf("fever", "child", "children", "convulsion", "seizure"),
            listOf("convulsion", "seizure", "child", "children"),
            defaultUrgency = ConditionUrgency.EMERGENCY),
        ConditionProfile(listOf("Stress", "stress"),
            listOf("stress", "anxiety", "overwhelm", "cope"),
            listOf("stress", "anxiety", "overwhelm"),
            defaultUrgency = ConditionUrgency.HOME_CARE),
        ConditionProfile(listOf("Depression", "Dépression"),
            listOf("depression", "sad", "hopeless", "sadness"),
            listOf("depression", "hopeless", "sad"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Mental Health", "Santé Mentale"),
            listOf("mental health", "mental", "sad", "anxious", "help"),
            listOf("mental health", "mental"),
            defaultUrgency = ConditionUrgency.SEE_DOCTOR),
        ConditionProfile(listOf("Bed Nets", "Moustiquaires"),
            listOf("bed net", "mosquito net", "net", "itn", "mii"),
            listOf("bed net", "mosquito net", "itn", "mii"),
            defaultUrgency = ConditionUrgency.HOME_CARE),
        ConditionProfile(listOf("Vaccination Schedule", "Calendrier vaccinal"),
            listOf("vaccination", "vaccine", "epi", "pev", "schedule", "immunization"),
            listOf("vaccination", "vaccine", "immunization", "schedule"),
            defaultUrgency = ConditionUrgency.HOME_CARE)
    )

    @Suppress("unused")
    private val symptomMap: List<Pair<List<String>, List<String>>>
        get() = conditionProfiles.map { it.symptoms to it.titleSubstrings }

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
        "poop" to listOf("stool", "diarrhea"),
        "pooping" to listOf("stool", "diarrhea"),
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
        "loose stool" to listOf("diarrhea", "loose", "stool"),
        "watery stool" to listOf("watery", "diarrhea"),
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

    private val symptomPhrases: List<String> by lazy {
        val fromProfiles = conditionProfiles.flatMap { it.symptoms }.filter { " " in it }.distinct()
        val fromInformal = informalSymptomMap.keys.filter { " " in it }
        (fromProfiles + fromInformal).distinct().sortedByDescending { it.length }
    }

    private companion object {
        const val KEY_SYMPTOM_WEIGHT = 3
        const val COMMON_SYMPTOM_WEIGHT = 1
        const val MAX_RESULTS = 5
        const val HIGH_CONFIDENCE_THRESHOLD = 0.45
        const val MODERATE_CONFIDENCE_THRESHOLD = 0.25
    }

    /** Valid symptom terms for "Check for" tips — filters out condition names and treatments. */
    private val validCheckForTerms: Set<String> by lazy {
        val terms = mutableSetOf<String>()
        SymptomCategories.symptomCategories.forEach { (_, symptoms) ->
            symptoms.forEach { (_, keyword) ->
                keyword.split(" ").forEach { token -> terms.add(token) }
            }
        }
        terms += setOf(
            "constipation", "burn", "wound", "cut", "blister", "scald",
            "choking", "unconscious", "convulsion", "seizure", "pale",
            "sore", "throat", "leg", "cramp", "heat", "blood"
        )
        terms
    }

    fun getSymptomMatchResults(articles: List<Article>, symptomText: String): List<SymptomMatchResult> {
        if (symptomText.isBlank()) return emptyList()
        val inputLower = symptomText.trim().lowercase()
        val tokens = tokenizeSymptomInput(inputLower)
        if (tokens.isEmpty()) return emptyList()
        val expandedTerms = expandSymptomTokens(tokens)

        val results = mutableListOf<SymptomMatchResult>()

        for (profile in conditionProfiles) {
            val matched = mutableListOf<String>()
            val keyMatched = mutableListOf<String>()

            for (k in profile.symptoms) {
                if (keywordPresent(k, tokens, expandedTerms)) {
                    matched.add(k)
                    if (k in profile.keySymptoms) keyMatched.add(k)
                }
            }

            val passesMinimum = matched.size >= profile.minSymptoms || keyMatched.isNotEmpty()
            if (!passesMinimum) continue

            val matchingArticle = articles.firstOrNull { article ->
                profile.titleSubstrings.any { article.title.contains(it, ignoreCase = true) }
            } ?: continue

            val totalSymptoms = profile.symptoms.size
            val rawScore = matched.size * COMMON_SYMPTOM_WEIGHT + keyMatched.size * KEY_SYMPTOM_WEIGHT
            val conditionCoverage = matched.size.toDouble() / totalSymptoms
            val score = rawScore * (0.4 + 0.6 * conditionCoverage)

            val confidence = when {
                conditionCoverage >= HIGH_CONFIDENCE_THRESHOLD || keyMatched.size >= 2 -> MatchConfidence.HIGH
                conditionCoverage >= MODERATE_CONFIDENCE_THRESHOLD || keyMatched.isNotEmpty() -> MatchConfidence.MODERATE
                else -> MatchConfidence.LOW
            }

            val unmatchedKey = profile.keySymptoms
                .filter { it !in matched && it.split(" ").all { term -> term in validCheckForTerms } }
                .take(3)

            results.add(SymptomMatchResult(matchingArticle, confidence, profile.defaultUrgency, score, matched, unmatchedKey))
        }

        return results
            .sortedWith(compareBy<SymptomMatchResult> { it.confidence.ordinal }
                .thenByDescending { it.score })
            .take(MAX_RESULTS)
    }

    @Deprecated("Use getSymptomMatchResults for richer results", ReplaceWith("getSymptomMatchResults(articles, symptomText).map { it.article }"))
    fun getSymptomMatches(articles: List<Article>, symptomText: String): List<Article> {
        return getSymptomMatchResults(articles, symptomText).map { it.article }
    }

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

    private fun expandSymptomTokens(tokens: List<String>): Set<String> {
        val set = mutableSetOf<String>()
        for (t in tokens) {
            set.add(t)
            informalSymptomMap[t]?.let { set.addAll(it) }
        }
        return set
    }

    private fun keywordPresent(keyword: String, tokens: List<String>, expandedTerms: Set<String>): Boolean {
        val kLower = keyword.lowercase()
        return if (" " in keyword) {
            tokens.any { matchesWithTypo(it, kLower) } || expandedTerms.any { matchesWithTypo(it, kLower) }
        } else {
            expandedTerms.any { it == kLower || matchesWithTypo(it, kLower) }
        }
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
