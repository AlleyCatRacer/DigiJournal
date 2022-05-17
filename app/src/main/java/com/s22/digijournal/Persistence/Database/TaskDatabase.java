package com.s22.digijournal.Persistence.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.s22.digijournal.Task.ModelTask;
import com.s22.digijournal.Persistence.DAO.TaskDAO;

@Database(entities = {ModelTask.class}, version = 2)
public abstract class TaskDatabase extends RoomDatabase
{
	private static TaskDatabase instance;
	public abstract TaskDAO taskDAO();
	
	public static synchronized TaskDatabase getInstance(Context context)
	{
		if (instance == null)
		{
			instance = Room.databaseBuilder(context, TaskDatabase.class, "task_database").
					fallbackToDestructiveMigration().build();
		}
		return instance;
	}
}
