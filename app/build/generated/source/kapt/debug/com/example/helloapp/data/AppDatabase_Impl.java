package com.example.helloapp.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ArticleDao _articleDao;

  private volatile VaccinationDao _vaccinationDao;

  private volatile GrowthDao _growthDao;

  private volatile PregnancyDao _pregnancyDao;

  private volatile ClinicDao _clinicDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(8) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `articles` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `content` TEXT NOT NULL, `summary` TEXT NOT NULL, `source` TEXT NOT NULL, `dateAdded` INTEGER NOT NULL, `category` TEXT NOT NULL, `isFavorite` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `vaccinations` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `recommendedAge` TEXT NOT NULL, `category` TEXT NOT NULL, `isCompleted` INTEGER NOT NULL, `dateCompleted` INTEGER, `childId` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `children` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `dateOfBirth` INTEGER NOT NULL, `gender` TEXT NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `growth_records` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `childId` INTEGER NOT NULL, `date` INTEGER NOT NULL, `weightKg` REAL NOT NULL, `heightCm` REAL NOT NULL, `headCircumferenceCm` REAL, `muacCm` REAL, `notes` TEXT, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`childId`) REFERENCES `children`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_growth_records_childId` ON `growth_records` (`childId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `pregnancies` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `motherName` TEXT NOT NULL, `lastMenstrualPeriod` INTEGER NOT NULL, `expectedDueDate` INTEGER NOT NULL, `notes` TEXT, `isActive` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `prenatal_visits` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `pregnancyId` INTEGER NOT NULL, `visitDate` INTEGER NOT NULL, `weekOfPregnancy` INTEGER NOT NULL, `weightKg` REAL, `bloodPressure` TEXT, `fetalHeartRate` INTEGER, `notes` TEXT, `nextVisitDate` INTEGER, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`pregnancyId`) REFERENCES `pregnancies`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_prenatal_visits_pregnancyId` ON `prenatal_visits` (`pregnancyId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `clinics` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `address` TEXT NOT NULL, `city` TEXT NOT NULL, `country` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `phone` TEXT, `services` TEXT, `openingHours` TEXT, `isHospital` INTEGER NOT NULL, `isEmergency` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '14a9a8b7ae5ced87b89f2cb7020a8134')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `articles`");
        db.execSQL("DROP TABLE IF EXISTS `vaccinations`");
        db.execSQL("DROP TABLE IF EXISTS `children`");
        db.execSQL("DROP TABLE IF EXISTS `growth_records`");
        db.execSQL("DROP TABLE IF EXISTS `pregnancies`");
        db.execSQL("DROP TABLE IF EXISTS `prenatal_visits`");
        db.execSQL("DROP TABLE IF EXISTS `clinics`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsArticles = new HashMap<String, TableInfo.Column>(8);
        _columnsArticles.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticles.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticles.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticles.put("summary", new TableInfo.Column("summary", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticles.put("source", new TableInfo.Column("source", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticles.put("dateAdded", new TableInfo.Column("dateAdded", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticles.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticles.put("isFavorite", new TableInfo.Column("isFavorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysArticles = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesArticles = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoArticles = new TableInfo("articles", _columnsArticles, _foreignKeysArticles, _indicesArticles);
        final TableInfo _existingArticles = TableInfo.read(db, "articles");
        if (!_infoArticles.equals(_existingArticles)) {
          return new RoomOpenHelper.ValidationResult(false, "articles(com.example.helloapp.data.Article).\n"
                  + " Expected:\n" + _infoArticles + "\n"
                  + " Found:\n" + _existingArticles);
        }
        final HashMap<String, TableInfo.Column> _columnsVaccinations = new HashMap<String, TableInfo.Column>(8);
        _columnsVaccinations.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaccinations.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaccinations.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaccinations.put("recommendedAge", new TableInfo.Column("recommendedAge", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaccinations.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaccinations.put("isCompleted", new TableInfo.Column("isCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaccinations.put("dateCompleted", new TableInfo.Column("dateCompleted", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaccinations.put("childId", new TableInfo.Column("childId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVaccinations = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVaccinations = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVaccinations = new TableInfo("vaccinations", _columnsVaccinations, _foreignKeysVaccinations, _indicesVaccinations);
        final TableInfo _existingVaccinations = TableInfo.read(db, "vaccinations");
        if (!_infoVaccinations.equals(_existingVaccinations)) {
          return new RoomOpenHelper.ValidationResult(false, "vaccinations(com.example.helloapp.data.Vaccination).\n"
                  + " Expected:\n" + _infoVaccinations + "\n"
                  + " Found:\n" + _existingVaccinations);
        }
        final HashMap<String, TableInfo.Column> _columnsChildren = new HashMap<String, TableInfo.Column>(5);
        _columnsChildren.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChildren.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChildren.put("dateOfBirth", new TableInfo.Column("dateOfBirth", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChildren.put("gender", new TableInfo.Column("gender", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChildren.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysChildren = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesChildren = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoChildren = new TableInfo("children", _columnsChildren, _foreignKeysChildren, _indicesChildren);
        final TableInfo _existingChildren = TableInfo.read(db, "children");
        if (!_infoChildren.equals(_existingChildren)) {
          return new RoomOpenHelper.ValidationResult(false, "children(com.example.helloapp.data.Child).\n"
                  + " Expected:\n" + _infoChildren + "\n"
                  + " Found:\n" + _existingChildren);
        }
        final HashMap<String, TableInfo.Column> _columnsGrowthRecords = new HashMap<String, TableInfo.Column>(9);
        _columnsGrowthRecords.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowthRecords.put("childId", new TableInfo.Column("childId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowthRecords.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowthRecords.put("weightKg", new TableInfo.Column("weightKg", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowthRecords.put("heightCm", new TableInfo.Column("heightCm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowthRecords.put("headCircumferenceCm", new TableInfo.Column("headCircumferenceCm", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowthRecords.put("muacCm", new TableInfo.Column("muacCm", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowthRecords.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowthRecords.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGrowthRecords = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysGrowthRecords.add(new TableInfo.ForeignKey("children", "CASCADE", "NO ACTION", Arrays.asList("childId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesGrowthRecords = new HashSet<TableInfo.Index>(1);
        _indicesGrowthRecords.add(new TableInfo.Index("index_growth_records_childId", false, Arrays.asList("childId"), Arrays.asList("ASC")));
        final TableInfo _infoGrowthRecords = new TableInfo("growth_records", _columnsGrowthRecords, _foreignKeysGrowthRecords, _indicesGrowthRecords);
        final TableInfo _existingGrowthRecords = TableInfo.read(db, "growth_records");
        if (!_infoGrowthRecords.equals(_existingGrowthRecords)) {
          return new RoomOpenHelper.ValidationResult(false, "growth_records(com.example.helloapp.data.GrowthRecord).\n"
                  + " Expected:\n" + _infoGrowthRecords + "\n"
                  + " Found:\n" + _existingGrowthRecords);
        }
        final HashMap<String, TableInfo.Column> _columnsPregnancies = new HashMap<String, TableInfo.Column>(7);
        _columnsPregnancies.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPregnancies.put("motherName", new TableInfo.Column("motherName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPregnancies.put("lastMenstrualPeriod", new TableInfo.Column("lastMenstrualPeriod", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPregnancies.put("expectedDueDate", new TableInfo.Column("expectedDueDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPregnancies.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPregnancies.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPregnancies.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPregnancies = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPregnancies = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPregnancies = new TableInfo("pregnancies", _columnsPregnancies, _foreignKeysPregnancies, _indicesPregnancies);
        final TableInfo _existingPregnancies = TableInfo.read(db, "pregnancies");
        if (!_infoPregnancies.equals(_existingPregnancies)) {
          return new RoomOpenHelper.ValidationResult(false, "pregnancies(com.example.helloapp.data.Pregnancy).\n"
                  + " Expected:\n" + _infoPregnancies + "\n"
                  + " Found:\n" + _existingPregnancies);
        }
        final HashMap<String, TableInfo.Column> _columnsPrenatalVisits = new HashMap<String, TableInfo.Column>(10);
        _columnsPrenatalVisits.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrenatalVisits.put("pregnancyId", new TableInfo.Column("pregnancyId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrenatalVisits.put("visitDate", new TableInfo.Column("visitDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrenatalVisits.put("weekOfPregnancy", new TableInfo.Column("weekOfPregnancy", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrenatalVisits.put("weightKg", new TableInfo.Column("weightKg", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrenatalVisits.put("bloodPressure", new TableInfo.Column("bloodPressure", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrenatalVisits.put("fetalHeartRate", new TableInfo.Column("fetalHeartRate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrenatalVisits.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrenatalVisits.put("nextVisitDate", new TableInfo.Column("nextVisitDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrenatalVisits.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPrenatalVisits = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysPrenatalVisits.add(new TableInfo.ForeignKey("pregnancies", "CASCADE", "NO ACTION", Arrays.asList("pregnancyId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesPrenatalVisits = new HashSet<TableInfo.Index>(1);
        _indicesPrenatalVisits.add(new TableInfo.Index("index_prenatal_visits_pregnancyId", false, Arrays.asList("pregnancyId"), Arrays.asList("ASC")));
        final TableInfo _infoPrenatalVisits = new TableInfo("prenatal_visits", _columnsPrenatalVisits, _foreignKeysPrenatalVisits, _indicesPrenatalVisits);
        final TableInfo _existingPrenatalVisits = TableInfo.read(db, "prenatal_visits");
        if (!_infoPrenatalVisits.equals(_existingPrenatalVisits)) {
          return new RoomOpenHelper.ValidationResult(false, "prenatal_visits(com.example.helloapp.data.PrenatalVisit).\n"
                  + " Expected:\n" + _infoPrenatalVisits + "\n"
                  + " Found:\n" + _existingPrenatalVisits);
        }
        final HashMap<String, TableInfo.Column> _columnsClinics = new HashMap<String, TableInfo.Column>(12);
        _columnsClinics.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClinics.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClinics.put("address", new TableInfo.Column("address", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClinics.put("city", new TableInfo.Column("city", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClinics.put("country", new TableInfo.Column("country", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClinics.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClinics.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClinics.put("phone", new TableInfo.Column("phone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClinics.put("services", new TableInfo.Column("services", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClinics.put("openingHours", new TableInfo.Column("openingHours", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClinics.put("isHospital", new TableInfo.Column("isHospital", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClinics.put("isEmergency", new TableInfo.Column("isEmergency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysClinics = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesClinics = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoClinics = new TableInfo("clinics", _columnsClinics, _foreignKeysClinics, _indicesClinics);
        final TableInfo _existingClinics = TableInfo.read(db, "clinics");
        if (!_infoClinics.equals(_existingClinics)) {
          return new RoomOpenHelper.ValidationResult(false, "clinics(com.example.helloapp.data.Clinic).\n"
                  + " Expected:\n" + _infoClinics + "\n"
                  + " Found:\n" + _existingClinics);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "14a9a8b7ae5ced87b89f2cb7020a8134", "f91d030d3bc9d509a5ebc4e562befc2a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "articles","vaccinations","children","growth_records","pregnancies","prenatal_visits","clinics");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `articles`");
      _db.execSQL("DELETE FROM `vaccinations`");
      _db.execSQL("DELETE FROM `children`");
      _db.execSQL("DELETE FROM `growth_records`");
      _db.execSQL("DELETE FROM `pregnancies`");
      _db.execSQL("DELETE FROM `prenatal_visits`");
      _db.execSQL("DELETE FROM `clinics`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ArticleDao.class, ArticleDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(VaccinationDao.class, VaccinationDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GrowthDao.class, GrowthDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PregnancyDao.class, PregnancyDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ClinicDao.class, ClinicDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ArticleDao articleDao() {
    if (_articleDao != null) {
      return _articleDao;
    } else {
      synchronized(this) {
        if(_articleDao == null) {
          _articleDao = new ArticleDao_Impl(this);
        }
        return _articleDao;
      }
    }
  }

  @Override
  public VaccinationDao vaccinationDao() {
    if (_vaccinationDao != null) {
      return _vaccinationDao;
    } else {
      synchronized(this) {
        if(_vaccinationDao == null) {
          _vaccinationDao = new VaccinationDao_Impl(this);
        }
        return _vaccinationDao;
      }
    }
  }

  @Override
  public GrowthDao growthDao() {
    if (_growthDao != null) {
      return _growthDao;
    } else {
      synchronized(this) {
        if(_growthDao == null) {
          _growthDao = new GrowthDao_Impl(this);
        }
        return _growthDao;
      }
    }
  }

  @Override
  public PregnancyDao pregnancyDao() {
    if (_pregnancyDao != null) {
      return _pregnancyDao;
    } else {
      synchronized(this) {
        if(_pregnancyDao == null) {
          _pregnancyDao = new PregnancyDao_Impl(this);
        }
        return _pregnancyDao;
      }
    }
  }

  @Override
  public ClinicDao clinicDao() {
    if (_clinicDao != null) {
      return _clinicDao;
    } else {
      synchronized(this) {
        if(_clinicDao == null) {
          _clinicDao = new ClinicDao_Impl(this);
        }
        return _clinicDao;
      }
    }
  }
}
