package com.example.helloapp.util

/**
 * WHO Growth Standards for children 0-5 years
 * Simplified z-score calculation based on WHO Child Growth Standards
 * Reference: https://www.who.int/tools/child-growth-standards
 */
object WHOGrowthStandards {
    
    // Weight-for-age (kg) - Boys: L, M, S values at key ages (months)
    // L = Box-Cox power, M = Median, S = Coefficient of variation
    private val boysWeightForAge = mapOf(
        0 to Triple(-0.3521, 3.3464, 0.14171),
        3 to Triple(-0.0107, 6.3762, 0.12087),
        6 to Triple(0.1638, 7.9340, 0.11316),
        9 to Triple(0.2550, 9.1649, 0.10873),
        12 to Triple(0.2994, 10.1530, 0.10612),
        18 to Triple(0.3469, 11.6989, 0.10327),
        24 to Triple(0.3728, 12.9515, 0.10181),
        36 to Triple(0.3574, 15.0139, 0.10084),
        48 to Triple(0.3086, 16.9023, 0.10126),
        60 to Triple(0.2430, 18.7341, 0.10235)
    )
    
    private val girlsWeightForAge = mapOf(
        0 to Triple(-0.3833, 3.2322, 0.14171),
        3 to Triple(-0.0230, 5.8458, 0.12294),
        6 to Triple(0.1551, 7.2969, 0.11727),
        9 to Triple(0.2507, 8.4712, 0.11414),
        12 to Triple(0.2969, 9.4210, 0.11271),
        18 to Triple(0.3472, 10.9455, 0.11137),
        24 to Triple(0.3737, 12.1777, 0.11110),
        36 to Triple(0.3582, 14.2055, 0.11154),
        48 to Triple(0.3095, 16.0860, 0.11274),
        60 to Triple(0.2458, 17.9321, 0.11446)
    )
    
    // Height/Length-for-age (cm) - Boys
    private val boysHeightForAge = mapOf(
        0 to Triple(1.0, 49.8842, 0.03795),
        3 to Triple(1.0, 61.4292, 0.03428),
        6 to Triple(1.0, 67.6236, 0.03311),
        9 to Triple(1.0, 72.0888, 0.03249),
        12 to Triple(1.0, 75.7488, 0.03215),
        18 to Triple(1.0, 82.2991, 0.03179),
        24 to Triple(1.0, 87.8161, 0.03163),
        36 to Triple(1.0, 96.4832, 0.03142),
        48 to Triple(1.0, 103.7142, 0.03139),
        60 to Triple(1.0, 110.0069, 0.03150)
    )
    
    private val girlsHeightForAge = mapOf(
        0 to Triple(1.0, 49.1477, 0.03790),
        3 to Triple(1.0, 59.8029, 0.03568),
        6 to Triple(1.0, 65.7311, 0.03475),
        9 to Triple(1.0, 70.1435, 0.03413),
        12 to Triple(1.0, 73.9015, 0.03369),
        18 to Triple(1.0, 80.7701, 0.03318),
        24 to Triple(1.0, 86.4204, 0.03297),
        36 to Triple(1.0, 95.0778, 0.03283),
        48 to Triple(1.0, 102.7416, 0.03282),
        60 to Triple(1.0, 109.4335, 0.03292)
    )
    
    // Weight-for-height (kg per cm) - simplified ranges
    private val boysWeightForHeight = mapOf(
        50 to Triple(0.2, 3.4, 0.09),
        60 to Triple(0.2, 5.9, 0.09),
        70 to Triple(0.2, 8.4, 0.09),
        80 to Triple(0.2, 10.5, 0.09),
        90 to Triple(0.2, 12.8, 0.09),
        100 to Triple(0.2, 15.5, 0.09),
        110 to Triple(0.2, 18.6, 0.10)
    )
    
    private val girlsWeightForHeight = mapOf(
        50 to Triple(0.2, 3.2, 0.09),
        60 to Triple(0.2, 5.6, 0.09),
        70 to Triple(0.2, 8.0, 0.09),
        80 to Triple(0.2, 10.1, 0.09),
        90 to Triple(0.2, 12.5, 0.09),
        100 to Triple(0.2, 15.2, 0.09),
        110 to Triple(0.2, 18.4, 0.10)
    )
    
    /**
     * Calculate z-score using LMS method
     * Z = ((value/M)^L - 1) / (L * S) when L ≠ 0
     * Z = ln(value/M) / S when L = 0
     */
    private fun calculateZScore(value: Float, l: Double, m: Double, s: Double): Double {
        return if (l != 0.0) {
            (Math.pow(value / m, l) - 1) / (l * s)
        } else {
            Math.log(value / m) / s
        }
    }
    
