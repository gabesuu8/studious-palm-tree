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
public final class GrowthDao_Impl implements GrowthDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Child> __insertionAdapterOfChild;

  private final EntityInsertionAdapter<GrowthRecord> __insertionAdapterOfGrowthRecord;

  private final EntityDeletionOrUpdateAdapter<Child> __deletionAdapterOfChild;

  private final EntityDeletionOrUpdateAdapter<GrowthRecord> __deletionAdapterOfGrowthRecord;

  private final EntityDeletionOrUpdateAdapter<Child> __updateAdapterOfChild;

  private final EntityDeletionOrUpdateAdapter<GrowthRecord> __updateAdapterOfGrowthRecord;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllChildren;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllGrowthRecordsForChild;

  public GrowthDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfChild = new EntityInsertionAdapter<Child>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `children` (`id`,`name`,`dateOfBirth`,`gender`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Child entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        statement.bindLong(3, entity.getDateOfBirth());
        if (entity.getGender() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getGender());
        }
        statement.bindLong(5, entity.getCreatedAt());
      }
    };
    this.__insertionAdapterOfGrowthRecord = new EntityInsertionAdapter<GrowthRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `growth_records` (`id`,`childId`,`date`,`weightKg`,`heightCm`,`headCircumferenceCm`,`notes`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final GrowthRecord entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getChildId());
        statement.bindLong(3, entity.getDate());
        statement.bindDouble(4, entity.getWeightKg());
        statement.bindDouble(5, entity.getHeightCm());
        if (entity.getHeadCircumferenceCm() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getHeadCircumferenceCm());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        statement.bindLong(8, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfChild = new EntityDeletionOrUpdateAdapter<Child>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `children` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Child entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfGrowthRecord = new EntityDeletionOrUpdateAdapter<GrowthRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `growth_records` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final GrowthRecord entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfChild = new EntityDeletionOrUpdateAdapter<Child>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `children` SET `id` = ?,`name` = ?,`dateOfBirth` = ?,`gender` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Child entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        statement.bindLong(3, entity.getDateOfBirth());
        if (entity.getGender() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getGender());
        }
        statement.bindLong(5, entity.getCreatedAt());
        statement.bindLong(6, entity.getId());
      }
    };
    this.__updateAdapterOfGrowthRecord = new EntityDeletionOrUpdateAdapter<GrowthRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `growth_records` SET `id` = ?,`childId` = ?,`date` = ?,`weightKg` = ?,`heightCm` = ?,`headCircumferenceCm` = ?,`notes` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final GrowthRecord entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getChildId());
        statement.bindLong(3, entity.getDate());
        statement.bindDouble(4, entity.getWeightKg());
        statement.bindDouble(5, entity.getHeightCm());
        if (entity.getHeadCircumferenceCm() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getHeadCircumferenceCm());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        statement.bindLong(8, entity.getCreatedAt());
        statement.bindLong(9, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllChildren = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM children";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllGrowthRecordsForChild = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM growth_records WHERE childId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertChild(final Child child, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfChild.insertAndReturnId(child);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertGrowthRecord(final GrowthRecord record,
      final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfGrowthRecord.insertAndReturnId(record);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteChild(final Child child, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfChild.handle(child);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteGrowthRecord(final GrowthRecord record,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfGrowthRecord.handle(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateChild(final Child child, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfChild.handle(child);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateGrowthRecord(final GrowthRecord record,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfGrowthRecord.handle(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteAllChildren(final Continuation<? super Unit> arg0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllChildren.acquire();
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
          __preparedStmtOfDeleteAllChildren.release(_stmt);
        }
      }
    }, arg0);
  }

  @Override
  public Object deleteAllGrowthRecordsForChild(final long childId,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllGrowthRecordsForChild.acquire();
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
          __preparedStmtOfDeleteAllGrowthRecordsForChild.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<Child>> getAllChildren() {
    final String _sql = "SELECT * FROM children ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"children"}, new Callable<List<Child>>() {
      @Override
      @NonNull
      public List<Child> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDateOfBirth = CursorUtil.getColumnIndexOrThrow(_cursor, "dateOfBirth");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Child> _result = new ArrayList<Child>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Child _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final long _tmpDateOfBirth;
            _tmpDateOfBirth = _cursor.getLong(_cursorIndexOfDateOfBirth);
            final String _tmpGender;
            if (_cursor.isNull(_cursorIndexOfGender)) {
              _tmpGender = null;
            } else {
              _tmpGender = _cursor.getString(_cursorIndexOfGender);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Child(_tmpId,_tmpName,_tmpDateOfBirth,_tmpGender,_tmpCreatedAt);
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
  public Object getChildById(final long childId, final Continuation<? super Child> arg1) {
    final String _sql = "SELECT * FROM children WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, childId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Child>() {
      @Override
      @Nullable
      public Child call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDateOfBirth = CursorUtil.getColumnIndexOrThrow(_cursor, "dateOfBirth");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final Child _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final long _tmpDateOfBirth;
            _tmpDateOfBirth = _cursor.getLong(_cursorIndexOfDateOfBirth);
            final String _tmpGender;
            if (_cursor.isNull(_cursorIndexOfGender)) {
              _tmpGender = null;
            } else {
              _tmpGender = _cursor.getString(_cursorIndexOfGender);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new Child(_tmpId,_tmpName,_tmpDateOfBirth,_tmpGender,_tmpCreatedAt);
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
  public Flow<List<GrowthRecord>> getGrowthRecordsForChild(final long childId) {
    final String _sql = "SELECT * FROM growth_records WHERE childId = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, childId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"growth_records"}, new Callable<List<GrowthRecord>>() {
      @Override
      @NonNull
      public List<GrowthRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfChildId = CursorUtil.getColumnIndexOrThrow(_cursor, "childId");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "weightKg");
          final int _cursorIndexOfHeightCm = CursorUtil.getColumnIndexOrThrow(_cursor, "heightCm");
          final int _cursorIndexOfHeadCircumferenceCm = CursorUtil.getColumnIndexOrThrow(_cursor, "headCircumferenceCm");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<GrowthRecord> _result = new ArrayList<GrowthRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GrowthRecord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpChildId;
            _tmpChildId = _cursor.getLong(_cursorIndexOfChildId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final float _tmpWeightKg;
            _tmpWeightKg = _cursor.getFloat(_cursorIndexOfWeightKg);
            final float _tmpHeightCm;
            _tmpHeightCm = _cursor.getFloat(_cursorIndexOfHeightCm);
            final Float _tmpHeadCircumferenceCm;
            if (_cursor.isNull(_cursorIndexOfHeadCircumferenceCm)) {
              _tmpHeadCircumferenceCm = null;
            } else {
              _tmpHeadCircumferenceCm = _cursor.getFloat(_cursorIndexOfHeadCircumferenceCm);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new GrowthRecord(_tmpId,_tmpChildId,_tmpDate,_tmpWeightKg,_tmpHeightCm,_tmpHeadCircumferenceCm,_tmpNotes,_tmpCreatedAt);
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
  public Flow<List<GrowthRecord>> getGrowthRecordsForChildAsc(final long childId) {
    final String _sql = "SELECT * FROM growth_records WHERE childId = ? ORDER BY date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, childId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"growth_records"}, new Callable<List<GrowthRecord>>() {
      @Override
      @NonNull
      public List<GrowthRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfChildId = CursorUtil.getColumnIndexOrThrow(_cursor, "childId");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "weightKg");
          final int _cursorIndexOfHeightCm = CursorUtil.getColumnIndexOrThrow(_cursor, "heightCm");
          final int _cursorIndexOfHeadCircumferenceCm = CursorUtil.getColumnIndexOrThrow(_cursor, "headCircumferenceCm");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<GrowthRecord> _result = new ArrayList<GrowthRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GrowthRecord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpChildId;
            _tmpChildId = _cursor.getLong(_cursorIndexOfChildId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final float _tmpWeightKg;
            _tmpWeightKg = _cursor.getFloat(_cursorIndexOfWeightKg);
            final float _tmpHeightCm;
            _tmpHeightCm = _cursor.getFloat(_cursorIndexOfHeightCm);
            final Float _tmpHeadCircumferenceCm;
            if (_cursor.isNull(_cursorIndexOfHeadCircumferenceCm)) {
              _tmpHeadCircumferenceCm = null;
            } else {
              _tmpHeadCircumferenceCm = _cursor.getFloat(_cursorIndexOfHeadCircumferenceCm);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new GrowthRecord(_tmpId,_tmpChildId,_tmpDate,_tmpWeightKg,_tmpHeightCm,_tmpHeadCircumferenceCm,_tmpNotes,_tmpCreatedAt);
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
  public Object getGrowthRecordById(final long recordId,
      final Continuation<? super GrowthRecord> arg1) {
    final String _sql = "SELECT * FROM growth_records WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, recordId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<GrowthRecord>() {
      @Override
      @Nullable
      public GrowthRecord call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfChildId = CursorUtil.getColumnIndexOrThrow(_cursor, "childId");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "weightKg");
          final int _cursorIndexOfHeightCm = CursorUtil.getColumnIndexOrThrow(_cursor, "heightCm");
          final int _cursorIndexOfHeadCircumferenceCm = CursorUtil.getColumnIndexOrThrow(_cursor, "headCircumferenceCm");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final GrowthRecord _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpChildId;
            _tmpChildId = _cursor.getLong(_cursorIndexOfChildId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final float _tmpWeightKg;
            _tmpWeightKg = _cursor.getFloat(_cursorIndexOfWeightKg);
            final float _tmpHeightCm;
            _tmpHeightCm = _cursor.getFloat(_cursorIndexOfHeightCm);
            final Float _tmpHeadCircumferenceCm;
            if (_cursor.isNull(_cursorIndexOfHeadCircumferenceCm)) {
              _tmpHeadCircumferenceCm = null;
            } else {
              _tmpHeadCircumferenceCm = _cursor.getFloat(_cursorIndexOfHeadCircumferenceCm);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new GrowthRecord(_tmpId,_tmpChildId,_tmpDate,_tmpWeightKg,_tmpHeightCm,_tmpHeadCircumferenceCm,_tmpNotes,_tmpCreatedAt);
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
  public Object getLatestGrowthRecord(final long childId,
      final Continuation<? super GrowthRecord> arg1) {
    final String _sql = "SELECT * FROM growth_records WHERE childId = ? ORDER BY date DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, childId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<GrowthRecord>() {
      @Override
      @Nullable
      public GrowthRecord call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfChildId = CursorUtil.getColumnIndexOrThrow(_cursor, "childId");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "weightKg");
          final int _cursorIndexOfHeightCm = CursorUtil.getColumnIndexOrThrow(_cursor, "heightCm");
          final int _cursorIndexOfHeadCircumferenceCm = CursorUtil.getColumnIndexOrThrow(_cursor, "headCircumferenceCm");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final GrowthRecord _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpChildId;
            _tmpChildId = _cursor.getLong(_cursorIndexOfChildId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final float _tmpWeightKg;
            _tmpWeightKg = _cursor.getFloat(_cursorIndexOfWeightKg);
            final float _tmpHeightCm;
            _tmpHeightCm = _cursor.getFloat(_cursorIndexOfHeightCm);
            final Float _tmpHeadCircumferenceCm;
            if (_cursor.isNull(_cursorIndexOfHeadCircumferenceCm)) {
              _tmpHeadCircumferenceCm = null;
            } else {
              _tmpHeadCircumferenceCm = _cursor.getFloat(_cursorIndexOfHeadCircumferenceCm);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new GrowthRecord(_tmpId,_tmpChildId,_tmpDate,_tmpWeightKg,_tmpHeightCm,_tmpHeadCircumferenceCm,_tmpNotes,_tmpCreatedAt);
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
  public Object getGrowthRecordCount(final long childId, final Continuation<? super Integer> arg1) {
    final String _sql = "SELECT COUNT(*) FROM growth_records WHERE childId = ?";
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
    }, arg1);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
