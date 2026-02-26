package com.example.helloapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.helloapp.fragment.ArticlesFragment
import com.example.helloapp.fragment.ClinicsFragment
import com.example.helloapp.fragment.PregnancyTrackerFragment
import com.example.helloapp.fragment.SymptomPredictorFragment
import com.example.helloapp.fragment.VaccinationGrowthFragment
import com.example.helloapp.util.LanguageHelper
import com.example.helloapp.util.NotificationHelper
import com.example.helloapp.worker.HealthReminderWorker
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

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
        
        // Set up notification channels and schedule daily health reminders
        NotificationHelper.createChannels(this)
        requestNotificationPermissionIfNeeded()
        BootReceiver.scheduleHealthReminder(this)

        // TODO: Remove after testing — fires the reminder worker immediately
        WorkManager.getInstance(this).enqueue(
            OneTimeWorkRequestBuilder<HealthReminderWorker>().build()
        )

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
                R.id.nav_trackers -> TAG_TRACKERS
                R.id.nav_pregnancy -> TAG_PREGNANCY
                R.id.nav_symptoms -> TAG_SYMPTOMS
                else -> TAG_ARTICLES
            }
            loadFragment(tag)
            true
        }
        
        // Set the selected item based on current fragment
        bottomNavigation.selectedItemId = when (currentFragmentTag) {
            TAG_ARTICLES -> R.id.nav_articles
            TAG_CLINICS -> R.id.nav_clinics
            TAG_TRACKERS -> R.id.nav_trackers
            TAG_PREGNANCY -> R.id.nav_pregnancy
            TAG_SYMPTOMS -> R.id.nav_symptoms
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
            TAG_TRACKERS -> VaccinationGrowthFragment()
            TAG_PREGNANCY -> PregnancyTrackerFragment()
            TAG_SYMPTOMS -> SymptomPredictorFragment()
            else -> ArticlesFragment()
        }
        
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment, tag)
            .commit()
    }
    
    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_NOTIFICATION_PERMISSION
                )
            }
        }
    }

    companion object {
        private const val TAG_ARTICLES = "articles"
        private const val TAG_CLINICS = "clinics"
        private const val TAG_TRACKERS = "trackers"
        private const val TAG_PREGNANCY = "pregnancy"
        private const val TAG_SYMPTOMS = "symptoms"
        private const val KEY_CURRENT_TAB = "current_tab"
        private const val REQUEST_NOTIFICATION_PERMISSION = 1001
    }
}
