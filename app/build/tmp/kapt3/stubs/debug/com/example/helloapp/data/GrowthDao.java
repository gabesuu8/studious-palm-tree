package com.example.helloapp.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00130\u0012H\'J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0016\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00130\u00122\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u001c\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00130\u00122\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u001c\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u001e\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006 "}, d2 = {"Lcom/example/helloapp/data/GrowthDao;", "", "deleteAllChildren", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllGrowthRecordsForChild", "childId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteChild", "child", "Lcom/example/helloapp/data/Child;", "(Lcom/example/helloapp/data/Child;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteGrowthRecord", "record", "Lcom/example/helloapp/data/GrowthRecord;", "(Lcom/example/helloapp/data/GrowthRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllChildren", "Lkotlinx/coroutines/flow/Flow;", "", "getChildById", "getGrowthRecordById", "recordId", "getGrowthRecordCount", "", "getGrowthRecordsForChild", "getGrowthRecordsForChildAsc", "getLatestGrowthRecord", "insertChild", "insertGrowthRecord", "updateChild", "updateGrowthRecord", "app_debug"})
@androidx.room.Dao()
public abstract interface GrowthDao {
    
    @androidx.room.Query(value = "SELECT * FROM children ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Child>> getAllChildren();
    
    @androidx.room.Query(value = "SELECT * FROM children WHERE id = :childId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getChildById(long childId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.helloapp.data.Child> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertChild(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Child child, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateChild(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Child child, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteChild(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Child child, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM children")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllChildren(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM growth_records WHERE childId = :childId ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.GrowthRecord>> getGrowthRecordsForChild(long childId);
    
    @androidx.room.Query(value = "SELECT * FROM growth_records WHERE childId = :childId ORDER BY date ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.GrowthRecord>> getGrowthRecordsForChildAsc(long childId);
    
    @androidx.room.Query(value = "SELECT * FROM growth_records WHERE id = :recordId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getGrowthRecordById(long recordId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.helloapp.data.GrowthRecord> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM growth_records WHERE childId = :childId ORDER BY date DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestGrowthRecord(long childId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.helloapp.data.GrowthRecord> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertGrowthRecord(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.GrowthRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateGrowthRecord(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.GrowthRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteGrowthRecord(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.GrowthRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM growth_records WHERE childId = :childId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllGrowthRecordsForChild(long childId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM growth_records WHERE childId = :childId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getGrowthRecordCount(long childId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}