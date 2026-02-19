package com.example.helloapp.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003\u001a\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003\u001a\'\u0010\t\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u000b\u001a\u001e\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f\u001a*\u0010\u0010\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u00032\u0018\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00120\u000f\u001a\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f2\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u00a8\u0006\u0014"}, d2 = {"keywordMatchesText", "", "keyword", "", "text", "levenshteinDistance", "", "a", "b", "matchesWithTypo", "maxDistance", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Z", "suggestClosestMatch", "query", "candidates", "", "suggestClosestMatchFromTexts", "titleToSearchable", "Lkotlin/Pair;", "wordsIn", "app_debug"})
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
     * True if [a] and [b] are close enough by Levenshtein distance to count as a typo match.
     * [maxDistance] defaults to allow 1 edit for short strings, 2 for longer (based on length).
     */
    public static final boolean matchesWithTypo(@org.jetbrains.annotations.NotNull()
    java.lang.String a, @org.jetbrains.annotations.NotNull()
    java.lang.String b, @org.jetbrains.annotations.Nullable()
    java.lang.Integer maxDistance) {
        return false;
    }
    
    /**
     * Returns the best matching string from [candidates] for [query], or null if no good match.
     * Uses Levenshtein distance; only returns a suggestion if the best distance is better than
     * a threshold (query length / 2 or 4, whichever is larger).
     * Also checks word-level matches so "fevr" can suggest a title containing "fever".
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String suggestClosestMatch(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> candidates) {
        return null;
    }
    
    /**
     * Words in text (letters only, length >= 2).
     */
    private static final java.util.List<java.lang.String> wordsIn(java.lang.String text) {
        return null;
    }
    
    /**
     * Like [suggestClosestMatch] but each candidate has a display title and a searchable text (e.g. title + summary).
     * Returns the display title of the best matching candidate so suggestions can use summary content too.
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String suggestClosestMatchFromTexts(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> titleToSearchable) {
        return null;
    }
    
    /**
     * True if [keyword] matches inside [text]: as substring, or as typo/prefix of a word.
     * So "malari" matches "malaria", "fevr" matches "fever", and exact substrings still match.
     */
    public static final boolean keywordMatchesText(@org.jetbrains.annotations.NotNull()
    java.lang.String keyword, @org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return false;
    }
}