package com.example.helloapp.viewmodel

import android.app.Application
import com.example.helloapp.data.Article
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ArticleViewModelSymptomMatchTest {

    private lateinit var viewModel: ArticleViewModel

    private fun article(title: String) = Article(
        title = title,
        content = "",
        summary = "",
        source = "test"
    )

    @Before
    fun setUp() {
        val mockApp = mockk<Application>(relaxed = true)
        every { mockApp.applicationContext } returns mockApp
        viewModel = ArticleViewModel(mockApp)
    }

    // --- Basic matching ---

    @Test
    fun emptyInput_returnsEmptyList() {
        val articles = listOf(article("Malaria"))
        assertTrue(viewModel.getSymptomMatchResults(articles, "").isEmpty())
        assertTrue(viewModel.getSymptomMatchResults(articles, "   ").isEmpty())
    }

    @Test
    fun noArticles_returnsEmptyList() {
        assertTrue(viewModel.getSymptomMatchResults(emptyList(), "fever chills").isEmpty())
    }

    @Test
    fun malariaSymptoms_matchesMalariaArticle() {
        val articles = listOf(article("Malaria Prevention and Treatment"))
        val results = viewModel.getSymptomMatchResults(articles, "fever chills sweating")
        assertEquals(1, results.size)
        assertTrue(results[0].article.title.contains("Malaria"))
        assertTrue(results[0].matchedSymptoms.contains("fever"))
        assertTrue(results[0].matchedSymptoms.contains("chills"))
        assertTrue(results[0].matchedSymptoms.contains("sweating"))
    }

    @Test
    fun diarrheaSymptoms_matchesDiarrhealArticle() {
        val articles = listOf(article("Diarrhoeal Diseases"))
        val results = viewModel.getSymptomMatchResults(articles, "diarrhea dehydration thirst")
        assertEquals(1, results.size)
        assertTrue(results[0].matchedSymptoms.contains("diarrhea"))
    }

    @Test
    fun unrelatedSymptoms_noMatch() {
        val articles = listOf(article("Malaria Prevention"))
        // "headache" alone doesn't meet the minimum 2 symptoms requirement
        val results = viewModel.getSymptomMatchResults(articles, "headache")
        // Should not match since malaria needs minSymptoms=2 and headache alone is just 1
        assertTrue(results.isEmpty())
    }

    // --- Confidence levels ---

    @Test
    fun highConfidence_withMultipleKeySymptoms() {
        val articles = listOf(article("Malaria Prevention"))
        // chills and sweating are both key symptoms; convulsions is also key
        val results = viewModel.getSymptomMatchResults(articles, "fever chills sweating convulsions")
        assertEquals(1, results.size)
        assertEquals(ArticleViewModel.MatchConfidence.HIGH, results[0].confidence)
    }

    @Test
    fun moderateConfidence_withOneKeySymptom() {
        val articles = listOf(article("Malaria"))
        // "chills" is a key symptom, "fever" is common
        val results = viewModel.getSymptomMatchResults(articles, "fever chills")
        assertEquals(1, results.size)
        assertEquals(ArticleViewModel.MatchConfidence.MODERATE, results[0].confidence)
    }

    // --- Urgency ---

    @Test
    fun malariaUrgency_isEmergency() {
        val articles = listOf(article("Malaria"))
        val results = viewModel.getSymptomMatchResults(articles, "fever chills sweating")
        assertEquals(ArticleViewModel.ConditionUrgency.EMERGENCY, results[0].urgency)
    }

    @Test
    fun allergiesUrgency_isHomeCare() {
        val articles = listOf(article("Allergies in Children"))
        val results = viewModel.getSymptomMatchResults(articles, "sneezing itchy hives rash")
        assertEquals(1, results.size)
        assertEquals(ArticleViewModel.ConditionUrgency.HOME_CARE, results[0].urgency)
    }

    // --- Informal symptom expansion ---

    @Test
    fun informalTerms_expandToFormalSymptoms() {
        val articles = listOf(article("Diarrhoeal Diseases"))
        // "poop" should expand to stool/diarrhoea/diarrhea; "tummy" to abdominal/stomach
        val results = viewModel.getSymptomMatchResults(articles, "poop loose stool dehydration")
        assertFalse("Informal terms should match", results.isEmpty())
    }

    @Test
    fun bellyExpands_toAbdominal() {
        val articles = listOf(article("Typhoid Fever and typhoïde"))
        // "belly" expands to "abdominal"/"stomach"; typhoid profile has "abdominal"
        val results = viewModel.getSymptomMatchResults(articles, "fever belly constipation")
        assertFalse("'belly' should expand to match abdominal", results.isEmpty())
    }

    @Test
    fun exhaustedExpands_toFatigue() {
        val articles = listOf(article("Malaria"))
        // "exhausted" expands to "fatigue"; with "fever" and "chills" that's enough
        val results = viewModel.getSymptomMatchResults(articles, "fever chills exhausted")
        assertFalse(results.isEmpty())
        assertTrue(results[0].matchedSymptoms.contains("fatigue"))
    }

    // --- Multiple article matching ---

    @Test
    fun multipleArticles_matchesRelevantOnes() {
        val articles = listOf(
            article("Malaria Prevention"),
            article("Dengue Fever Guide"),
            article("Allergies in Children")
        )
        // "fever headache rash" could match Malaria and Dengue
        val results = viewModel.getSymptomMatchResults(articles, "fever headache rash joint muscle")
        assertTrue("Should match at least one article", results.isNotEmpty())
        assertTrue("Dengue should be among results",
            results.any { it.article.title.contains("Dengue") })
    }

    @Test
    fun maxResults_cappedAtFive() {
        val articles = listOf(
            article("Malaria"), article("Dengue"), article("Typhoid Fever"),
            article("Yellow fever"), article("Cholera"),
            article("Measles"), article("Fever in Children"),
            article("Meningitis")
        )
        // "fever" + common symptoms that could match many conditions
        val results = viewModel.getSymptomMatchResults(articles, "fever headache vomiting rash bleeding")
        assertTrue("Results should be at most 5", results.size <= 5)
    }

    // --- Sorting ---

    @Test
    fun results_sortedByConfidenceThenScore() {
        val articles = listOf(
            article("Malaria"), article("Dengue"), article("Measles")
        )
        val results = viewModel.getSymptomMatchResults(articles, "fever headache rash chills sweating")
        if (results.size >= 2) {
            for (i in 0 until results.size - 1) {
                val curr = results[i]
                val next = results[i + 1]
                // Higher confidence (lower ordinal) should come first
                assertTrue(
                    "Results should be sorted by confidence then score",
                    curr.confidence.ordinal <= next.confidence.ordinal
                )
            }
        }
    }

    // --- Differentiating symptoms ---

    @Test
    fun differentiatingSymptoms_containsUnmatchedKeySymptoms() {
        val articles = listOf(article("Malaria"))
        // Only match "fever" and "chills"; "sweating" and "convulsions" are key but not provided
        val results = viewModel.getSymptomMatchResults(articles, "fever chills")
        assertEquals(1, results.size)
        // "sweating" and "convulsions" are key symptoms not matched
        assertTrue("Should suggest unmatched key symptoms",
            results[0].differentiatingSymptoms.isNotEmpty())
    }

    // --- French article titles ---

    @Test
    fun frenchTitleMatches_paludisme() {
        val articles = listOf(article("Paludisme : prévention et traitement"))
        val results = viewModel.getSymptomMatchResults(articles, "fever chills sweating")
        assertFalse("French title 'Paludisme' should match malaria profile", results.isEmpty())
    }

    @Test
    fun frenchTitleMatches_diarrhee() {
        val articles = listOf(article("Maladies diarrhéiques et diarrhée"))
        val results = viewModel.getSymptomMatchResults(articles, "diarrhea dehydration thirst")
        assertFalse("French title containing 'diarrhée' should match", results.isEmpty())
    }
}
