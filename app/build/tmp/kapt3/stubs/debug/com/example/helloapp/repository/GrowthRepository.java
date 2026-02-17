package com.example.helloapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00070\u00062\u0006\u0010\u0014\u001a\u00020\u0015J\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00070\u00062\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u001c\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u001e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/example/helloapp/repository/GrowthRepository;", "", "growthDao", "Lcom/example/helloapp/data/GrowthDao;", "(Lcom/example/helloapp/data/GrowthDao;)V", "allChildren", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/helloapp/data/Child;", "getAllChildren", "()Lkotlinx/coroutines/flow/Flow;", "deleteChild", "", "child", "(Lcom/example/helloapp/data/Child;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteGrowthRecord", "record", "Lcom/example/helloapp/data/GrowthRecord;", "(Lcom/example/helloapp/data/GrowthRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getChildById", "childId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getGrowthRecordCount", "", "getGrowthRecordsForChild", "getGrowthRecordsForChildAsc", "getLatestGrowthRecord", "insertChild", "insertGrowthRecord", "updateChild", "updateGrowthRecord", "app_debug"})
public final class GrowthRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.helloapp.data.GrowthDao growthDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Child>> allChildren = null;
    
    public GrowthRepository(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.GrowthDao growthDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Child>> getAllChildren() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.GrowthRecord>> getGrowthRecordsForChild(long childId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.GrowthRecord>> getGrowthRecordsForChildAsc(long childId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getChildById(long childId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.helloapp.data.Child> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLatestGrowthRecord(long childId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.helloapp.data.GrowthRecord> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertChild(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Child child, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateChild(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Child child, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteChild(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Child child, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertGrowthRecord(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.GrowthRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateGrowthRecord(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.GrowthRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteGrowthRecord(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.GrowthRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getGrowthRecordCount(long childId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
}