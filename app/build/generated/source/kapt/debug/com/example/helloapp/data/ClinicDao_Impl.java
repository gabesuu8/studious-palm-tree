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
public final class ClinicDao_Impl implements ClinicDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Clinic> __insertionAdapterOfClinic;

  private final EntityDeletionOrUpdateAdapter<Clinic> __deletionAdapterOfClinic;

  private final EntityDeletionOrUpdateAdapter<Clinic> __updateAdapterOfClinic;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ClinicDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfClinic = new EntityInsertionAdapter<Clinic>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `clinics` (`id`,`name`,`address`,`city`,`country`,`latitude`,`longitude`,`phone`,`services`,`openingHours`,`isHospital`,`isEmergency`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Clinic entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getAddress() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAddress());
        }
        if (entity.getCity() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCity());
        }
        if (entity.getCountry() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCountry());
        }
        statement.bindDouble(6, entity.getLatitude());
        statement.bindDouble(7, entity.getLongitude());
        if (entity.getPhone() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getPhone());
        }
        if (entity.getServices() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getServices());
        }
        if (entity.getOpeningHours() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getOpeningHours());
        }
        final int _tmp = entity.isHospital() ? 1 : 0;
        statement.bindLong(11, _tmp);
        final int _tmp_1 = entity.isEmergency() ? 1 : 0;
        statement.bindLong(12, _tmp_1);
      }
    };
    this.__deletionAdapterOfClinic = new EntityDeletionOrUpdateAdapter<Clinic>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `clinics` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Clinic entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfClinic = new EntityDeletionOrUpdateAdapter<Clinic>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `clinics` SET `id` = ?,`name` = ?,`address` = ?,`city` = ?,`country` = ?,`latitude` = ?,`longitude` = ?,`phone` = ?,`services` = ?,`openingHours` = ?,`isHospital` = ?,`isEmergency` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Clinic entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getAddress() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAddress());
        }
        if (entity.getCity() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCity());
        }
        if (entity.getCountry() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCountry());
        }
        statement.bindDouble(6, entity.getLatitude());
        statement.bindDouble(7, entity.getLongitude());
        if (entity.getPhone() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getPhone());
        }
        if (entity.getServices() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getServices());
        }
        if (entity.getOpeningHours() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getOpeningHours());
        }
        final int _tmp = entity.isHospital() ? 1 : 0;
        statement.bindLong(11, _tmp);
        final int _tmp_1 = entity.isEmergency() ? 1 : 0;
        statement.bindLong(12, _tmp_1);
        statement.bindLong(13, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM clinics";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final Clinic clinic, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfClinic.insertAndReturnId(clinic);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<Clinic> clinics,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfClinic.insert(clinics);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Clinic clinic, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfClinic.handle(clinic);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Clinic clinic, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfClinic.handle(clinic);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
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
  public Flow<List<Clinic>> getAllClinics() {
    final String _sql = "SELECT * FROM clinics ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"clinics"}, new Callable<List<Clinic>>() {
      @Override
      @NonNull
      public List<Clinic> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfServices = CursorUtil.getColumnIndexOrThrow(_cursor, "services");
          final int _cursorIndexOfOpeningHours = CursorUtil.getColumnIndexOrThrow(_cursor, "openingHours");
          final int _cursorIndexOfIsHospital = CursorUtil.getColumnIndexOrThrow(_cursor, "isHospital");
          final int _cursorIndexOfIsEmergency = CursorUtil.getColumnIndexOrThrow(_cursor, "isEmergency");
          final List<Clinic> _result = new ArrayList<Clinic>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Clinic _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpCountry;
            if (_cursor.isNull(_cursorIndexOfCountry)) {
              _tmpCountry = null;
            } else {
              _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            }
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpServices;
            if (_cursor.isNull(_cursorIndexOfServices)) {
              _tmpServices = null;
            } else {
              _tmpServices = _cursor.getString(_cursorIndexOfServices);
            }
            final String _tmpOpeningHours;
            if (_cursor.isNull(_cursorIndexOfOpeningHours)) {
              _tmpOpeningHours = null;
            } else {
              _tmpOpeningHours = _cursor.getString(_cursorIndexOfOpeningHours);
            }
            final boolean _tmpIsHospital;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsHospital);
            _tmpIsHospital = _tmp != 0;
            final boolean _tmpIsEmergency;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsEmergency);
            _tmpIsEmergency = _tmp_1 != 0;
            _item = new Clinic(_tmpId,_tmpName,_tmpAddress,_tmpCity,_tmpCountry,_tmpLatitude,_tmpLongitude,_tmpPhone,_tmpServices,_tmpOpeningHours,_tmpIsHospital,_tmpIsEmergency);
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
  public Flow<List<Clinic>> getClinicsByCountry(final String country) {
    final String _sql = "SELECT * FROM clinics WHERE country = ? ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (country == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, country);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"clinics"}, new Callable<List<Clinic>>() {
      @Override
      @NonNull
      public List<Clinic> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfServices = CursorUtil.getColumnIndexOrThrow(_cursor, "services");
          final int _cursorIndexOfOpeningHours = CursorUtil.getColumnIndexOrThrow(_cursor, "openingHours");
          final int _cursorIndexOfIsHospital = CursorUtil.getColumnIndexOrThrow(_cursor, "isHospital");
          final int _cursorIndexOfIsEmergency = CursorUtil.getColumnIndexOrThrow(_cursor, "isEmergency");
          final List<Clinic> _result = new ArrayList<Clinic>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Clinic _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpCountry;
            if (_cursor.isNull(_cursorIndexOfCountry)) {
              _tmpCountry = null;
            } else {
              _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            }
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpServices;
            if (_cursor.isNull(_cursorIndexOfServices)) {
              _tmpServices = null;
            } else {
              _tmpServices = _cursor.getString(_cursorIndexOfServices);
            }
            final String _tmpOpeningHours;
            if (_cursor.isNull(_cursorIndexOfOpeningHours)) {
              _tmpOpeningHours = null;
            } else {
              _tmpOpeningHours = _cursor.getString(_cursorIndexOfOpeningHours);
            }
            final boolean _tmpIsHospital;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsHospital);
            _tmpIsHospital = _tmp != 0;
            final boolean _tmpIsEmergency;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsEmergency);
            _tmpIsEmergency = _tmp_1 != 0;
            _item = new Clinic(_tmpId,_tmpName,_tmpAddress,_tmpCity,_tmpCountry,_tmpLatitude,_tmpLongitude,_tmpPhone,_tmpServices,_tmpOpeningHours,_tmpIsHospital,_tmpIsEmergency);
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
  public Flow<List<Clinic>> getClinicsByCity(final String city) {
    final String _sql = "SELECT * FROM clinics WHERE city = ? ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (city == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, city);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"clinics"}, new Callable<List<Clinic>>() {
      @Override
      @NonNull
      public List<Clinic> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfServices = CursorUtil.getColumnIndexOrThrow(_cursor, "services");
          final int _cursorIndexOfOpeningHours = CursorUtil.getColumnIndexOrThrow(_cursor, "openingHours");
          final int _cursorIndexOfIsHospital = CursorUtil.getColumnIndexOrThrow(_cursor, "isHospital");
          final int _cursorIndexOfIsEmergency = CursorUtil.getColumnIndexOrThrow(_cursor, "isEmergency");
          final List<Clinic> _result = new ArrayList<Clinic>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Clinic _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpCountry;
            if (_cursor.isNull(_cursorIndexOfCountry)) {
              _tmpCountry = null;
            } else {
              _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            }
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpServices;
            if (_cursor.isNull(_cursorIndexOfServices)) {
              _tmpServices = null;
            } else {
              _tmpServices = _cursor.getString(_cursorIndexOfServices);
            }
            final String _tmpOpeningHours;
            if (_cursor.isNull(_cursorIndexOfOpeningHours)) {
              _tmpOpeningHours = null;
            } else {
              _tmpOpeningHours = _cursor.getString(_cursorIndexOfOpeningHours);
            }
            final boolean _tmpIsHospital;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsHospital);
            _tmpIsHospital = _tmp != 0;
            final boolean _tmpIsEmergency;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsEmergency);
            _tmpIsEmergency = _tmp_1 != 0;
            _item = new Clinic(_tmpId,_tmpName,_tmpAddress,_tmpCity,_tmpCountry,_tmpLatitude,_tmpLongitude,_tmpPhone,_tmpServices,_tmpOpeningHours,_tmpIsHospital,_tmpIsEmergency);
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
  public Flow<List<Clinic>> getEmergencyClinics() {
    final String _sql = "SELECT * FROM clinics WHERE isEmergency = 1 ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"clinics"}, new Callable<List<Clinic>>() {
      @Override
      @NonNull
      public List<Clinic> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfServices = CursorUtil.getColumnIndexOrThrow(_cursor, "services");
          final int _cursorIndexOfOpeningHours = CursorUtil.getColumnIndexOrThrow(_cursor, "openingHours");
          final int _cursorIndexOfIsHospital = CursorUtil.getColumnIndexOrThrow(_cursor, "isHospital");
          final int _cursorIndexOfIsEmergency = CursorUtil.getColumnIndexOrThrow(_cursor, "isEmergency");
          final List<Clinic> _result = new ArrayList<Clinic>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Clinic _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpCountry;
            if (_cursor.isNull(_cursorIndexOfCountry)) {
              _tmpCountry = null;
            } else {
              _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            }
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpServices;
            if (_cursor.isNull(_cursorIndexOfServices)) {
              _tmpServices = null;
            } else {
              _tmpServices = _cursor.getString(_cursorIndexOfServices);
            }
            final String _tmpOpeningHours;
            if (_cursor.isNull(_cursorIndexOfOpeningHours)) {
              _tmpOpeningHours = null;
            } else {
              _tmpOpeningHours = _cursor.getString(_cursorIndexOfOpeningHours);
            }
            final boolean _tmpIsHospital;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsHospital);
            _tmpIsHospital = _tmp != 0;
            final boolean _tmpIsEmergency;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsEmergency);
            _tmpIsEmergency = _tmp_1 != 0;
            _item = new Clinic(_tmpId,_tmpName,_tmpAddress,_tmpCity,_tmpCountry,_tmpLatitude,_tmpLongitude,_tmpPhone,_tmpServices,_tmpOpeningHours,_tmpIsHospital,_tmpIsEmergency);
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
  public Flow<List<Clinic>> getHospitals() {
    final String _sql = "SELECT * FROM clinics WHERE isHospital = 1 ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"clinics"}, new Callable<List<Clinic>>() {
      @Override
      @NonNull
      public List<Clinic> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfServices = CursorUtil.getColumnIndexOrThrow(_cursor, "services");
          final int _cursorIndexOfOpeningHours = CursorUtil.getColumnIndexOrThrow(_cursor, "openingHours");
          final int _cursorIndexOfIsHospital = CursorUtil.getColumnIndexOrThrow(_cursor, "isHospital");
          final int _cursorIndexOfIsEmergency = CursorUtil.getColumnIndexOrThrow(_cursor, "isEmergency");
          final List<Clinic> _result = new ArrayList<Clinic>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Clinic _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpCountry;
            if (_cursor.isNull(_cursorIndexOfCountry)) {
              _tmpCountry = null;
            } else {
              _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            }
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpServices;
            if (_cursor.isNull(_cursorIndexOfServices)) {
              _tmpServices = null;
            } else {
              _tmpServices = _cursor.getString(_cursorIndexOfServices);
            }
            final String _tmpOpeningHours;
            if (_cursor.isNull(_cursorIndexOfOpeningHours)) {
              _tmpOpeningHours = null;
            } else {
              _tmpOpeningHours = _cursor.getString(_cursorIndexOfOpeningHours);
            }
            final boolean _tmpIsHospital;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsHospital);
            _tmpIsHospital = _tmp != 0;
            final boolean _tmpIsEmergency;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsEmergency);
            _tmpIsEmergency = _tmp_1 != 0;
            _item = new Clinic(_tmpId,_tmpName,_tmpAddress,_tmpCity,_tmpCountry,_tmpLatitude,_tmpLongitude,_tmpPhone,_tmpServices,_tmpOpeningHours,_tmpIsHospital,_tmpIsEmergency);
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
  public Flow<List<String>> getAllCountries() {
    final String _sql = "SELECT DISTINCT country FROM clinics ORDER BY country ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"clinics"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
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
  public Flow<List<String>> getCitiesByCountry(final String country) {
    final String _sql = "SELECT DISTINCT city FROM clinics WHERE country = ? ORDER BY city ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (country == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, country);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"clinics"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
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
  public Object getClinicCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM clinics";
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
