package com.example.helloapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u00015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001bJO\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010\'\u001a\u0004\u0018\u00010\u001b\u00a2\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u0007J\u000e\u0010-\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u0007J\u000e\u0010.\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\u0010J\u0018\u0010/\u001a\u0002002\u0006\u0010,\u001a\u00020\u00072\b\b\u0002\u00101\u001a\u00020\u001dJ\u0010\u00102\u001a\u00020\u00192\b\u0010,\u001a\u0004\u0018\u00010\u0007J\u000e\u00103\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u0007J\u000e\u00104\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\u0010R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\fR\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00066"}, d2 = {"Lcom/example/helloapp/viewmodel/GrowthViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_selectedChild", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/helloapp/data/Child;", "allChildren", "Lkotlinx/coroutines/flow/StateFlow;", "", "getAllChildren", "()Lkotlinx/coroutines/flow/StateFlow;", "database", "Lcom/example/helloapp/data/AppDatabase;", "growthRecords", "Lcom/example/helloapp/data/GrowthRecord;", "getGrowthRecords", "repository", "Lcom/example/helloapp/repository/GrowthRepository;", "selectedChild", "getSelectedChild", "vaccinationRepository", "Lcom/example/helloapp/repository/VaccinationRepository;", "addChild", "", "name", "", "dateOfBirth", "", "gender", "addGrowthRecord", "childId", "date", "weightKg", "", "heightCm", "headCircumferenceCm", "muacCm", "notes", "(JJFFLjava/lang/Float;Ljava/lang/Float;Ljava/lang/String;)V", "assessGrowth", "Lcom/example/helloapp/util/WHOGrowthStandards$GrowthAssessment;", "record", "child", "deleteChild", "deleteGrowthRecord", "getChildAgeMonths", "", "measurementDate", "selectChild", "updateChild", "updateGrowthRecord", "Factory", "app_debug"})
public final class GrowthViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.helloapp.data.AppDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.helloapp.repository.GrowthRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.helloapp.repository.VaccinationRepository vaccinationRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.helloapp.data.Child>> allChildren = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.helloapp.data.Child> _selectedChild = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.helloapp.data.Child> selectedChild = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.helloapp.data.GrowthRecord>> growthRecords = null;
    
    public GrowthViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.helloapp.data.Child>> getAllChildren() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.helloapp.data.Child> getSelectedChild() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.helloapp.data.GrowthRecord>> getGrowthRecords() {
        return null;
    }
    
    public final void selectChild(@org.jetbrains.annotations.Nullable()
    com.example.helloapp.data.Child child) {
    }
    
    public final void addChild(@org.jetbrains.annotations.NotNull()
    java.lang.String name, long dateOfBirth, @org.jetbrains.annotations.NotNull()
    java.lang.String gender) {
    }
    
    public final void updateChild(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Child child) {
    }
    
    public final void deleteChild(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Child child) {
    }
    
    public final void addGrowthRecord(long childId, long date, float weightKg, float heightCm, @org.jetbrains.annotations.Nullable()
    java.lang.Float headCircumferenceCm, @org.jetbrains.annotations.Nullable()
    java.lang.Float muacCm, @org.jetbrains.annotations.Nullable()
    java.lang.String notes) {
    }
    
    public final void updateGrowthRecord(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.GrowthRecord record) {
    }
    
    public final void deleteGrowthRecord(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.GrowthRecord record) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.helloapp.util.WHOGrowthStandards.GrowthAssessment assessGrowth(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.GrowthRecord record, @org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Child child) {
        return null;
    }
    
    public final int getChildAgeMonths(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Child child, long measurementDate) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/helloapp/viewmodel/GrowthViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"})
    public static final class Factory implements androidx.lifecycle.ViewModelProvider.Factory {
        @org.jetbrains.annotations.NotNull()
        private final android.app.Application application = null;
        
        public Factory(@org.jetbrains.annotations.NotNull()
        android.app.Application application) {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
        java.lang.Class<T> modelClass) {
            return null;
        }
    }
}