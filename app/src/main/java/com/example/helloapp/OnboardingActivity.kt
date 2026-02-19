package com.example.helloapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.helloapp.util.LanguageHelper

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var indicatorContainer: LinearLayout
    private lateinit var btnSkip: View
    private lateinit var btnNext: View
    
    private val onboardingPages = mutableListOf<OnboardingPage>()

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageHelper.applyLanguage(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        title = getString(R.string.onboarding_tutorial_title)
        
        // Initialize onboarding pages with localized strings (tutorial at start)
        initializePages()
        
        viewPager = findViewById(R.id.viewPager)
        indicatorContainer = findViewById(R.id.indicatorContainer)
        btnSkip = findViewById(R.id.btnSkip)
        btnNext = findViewById(R.id.btnNext)
        
        // Setup ViewPager
        val adapter = OnboardingAdapter(onboardingPages)
        viewPager.adapter = adapter
        
        // Setup indicators
        setupIndicators()
        updateIndicators(0)
        
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateIndicators(position)
                updateButtons(position)
            }
        })
        
        // Button click listeners
        btnSkip.setOnClickListener {
            finishOnboarding()
        }
        
        btnNext.setOnClickListener {
            if (viewPager.currentItem < onboardingPages.size - 1) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else {
                finishOnboarding()
            }
        }
    }
    
    private fun initializePages() {
        onboardingPages.clear()
        onboardingPages.addAll(listOf(
            OnboardingPage(
                iconResId = R.drawable.ic_health,
                title = getString(R.string.onboarding_tutorial_title),
                description = getString(R.string.onboarding_welcome_desc)
            ),
            OnboardingPage(
                iconResId = android.R.drawable.ic_menu_agenda,
                title = getString(R.string.onboarding_articles_title),
                description = getString(R.string.onboarding_articles_desc)
            ),
            OnboardingPage(
                iconResId = R.drawable.ic_growth,
                title = getString(R.string.onboarding_growth_title),
                description = getString(R.string.onboarding_growth_desc)
            ),
            OnboardingPage(
                iconResId = R.drawable.ic_pregnant,
                title = getString(R.string.onboarding_pregnancy_title),
                description = getString(R.string.onboarding_pregnancy_desc)
            ),
            OnboardingPage(
                iconResId = R.drawable.ic_vaccine,
                title = getString(R.string.onboarding_vaccine_title),
                description = getString(R.string.onboarding_vaccine_desc)
            ),
            OnboardingPage(
                iconResId = R.drawable.ic_clinic,
                title = getString(R.string.onboarding_clinics_title),
                description = getString(R.string.onboarding_clinics_desc)
            ),
            OnboardingPage(
                iconResId = android.R.drawable.ic_menu_save,
                title = getString(R.string.onboarding_offline_title),
                description = getString(R.string.onboarding_offline_desc)
            )
        ))
    }
    
    private fun setupIndicators() {
        indicatorContainer.removeAllViews()
        val indicators = arrayOfNulls<View>(onboardingPages.size)
        
        val layoutParams = LinearLayout.LayoutParams(
            24, 24
        ).apply {
            setMargins(8, 0, 8, 0)
        }
        
        for (i in indicators.indices) {
            indicators[i] = View(this).apply {
                background = ContextCompat.getDrawable(
                    this@OnboardingActivity,
                    R.drawable.indicator_inactive
                )
                this.layoutParams = layoutParams
            }
            indicatorContainer.addView(indicators[i])
        }
    }
    
    private fun updateIndicators(position: Int) {
        for (i in 0 until indicatorContainer.childCount) {
            val indicator = indicatorContainer.getChildAt(i)
            indicator.background = ContextCompat.getDrawable(
                this,
                if (i == position) R.drawable.indicator_active else R.drawable.indicator_inactive
            )
        }
    }
    
    private fun updateButtons(position: Int) {
        val btnNextText = findViewById<TextView>(R.id.btnNext)
        if (position == onboardingPages.size - 1) {
            btnNextText.text = getString(R.string.get_started)
            btnSkip.visibility = View.INVISIBLE
        } else {
            btnNextText.text = getString(R.string.next)
            btnSkip.visibility = View.VISIBLE
        }
    }
    
    private fun finishOnboarding() {
        // Mark onboarding as completed
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_ONBOARDING_COMPLETED, true).apply()
        
        // Go to main activity
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    
    companion object {
        const val PREFS_NAME = "onboarding_prefs"
        const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"
        
        fun isOnboardingCompleted(context: Context): Boolean {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return prefs.getBoolean(KEY_ONBOARDING_COMPLETED, false)
        }
    }
}

data class OnboardingPage(
    val iconResId: Int,
    val title: String,
    val description: String
)

class OnboardingAdapter(
    private val pages: List<OnboardingPage>
) : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_onboarding_page, parent, false)
        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    override fun getItemCount(): Int = pages.size

    class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgIcon: ImageView = itemView.findViewById(R.id.imgIcon)
        private val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        private val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)

        fun bind(page: OnboardingPage) {
            imgIcon.setImageResource(page.iconResId)
            txtTitle.text = page.title
            txtDescription.text = page.description
        }
    }
}
