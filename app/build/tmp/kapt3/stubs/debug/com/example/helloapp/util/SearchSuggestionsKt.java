package com.example.helloapp.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003\u001a\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u00a8\u0006\t"}, d2 = {"levenshteinDistance", "", "a", "", "b", "suggestClosestMatch", "query", "candidates", "", "app_debug"})
public final class SearchSuggestionsKt {
    
    /**
     * Levenshtein distance between two strings (number of single-character edits).
     */
    public static final int levenshteinDistance(@org.jetbrains.annotations.NotNull()
    java.lang.String a, @org.jetbrains.annotations.NotNull()
    java.lang.String b) {
        return 0;
    }
    
    /**
     * Returns the best matching string from [candidates] for [query], or null if no good match.
     * Uses Levenshtein distance; only returns a suggestion if the best distance is better than
     * a threshold (query length / 2 or 4, whichever is larger).
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String suggestClosestMatch(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> candidates) {
        return null;
    }
}