package com.example.helloapp.util

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import java.util.Locale

object LanguageHelper {
    private const val PREF_NAME = "language_pref"
    private const val KEY_LANGUAGE = "selected_language"
    
    const val ENGLISH = "en"
    const val FRENCH = "fr"
    
    fun getLanguage(context: Context): String {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        // If no language is set yet, explicitly set English as default
        if (!prefs.contains(KEY_LANGUAGE)) {
            prefs.edit().putString(KEY_LANGUAGE, ENGLISH).apply()
        }
        return prefs.getString(KEY_LANGUAGE, ENGLISH) ?: ENGLISH
    }
    
    fun setLanguage(context: Context, languageCode: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LANGUAGE, languageCode).apply()
    }
    
    fun applyLanguage(context: Context): Context {
        val languageCode = getLanguage(context)
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        
        return context.createConfigurationContext(config)
    }
    
    fun isFrench(context: Context): Boolean {
        return getLanguage(context) == FRENCH
    }
}
