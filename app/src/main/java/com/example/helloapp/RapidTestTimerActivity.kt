package com.example.helloapp

import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.helloapp.R
import com.google.android.material.appbar.MaterialToolbar

class RapidTestTimerActivity : AppCompatActivity() {

    companion object {
        private const val DEFAULT_TIME_MM_SS = "15:00"
        private const val MAX_TOTAL_SECONDS = 120 * 60 // 120 minutes
        private const val MS_PER_SECOND = 1000L
        private const val KEY_REMAINING_MS = "remaining_ms"
        private const val KEY_WAS_RUNNING = "was_running"
    }

    private var countDownTimer: CountDownTimer? = null
    private var remainingMillis: Long = 0
    private var isRunning: Boolean = false

    private lateinit var countdownText: TextView
    private lateinit var timerDoneText: TextView
    private lateinit var timeEdit: EditText
    private lateinit var btnStart: Button
    private lateinit var btnPause: Button
    private lateinit var btnReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rapid_test_timer)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        countdownText = findViewById(R.id.countdownText)
        timerDoneText = findViewById(R.id.timerDoneText)
        timeEdit = findViewById(R.id.timeEdit)
        btnStart = findViewById(R.id.btnStart)
        btnPause = findViewById(R.id.btnPause)
        btnReset = findViewById(R.id.btnReset)

        timeEdit.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                applyInputToTimer()
                timeEdit.clearFocus()
                true
            } else false
        }

        btnStart.setOnClickListener { startTimer() }
        btnPause.setOnClickListener { pauseTimer() }
        btnReset.setOnClickListener { resetTimer() }

        if (savedInstanceState != null) {
            remainingMillis = savedInstanceState.getLong(KEY_REMAINING_MS, 0)
            isRunning = savedInstanceState.getBoolean(KEY_WAS_RUNNING, false)
            if (remainingMillis > 0) {
                updateDisplayFromMillis(remainingMillis)
                syncTimeEditFromMillis(remainingMillis)
                if (isRunning) startTimer() else setButtonsForPaused()
            } else {
                initIdleState()
            }
        } else {
            timeEdit.setText(DEFAULT_TIME_MM_SS)
            remainingMillis = parseTimeInputToMillis(DEFAULT_TIME_MM_SS)
            initIdleState()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_REMAINING_MS, remainingMillis)
        outState.putBoolean(KEY_WAS_RUNNING, isRunning)
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    /** Parse "mm:ss" or "mm" to total millis. Invalid input returns default 15:00. */
    private fun parseTimeInputToMillis(input: String): Long {
        val s = input.trim()
        if (s.isEmpty()) return parseTimeInputToMillis(DEFAULT_TIME_MM_SS)
        val parts = s.split(":")
        return when (parts.size) {
            1 -> {
                val mins = parts[0].toIntOrNull()?.coerceIn(0, 120) ?: 15
                (mins * 60 * MS_PER_SECOND).toLong()
            }
            2 -> {
                val mins = parts[0].toIntOrNull()?.coerceIn(0, 120) ?: 0
                val secs = parts[1].toIntOrNull()?.coerceIn(0, 59) ?: 0
                ((mins * 60 + secs).coerceAtMost(MAX_TOTAL_SECONDS) * MS_PER_SECOND).toLong()
            }
            else -> parseTimeInputToMillis(DEFAULT_TIME_MM_SS)
        }
    }

    private fun formatMillisToMmSs(millis: Long): String {
        val totalSeconds = (millis / MS_PER_SECOND).toInt()
        val mins = totalSeconds / 60
        val secs = totalSeconds % 60
        return getString(R.string.rapid_test_time_format, mins, secs)
    }

    /** When user presses Enter: parse input, update remainingMillis and display, sync edit text. */
    private fun applyInputToTimer() {
        if (isRunning) return
        val input = timeEdit.text.toString()
        remainingMillis = parseTimeInputToMillis(input)
        syncTimeEditFromMillis(remainingMillis)
        updateDisplayFromMillis(remainingMillis)
    }

    private fun syncTimeEditFromMillis(millis: Long) {
        timeEdit.setText(formatMillisToMmSs(millis))
    }

    private fun getInputMillis(): Long = parseTimeInputToMillis(timeEdit.text.toString())

    private fun startTimer() {
        if (isRunning) return
        if (remainingMillis <= 0) {
            remainingMillis = getInputMillis()
            if (remainingMillis <= 0) remainingMillis = parseTimeInputToMillis(DEFAULT_TIME_MM_SS)
            syncTimeEditFromMillis(remainingMillis)
        }
        isRunning = true
        countdownText.visibility = View.VISIBLE
        timerDoneText.visibility = View.GONE
        btnStart.isEnabled = false
        btnPause.isEnabled = true
        timeEdit.isEnabled = false

        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(remainingMillis, MS_PER_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                remainingMillis = millisUntilFinished
                updateDisplayFromMillis(millisUntilFinished)
            }

            override fun onFinish() {
                remainingMillis = 0
                isRunning = false
                countdownText.visibility = View.GONE
                timerDoneText.visibility = View.VISIBLE
                setButtonsForFinished()
                vibrateDone()
            }
        }.start()
    }

    private fun pauseTimer() {
        if (!isRunning) return
        countDownTimer?.cancel()
        countDownTimer = null
        isRunning = false
        setButtonsForPaused()
    }

    private fun resetTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
        isRunning = false
        remainingMillis = getInputMillis()
        if (remainingMillis <= 0) remainingMillis = parseTimeInputToMillis(DEFAULT_TIME_MM_SS)
        syncTimeEditFromMillis(remainingMillis)
        updateDisplayFromMillis(remainingMillis)
        initIdleState()
    }

    private fun initIdleState() {
        updateDisplayFromMillis(remainingMillis)
        syncTimeEditFromMillis(remainingMillis)
        countdownText.visibility = View.VISIBLE
        timerDoneText.visibility = View.GONE
        btnStart.isEnabled = true
        btnPause.isEnabled = false
        btnPause.text = getString(R.string.rapid_test_pause)
        timeEdit.isEnabled = true
    }

    private fun setButtonsForPaused() {
        btnStart.isEnabled = true
        btnStart.text = getString(R.string.rapid_test_resume)
        btnPause.isEnabled = false
        timeEdit.isEnabled = true
    }

    private fun setButtonsForFinished() {
        btnStart.isEnabled = true
        btnStart.text = getString(R.string.rapid_test_start)
        btnPause.isEnabled = false
        timeEdit.isEnabled = true
    }

    private fun updateDisplayFromMillis(millis: Long) {
        val totalSeconds = (millis / MS_PER_SECOND).toInt()
        val mins = totalSeconds / 60
        val secs = totalSeconds % 60
        countdownText.text = getString(R.string.rapid_test_time_format, mins, secs)
    }

    private fun vibrateDone() {
        val vibrator = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            getSystemService(VibratorManager::class.java)?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            getSystemService(Vibrator::class.java)
        }
        if (vibrator != null && vibrator.hasVibrator()) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(500)
            }
        }
    }
}
