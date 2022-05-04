package com.s22.digijournal.Persistence.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.s22.digijournal.Model.Task;

import java.util.List;

@Dao
public abstract class TaskDAO
{
	@Insert abstract void insertTask(Task task);
	@Update abstract void editTask(Task task);
	@Query("SELECT * FROM task ORDER BY taskId") public abstract LiveData<List<Task>> getAllTasks();
	@Query("DELETE FROM task WHERE taskID = :taskId") public abstract void removeTask(int taskId);
	@Query("DELETE FROM task") public abstract void removeAll();
	
	@Insert public void addTask(Task task)
	{
		task.setDateEdited(System.currentTimeMillis());
		insertTask(task);
	}
	
	@Update public void updateTask(Task task)
	{
		task.setDateEdited(System.currentTimeMillis());
		editTask(task);
	}
	
}
