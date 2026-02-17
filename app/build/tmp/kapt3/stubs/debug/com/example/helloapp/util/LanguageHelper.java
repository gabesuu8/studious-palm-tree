package com.example.helloapp.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\tJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/example/helloapp/util/LanguageHelper;", "", "()V", "ENGLISH", "", "FRENCH", "KEY_LANGUAGE", "PREF_NAME", "applyLanguage", "Landroid/content/Context;", "context", "getLanguage", "isFrench", "", "setLanguage", "", "languageCode", "app_debug"})
public final class LanguageHelper {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREF_NAME = "language_pref";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LANGUAGE = "selected_language";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ENGLISH = "en";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FRENCH = "fr";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.helloapp.util.LanguageHelper INSTANCE = null;
    
    private LanguageHelper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLanguage(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    public final void setLanguage(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String languageCode) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context applyLanguage(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    public final boolean isFrench(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
}