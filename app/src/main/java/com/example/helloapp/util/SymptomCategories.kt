package com.example.helloapp.util

import com.example.helloapp.adapter.SymptomChipColors

object SymptomCategories {

    data class CategoryColors(val fillColor: Int, val accentColor: Int, val checkedColor: Int)

    val symptomCategories: List<Pair<String, List<Pair<String, String>>>> = listOf(
        "General" to listOf(
            "Fever"       to "fever",
            "Fatigue"     to "fatigue tired",
            "Chills"      to "chills",
            "Sweating"    to "sweating",
            "Weight loss" to "weight",
            "Night sweats" to "night sweats"
        ),
        "Head & Neurological" to listOf(
            "Headache"       to "headache",
            "Stiff neck"     to "stiff neck",
            "Blurred vision" to "blurred vision",
            "Convulsions"    to "convulsions",
            "Dizziness"      to "dizziness"
        ),
        "Digestive" to listOf(
            "Nausea"         to "nausea",
            "Vomiting"       to "vomiting",
            "Diarrhea"       to "diarrhea",
            "Abdominal pain" to "abdominal",
            "Dehydration"    to "dehydration",
            "Thirst"         to "thirst"
        ),
        "Respiratory" to listOf(
            "Cough"               to "cough",
            "Chest pain"          to "chest",
            "Shortness of breath" to "breathing"
        ),
        "Skin" to listOf(
            "Rash"     to "rash",
            "Itching"  to "itchy",
            "Jaundice" to "jaundice",
            "Swelling" to "swelling",
            "Hives"    to "hives",
            "Bleeding" to "bleeding"
        ),
        "Muscles & Joints" to listOf(
            "Muscle pain" to "muscle pain",
            "Joint pain"  to "joint",
            "Weakness"    to "weakness"
        ),
        "Mental Health" to listOf(
            "Anxiety"     to "anxiety",
            "Sadness"     to "sad",
            "Stress"      to "stress",
            "Hopelessness" to "hopeless"
        )
    )

    val categoryColorsList = listOf(
        CategoryColors(0xFFB2DFDB.toInt(), 0xFF00695C.toInt(), 0xFF00897B.toInt()),  // General – teal
        CategoryColors(0xFFBBDEFB.toInt(), 0xFF1565C0.toInt(), 0xFF1976D2.toInt()),  // Head – blue
        CategoryColors(0xFFC8E6C9.toInt(), 0xFF2E7D32.toInt(), 0xFF388E3C.toInt()),  // Digestive – green
        CategoryColors(0xFFB2EBF2.toInt(), 0xFF00838F.toInt(), 0xFF0097A7.toInt()),  // Respiratory – cyan
        CategoryColors(0xFFF8BBD0.toInt(), 0xFFAD1457.toInt(), 0xFFC2185B.toInt()),  // Skin – pink
        CategoryColors(0xFFFFE0B2.toInt(), 0xFFE65100.toInt(), 0xFFF57C00.toInt()),  // Muscles – orange
        CategoryColors(0xFFE1BEE7.toInt(), 0xFF6A1B9A.toInt(), 0xFF7B1FA2.toInt())   // Mental – purple
    )

    fun buildSymptomColorMap(): Map<String, SymptomChipColors> {
        val colorMap = mutableMapOf<String, SymptomChipColors>()
        symptomCategories.forEachIndexed { catIndex, (_, symptoms) ->
            val cc = categoryColorsList[catIndex]
            val chipColors = SymptomChipColors(cc.fillColor, cc.accentColor, cc.checkedColor)
            for ((label, keyword) in symptoms) {
                colorMap[label] = chipColors
                colorMap[label.lowercase()] = chipColors
                keyword.split(" ").forEach { token ->
                    colorMap[token] = chipColors
                }
            }
        }
        return colorMap
    }
}
