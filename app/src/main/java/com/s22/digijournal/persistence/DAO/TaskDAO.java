package com.s22.digijournal.persistence.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.s22.digijournal.ModelTask;

import java.util.List;

@Dao
public interface TaskDAO
{
	@Insert void insert(ModelTask task);
	
	@Update void update(ModelTask task);
	
	@Delete void delete(ModelTask task);
	
	@Query("SELECT * FROM task_table ORDER BY taskID")
	LiveData<List<ModelTask>> getAllTasks();
	
	@Query("DELETE FROM task_table")
	void removeAllTasks();
}
