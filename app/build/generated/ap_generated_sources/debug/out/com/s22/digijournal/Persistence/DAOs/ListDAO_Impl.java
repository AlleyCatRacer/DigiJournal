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
import com.s22.digijournal.Model.TaskList;
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
public final class ListDAO_Impl implements ListDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TaskList> __insertionAdapterOfTaskList;

  private final EntityDeletionOrUpdateAdapter<TaskList> __updateAdapterOfTaskList;

  private final SharedSQLiteStatement __preparedStmtOfRemoveAll;

  private final SharedSQLiteStatement __preparedStmtOfRemoveList;

  public ListDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTaskList = new EntityInsertionAdapter<TaskList>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `TaskList` (`listName`) VALUES (?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TaskList value) {
        if (value.getListName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getListName());
        }
      }
    };
    this.__updateAdapterOfTaskList = new EntityDeletionOrUpdateAdapter<TaskList>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `TaskList` SET `listName` = ? WHERE `listName` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TaskList value) {
        if (value.getListName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getListName());
        }
        if (value.getListName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getListName());
        }
      }
    };
    this.__preparedStmtOfRemoveAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM taskList";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveList = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM taskList WHERE listName = ?";
        return _query;
      }
    };
  }

  @Override
  public void addList(final TaskList list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTaskList.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateList(final TaskList list) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTaskList.handle(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
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
  public void removeList(final String listName) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveList.acquire();
    int _argIndex = 1;
    if (listName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, listName);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfRemoveList.release(_stmt);
    }
  }

  @Override
  public LiveData<List<TaskList>> getAllLists() {
    final String _sql = "SELECT `taskList`.`listName` AS `listName` FROM taskList";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"taskList"}, true, new Callable<List<TaskList>>() {
      @Override
      public List<TaskList> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfListName = 0;
            final List<TaskList> _result = new ArrayList<TaskList>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final TaskList _item;
              _item = new TaskList();
              final String _tmpListName;
              if (_cursor.isNull(_cursorIndexOfListName)) {
                _tmpListName = null;
              } else {
                _tmpListName = _cursor.getString(_cursorIndexOfListName);
              }
              _item.setListName(_tmpListName);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
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
