package com.example.helloapp.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\u000e"}, d2 = {"Lcom/example/helloapp/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "articleDao", "Lcom/example/helloapp/data/ArticleDao;", "clinicDao", "Lcom/example/helloapp/data/ClinicDao;", "growthDao", "Lcom/example/helloapp/data/GrowthDao;", "pregnancyDao", "Lcom/example/helloapp/data/PregnancyDao;", "vaccinationDao", "Lcom/example/helloapp/data/VaccinationDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.example.helloapp.data.Article.class, com.example.helloapp.data.Vaccination.class, com.example.helloapp.data.Child.class, com.example.helloapp.data.GrowthRecord.class, com.example.helloapp.data.Pregnancy.class, com.example.helloapp.data.PrenatalVisit.class, com.example.helloapp.data.Clinic.class}, version = 13, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.example.helloapp.data.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.helloapp.data.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.helloapp.data.ArticleDao articleDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.helloapp.data.VaccinationDao vaccinationDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.helloapp.data.GrowthDao growthDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.helloapp.data.PregnancyDao pregnancyDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.helloapp.data.ClinicDao clinicDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/helloapp/data/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/example/helloapp/data/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.helloapp.data.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}