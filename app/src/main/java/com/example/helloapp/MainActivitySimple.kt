package com.example.helloapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivitySimple : AppCompatActivity() {
    private val TAG = "MainActivitySimple"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate started")
        
        try {
            // Create a simple layout programmatically to test
            val textView = TextView(this)
            textView.text = "App is working! If you see this, the basic app loads."
            textView.textSize = 24f
            setContentView(textView)
            Log.d(TAG, "App loaded successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error in onCreate", e)
            e.printStackTrace()
            throw e
        }
    }
}
