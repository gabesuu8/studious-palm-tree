package com.example.helloapp.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
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
public final class VaccinationDao_Impl implements VaccinationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Vaccination> __insertionAdapterOfVaccination;

  private final EntityDeletionOrUpdateAdapter<Vaccination> __deletionAdapterOfVaccination;

  private final EntityDeletionOrUpdateAdapter<Vaccination> __updateAdapterOfVaccination;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllForChild;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTemplates;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDuplicates;

  public VaccinationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVaccination = new EntityInsertionAdapter<Vaccination>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `vaccinations` (`id`,`name`,`description`,`totalDoses`,`completedDoses`,`doseSchedule`,`category`,`lastDoseDate`,`childId`,`doseDates`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Vaccination entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        statement.bindLong(4, entity.getTotalDoses());
        statement.bindLong(5, entity.getCompletedDoses());
        if (entity.getDoseSchedule() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getDoseSchedule());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCategory());
        }
        if (entity.getLastDoseDate() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getLastDoseDate());
        }
        statement.bindLong(9, entity.getChildId());
        if (entity.getDoseDates() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getDoseDates());
        }
      }
    };
    this.__deletionAdapterOfVaccination = new EntityDeletionOrUpdateAdapter<Vaccination>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `vaccinations` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Vaccination entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfVaccination = new EntityDeletionOrUpdateAdapter<Vaccination>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `vaccinations` SET `id` = ?,`name` = ?,`description` = ?,`totalDoses` = ?,`completedDoses` = ?,`doseSchedule` = ?,`category` = ?,`lastDoseDate` = ?,`childId` = ?,`doseDates` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Vaccination entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        statement.bindLong(4, entity.getTotalDoses());
        statement.bindLong(5, entity.getCompletedDoses());
        if (entity.getDoseSchedule() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getDoseSchedule());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCategory());
        }
        if (entity.getLastDoseDate() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getLastDoseDate());
        }
        statement.bindLong(9, entity.getChildId());
        if (entity.getDoseDates() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getDoseDates());
        }
        statement.bindLong(11, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllForChild = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM vaccinations WHERE childId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteTemplates = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM vaccinations WHERE childId = 0";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM vaccinations";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteDuplicates = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM vaccinations WHERE id NOT IN (SELECT MIN(id) FROM vaccinations GROUP BY name, childId)";
        return _query;
      }
    };
  }

  @Override
  public Object insertVaccination(final Vaccination vaccination,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfVaccination.insert(vaccination);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<Vaccination> vaccinations,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfVaccination.insert(vaccinations);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteVaccination(final Vaccination vaccination,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfVaccination.handle(vaccination);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateVaccination(final Vaccination vaccination,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfVaccination.handle(vaccination);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllForChild(final long childId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllForChild.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, childId);
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
          __preparedStmtOfDeleteAllForChild.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTemplates(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTemplates.acquire();
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
          __preparedStmtOfDeleteTemplates.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
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
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteDuplicates(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDuplicates.acquire();
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
          __preparedStmtOfDeleteDuplicates.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Vaccination>> getVaccinationsForChild(final long childId) {
    final String _sql = "SELECT * FROM vaccinations WHERE childId = ? ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, childId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vaccinations"}, new Callable<List<Vaccination>>() {
      @Override
      @NonNull
      public List<Vaccination> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTotalDoses = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDoses");
          final int _cursorIndexOfCompletedDoses = CursorUtil.getColumnIndexOrThrow(_cursor, "completedDoses");
          final int _cursorIndexOfDoseSchedule = CursorUtil.getColumnIndexOrThrow(_cursor, "doseSchedule");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfLastDoseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastDoseDate");
          final int _cursorIndexOfChildId = CursorUtil.getColumnIndexOrThrow(_cursor, "childId");
          final int _cursorIndexOfDoseDates = CursorUtil.getColumnIndexOrThrow(_cursor, "doseDates");
          final List<Vaccination> _result = new ArrayList<Vaccination>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Vaccination _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final int _tmpTotalDoses;
            _tmpTotalDoses = _cursor.getInt(_cursorIndexOfTotalDoses);
            final int _tmpCompletedDoses;
            _tmpCompletedDoses = _cursor.getInt(_cursorIndexOfCompletedDoses);
            final String _tmpDoseSchedule;
            if (_cursor.isNull(_cursorIndexOfDoseSchedule)) {
              _tmpDoseSchedule = null;
            } else {
              _tmpDoseSchedule = _cursor.getString(_cursorIndexOfDoseSchedule);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Long _tmpLastDoseDate;
            if (_cursor.isNull(_cursorIndexOfLastDoseDate)) {
              _tmpLastDoseDate = null;
            } else {
              _tmpLastDoseDate = _cursor.getLong(_cursorIndexOfLastDoseDate);
            }
            final long _tmpChildId;
            _tmpChildId = _cursor.getLong(_cursorIndexOfChildId);
            final String _tmpDoseDates;
            if (_cursor.isNull(_cursorIndexOfDoseDates)) {
              _tmpDoseDates = null;
            } else {
              _tmpDoseDates = _cursor.getString(_cursorIndexOfDoseDates);
            }
            _item = new Vaccination(_tmpId,_tmpName,_tmpDescription,_tmpTotalDoses,_tmpCompletedDoses,_tmpDoseSchedule,_tmpCategory,_tmpLastDoseDate,_tmpChildId,_tmpDoseDates);
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
  public Flow<List<Vaccination>> getTemplateVaccinations() {
    final String _sql = "SELECT * FROM vaccinations WHERE childId = 0 ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vaccinations"}, new Callable<List<Vaccination>>() {
      @Override
      @NonNull
      public List<Vaccination> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTotalDoses = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDoses");
          final int _cursorIndexOfCompletedDoses = CursorUtil.getColumnIndexOrThrow(_cursor, "completedDoses");
          final int _cursorIndexOfDoseSchedule = CursorUtil.getColumnIndexOrThrow(_cursor, "doseSchedule");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfLastDoseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastDoseDate");
          final int _cursorIndexOfChildId = CursorUtil.getColumnIndexOrThrow(_cursor, "childId");
          final int _cursorIndexOfDoseDates = CursorUtil.getColumnIndexOrThrow(_cursor, "doseDates");
          final List<Vaccination> _result = new ArrayList<Vaccination>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Vaccination _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final int _tmpTotalDoses;
            _tmpTotalDoses = _cursor.getInt(_cursorIndexOfTotalDoses);
            final int _tmpCompletedDoses;
            _tmpCompletedDoses = _cursor.getInt(_cursorIndexOfCompletedDoses);
            final String _tmpDoseSchedule;
            if (_cursor.isNull(_cursorIndexOfDoseSchedule)) {
              _tmpDoseSchedule = null;
            } else {
              _tmpDoseSchedule = _cursor.getString(_cursorIndexOfDoseSchedule);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Long _tmpLastDoseDate;
            if (_cursor.isNull(_cursorIndexOfLastDoseDate)) {
              _tmpLastDoseDate = null;
            } else {
              _tmpLastDoseDate = _cursor.getLong(_cursorIndexOfLastDoseDate);
            }
            final long _tmpChildId;
            _tmpChildId = _cursor.getLong(_cursorIndexOfChildId);
            final String _tmpDoseDates;
            if (_cursor.isNull(_cursorIndexOfDoseDates)) {
              _tmpDoseDates = null;
            } else {
              _tmpDoseDates = _cursor.getString(_cursorIndexOfDoseDates);
            }
            _item = new Vaccination(_tmpId,_tmpName,_tmpDescription,_tmpTotalDoses,_tmpCompletedDoses,_tmpDoseSchedule,_tmpCategory,_tmpLastDoseDate,_tmpChildId,_tmpDoseDates);
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
  public Object getTemplateVaccinationsOnce(
      final Continuation<? super List<Vaccination>> $completion) {
    final String _sql = "SELECT * FROM vaccinations WHERE childId = 0 ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Vaccination>>() {
      @Override
      @NonNull
      public List<Vaccination> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTotalDoses = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDoses");
          final int _cursorIndexOfCompletedDoses = CursorUtil.getColumnIndexOrThrow(_cursor, "completedDoses");
          final int _cursorIndexOfDoseSchedule = CursorUtil.getColumnIndexOrThrow(_cursor, "doseSchedule");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfLastDoseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastDoseDate");
          final int _cursorIndexOfChildId = CursorUtil.getColumnIndexOrThrow(_cursor, "childId");
          final int _cursorIndexOfDoseDates = CursorUtil.getColumnIndexOrThrow(_cursor, "doseDates");
          final List<Vaccination> _result = new ArrayList<Vaccination>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Vaccination _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final int _tmpTotalDoses;
            _tmpTotalDoses = _cursor.getInt(_cursorIndexOfTotalDoses);
            final int _tmpCompletedDoses;
            _tmpCompletedDoses = _cursor.getInt(_cursorIndexOfCompletedDoses);
            final String _tmpDoseSchedule;
            if (_cursor.isNull(_cursorIndexOfDoseSchedule)) {
              _tmpDoseSchedule = null;
            } else {
              _tmpDoseSchedule = _cursor.getString(_cursorIndexOfDoseSchedule);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Long _tmpLastDoseDate;
            if (_cursor.isNull(_cursorIndexOfLastDoseDate)) {
              _tmpLastDoseDate = null;
            } else {
              _tmpLastDoseDate = _cursor.getLong(_cursorIndexOfLastDoseDate);
            }
            final long _tmpChildId;
            _tmpChildId = _cursor.getLong(_cursorIndexOfChildId);
            final String _tmpDoseDates;
            if (_cursor.isNull(_cursorIndexOfDoseDates)) {
              _tmpDoseDates = null;
            } else {
              _tmpDoseDates = _cursor.getString(_cursorIndexOfDoseDates);
            }
            _item = new Vaccination(_tmpId,_tmpName,_tmpDescription,_tmpTotalDoses,_tmpCompletedDoses,_tmpDoseSchedule,_tmpCategory,_tmpLastDoseDate,_tmpChildId,_tmpDoseDates);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTemplateCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM vaccinations WHERE childId = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
    }, $completion);
  }

  @Override
  public Object getVaccinationCountForChild(final long childId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM vaccinations WHERE childId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, childId);
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
    }, $completion);
  }

  @Override
  public Flow<List<Vaccination>> getCompletedVaccinationsForChild(final long childId) {
    final String _sql = "SELECT * FROM vaccinations WHERE childId = ? AND completedDoses >= totalDoses";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, childId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"vaccinations"}, new Callable<List<Vaccination>>() {
      @Override
      @NonNull
      public List<Vaccination> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTotalDoses = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDoses");
          final int _cursorIndexOfCompletedDoses = CursorUtil.getColumnIndexOrThrow(_cursor, "completedDoses");
          final int _cursorIndexOfDoseSchedule = CursorUtil.getColumnIndexOrThrow(_cursor, "doseSchedule");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfLastDoseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastDoseDate");
          final int _cursorIndexOfChildId = CursorUtil.getColumnIndexOrThrow(_cursor, "childId");
          final int _cursorIndexOfDoseDates = CursorUtil.getColumnIndexOrThrow(_cursor, "doseDates");
          final List<Vaccination> _result = new ArrayList<Vaccination>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Vaccination _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final int _tmpTotalDoses;
            _tmpTotalDoses = _cursor.getInt(_cursorIndexOfTotalDoses);
            final int _tmpCompletedDoses;
            _tmpCompletedDoses = _cursor.getInt(_cursorIndexOfCompletedDoses);
            final String _tmpDoseSchedule;
            if (_cursor.isNull(_cursorIndexOfDoseSchedule)) {
              _tmpDoseSchedule = null;
            } else {
              _tmpDoseSchedule = _cursor.getString(_cursorIndexOfDoseSchedule);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Long _tmpLastDoseDate;
            if (_cursor.isNull(_cursorIndexOfLastDoseDate)) {
              _tmpLastDoseDate = null;
            } else {
              _tmpLastDoseDate = _cursor.getLong(_cursorIndexOfLastDoseDate);
            }
            final long _tmpChildId;
            _tmpChildId = _cursor.getLong(_cursorIndexOfChildId);
            final String _tmpDoseDates;
            if (_cursor.isNull(_cursorIndexOfDoseDates)) {
              _tmpDoseDates = null;
            } else {
              _tmpDoseDates = _cursor.getString(_cursorIndexOfDoseDates);
            }
            _item = new Vaccination(_tmpId,_tmpName,_tmpDescription,_tmpTotalDoses,_tmpCompletedDoses,_tmpDoseSchedule,_tmpCategory,_tmpLastDoseDate,_tmpChildId,_tmpDoseDates);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
