package com.example.helloapp.util

import org.junit.Assert.*
import org.junit.Test

class WHOGrowthStandardsTest {

    // --- assessGrowth() integration tests ---

    @Test
    fun assessGrowth_normalBoy_returnsNormalStatus() {
        // 12-month-old boy, median weight ~10.15 kg, median height ~75.7 cm
        val birthDate = 0L
        val measurementDate = 365L * 24 * 60 * 60 * 1000 // ~12 months
        val result = WHOGrowthStandards.assessGrowth(
            weightKg = 10.0f,
            heightCm = 75.0f,
            birthDate = birthDate,
            measurementDate = measurementDate,
            isMale = true
        )
        assertEquals(WHOGrowthStandards.NutritionalStatus.NORMAL, result.weightStatus)
        assertEquals(WHOGrowthStandards.NutritionalStatus.NORMAL, result.heightStatus)
    }

    @Test
    fun assessGrowth_normalGirl_returnsNormalStatus() {
        // 12-month-old girl, median weight ~9.42 kg, median height ~73.9 cm
        val birthDate = 0L
        val measurementDate = 365L * 24 * 60 * 60 * 1000
        val result = WHOGrowthStandards.assessGrowth(
            weightKg = 9.4f,
            heightCm = 73.5f,
            birthDate = birthDate,
            measurementDate = measurementDate,
            isMale = false
        )
        assertEquals(WHOGrowthStandards.NutritionalStatus.NORMAL, result.weightStatus)
        assertEquals(WHOGrowthStandards.NutritionalStatus.NORMAL, result.heightStatus)
    }

    @Test
    fun assessGrowth_severelyUnderweightBoy_returnsSevereMalnutrition() {
        val birthDate = 0L
        val measurementDate = 365L * 24 * 60 * 60 * 1000 // ~12 months
        // Median weight at 12m boys is ~10.15 kg; 5 kg is far below -3 SD
        val result = WHOGrowthStandards.assessGrowth(
            weightKg = 5.0f,
            heightCm = 75.0f,
            birthDate = birthDate,
            measurementDate = measurementDate,
            isMale = true
        )
        assertEquals(WHOGrowthStandards.NutritionalStatus.SEVERE_MALNUTRITION, result.weightStatus)
    }

    @Test
    fun assessGrowth_overweightChild_returnsOverweightOrObese() {
        val birthDate = 0L
        val measurementDate = 365L * 24 * 60 * 60 * 1000
        // 18 kg at 12 months is very high
        val result = WHOGrowthStandards.assessGrowth(
            weightKg = 18.0f,
            heightCm = 75.0f,
            birthDate = birthDate,
            measurementDate = measurementDate,
            isMale = true
        )
        assertTrue(
            result.weightStatus == WHOGrowthStandards.NutritionalStatus.OVERWEIGHT ||
            result.weightStatus == WHOGrowthStandards.NutritionalStatus.OBESE
        )
    }

    // --- calculateAgeMonths() tests ---

    @Test
    fun calculateAgeMonths_newborn_returnsZero() {
        val now = System.currentTimeMillis()
        assertEquals(0, WHOGrowthStandards.calculateAgeMonths(now, now))
    }

    @Test
    fun calculateAgeMonths_oneYearOld_returnsTwelve() {
        val birthDate = 0L
        val oneYear = 365L * 24 * 60 * 60 * 1000
        assertEquals(12, WHOGrowthStandards.calculateAgeMonths(birthDate, oneYear))
    }

    @Test
    fun calculateAgeMonths_sixMonths_returnsSix() {
        val birthDate = 0L
        val sixMonths = (6 * 30.44 * 24 * 60 * 60 * 1000).toLong()
        assertEquals(6, WHOGrowthStandards.calculateAgeMonths(birthDate, sixMonths))
    }

    // --- Z-score calculation tests ---

    @Test
    fun calculateWeightForAgeZScore_medianWeight_returnsNearZero() {
        // Median weight for 12-month boy is 10.153 kg
        val zScore = WHOGrowthStandards.calculateWeightForAgeZScore(10.15f, 12, isMale = true)
        assertTrue("Z-score for median weight should be near 0, was $zScore", Math.abs(zScore) < 0.1)
    }

    @Test
    fun calculateWeightForAgeZScore_lowWeight_returnsNegative() {
        val zScore = WHOGrowthStandards.calculateWeightForAgeZScore(7.0f, 12, isMale = true)
        assertTrue("Z-score for low weight should be negative, was $zScore", zScore < -1.0)
    }

    @Test
    fun calculateWeightForAgeZScore_highWeight_returnsPositive() {
        val zScore = WHOGrowthStandards.calculateWeightForAgeZScore(14.0f, 12, isMale = true)
        assertTrue("Z-score for high weight should be positive, was $zScore", zScore > 1.0)
    }

    @Test
    fun calculateHeightForAgeZScore_medianHeight_returnsNearZero() {
        // Median height for 12-month boy is 75.7488 cm
        val zScore = WHOGrowthStandards.calculateHeightForAgeZScore(75.7f, 12, isMale = true)
        assertTrue("Z-score for median height should be near 0, was $zScore", Math.abs(zScore) < 0.1)
    }

    @Test
    fun calculateWeightForHeightZScore_normalProportion_returnsNearZero() {
        // At 70cm, median weight for boys is 8.4 kg
        val zScore = WHOGrowthStandards.calculateWeightForHeightZScore(8.4f, 70f, isMale = true)
        assertTrue("Z-score should be near 0, was $zScore", Math.abs(zScore) < 0.1)
    }

    // --- interpretZScore() tests ---

    @Test
    fun interpretZScore_severeMalnutrition() {
        assertEquals(WHOGrowthStandards.NutritionalStatus.SEVERE_MALNUTRITION, WHOGrowthStandards.interpretZScore(-3.5))
    }

