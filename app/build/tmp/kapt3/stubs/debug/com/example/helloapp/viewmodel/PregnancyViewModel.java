package com.example.helloapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0002:;B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\"\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001dJ_\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u00152\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010\'\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u001f\u00a2\u0006\u0002\u0010*J\u000e\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u0007J\u000e\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020\u0012J\u000e\u0010/\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u0007J\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\nJ\u000e\u00102\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\u0007J\u0015\u00103\u001a\u0004\u0018\u00010\u00152\u0006\u00104\u001a\u00020\u0015\u00a2\u0006\u0002\u00105J\u000e\u00106\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u0015J\u000e\u00107\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u001fJ\u0010\u00108\u001a\u00020\u001b2\b\u0010,\u001a\u0004\u0018\u00010\u0007J\u000e\u00109\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u0007R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\f\u00a8\u0006<"}, d2 = {"Lcom/example/helloapp/viewmodel/PregnancyViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_selectedPregnancy", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/helloapp/data/Pregnancy;", "activePregnancies", "Lkotlinx/coroutines/flow/StateFlow;", "", "getActivePregnancies", "()Lkotlinx/coroutines/flow/StateFlow;", "allPregnancies", "getAllPregnancies", "database", "Lcom/example/helloapp/data/AppDatabase;", "prenatalVisits", "Lcom/example/helloapp/data/PrenatalVisit;", "getPrenatalVisits", "recommendedVisitWeeks", "", "repository", "Lcom/example/helloapp/repository/PregnancyRepository;", "selectedPregnancy", "getSelectedPregnancy", "addPregnancy", "", "motherName", "", "lastMenstrualPeriod", "", "notes", "addPrenatalVisit", "pregnancyId", "visitDate", "weekOfPregnancy", "weightKg", "", "bloodPressure", "fetalHeartRate", "nextVisitDate", "(JJILjava/lang/Float;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)V", "deletePregnancy", "pregnancy", "deleteVisit", "visit", "getCurrentWeek", "getDangerSigns", "Lcom/example/helloapp/viewmodel/PregnancyViewModel$DangerSign;", "getDaysRemaining", "getRecommendedNextVisitWeek", "currentWeek", "(I)Ljava/lang/Integer;", "getTrimester", "markPregnancyComplete", "selectPregnancy", "updatePregnancy", "DangerSign", "Factory", "app_debug"})
public final class PregnancyViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.helloapp.data.AppDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.helloapp.repository.PregnancyRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.helloapp.data.Pregnancy>> activePregnancies = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.helloapp.data.Pregnancy>> allPregnancies = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.helloapp.data.Pregnancy> _selectedPregnancy = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.helloapp.data.Pregnancy> selectedPregnancy = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.helloapp.data.PrenatalVisit>> prenatalVisits = null;
    
    /**
     * WHO-recommended antenatal visit weeks (8 contacts). Returns next recommended week after current, or null if past all.
     */
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Integer> recommendedVisitWeeks = null;
    
    public PregnancyViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.helloapp.data.Pregnancy>> getActivePregnancies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.helloapp.data.Pregnancy>> getAllPregnancies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.helloapp.data.Pregnancy> getSelectedPregnancy() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.helloapp.data.PrenatalVisit>> getPrenatalVisits() {
        return null;
    }
    
    public final void selectPregnancy(@org.jetbrains.annotations.Nullable()
    com.example.helloapp.data.Pregnancy pregnancy) {
    }
    
    public final void addPregnancy(@org.jetbrains.annotations.NotNull()
    java.lang.String motherName, long lastMenstrualPeriod, @org.jetbrains.annotations.Nullable()
    java.lang.String notes) {
    }
    
    public final void updatePregnancy(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Pregnancy pregnancy) {
    }
    
    public final void markPregnancyComplete(long pregnancyId) {
    }
    
    public final void deletePregnancy(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Pregnancy pregnancy) {
    }
    
    public final void addPrenatalVisit(long pregnancyId, long visitDate, int weekOfPregnancy, @org.jetbrains.annotations.Nullable()
    java.lang.Float weightKg, @org.jetbrains.annotations.Nullable()
    java.lang.String bloodPressure, @org.jetbrains.annotations.Nullable()
    java.lang.Integer fetalHeartRate, @org.jetbrains.annotations.Nullable()
    java.lang.String notes, @org.jetbrains.annotations.Nullable()
    java.lang.Long nextVisitDate) {
    }
    
    public final void deleteVisit(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.PrenatalVisit visit) {
    }
    
    public final int getCurrentWeek(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Pregnancy pregnancy) {
        return 0;
    }
    
    public final int getDaysRemaining(@org.jetbrains.annotations.NotNull()
    com.example.helloapp.data.Pregnancy pregnancy) {
        return 0;
    }
    
    public final int getTrimester(int weekOfPregnancy) {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getRecommendedNextVisitWeek(int currentWeek) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.helloapp.viewmodel.PregnancyViewModel.DangerSign> getDangerSigns() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\bH\u00c6\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u00a8\u0006\u001b"}, d2 = {"Lcom/example/helloapp/viewmodel/PregnancyViewModel$DangerSign;", "", "name", "", "nameFr", "description", "descriptionFr", "isUrgent", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getDescription", "()Ljava/lang/String;", "getDescriptionFr", "()Z", "getName", "getNameFr", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
    public static final class DangerSign {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String nameFr = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String description = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String descriptionFr = null;
        private final boolean isUrgent = false;
        
        public DangerSign(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String nameFr, @org.jetbrains.annotations.NotNull()
        java.lang.String description, @org.jetbrains.annotations.NotNull()
        java.lang.String descriptionFr, boolean isUrgent) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getNameFr() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDescription() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDescriptionFr() {
            return null;
        }
        
        public final boolean isUrgent() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        public final boolean component5() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.viewmodel.PregnancyViewModel.DangerSign copy(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String nameFr, @org.jetbrains.annotations.NotNull()
        java.lang.String description, @org.jetbrains.annotations.NotNull()
        java.lang.String descriptionFr, boolean isUrgent) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/helloapp/viewmodel/PregnancyViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"})
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