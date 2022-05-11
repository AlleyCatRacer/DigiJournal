package com.s22.digijournal.persistence.DAO;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.s22.digijournal.ModelTask;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskDAO_Impl implements TaskDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ModelTask> __insertionAdapterOfModelTask;

  private final EntityDeletionOrUpdateAdapter<ModelTask> __deletionAdapterOfModelTask;

  private final EntityDeletionOrUpdateAdapter<ModelTask> __updateAdapterOfModelTask;

  private final SharedSQLiteStatement __preparedStmtOfRemoveAllTasks;

  public TaskDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfModelTask = new EntityInsertionAdapter<ModelTask>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `task_table` (`ID`,`name`,`description`,`deadline`,`status`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ModelTask value) {
        stmt.bindLong(1, value.getID());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getDeadline());
        final int _tmp = value.isCompleted() ? 1 : 0;
        stmt.bindLong(5, _tmp);
      }
    };
    this.__deletionAdapterOfModelTask = new EntityDeletionOrUpdateAdapter<ModelTask>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `task_table` WHERE `ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ModelTask value) {
        stmt.bindLong(1, value.getID());
      }
    };
    this.__updateAdapterOfModelTask = new EntityDeletionOrUpdateAdapter<ModelTask>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `task_table` SET `ID` = ?,`name` = ?,`description` = ?,`deadline` = ?,`status` = ? WHERE `ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ModelTask value) {
        stmt.bindLong(1, value.getID());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getDeadline());
        final int _tmp = value.isCompleted() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        stmt.bindLong(6, value.getID());
      }
    };
    this.__preparedStmtOfRemoveAllTasks = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM task_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(final ModelTask task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfModelTask.insert(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final ModelTask task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfModelTask.handle(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ModelTask task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfModelTask.handle(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void removeAllTasks() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveAllTasks.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfRemoveAllTasks.release(_stmt);
    }
  }

  @Override
  public LiveData<List<ModelTask>> getAllTasks() {
    final String _sql = "SELECT * FROM task_table ORDER BY ID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"task_table"}, false, new Callable<List<ModelTask>>() {
      @Override
      public List<ModelTask> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfID = CursorUtil.getColumnIndexOrThrow(_cursor, "ID");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<ModelTask> _result = new ArrayList<ModelTask>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ModelTask _item;
            _item = new ModelTask();
            final int _tmpID;
            _tmpID = _cursor.getInt(_cursorIndexOfID);
            _item.setID(_tmpID);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            _item.setName(_tmpName);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item.setDescription(_tmpDescription);
            final long _tmpDeadline;
            _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            _item.setDeadline(_tmpDeadline);
            final boolean _tmpCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCompleted);
            _tmpCompleted = _tmp != 0;
            _item.setCompleted(_tmpCompleted);
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

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
