package com.example.helloapp.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00110\u00102\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u000e\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00110\u0010H\'J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00110\u00102\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u001c\u0010\u0018\u001a\u00020\u00032\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u001c\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u001d"}, d2 = {"Lcom/example/helloapp/data/VaccinationDao;", "", "deleteAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllForChild", "childId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDuplicates", "deleteTemplates", "deleteVaccination", "vaccination", "Lcom/example/helloapp/data/Vaccination;", "(Lcom/example/helloapp/data/Vaccination;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCompletedVaccinationsForChild", "Lkotlinx/coroutines/flow/Flow;", "", "getTemplateCount", "", "getTemplateVaccinations", "getTemplateVaccinationsOnce", "getVaccinationCountForChild", "getVaccinationsForChild", "insertAll", "vaccinations", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertVaccination", "updateVaccination", "app_debug"})
@androidx.room.Dao()
public abstract interface VaccinationDao {
    
    /**
     * All vaccinations for a specific child (from Growth tracker).
     */
    @androidx.room.Query(value = "SELECT * FROM vaccinations WHERE childId = :childId ORDER BY id ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Vaccination>> getVaccinationsForChild(long childId);
    
    /**
     * Template schedule (childId = 0) - used to create records for new children.
     */
    @androidx.room.Query(value = "SELECT * FROM vaccinations WHERE childId = 0 ORDER BY id ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Vaccination>> getTemplateVaccinations();
    
    @androidx.room.Query(value = "SELECT * FROM vaccinations WHERE childId = 0 ORDER BY id ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTemplateVaccinationsOnce(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.helloapp.data.Vaccination>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM vaccinations WHERE childId = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTemplateCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM vaccinations WHERE childId = :childId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getVaccinationCountForChild(long childId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM vaccinations WHERE childId = :childId AND completedDoses >= totalDoses")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.helloapp.data.Vaccination>> getCompletedVaccinationsForChild(long childId);
    
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertVaccination(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Vaccination vaccination, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.helloapp.data.Vaccination> vaccinations, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateVaccination(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Vaccination vaccination, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteVaccination(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Vaccination vaccination, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM vaccinations WHERE childId = :childId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllForChild(long childId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM vaccinations WHERE childId = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTemplates(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM vaccinations")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM vaccinations WHERE id NOT IN (SELECT MIN(id) FROM vaccinations GROUP BY name, childId)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteDuplicates(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}