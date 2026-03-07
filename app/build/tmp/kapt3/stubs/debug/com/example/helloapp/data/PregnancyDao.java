package com.example.helloapp.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000eH'J\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000eH'J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0017\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f0\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H'J\u001c\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f0\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H'J\u0016\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u001d\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010 \u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006!"}, d2 = {"Lcom/example/helloapp/data/PregnancyDao;", "", "deleteAllPregnancies", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePregnancy", "pregnancy", "Lcom/example/helloapp/data/Pregnancy;", "(Lcom/example/helloapp/data/Pregnancy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteVisit", "visit", "Lcom/example/helloapp/data/PrenatalVisit;", "(Lcom/example/helloapp/data/PrenatalVisit;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActivePregnancies", "Lkotlinx/coroutines/flow/Flow;", "", "getAllPregnancies", "getLatestVisit", "pregnancyId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPregnancyById", "getVisitById", "visitId", "getVisitCount", "", "getVisitsForPregnancy", "getVisitsForPregnancyAsc", "insertPregnancy", "insertVisit", "markPregnancyComplete", "updatePregnancy", "updateVisit", "app_debug"})
@androidx.room.Dao()
public abstract interface PregnancyDao {
    
    @androidx.room.Query(value = "SELECT * FROM pregnancies WHERE isActive = 1 ORDER BY expectedDueDate ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Pregnancy>> getActivePregnancies();
    
    @androidx.room.Query(value = "SELECT * FROM pregnancies ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Pregnancy>> getAllPregnancies();
    
    @androidx.room.Query(value = "SELECT * FROM pregnancies WHERE id = :pregnancyId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPregnancyById(long pregnancyId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.helloapp.data.Pregnancy> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertPregnancy(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Pregnancy pregnancy, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updatePregnancy(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Pregnancy pregnancy, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deletePregnancy(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Pregnancy pregnancy, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE pregnancies SET isActive = 0 WHERE id = :pregnancyId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markPregnancyComplete(long pregnancyId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM pregnancies")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllPregnancies(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM prenatal_visits WHERE pregnancyId = :pregnancyId ORDER BY visitDate DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.PrenatalVisit>> getVisitsForPregnancy(long pregnancyId);
    
    @androidx.room.Query(value = "SELECT * FROM prenatal_visits WHERE pregnancyId = :pregnancyId ORDER BY visitDate ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.PrenatalVisit>> getVisitsForPregnancyAsc(long pregnancyId);
    
    @androidx.room.Query(value = "SELECT * FROM prenatal_visits WHERE id = :visitId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getVisitById(long visitId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.helloapp.data.PrenatalVisit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM prenatal_visits WHERE pregnancyId = :pregnancyId ORDER BY visitDate DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestVisit(long pregnancyId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.helloapp.data.PrenatalVisit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertVisit(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.PrenatalVisit visit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateVisit(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.PrenatalVisit visit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteVisit(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.PrenatalVisit visit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM prenatal_visits WHERE pregnancyId = :pregnancyId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getVisitCount(long pregnancyId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}