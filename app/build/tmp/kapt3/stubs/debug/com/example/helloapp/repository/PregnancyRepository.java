package com.example.helloapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00070\u00062\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00070\u00062\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010 \u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010!\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\"\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/example/helloapp/repository/PregnancyRepository;", "", "pregnancyDao", "Lcom/example/helloapp/data/PregnancyDao;", "(Lcom/example/helloapp/data/PregnancyDao;)V", "activePregnancies", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/helloapp/data/Pregnancy;", "getActivePregnancies", "()Lkotlinx/coroutines/flow/Flow;", "allPregnancies", "getAllPregnancies", "deletePregnancy", "", "pregnancy", "(Lcom/example/helloapp/data/Pregnancy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteVisit", "visit", "Lcom/example/helloapp/data/PrenatalVisit;", "(Lcom/example/helloapp/data/PrenatalVisit;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLatestVisit", "pregnancyId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPregnancyById", "getVisitCount", "", "getVisitsForPregnancy", "getVisitsForPregnancyAsc", "insertPregnancy", "insertVisit", "markPregnancyComplete", "updatePregnancy", "updateVisit", "app_debug"})
public final class PregnancyRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.helloapp.data.PregnancyDao pregnancyDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Pregnancy>> activePregnancies = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Pregnancy>> allPregnancies = null;
    
    public PregnancyRepository(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.PregnancyDao pregnancyDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Pregnancy>> getActivePregnancies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Pregnancy>> getAllPregnancies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.PrenatalVisit>> getVisitsForPregnancy(long pregnancyId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.PrenatalVisit>> getVisitsForPregnancyAsc(long pregnancyId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPregnancyById(long pregnancyId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.helloapp.data.Pregnancy> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLatestVisit(long pregnancyId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.helloapp.data.PrenatalVisit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertPregnancy(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Pregnancy pregnancy, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updatePregnancy(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Pregnancy pregnancy, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deletePregnancy(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Pregnancy pregnancy, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markPregnancyComplete(long pregnancyId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertVisit(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.PrenatalVisit visit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateVisit(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.PrenatalVisit visit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteVisit(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.PrenatalVisit visit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getVisitCount(long pregnancyId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
}