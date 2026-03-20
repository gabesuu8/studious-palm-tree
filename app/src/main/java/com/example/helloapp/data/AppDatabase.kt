package com.example.helloapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Article::class, 
        Vaccination::class,
        Child::class,
        GrowthRecord::class,
        Pregnancy::class,
        PrenatalVisit::class,
        Clinic::class
    ], 
    version = 13,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun vaccinationDao(): VaccinationDao
    abstract fun growthDao(): GrowthDao
    abstract fun pregnancyDao(): PregnancyDao
    abstract fun clinicDao(): ClinicDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "healthcare_app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
