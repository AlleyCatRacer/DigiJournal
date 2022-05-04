package com.s22.digijournal.Persistence.DAOs;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.s22.digijournal.Model.Task;
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
public final class TaskDAO_Impl extends TaskDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Task> __insertionAdapterOfTask;

  private final EntityDeletionOrUpdateAdapter<Task> __updateAdapterOfTask;

  private final SharedSQLiteStatement __preparedStmtOfRemoveTask;

  private final SharedSQLiteStatement __preparedStmtOfRemoveAll;

  public TaskDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTask = new EntityInsertionAdapter<Task>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Task` (`taskID`,`status`,`taskName`,`description`,`dateAdded`,`dateEdited`,`deadline`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Task value) {
        stmt.bindLong(1, value.getTaskID());
        final int _tmp = value.isDone() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        if (value.getTaskName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTaskName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        stmt.bindLong(5, value.getDateAdded());
        stmt.bindLong(6, value.getDateEdited());
        stmt.bindLong(7, value.getDeadline());
      }
    };
    this.__updateAdapterOfTask = new EntityDeletionOrUpdateAdapter<Task>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Task` SET `taskID` = ?,`status` = ?,`taskName` = ?,`description` = ?,`dateAdded` = ?,`dateEdited` = ?,`deadline` = ? WHERE `taskID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Task value) {
        stmt.bindLong(1, value.getTaskID());
        final int _tmp = value.isDone() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        if (value.getTaskName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTaskName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        stmt.bindLong(5, value.getDateAdded());
        stmt.bindLong(6, value.getDateEdited());
        stmt.bindLong(7, value.getDeadline());
        stmt.bindLong(8, value.getTaskID());
      }
    };
    this.__preparedStmtOfRemoveTask = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM task WHERE taskID = ?";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM task";
        return _query;
      }
    };
  }

  @Override
  void insertTask(final Task task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTask.insert(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  void editTask(final Task task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTask.handle(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void removeTask(final int taskId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveTask.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, taskId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfRemoveTask.release(_stmt);
    }
  }

  @Override
  public void removeAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfRemoveAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Task>> getAllTasks() {
    final String _sql = "SELECT `task`.`taskID` AS `taskID`, `task`.`status` AS `status`, `task`.`taskName` AS `taskName`, `task`.`description` AS `description`, `task`.`dateAdded` AS `dateAdded`, `task`.`dateEdited` AS `dateEdited`, `task`.`deadline` AS `deadline` FROM task ORDER BY taskId";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"task"}, false, new Callable<List<Task>>() {
      @Override
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTaskID = 0;
          final int _cursorIndexOfIsDone = 1;
          final int _cursorIndexOfTaskName = 2;
          final int _cursorIndexOfDescription = 3;
          final int _cursorIndexOfDateAdded = 4;
          final int _cursorIndexOfDateEdited = 5;
          final int _cursorIndexOfDeadline = 6;
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Task _item;
            _item = new Task();
            _item.taskID = _cursor.getInt(_cursorIndexOfTaskID);
            final boolean _tmpIsDone;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDone);
            _tmpIsDone = _tmp != 0;
            _item.setIsDone(_tmpIsDone);
            final String _tmpTaskName;
            if (_cursor.isNull(_cursorIndexOfTaskName)) {
              _tmpTaskName = null;
            } else {
              _tmpTaskName = _cursor.getString(_cursorIndexOfTaskName);
            }
            _item.setTaskName(_tmpTaskName);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item.setDescription(_tmpDescription);
            final long _tmpDateAdded;
            _tmpDateAdded = _cursor.getLong(_cursorIndexOfDateAdded);
            _item.setDateAdded(_tmpDateAdded);
            final long _tmpDateEdited;
            _tmpDateEdited = _cursor.getLong(_cursorIndexOfDateEdited);
            _item.setDateEdited(_tmpDateEdited);
            final long _tmpDeadline;
            _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            _item.setDeadline(_tmpDeadline);
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
