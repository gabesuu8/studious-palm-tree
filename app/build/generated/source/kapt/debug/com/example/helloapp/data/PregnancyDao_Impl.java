package com.example.helloapp.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PregnancyDao_Impl implements PregnancyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Pregnancy> __insertionAdapterOfPregnancy;

  private final EntityInsertionAdapter<PrenatalVisit> __insertionAdapterOfPrenatalVisit;

  private final EntityDeletionOrUpdateAdapter<Pregnancy> __deletionAdapterOfPregnancy;

  private final EntityDeletionOrUpdateAdapter<PrenatalVisit> __deletionAdapterOfPrenatalVisit;

  private final EntityDeletionOrUpdateAdapter<Pregnancy> __updateAdapterOfPregnancy;

  private final EntityDeletionOrUpdateAdapter<PrenatalVisit> __updateAdapterOfPrenatalVisit;

  private final SharedSQLiteStatement __preparedStmtOfMarkPregnancyComplete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPregnancies;

  public PregnancyDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPregnancy = new EntityInsertionAdapter<Pregnancy>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `pregnancies` (`id`,`motherName`,`lastMenstrualPeriod`,`expectedDueDate`,`notes`,`isActive`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Pregnancy entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getMotherName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getMotherName());
        }
        statement.bindLong(3, entity.getLastMenstrualPeriod());
        statement.bindLong(4, entity.getExpectedDueDate());
        if (entity.getNotes() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getNotes());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getCreatedAt());
      }
    };
    this.__insertionAdapterOfPrenatalVisit = new EntityInsertionAdapter<PrenatalVisit>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `prenatal_visits` (`id`,`pregnancyId`,`visitDate`,`weekOfPregnancy`,`weightKg`,`bloodPressure`,`fetalHeartRate`,`notes`,`nextVisitDate`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PrenatalVisit entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getPregnancyId());
        statement.bindLong(3, entity.getVisitDate());
        statement.bindLong(4, entity.getWeekOfPregnancy());
        if (entity.getWeightKg() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getWeightKg());
        }
        if (entity.getBloodPressure() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBloodPressure());
        }
        if (entity.getFetalHeartRate() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getFetalHeartRate());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getNotes());
        }
        if (entity.getNextVisitDate() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getNextVisitDate());
        }
        statement.bindLong(10, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfPregnancy = new EntityDeletionOrUpdateAdapter<Pregnancy>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `pregnancies` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Pregnancy entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfPrenatalVisit = new EntityDeletionOrUpdateAdapter<PrenatalVisit>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `prenatal_visits` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PrenatalVisit entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPregnancy = new EntityDeletionOrUpdateAdapter<Pregnancy>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `pregnancies` SET `id` = ?,`motherName` = ?,`lastMenstrualPeriod` = ?,`expectedDueDate` = ?,`notes` = ?,`isActive` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Pregnancy entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getMotherName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getMotherName());
        }
        statement.bindLong(3, entity.getLastMenstrualPeriod());
        statement.bindLong(4, entity.getExpectedDueDate());
        if (entity.getNotes() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getNotes());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getCreatedAt());
        statement.bindLong(8, entity.getId());
      }
    };
    this.__updateAdapterOfPrenatalVisit = new EntityDeletionOrUpdateAdapter<PrenatalVisit>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `prenatal_visits` SET `id` = ?,`pregnancyId` = ?,`visitDate` = ?,`weekOfPregnancy` = ?,`weightKg` = ?,`bloodPressure` = ?,`fetalHeartRate` = ?,`notes` = ?,`nextVisitDate` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PrenatalVisit entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getPregnancyId());
        statement.bindLong(3, entity.getVisitDate());
        statement.bindLong(4, entity.getWeekOfPregnancy());
        if (entity.getWeightKg() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getWeightKg());
        }
        if (entity.getBloodPressure() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBloodPressure());
        }
        if (entity.getFetalHeartRate() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getFetalHeartRate());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getNotes());
        }
        if (entity.getNextVisitDate() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getNextVisitDate());
        }
        statement.bindLong(10, entity.getCreatedAt());
        statement.bindLong(11, entity.getId());
      }
    };
    this.__preparedStmtOfMarkPregnancyComplete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE pregnancies SET isActive = 0 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllPregnancies = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM pregnancies";
        return _query;
      }
    };
  }

  @Override
  public Object insertPregnancy(final Pregnancy pregnancy, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPregnancy.insertAndReturnId(pregnancy);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertVisit(final PrenatalVisit visit, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPrenatalVisit.insertAndReturnId(visit);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deletePregnancy(final Pregnancy pregnancy, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPregnancy.handle(pregnancy);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteVisit(final PrenatalVisit visit, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPrenatalVisit.handle(visit);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updatePregnancy(final Pregnancy pregnancy, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPregnancy.handle(pregnancy);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateVisit(final PrenatalVisit visit, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPrenatalVisit.handle(visit);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object markPregnancyComplete(final long pregnancyId,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkPregnancyComplete.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, pregnancyId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfMarkPregnancyComplete.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteAllPregnancies(final Continuation<? super Unit> arg0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPregnancies.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllPregnancies.release(_stmt);
        }
      }
    }, arg0);
  }

  @Override
  public Flow<List<Pregnancy>> getActivePregnancies() {
    final String _sql = "SELECT * FROM pregnancies WHERE isActive = 1 ORDER BY expectedDueDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"pregnancies"}, new Callable<List<Pregnancy>>() {
      @Override
      @NonNull
      public List<Pregnancy> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMotherName = CursorUtil.getColumnIndexOrThrow(_cursor, "motherName");
          final int _cursorIndexOfLastMenstrualPeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMenstrualPeriod");
          final int _cursorIndexOfExpectedDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expectedDueDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Pregnancy> _result = new ArrayList<Pregnancy>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Pregnancy _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpMotherName;
            if (_cursor.isNull(_cursorIndexOfMotherName)) {
              _tmpMotherName = null;
            } else {
              _tmpMotherName = _cursor.getString(_cursorIndexOfMotherName);
            }
            final long _tmpLastMenstrualPeriod;
            _tmpLastMenstrualPeriod = _cursor.getLong(_cursorIndexOfLastMenstrualPeriod);
            final long _tmpExpectedDueDate;
            _tmpExpectedDueDate = _cursor.getLong(_cursorIndexOfExpectedDueDate);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Pregnancy(_tmpId,_tmpMotherName,_tmpLastMenstrualPeriod,_tmpExpectedDueDate,_tmpNotes,_tmpIsActive,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Pregnancy>> getAllPregnancies() {
    final String _sql = "SELECT * FROM pregnancies ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"pregnancies"}, new Callable<List<Pregnancy>>() {
      @Override
      @NonNull
      public List<Pregnancy> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMotherName = CursorUtil.getColumnIndexOrThrow(_cursor, "motherName");
          final int _cursorIndexOfLastMenstrualPeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMenstrualPeriod");
          final int _cursorIndexOfExpectedDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expectedDueDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Pregnancy> _result = new ArrayList<Pregnancy>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Pregnancy _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpMotherName;
            if (_cursor.isNull(_cursorIndexOfMotherName)) {
              _tmpMotherName = null;
            } else {
              _tmpMotherName = _cursor.getString(_cursorIndexOfMotherName);
            }
            final long _tmpLastMenstrualPeriod;
            _tmpLastMenstrualPeriod = _cursor.getLong(_cursorIndexOfLastMenstrualPeriod);
            final long _tmpExpectedDueDate;
            _tmpExpectedDueDate = _cursor.getLong(_cursorIndexOfExpectedDueDate);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Pregnancy(_tmpId,_tmpMotherName,_tmpLastMenstrualPeriod,_tmpExpectedDueDate,_tmpNotes,_tmpIsActive,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getPregnancyById(final long pregnancyId,
      final Continuation<? super Pregnancy> arg1) {
    final String _sql = "SELECT * FROM pregnancies WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, pregnancyId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Pregnancy>() {
      @Override
      @Nullable
      public Pregnancy call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMotherName = CursorUtil.getColumnIndexOrThrow(_cursor, "motherName");
          final int _cursorIndexOfLastMenstrualPeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMenstrualPeriod");
          final int _cursorIndexOfExpectedDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expectedDueDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final Pregnancy _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpMotherName;
            if (_cursor.isNull(_cursorIndexOfMotherName)) {
              _tmpMotherName = null;
            } else {
              _tmpMotherName = _cursor.getString(_cursorIndexOfMotherName);
            }
            final long _tmpLastMenstrualPeriod;
            _tmpLastMenstrualPeriod = _cursor.getLong(_cursorIndexOfLastMenstrualPeriod);
            final long _tmpExpectedDueDate;
            _tmpExpectedDueDate = _cursor.getLong(_cursorIndexOfExpectedDueDate);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new Pregnancy(_tmpId,_tmpMotherName,_tmpLastMenstrualPeriod,_tmpExpectedDueDate,_tmpNotes,_tmpIsActive,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<PrenatalVisit>> getVisitsForPregnancy(final long pregnancyId) {
    final String _sql = "SELECT * FROM prenatal_visits WHERE pregnancyId = ? ORDER BY visitDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, pregnancyId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"prenatal_visits"}, new Callable<List<PrenatalVisit>>() {
      @Override
      @NonNull
      public List<PrenatalVisit> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPregnancyId = CursorUtil.getColumnIndexOrThrow(_cursor, "pregnancyId");
          final int _cursorIndexOfVisitDate = CursorUtil.getColumnIndexOrThrow(_cursor, "visitDate");
          final int _cursorIndexOfWeekOfPregnancy = CursorUtil.getColumnIndexOrThrow(_cursor, "weekOfPregnancy");
          final int _cursorIndexOfWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "weightKg");
          final int _cursorIndexOfBloodPressure = CursorUtil.getColumnIndexOrThrow(_cursor, "bloodPressure");
          final int _cursorIndexOfFetalHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "fetalHeartRate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfNextVisitDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextVisitDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<PrenatalVisit> _result = new ArrayList<PrenatalVisit>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrenatalVisit _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpPregnancyId;
            _tmpPregnancyId = _cursor.getLong(_cursorIndexOfPregnancyId);
            final long _tmpVisitDate;
            _tmpVisitDate = _cursor.getLong(_cursorIndexOfVisitDate);
            final int _tmpWeekOfPregnancy;
            _tmpWeekOfPregnancy = _cursor.getInt(_cursorIndexOfWeekOfPregnancy);
            final Float _tmpWeightKg;
            if (_cursor.isNull(_cursorIndexOfWeightKg)) {
              _tmpWeightKg = null;
            } else {
              _tmpWeightKg = _cursor.getFloat(_cursorIndexOfWeightKg);
            }
            final String _tmpBloodPressure;
            if (_cursor.isNull(_cursorIndexOfBloodPressure)) {
              _tmpBloodPressure = null;
            } else {
              _tmpBloodPressure = _cursor.getString(_cursorIndexOfBloodPressure);
            }
            final Integer _tmpFetalHeartRate;
            if (_cursor.isNull(_cursorIndexOfFetalHeartRate)) {
              _tmpFetalHeartRate = null;
            } else {
              _tmpFetalHeartRate = _cursor.getInt(_cursorIndexOfFetalHeartRate);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpNextVisitDate;
            if (_cursor.isNull(_cursorIndexOfNextVisitDate)) {
              _tmpNextVisitDate = null;
            } else {
              _tmpNextVisitDate = _cursor.getLong(_cursorIndexOfNextVisitDate);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new PrenatalVisit(_tmpId,_tmpPregnancyId,_tmpVisitDate,_tmpWeekOfPregnancy,_tmpWeightKg,_tmpBloodPressure,_tmpFetalHeartRate,_tmpNotes,_tmpNextVisitDate,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<PrenatalVisit>> getVisitsForPregnancyAsc(final long pregnancyId) {
    final String _sql = "SELECT * FROM prenatal_visits WHERE pregnancyId = ? ORDER BY visitDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, pregnancyId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"prenatal_visits"}, new Callable<List<PrenatalVisit>>() {
      @Override
      @NonNull
      public List<PrenatalVisit> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPregnancyId = CursorUtil.getColumnIndexOrThrow(_cursor, "pregnancyId");
          final int _cursorIndexOfVisitDate = CursorUtil.getColumnIndexOrThrow(_cursor, "visitDate");
          final int _cursorIndexOfWeekOfPregnancy = CursorUtil.getColumnIndexOrThrow(_cursor, "weekOfPregnancy");
          final int _cursorIndexOfWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "weightKg");
          final int _cursorIndexOfBloodPressure = CursorUtil.getColumnIndexOrThrow(_cursor, "bloodPressure");
          final int _cursorIndexOfFetalHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "fetalHeartRate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfNextVisitDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextVisitDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<PrenatalVisit> _result = new ArrayList<PrenatalVisit>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrenatalVisit _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpPregnancyId;
            _tmpPregnancyId = _cursor.getLong(_cursorIndexOfPregnancyId);
            final long _tmpVisitDate;
            _tmpVisitDate = _cursor.getLong(_cursorIndexOfVisitDate);
            final int _tmpWeekOfPregnancy;
            _tmpWeekOfPregnancy = _cursor.getInt(_cursorIndexOfWeekOfPregnancy);
            final Float _tmpWeightKg;
            if (_cursor.isNull(_cursorIndexOfWeightKg)) {
              _tmpWeightKg = null;
            } else {
              _tmpWeightKg = _cursor.getFloat(_cursorIndexOfWeightKg);
            }
            final String _tmpBloodPressure;
            if (_cursor.isNull(_cursorIndexOfBloodPressure)) {
              _tmpBloodPressure = null;
            } else {
              _tmpBloodPressure = _cursor.getString(_cursorIndexOfBloodPressure);
            }
            final Integer _tmpFetalHeartRate;
            if (_cursor.isNull(_cursorIndexOfFetalHeartRate)) {
              _tmpFetalHeartRate = null;
            } else {
              _tmpFetalHeartRate = _cursor.getInt(_cursorIndexOfFetalHeartRate);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpNextVisitDate;
            if (_cursor.isNull(_cursorIndexOfNextVisitDate)) {
              _tmpNextVisitDate = null;
            } else {
              _tmpNextVisitDate = _cursor.getLong(_cursorIndexOfNextVisitDate);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new PrenatalVisit(_tmpId,_tmpPregnancyId,_tmpVisitDate,_tmpWeekOfPregnancy,_tmpWeightKg,_tmpBloodPressure,_tmpFetalHeartRate,_tmpNotes,_tmpNextVisitDate,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getVisitById(final long visitId, final Continuation<? super PrenatalVisit> arg1) {
    final String _sql = "SELECT * FROM prenatal_visits WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, visitId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PrenatalVisit>() {
      @Override
      @Nullable
      public PrenatalVisit call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPregnancyId = CursorUtil.getColumnIndexOrThrow(_cursor, "pregnancyId");
          final int _cursorIndexOfVisitDate = CursorUtil.getColumnIndexOrThrow(_cursor, "visitDate");
          final int _cursorIndexOfWeekOfPregnancy = CursorUtil.getColumnIndexOrThrow(_cursor, "weekOfPregnancy");
          final int _cursorIndexOfWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "weightKg");
          final int _cursorIndexOfBloodPressure = CursorUtil.getColumnIndexOrThrow(_cursor, "bloodPressure");
          final int _cursorIndexOfFetalHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "fetalHeartRate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfNextVisitDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextVisitDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final PrenatalVisit _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpPregnancyId;
            _tmpPregnancyId = _cursor.getLong(_cursorIndexOfPregnancyId);
            final long _tmpVisitDate;
            _tmpVisitDate = _cursor.getLong(_cursorIndexOfVisitDate);
            final int _tmpWeekOfPregnancy;
            _tmpWeekOfPregnancy = _cursor.getInt(_cursorIndexOfWeekOfPregnancy);
            final Float _tmpWeightKg;
            if (_cursor.isNull(_cursorIndexOfWeightKg)) {
              _tmpWeightKg = null;
            } else {
              _tmpWeightKg = _cursor.getFloat(_cursorIndexOfWeightKg);
            }
            final String _tmpBloodPressure;
            if (_cursor.isNull(_cursorIndexOfBloodPressure)) {
              _tmpBloodPressure = null;
            } else {
              _tmpBloodPressure = _cursor.getString(_cursorIndexOfBloodPressure);
            }
            final Integer _tmpFetalHeartRate;
            if (_cursor.isNull(_cursorIndexOfFetalHeartRate)) {
              _tmpFetalHeartRate = null;
            } else {
              _tmpFetalHeartRate = _cursor.getInt(_cursorIndexOfFetalHeartRate);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpNextVisitDate;
            if (_cursor.isNull(_cursorIndexOfNextVisitDate)) {
              _tmpNextVisitDate = null;
            } else {
              _tmpNextVisitDate = _cursor.getLong(_cursorIndexOfNextVisitDate);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new PrenatalVisit(_tmpId,_tmpPregnancyId,_tmpVisitDate,_tmpWeekOfPregnancy,_tmpWeightKg,_tmpBloodPressure,_tmpFetalHeartRate,_tmpNotes,_tmpNextVisitDate,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Object getLatestVisit(final long pregnancyId,
      final Continuation<? super PrenatalVisit> arg1) {
    final String _sql = "SELECT * FROM prenatal_visits WHERE pregnancyId = ? ORDER BY visitDate DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, pregnancyId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PrenatalVisit>() {
      @Override
      @Nullable
      public PrenatalVisit call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPregnancyId = CursorUtil.getColumnIndexOrThrow(_cursor, "pregnancyId");
          final int _cursorIndexOfVisitDate = CursorUtil.getColumnIndexOrThrow(_cursor, "visitDate");
          final int _cursorIndexOfWeekOfPregnancy = CursorUtil.getColumnIndexOrThrow(_cursor, "weekOfPregnancy");
          final int _cursorIndexOfWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "weightKg");
          final int _cursorIndexOfBloodPressure = CursorUtil.getColumnIndexOrThrow(_cursor, "bloodPressure");
          final int _cursorIndexOfFetalHeartRate = CursorUtil.getColumnIndexOrThrow(_cursor, "fetalHeartRate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfNextVisitDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextVisitDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final PrenatalVisit _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpPregnancyId;
            _tmpPregnancyId = _cursor.getLong(_cursorIndexOfPregnancyId);
            final long _tmpVisitDate;
            _tmpVisitDate = _cursor.getLong(_cursorIndexOfVisitDate);
            final int _tmpWeekOfPregnancy;
            _tmpWeekOfPregnancy = _cursor.getInt(_cursorIndexOfWeekOfPregnancy);
            final Float _tmpWeightKg;
            if (_cursor.isNull(_cursorIndexOfWeightKg)) {
              _tmpWeightKg = null;
            } else {
              _tmpWeightKg = _cursor.getFloat(_cursorIndexOfWeightKg);
            }
            final String _tmpBloodPressure;
            if (_cursor.isNull(_cursorIndexOfBloodPressure)) {
              _tmpBloodPressure = null;
            } else {
              _tmpBloodPressure = _cursor.getString(_cursorIndexOfBloodPressure);
            }
            final Integer _tmpFetalHeartRate;
            if (_cursor.isNull(_cursorIndexOfFetalHeartRate)) {
              _tmpFetalHeartRate = null;
            } else {
              _tmpFetalHeartRate = _cursor.getInt(_cursorIndexOfFetalHeartRate);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpNextVisitDate;
            if (_cursor.isNull(_cursorIndexOfNextVisitDate)) {
              _tmpNextVisitDate = null;
            } else {
              _tmpNextVisitDate = _cursor.getLong(_cursorIndexOfNextVisitDate);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new PrenatalVisit(_tmpId,_tmpPregnancyId,_tmpVisitDate,_tmpWeekOfPregnancy,_tmpWeightKg,_tmpBloodPressure,_tmpFetalHeartRate,_tmpNotes,_tmpNextVisitDate,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Object getVisitCount(final long pregnancyId, final Continuation<? super Integer> arg1) {
    final String _sql = "SELECT COUNT(*) FROM prenatal_visits WHERE pregnancyId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, pregnancyId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
