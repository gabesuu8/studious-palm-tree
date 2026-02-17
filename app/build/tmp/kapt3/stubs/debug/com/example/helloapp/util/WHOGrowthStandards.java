package com.example.helloapp.util;

/**
 * WHO Growth Standards for children 0-5 years
 * Simplified z-score calculation based on WHO Child Growth Standards
 * Reference: https://www.who.int/tools/child-growth-standards
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002&\'B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J.\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0013J\u001e\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016J\u001e\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016J\u001e\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016J(\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0002JJ\u0010!\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0019\u001a\u00020\u00052$\u0010\"\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0004H\u0002J\u000e\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0007R,\u0010\u0003\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R,\u0010\b\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R,\u0010\t\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R,\u0010\n\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R,\u0010\u000b\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R,\u0010\f\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/example/helloapp/util/WHOGrowthStandards;", "", "()V", "boysHeightForAge", "", "", "Lkotlin/Triple;", "", "boysWeightForAge", "boysWeightForHeight", "girlsHeightForAge", "girlsWeightForAge", "girlsWeightForHeight", "assessGrowth", "Lcom/example/helloapp/util/WHOGrowthStandards$GrowthAssessment;", "weightKg", "", "heightCm", "birthDate", "", "measurementDate", "isMale", "", "calculateAgeMonths", "calculateHeightForAgeZScore", "ageMonths", "calculateWeightForAgeZScore", "calculateWeightForHeightZScore", "calculateZScore", "value", "l", "m", "s", "getClosestAgePoint", "referenceMap", "interpretZScore", "Lcom/example/helloapp/util/WHOGrowthStandards$NutritionalStatus;", "zScore", "GrowthAssessment", "NutritionalStatus", "app_debug"})
public final class WHOGrowthStandards {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.Integer, kotlin.Triple<java.lang.Double, java.lang.Double, java.lang.Double>> boysWeightForAge = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.Integer, kotlin.Triple<java.lang.Double, java.lang.Double, java.lang.Double>> girlsWeightForAge = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.Integer, kotlin.Triple<java.lang.Double, java.lang.Double, java.lang.Double>> boysHeightForAge = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.Integer, kotlin.Triple<java.lang.Double, java.lang.Double, java.lang.Double>> girlsHeightForAge = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.Integer, kotlin.Triple<java.lang.Double, java.lang.Double, java.lang.Double>> boysWeightForHeight = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.Integer, kotlin.Triple<java.lang.Double, java.lang.Double, java.lang.Double>> girlsWeightForHeight = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.helloapp.util.WHOGrowthStandards INSTANCE = null;
    
    private WHOGrowthStandards() {
        super();
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
     * Calculate weight-for-height z-score
     */
    public final double calculateWeightForHeightZScore(float weightKg, float heightCm, boolean isMale) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0007H\u00c6\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e\u00a8\u0006!"}, d2 = {"Lcom/example/helloapp/util/WHOGrowthStandards$GrowthAssessment;", "", "weightForAgeZScore", "", "heightForAgeZScore", "weightForHeightZScore", "weightStatus", "Lcom/example/helloapp/util/WHOGrowthStandards$NutritionalStatus;", "heightStatus", "wastingStatus", "(DDDLcom/example/helloapp/util/WHOGrowthStandards$NutritionalStatus;Lcom/example/helloapp/util/WHOGrowthStandards$NutritionalStatus;Lcom/example/helloapp/util/WHOGrowthStandards$NutritionalStatus;)V", "getHeightForAgeZScore", "()D", "getHeightStatus", "()Lcom/example/helloapp/util/WHOGrowthStandards$NutritionalStatus;", "getWastingStatus", "getWeightForAgeZScore", "getWeightForHeightZScore", "getWeightStatus", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
    public static final class GrowthAssessment {
        private final double weightForAgeZScore = 0.0;
        private final double heightForAgeZScore = 0.0;
        private final double weightForHeightZScore = 0.0;
        @org.jetbrains.annotations.NotNull()
        private final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus weightStatus = null;
        @org.jetbrains.annotations.NotNull()
        private final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus heightStatus = null;
        @org.jetbrains.annotations.NotNull()
        private final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus wastingStatus = null;
        
        public GrowthAssessment(double weightForAgeZScore, double heightForAgeZScore, double weightForHeightZScore, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus weightStatus, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus heightStatus, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus wastingStatus) {
            super();
        }
        
        public final double getWeightForAgeZScore() {
            return 0.0;
        }
        
        public final double getHeightForAgeZScore() {
            return 0.0;
        }
        
        public final double getWeightForHeightZScore() {
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
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus getWastingStatus() {
            return null;
        }
        
        public final double component1() {
            return 0.0;
        }
        
        public final double component2() {
            return 0.0;
        }
        
        public final double component3() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.util.WHOGrowthStandards.GrowthAssessment copy(double weightForAgeZScore, double heightForAgeZScore, double weightForHeightZScore, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus weightStatus, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus heightStatus, @org.jetbrains.annotations.NotNull()
        com.example.helloapp.util.WHOGrowthStandards.NutritionalStatus wastingStatus) {
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