package com.s22.digijournal.Persistence.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.s22.digijournal.Model.TaskList;

import java.util.List;

@Dao public interface ListDAO
{
	@Insert void addList(TaskList list);
	@Update void updateList(TaskList list);
	@Transaction
	@Query("SELECT * FROM taskList") LiveData<List<TaskList>> getAllLists();
	@Query("DELETE FROM taskList") void removeAll();
	@Query("DELETE FROM taskList WHERE listName = :listName") void removeList(String listName);
}