    /**
     * Get the closest age reference point
     */
    private fun getClosestAgePoint(ageMonths: Int, referenceMap: Map<Int, Triple<Double, Double, Double>>): Triple<Double, Double, Double>? {
        val ages = referenceMap.keys.toList().sorted()
        val closestAge = ages.minByOrNull { Math.abs(it - ageMonths) } ?: return null
        return referenceMap[closestAge]
    }
    
    /**
     * Calculate weight-for-age z-score
     * @param weightKg Weight in kilograms
     * @param ageMonths Age in months
     * @param isMale True for boys, false for girls
     * @return Z-score value
     */
    fun calculateWeightForAgeZScore(weightKg: Float, ageMonths: Int, isMale: Boolean): Double {
        val reference = if (isMale) boysWeightForAge else girlsWeightForAge
        val (l, m, s) = getClosestAgePoint(ageMonths, reference) ?: return 0.0
        return calculateZScore(weightKg, l, m, s)
    }
    
    /**
     * Calculate height/length-for-age z-score
     */
    fun calculateHeightForAgeZScore(heightCm: Float, ageMonths: Int, isMale: Boolean): Double {
        val reference = if (isMale) boysHeightForAge else girlsHeightForAge
        val (l, m, s) = getClosestAgePoint(ageMonths, reference) ?: return 0.0
        return calculateZScore(heightCm, l, m, s)
    }
    
    /**
     * Calculate weight-for-height z-score
     */
    fun calculateWeightForHeightZScore(weightKg: Float, heightCm: Float, isMale: Boolean): Double {
        val reference = if (isMale) boysWeightForHeight else girlsWeightForHeight
        val heights = reference.keys.toList().sorted()
        val closestHeight = heights.minByOrNull { Math.abs(it - heightCm.toInt()) } ?: return 0.0
        val (l, m, s) = reference[closestHeight] ?: return 0.0
        return calculateZScore(weightKg, l, m, s)
    }
    
    /**
     * Interpret z-score for nutritional status
     */
    fun interpretZScore(zScore: Double): NutritionalStatus {
        return when {
            zScore < -3 -> NutritionalStatus.SEVERE_MALNUTRITION
            zScore < -2 -> NutritionalStatus.MODERATE_MALNUTRITION
            zScore < -1 -> NutritionalStatus.MILD_MALNUTRITION
            zScore <= 1 -> NutritionalStatus.NORMAL
            zScore <= 2 -> NutritionalStatus.OVERWEIGHT
            else -> NutritionalStatus.OBESE
        }
    }
    
    /**
     * Calculate age in months from birth date
     */
    fun calculateAgeMonths(birthDate: Long, measurementDate: Long = System.currentTimeMillis()): Int {
        val diffMillis = measurementDate - birthDate
        val diffDays = diffMillis / (24 * 60 * 60 * 1000L)
        return (diffDays / 30.44).toInt() // Average days per month
    }
    
    enum class NutritionalStatus(val description: String, val descriptionFr: String, val colorCode: String) {
        SEVERE_MALNUTRITION("Severe Malnutrition", "Malnutrition sévère", "#D32F2F"),
        MODERATE_MALNUTRITION("Moderate Malnutrition", "Malnutrition modérée", "#F57C00"),
        MILD_MALNUTRITION("Risk of Malnutrition", "Risque de malnutrition", "#FBC02D"),
        NORMAL("Normal", "Normal", "#388E3C"),
        OVERWEIGHT("Overweight", "Surpoids", "#F57C00"),
        OBESE("Obese", "Obésité", "#D32F2F")
    }
    
    data class GrowthAssessment(
        val weightForAgeZScore: Double,
        val heightForAgeZScore: Double,
        val weightForHeightZScore: Double,
        val weightStatus: NutritionalStatus,
        val heightStatus: NutritionalStatus, // Stunting assessment
        val wastingStatus: NutritionalStatus // Wasting assessment (acute malnutrition)
    )
    
    /**
     * Complete growth assessment
     */
    fun assessGrowth(
        weightKg: Float,
        heightCm: Float,
        birthDate: Long,
        measurementDate: Long,
        isMale: Boolean
    ): GrowthAssessment {
        val ageMonths = calculateAgeMonths(birthDate, measurementDate)
        
        val wfaZ = calculateWeightForAgeZScore(weightKg, ageMonths, isMale)
        val hfaZ = calculateHeightForAgeZScore(heightCm, ageMonths, isMale)
        val wfhZ = calculateWeightForHeightZScore(weightKg, heightCm, isMale)
        
        return GrowthAssessment(
            weightForAgeZScore = wfaZ,
            heightForAgeZScore = hfaZ,
            weightForHeightZScore = wfhZ,
            weightStatus = interpretZScore(wfaZ),
            heightStatus = interpretZScore(hfaZ),
            wastingStatus = interpretZScore(wfhZ)
        )
    }
}