    @Test
    fun interpretZScore_moderateMalnutrition() {
        assertEquals(WHOGrowthStandards.NutritionalStatus.MODERATE_MALNUTRITION, WHOGrowthStandards.interpretZScore(-2.5))
    }

    @Test
    fun interpretZScore_mildMalnutrition() {
        assertEquals(WHOGrowthStandards.NutritionalStatus.MILD_MALNUTRITION, WHOGrowthStandards.interpretZScore(-1.5))
    }

    @Test
    fun interpretZScore_normal() {
        assertEquals(WHOGrowthStandards.NutritionalStatus.NORMAL, WHOGrowthStandards.interpretZScore(0.0))
    }

    @Test
    fun interpretZScore_overweight() {
        assertEquals(WHOGrowthStandards.NutritionalStatus.OVERWEIGHT, WHOGrowthStandards.interpretZScore(1.5))
    }

    @Test
    fun interpretZScore_obese() {
        assertEquals(WHOGrowthStandards.NutritionalStatus.OBESE, WHOGrowthStandards.interpretZScore(2.5))
    }

    @Test
    fun interpretZScore_boundaryValues() {
        // Exact boundary: -3 should be MODERATE (not SEVERE)
        assertEquals(WHOGrowthStandards.NutritionalStatus.MODERATE_MALNUTRITION, WHOGrowthStandards.interpretZScore(-3.0))
        // Exact boundary: -2 should be MILD
        assertEquals(WHOGrowthStandards.NutritionalStatus.MILD_MALNUTRITION, WHOGrowthStandards.interpretZScore(-2.0))
        // Exact boundary: -1 should be NORMAL
        assertEquals(WHOGrowthStandards.NutritionalStatus.NORMAL, WHOGrowthStandards.interpretZScore(-1.0))
        // Exact boundary: 1 should be NORMAL
        assertEquals(WHOGrowthStandards.NutritionalStatus.NORMAL, WHOGrowthStandards.interpretZScore(1.0))
        // Exact boundary: 2 should be OVERWEIGHT
        assertEquals(WHOGrowthStandards.NutritionalStatus.OVERWEIGHT, WHOGrowthStandards.interpretZScore(2.0))
    }

    // --- MUAC tests ---

    @Test
    fun interpretMuac_severe() {
        assertEquals(WHOGrowthStandards.MuacStatus.SEVERE, WHOGrowthStandards.interpretMuac(10.0f))
    }

    @Test
    fun interpretMuac_moderate() {
        assertEquals(WHOGrowthStandards.MuacStatus.MODERATE, WHOGrowthStandards.interpretMuac(12.0f))
    }

    @Test
    fun interpretMuac_normal() {
        assertEquals(WHOGrowthStandards.MuacStatus.NORMAL, WHOGrowthStandards.interpretMuac(13.0f))
    }

    @Test
    fun interpretMuac_boundaryValues() {
        assertEquals(WHOGrowthStandards.MuacStatus.SEVERE, WHOGrowthStandards.interpretMuac(11.4f))
        assertEquals(WHOGrowthStandards.MuacStatus.MODERATE, WHOGrowthStandards.interpretMuac(11.5f))
        assertEquals(WHOGrowthStandards.MuacStatus.MODERATE, WHOGrowthStandards.interpretMuac(12.4f))
        assertEquals(WHOGrowthStandards.MuacStatus.NORMAL, WHOGrowthStandards.interpretMuac(12.5f))
    }

    // --- Head circumference tests ---

    @Test
    fun headCircumferenceNormalRange_newborn() {
        val range = WHOGrowthStandards.getHeadCircumferenceNormalRange(0)
        assertEquals(33, range.first)
        assertEquals(37, range.second)
    }

    @Test
    fun headCircumferenceNormalRange_olderChild() {
        val range = WHOGrowthStandards.getHeadCircumferenceNormalRange(36)
        assertEquals(47, range.first)
        assertEquals(52, range.second)
    }

    // --- Inverse LMS (value from Z-score) tests ---

    @Test
    fun getWeightForAgeAtZScore_medianBoy12m() {
        val weight = WHOGrowthStandards.getWeightForAgeAtZScore(12, 0.0, isMale = true)
        // Median weight at 12m boys is ~10.153 kg
        assertTrue("Median weight should be ~10.15, was $weight", Math.abs(weight - 10.15) < 0.1)
    }

    @Test
    fun getHeightForAgeAtZScore_medianGirl12m() {
        val height = WHOGrowthStandards.getHeightForAgeAtZScore(12, 0.0, isMale = false)
        // Median height at 12m girls is ~73.9 cm
        assertTrue("Median height should be ~73.9, was $height", Math.abs(height - 73.9) < 0.2)
    }

    // --- Gender differentiation ---

    @Test
    fun zScores_differBetweenGenders() {
        val boysZ = WHOGrowthStandards.calculateWeightForAgeZScore(10.0f, 12, isMale = true)
        val girlsZ = WHOGrowthStandards.calculateWeightForAgeZScore(10.0f, 12, isMale = false)
        // Same weight should produce different z-scores for boys vs girls
        assertNotEquals("Z-scores should differ between genders", boysZ, girlsZ, 0.01)
    }

    // --- Closest age interpolation ---

    @Test
    fun calculateZScore_nonReferenceAge_usesClosestPoint() {
        // Age 15 months is not a reference point; closest is 12 months
        val z15 = WHOGrowthStandards.calculateWeightForAgeZScore(10.15f, 15, isMale = true)
        val z12 = WHOGrowthStandards.calculateWeightForAgeZScore(10.15f, 12, isMale = true)
        // Should use the same reference point (12 months)
        assertEquals(z12, z15, 0.001)
    }
}
