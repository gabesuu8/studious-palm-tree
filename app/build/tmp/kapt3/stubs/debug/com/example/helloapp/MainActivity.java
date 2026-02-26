package com.example.helloapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0006H\u0002J\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u000fH\u0014J\b\u0010\u0012\u001a\u00020\bH\u0002J\b\u0010\u0013\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/helloapp/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "bottomNavigation", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "currentFragmentTag", "", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "loadFragment", "tag", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "requestNotificationPermissionIfNeeded", "setupBottomNavigation", "Companion", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigation;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentFragmentTag = "articles";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG_ARTICLES = "articles";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG_CLINICS = "clinics";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG_TRACKERS = "trackers";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG_PREGNANCY = "pregnancy";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG_SYMPTOMS = "symptoms";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CURRENT_TAB = "current_tab";
    private static final int REQUEST_NOTIFICATION_PERMISSION = 1001;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.helloapp.MainActivity.Companion Companion = null;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void attachBaseContext(@org.jetbrains.annotations.NotNull()
    android.content.Context newBase) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void setupBottomNavigation() {
    }
    
    private final void loadFragment(java.lang.String tag) {
    }
    
    private final void requestNotificationPermissionIfNeeded() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/example/helloapp/MainActivity$Companion;", "", "()V", "KEY_CURRENT_TAB", "", "REQUEST_NOTIFICATION_PERMISSION", "", "TAG_ARTICLES", "TAG_CLINICS", "TAG_PREGNANCY", "TAG_SYMPTOMS", "TAG_TRACKERS", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}