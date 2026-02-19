package com.example.helloapp.util

/**
 * Levenshtein distance between two strings (number of single-character edits).
 */
fun levenshteinDistance(a: String, b: String): Int {
    if (a.isEmpty()) return b.length
    if (b.isEmpty()) return a.length
    val aLen = a.length
    val bLen = b.length
    var prev = IntArray(bLen + 1) { it }
    var curr = IntArray(bLen + 1)
    for (i in 1..aLen) {
        curr[0] = i
        for (j in 1..bLen) {
            val cost = if (a[i - 1] == b[j - 1]) 0 else 1
            curr[j] = minOf(
                prev[j] + 1,
                curr[j - 1] + 1,
                prev[j - 1] + cost
            )
        }
        val tmp = prev
        prev = curr
        curr = tmp
    }
    return prev[bLen]
}

/**
 * True if [a] and [b] are close enough by Levenshtein distance to count as a typo match.
 * [maxDistance] defaults to allow 1 edit for short strings, 2 for longer (based on length).
 */
fun matchesWithTypo(a: String, b: String, maxDistance: Int? = null): Boolean {
    if (a == b) return true
    val len = minOf(a.length, b.length)
    if (len <= 2) return a == b
    val threshold = maxDistance ?: maxOf(1, len / 3)
    return levenshteinDistance(a, b) <= threshold
}

/**
 * Returns the best matching string from [candidates] for [query], or null if no good match.
 * Uses Levenshtein distance; only returns a suggestion if the best distance is better than
 * a threshold (query length / 2 or 4, whichever is larger).
 * Also checks word-level matches so "fevr" can suggest a title containing "fever".
 */
fun suggestClosestMatch(query: String, candidates: List<String>): String? {
    if (query.isBlank() || candidates.isEmpty()) return null
    val q = query.trim().lowercase()
    val threshold = maxOf(3, q.length / 2)
    var best: String? = null
    var bestDist = Int.MAX_VALUE
    for (c in candidates) {
        val cLower = c.lowercase()
        val dist = levenshteinDistance(q, cLower)
        if (dist < bestDist && dist <= threshold) {
            bestDist = dist
            best = c
        }
        // Query as substring of candidate (e.g. "malari" -> "Malaria")
        if (q.length >= 2 && cLower.contains(q)) {
            val subDist = cLower.length - q.length
            if (subDist < bestDist) {
                bestDist = subDist
                best = c
            }
        }
        // Word-level: any word in candidate fuzzy-matches or is a prefix match
        val words = cLower.split(Regex("\\W+")).filter { it.length >= 2 }
        for (word in words) {
            if (word.startsWith(q) && q.length >= 2) {
                val d = word.length - q.length
                if (d < bestDist) {
                    bestDist = d
                    best = c
                }
            }
            if (matchesWithTypo(q, word) && q.length >= 2) {
                val d = levenshteinDistance(q, word)
                if (d < bestDist && d <= threshold) {
                    bestDist = d
                    best = c
                }
            }
        }
    }
    return best
}

/** Words in text (letters only, length >= 2). */
private fun wordsIn(text: String): List<String> =
    text.lowercase().split(Regex("\\W+")).filter { it.length >= 2 }

/**
 * Like [suggestClosestMatch] but each candidate has a display title and a searchable text (e.g. title + summary).
 * Returns the display title of the best matching candidate so suggestions can use summary content too.
 */
fun suggestClosestMatchFromTexts(query: String, titleToSearchable: List<Pair<String, String>>): String? {
    if (query.isBlank() || titleToSearchable.isEmpty()) return null
    val q = query.trim().lowercase()
    val threshold = maxOf(3, q.length / 2)
    var bestTitle: String? = null
    var bestDist = Int.MAX_VALUE
    for ((title, searchable) in titleToSearchable) {
        val sLower = searchable.lowercase()
        val dist = levenshteinDistance(q, sLower)
        if (dist < bestDist && dist <= threshold) {
            bestDist = dist
            bestTitle = title
        }
        if (q.length >= 2 && sLower.contains(q)) {
            val subDist = sLower.length - q.length
            if (subDist < bestDist) {
                bestDist = subDist
                bestTitle = title
            }
        }
        val words = wordsIn(searchable)
        for (word in words) {
            if (word.startsWith(q) && q.length >= 2) {
                val d = word.length - q.length
                if (d < bestDist) {
                    bestDist = d
                    bestTitle = title
                }
            }
            if (matchesWithTypo(q, word) && q.length >= 2) {
                val d = levenshteinDistance(q, word)
                if (d < bestDist && d <= threshold) {
                    bestDist = d
                    bestTitle = title
                }
            }
        }
    }
    return bestTitle
}

/**
 * True if [keyword] matches inside [text]: as substring, or as typo/prefix of a word.
 * So "malari" matches "malaria", "fevr" matches "fever", and exact substrings still match.
 */
fun keywordMatchesText(keyword: String, text: String): Boolean {
    if (keyword.isBlank()) return false
    val textLower = text.lowercase()
    val k = keyword.lowercase().trim()
    if (textLower.contains(k)) return true
    for (word in wordsIn(text)) {
        if (word == k) return true
        if (k.length >= 2 && word.startsWith(k)) return true
        if (k.length >= 2 && k.startsWith(word)) return true
        if (matchesWithTypo(k, word)) return true
    }
    return false
}
