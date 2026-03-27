package com.example.helloapp.util;

/**
 * WHO Growth Standards for children 0-5 years
 * Simplified z-score calculation based on WHO Child Growth Standards
 * Reference: https://www.who.int/tools/child-growth-standards
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0003/01B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J.\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018J\u0018\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0015J\u001e\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018J(\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0007H\u0002J(\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0007H\u0002JJ\u0010$\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u001b\u001a\u00020\u00052$\u0010%\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0004H\u0002J\u001a\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\'2\u0006\u0010\u001b\u001a\u00020\u0005J\u001e\u0010(\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010)\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0012J\u000e\u0010-\u001a\u00020.2\u0006\u0010#\u001a\u00020\u0007R,\u0010\u0003\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R,\u0010\b\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R,\u0010\t\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R,\u0010\n\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u00062"}, d2 = {"Lcom/example/helloapp/util/WHOGrowthStandards;", "", "()V", "boysHeightForAge", "", "", "Lkotlin/Triple;", "", "boysWeightForAge", "girlsHeightForAge", "girlsWeightForAge", "referenceAges", "", "getReferenceAges", "()Ljava/util/List;", "assessGrowth", "Lcom/example/helloapp/util/WHOGrowthStandards$GrowthAssessment;", "weightKg", "", "heightCm", "birthDate", "", "measurementDate", "isMale", "", "calculateAgeMonths", "calculateHeightForAgeZScore", "ageMonths", "calculateWeightForAgeZScore", "calculateZScore", "value", "l", "m", "s", "computeValueFromZScore", "zScore", "getClosestAgePoint", "referenceMap", "getHeadCircumferenceNormalRange", "Lkotlin/Pair;", "getHeightForAgeAtZScore", "getWeightForAgeAtZScore", "interpretMuac", "Lcom/example/helloapp/util/WHOGrowthStandards$MuacStatus;", "muacCm", "interpretZScore", "Lcom/example/helloapp/util/WHOGrowthStandards$NutritionalStatus;", "GrowthAssessment", "MuacStatus", "NutritionalStatus", "app_debug"})
public final class WHOGrowthStandards {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.Integer, kotlin.Triple<java.lang.Double, java.lang.Double, java.lang.Double>> boysWeightForAge = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.Integer, kotlin.Triple<java.lang.Double, java.lang.Double, java.lang.Double>> girlsWeightForAge = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.Integer, kotlin.Triple<java.lang.Double, java.lang.Double, java.lang.Double>> boysHeightForAge = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.Integer, kotlin.Triple<java.lang.Double, java.lang.Double, java.lang.Double>> girlsHeightForAge = null;
    
    /**
     * Ages (months) for which we have WHO reference data
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.Integer> referenceAges = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.helloapp.util.WHOGrowthStandards INSTANCE = null;
    
    private WHOGrowthStandards() {
        super();
    }
    
    /**
     * Ages (months) for which we have WHO reference data
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Integer> getReferenceAges() {
        return null;
    }
    
    /**
     * WHO head circumference normal range (cm) by age in months.
     * Returns (min, max) of the expected range.
     * Most clinically meaningful for children under 36 months.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<java.lang.Integer, java.lang.Integer> getHeadCircumferenceNormalRange(int ageMonths) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.helloapp.util.WHOGrowthStandards.MuacStatus interpretMuac(float muacCm) {
        return null;
    }
    
    /**
     * Inverse LMS: compute the measurement value corresponding to a given Z-score.
     * Y = M * (1 + L*S*Z)^(1/L) when L ≠ 0
     * Y = M * exp(S*Z)           when L = 0
     */
    private final float computeValueFromZScore(double zScore, double l, double m, double s) {
        return 0.0F;
    }
    
    /**
     * Weight (kg) at the given age and Z-score from the WHO reference table
     */
    public final float getWeightForAgeAtZScore(int ageMonths, double zScore, boolean isMale) {
        return 0.0F;
    }
    
    /**
     * Height (cm) at the given age and Z-score from the WHO reference table
     */
    public final float getHeightForAgeAtZScore(int ageMonths, double zScore, boolean isMale) {
        return 0.0F;
    }
    
    /**
     * Calculate z-score using LMS method
     * Z = ((value/M)^L - 1) / (L * S) when L ≠ 0
     * Z = ln(value/M) / S when L = 0
     */
    private final double calculateZScore(float value, double l, double m, double s) {
        return 0.0;
    }
    
    /**
     * Get the closest age reference point
     */
    private final kotlin.Triple<java.lang.Double, java.lang.Double, java.lang.Double> getClosestAgePoint(int ageMonths, java.util.Map<java.lang.Integer, kotlin.Triple<java.lang.Double, java.lang.Double, java.lang.Double>> referenceMap) {
        return null;
    }
    
    /**
     * Calculate weight-for-age z-score
     * @param weightKg Weight in kilograms
     * @param ageMonths Age in months
     * @param isMale True for boys, false for girls
     * @return Z-score value
     */
    public final double calculateWeightForAgeZScore(float weightKg, int ageMonths, boolean isMale) {
        return 0.0;
    }
    
    /**
     * Calculate height/length-for-age z-score
     */
    public final double calculateHeightForAgeZScore(float heightCm, int ageMonths, boolean isMale) {
        return 0.0;
    }
    
    /**
     * Interpret z-score for nutritional status
     */
    @org.jetbrains.annotations.NotNull()
    public final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus interpretZScore(double zScore) {
        return null;
    }
    
    /**
     * Calculate age in months from birth date
     */
    public final int calculateAgeMonths(long birthDate, long measurementDate) {
        return 0;
    }
    
    /**
     * Complete growth assessment
     */
    @org.jetbrains.annotations.NotNull()
    public final com.example.helloapp.util.WHOGrowthStandards.GrowthAssessment assessGrowth(float weightKg, float heightCm, long birthDate, long measurementDate, boolean isMale) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0006H\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u001b"}, d2 = {"Lcom/example/helloapp/util/WHOGrowthStandards$GrowthAssessment;", "", "weightForAgeZScore", "", "heightForAgeZScore", "weightStatus", "Lcom/example/helloapp/util/WHOGrowthStandards$NutritionalStatus;", "heightStatus", "(DDLcom/example/helloapp/util/WHOGrowthStandards$NutritionalStatus;Lcom/example/helloapp/util/WHOGrowthStandards$NutritionalStatus;)V", "getHeightForAgeZScore", "()D", "getHeightStatus", "()Lcom/example/helloapp/util/WHOGrowthStandards$NutritionalStatus;", "getWeightForAgeZScore", "getWeightStatus", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
    public static final class GrowthAssessment {
        private final double weightForAgeZScore = 0.0;
        private final double heightForAgeZScore = 0.0;
        @org.jetbrains.annotations.NotNull()
        private final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus weightStatus = null;
        @org.jetbrains.annotations.NotNull()
        private final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus heightStatus = null;
        
        public GrowthAssessment(double weightForAgeZScore, double heightForAgeZScore, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus weightStatus, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus heightStatus) {
            super();
        }
        
        public final double getWeightForAgeZScore() {
            return 0.0;
        }
        
        public final double getHeightForAgeZScore() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus getWeightStatus() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus getHeightStatus() {
            return null;
        }
        
        public final double component1() {
            return 0.0;
        }
        
        public final double component2() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.util.WHOGrowthStandards.GrowthAssessment copy(double weightForAgeZScore, double heightForAgeZScore, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus weightStatus, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus heightStatus) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    /**
     * MUAC (Mid-Upper Arm Circumference) status per WHO/UNICEF thresholds.
     * Used for acute malnutrition screening in children 6–59 months.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bj\u0002\b\u000bj\u0002\b\fj\u0002\b\r\u00a8\u0006\u000e"}, d2 = {"Lcom/example/helloapp/util/WHOGrowthStandards$MuacStatus;", "", "description", "", "descriptionFr", "colorCode", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getColorCode", "()Ljava/lang/String;", "getDescription", "getDescriptionFr", "SEVERE", "MODERATE", "NORMAL", "app_debug"})
    public static enum MuacStatus {
        /*public static final*/ SEVERE /* = new SEVERE(null, null, null) */,
        /*public static final*/ MODERATE /* = new MODERATE(null, null, null) */,
        /*public static final*/ NORMAL /* = new NORMAL(null, null, null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String description = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String descriptionFr = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String colorCode = null;
        
        MuacStatus(java.lang.String description, java.lang.String descriptionFr, java.lang.String colorCode) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDescription() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDescriptionFr() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getColorCode() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.example.helloapp.util.WHOGrowthStandards.MuacStatus> getEntries() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/example/helloapp/util/WHOGrowthStandards$NutritionalStatus;", "", "description", "", "descriptionFr", "colorCode", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getColorCode", "()Ljava/lang/String;", "getDescription", "getDescriptionFr", "SEVERE_MALNUTRITION", "MODERATE_MALNUTRITION", "MILD_MALNUTRITION", "NORMAL", "OVERWEIGHT", "OBESE", "app_debug"})
    public static enum NutritionalStatus {
        /*public static final*/ SEVERE_MALNUTRITION /* = new SEVERE_MALNUTRITION(null, null, null) */,
        /*public static final*/ MODERATE_MALNUTRITION /* = new MODERATE_MALNUTRITION(null, null, null) */,
        /*public static final*/ MILD_MALNUTRITION /* = new MILD_MALNUTRITION(null, null, null) */,
        /*public static final*/ NORMAL /* = new NORMAL(null, null, null) */,
        /*public static final*/ OVERWEIGHT /* = new OVERWEIGHT(null, null, null) */,
        /*public static final*/ OBESE /* = new OBESE(null, null, null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String description = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String descriptionFr = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String colorCode = null;
        
        NutritionalStatus(java.lang.String description, java.lang.String descriptionFr, java.lang.String colorCode) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDescription() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDescriptionFr() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getColorCode() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus> getEntries() {
            return null;
        }
    }
}