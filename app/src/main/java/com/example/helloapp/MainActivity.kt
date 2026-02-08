package com.example.helloapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.helloapp.fragment.ArticlesFragment
import com.example.helloapp.fragment.ClinicsFragment
import com.example.helloapp.fragment.GrowthTrackerFragment
import com.example.helloapp.fragment.PregnancyTrackerFragment
import com.example.helloapp.fragment.VaccinationFragment
import com.example.helloapp.util.LanguageHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    
    private lateinit var bottomNavigation: BottomNavigationView
    private var currentFragmentTag: String = TAG_ARTICLES

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageHelper.applyLanguage(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Check if onboarding is completed
        if (!OnboardingActivity.isOnboardingCompleted(this)) {
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
            return
        }
        
        setContentView(R.layout.activity_main)
        
        bottomNavigation = findViewById(R.id.bottomNavigation)
        
        if (savedInstanceState != null) {
            currentFragmentTag = savedInstanceState.getString(KEY_CURRENT_TAB, TAG_ARTICLES)
        }
        
        setupBottomNavigation()
        
        // Load initial fragment only if this is a fresh start
        if (savedInstanceState == null) {
            loadFragment(TAG_ARTICLES)
        }
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_CURRENT_TAB, currentFragmentTag)
    }
    
    private fun setupBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener { item ->
            val tag = when (item.itemId) {
                R.id.nav_articles -> TAG_ARTICLES
                R.id.nav_clinics -> TAG_CLINICS
                R.id.nav_growth -> TAG_GROWTH
                R.id.nav_pregnancy -> TAG_PREGNANCY
                R.id.nav_vaccinations -> TAG_VACCINATION
                else -> TAG_ARTICLES
            }
            loadFragment(tag)
            true
        }
        
        // Set the selected item based on current fragment
        bottomNavigation.selectedItemId = when (currentFragmentTag) {
            TAG_ARTICLES -> R.id.nav_articles
            TAG_CLINICS -> R.id.nav_clinics
            TAG_GROWTH -> R.id.nav_growth
            TAG_PREGNANCY -> R.id.nav_pregnancy
            TAG_VACCINATION -> R.id.nav_vaccinations
            else -> R.id.nav_articles
        }
    }
    
    private fun loadFragment(tag: String) {
        // Don't reload if already showing this fragment
        if (currentFragmentTag == tag && supportFragmentManager.findFragmentByTag(tag) != null) {
            return
        }
        
        currentFragmentTag = tag
        
        val fragment = when (tag) {
            TAG_ARTICLES -> ArticlesFragment()
            TAG_CLINICS -> ClinicsFragment()
            TAG_GROWTH -> GrowthTrackerFragment()
            TAG_PREGNANCY -> PregnancyTrackerFragment()
            TAG_VACCINATION -> VaccinationFragment()
            else -> ArticlesFragment()
        }
        
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment, tag)
            .commit()
    }
    
    companion object {
        private const val TAG_ARTICLES = "articles"
        private const val TAG_CLINICS = "clinics"
        private const val TAG_GROWTH = "growth"
        private const val TAG_PREGNANCY = "pregnancy"
        private const val TAG_VACCINATION = "vaccination"
        private const val KEY_CURRENT_TAB = "current_tab"
    }
}
