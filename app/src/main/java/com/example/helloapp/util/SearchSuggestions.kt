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
 */
fun suggestClosestMatch(query: String, candidates: List<String>): String? {
    if (query.isBlank() || candidates.isEmpty()) return null
    val q = query.trim().lowercase()
    val threshold = maxOf(4, q.length / 2)
    var best: String? = null
    var bestDist = Int.MAX_VALUE
    for (c in candidates) {
        val cLower = c.lowercase()
        val dist = levenshteinDistance(q, cLower)
        if (dist < bestDist && dist <= threshold) {
            bestDist = dist
            best = c
        }
        // Also check if query is substring of candidate (e.g. "malari" -> "Malaria")
        if (cLower.contains(q) && q.length >= 3) {
            val subDist = cLower.length - q.length
            if (subDist < bestDist) {
                bestDist = subDist
                best = c
            }
        }
    }
    return best
}
