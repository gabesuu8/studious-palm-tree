package com.example.helloapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 *2\u00020\u0001:\u0001*B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0002J\b\u0010\u0017\u001a\u00020\u000eH\u0002J\b\u0010\u0018\u001a\u00020\u0013H\u0002J\u0012\u0010\u0019\u001a\u00020\u00132\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u0013H\u0014J\u0010\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001bH\u0014J\b\u0010\u001f\u001a\u00020\fH\u0016J\u0010\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u0015H\u0002J\b\u0010\"\u001a\u00020\u0013H\u0002J\b\u0010#\u001a\u00020\u0013H\u0002J\b\u0010$\u001a\u00020\u0013H\u0002J\b\u0010%\u001a\u00020\u0013H\u0002J\b\u0010&\u001a\u00020\u0013H\u0002J\u0010\u0010\'\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u000eH\u0002J\u0010\u0010(\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u000eH\u0002J\b\u0010)\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/example/helloapp/RapidTestTimerActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "btnPause", "Landroid/widget/Button;", "btnReset", "btnStart", "countDownTimer", "Landroid/os/CountDownTimer;", "countdownText", "Landroid/widget/TextView;", "isRunning", "", "remainingMillis", "", "timeEdit", "Landroid/widget/EditText;", "timerDoneText", "applyInputToTimer", "", "formatMillisToMmSs", "", "millis", "getInputMillis", "initIdleState", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onSaveInstanceState", "outState", "onSupportNavigateUp", "parseTimeInputToMillis", "input", "pauseTimer", "resetTimer", "setButtonsForFinished", "setButtonsForPaused", "startTimer", "syncTimeEditFromMillis", "updateDisplayFromMillis", "vibrateDone", "Companion", "app_debug"})
public final class RapidTestTimerActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DEFAULT_TIME_MM_SS = "15:00";
    private static final int MAX_TOTAL_SECONDS = 7200;
    private static final long MS_PER_SECOND = 1000L;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_REMAINING_MS = "remaining_ms";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_WAS_RUNNING = "was_running";
    @org.jetbrains.annotations.Nullable()
    private android.os.CountDownTimer countDownTimer;
    private long remainingMillis = 0L;
    private boolean isRunning = false;
    private android.widget.TextView countdownText;
    private android.widget.TextView timerDoneText;
    private android.widget.EditText timeEdit;
    private android.widget.Button btnStart;
    private android.widget.Button btnPause;
    private android.widget.Button btnReset;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.helloapp.RapidTestTimerActivity.Companion Companion = null;
    
    public RapidTestTimerActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    /**
     * Parse "mm:ss" or "mm" to total millis. Invalid input returns default 15:00.
     */
    private final long parseTimeInputToMillis(java.lang.String input) {
        return 0L;
    }
    
    private final java.lang.String formatMillisToMmSs(long millis) {
        return null;
    }
    
    /**
     * When user presses Enter: parse input, update remainingMillis and display, sync edit text.
     */
    private final void applyInputToTimer() {
    }
    
    private final void syncTimeEditFromMillis(long millis) {
    }
    
    private final long getInputMillis() {
        return 0L;
    }
    
    private final void startTimer() {
    }
    
    private final void pauseTimer() {
    }
    
    private final void resetTimer() {
    }
    
    private final void initIdleState() {
    }
    
    private final void setButtonsForPaused() {
    }
    
    private final void setButtonsForFinished() {
    }
    
    private final void updateDisplayFromMillis(long millis) {
    }
    
    private final void vibrateDone() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/helloapp/RapidTestTimerActivity$Companion;", "", "()V", "DEFAULT_TIME_MM_SS", "", "KEY_REMAINING_MS", "KEY_WAS_RUNNING", "MAX_TOTAL_SECONDS", "", "MS_PER_SECOND", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}