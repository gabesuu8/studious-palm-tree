package com.example.helloapp.viewmodel

import org.junit.Assert.*
import org.junit.Test

class VaccinationDateParsingTest {

    // --- English formats ---

    @Test
    fun parseAtBirth_returnsZero() {
        assertEquals(0L, VaccinationViewModel.parseRecommendedAgeToDays("At birth"))
    }

    @Test
    fun parseAtBirthWithExtra_returnsZero() {
        assertEquals(0L, VaccinationViewModel.parseRecommendedAgeToDays("At birth (within 24 hours)"))
    }

    @Test
    fun parseWeeks_returnsCorrectDays() {
        assertEquals(42L, VaccinationViewModel.parseRecommendedAgeToDays("6 weeks"))
        assertEquals(70L, VaccinationViewModel.parseRecommendedAgeToDays("10 weeks"))
        assertEquals(98L, VaccinationViewModel.parseRecommendedAgeToDays("14 weeks"))
    }

    @Test
    fun parseMonths_returnsCorrectDays() {
        assertEquals(180L, VaccinationViewModel.parseRecommendedAgeToDays("6 months"))
        assertEquals(270L, VaccinationViewModel.parseRecommendedAgeToDays("9 months"))
        assertEquals(360L, VaccinationViewModel.parseRecommendedAgeToDays("12 months"))
    }

    @Test
    fun parseMonthRange_usesFirstNumber() {
        // "15-18 months" should extract 15
        assertEquals(450L, VaccinationViewModel.parseRecommendedAgeToDays("15-18 months"))
    }

    @Test
    fun parseMonthSeries_usesFirstNumber() {
        // "5-17 months (series)" should extract 5
        assertEquals(150L, VaccinationViewModel.parseRecommendedAgeToDays("5-17 months (series)"))
    }

    @Test
    fun parseYears_returnsCorrectDays() {
        assertEquals(730L, VaccinationViewModel.parseRecommendedAgeToDays("2 years and above"))
    }

    @Test
    fun parseYearsNoNumber_defaultsToTwo() {
        // "years" without a number should default to 2
        assertEquals(730L, VaccinationViewModel.parseRecommendedAgeToDays("years and above"))
    }

    // --- French formats ---

    @Test
    fun parseFrenchBirth_returnsZero() {
        assertEquals(0L, VaccinationViewModel.parseRecommendedAgeToDays("À la naissance"))
    }

    @Test
    fun parseFrenchBirthWithExtra_returnsZero() {
        assertEquals(0L, VaccinationViewModel.parseRecommendedAgeToDays("À la naissance (dans les 24 heures)"))
    }

    @Test
    fun parseFrenchWeeks_returnsCorrectDays() {
        assertEquals(42L, VaccinationViewModel.parseRecommendedAgeToDays("6 semaines"))
        assertEquals(70L, VaccinationViewModel.parseRecommendedAgeToDays("10 semaines"))
        assertEquals(98L, VaccinationViewModel.parseRecommendedAgeToDays("14 semaines"))
    }

    @Test
    fun parseFrenchMonths_returnsCorrectDays() {
        assertEquals(180L, VaccinationViewModel.parseRecommendedAgeToDays("6 mois"))
        assertEquals(270L, VaccinationViewModel.parseRecommendedAgeToDays("9 mois"))
        assertEquals(360L, VaccinationViewModel.parseRecommendedAgeToDays("12 mois"))
    }

    @Test
    fun parseFrenchMonthRange_usesFirstNumber() {
        assertEquals(450L, VaccinationViewModel.parseRecommendedAgeToDays("15-18 mois"))
    }

    @Test
    fun parseFrenchMonthSeries_usesFirstNumber() {
        assertEquals(150L, VaccinationViewModel.parseRecommendedAgeToDays("5-17 mois (série)"))
    }

    @Test
    fun parseFrenchYears_returnsCorrectDays() {
        assertEquals(730L, VaccinationViewModel.parseRecommendedAgeToDays("2 ans et plus"))
    }

    // --- Edge cases ---

    @Test
    fun parseEmptyString_returnsZero() {
        assertEquals(0L, VaccinationViewModel.parseRecommendedAgeToDays(""))
    }

    @Test
    fun parseUnrecognizedFormat_returnsZero() {
        assertEquals(0L, VaccinationViewModel.parseRecommendedAgeToDays("unknown format"))
    }

    @Test
    fun parseCaseInsensitive() {
        assertEquals(0L, VaccinationViewModel.parseRecommendedAgeToDays("AT BIRTH"))
        assertEquals(42L, VaccinationViewModel.parseRecommendedAgeToDays("6 WEEKS"))
        assertEquals(180L, VaccinationViewModel.parseRecommendedAgeToDays("6 MONTHS"))
    }
}
