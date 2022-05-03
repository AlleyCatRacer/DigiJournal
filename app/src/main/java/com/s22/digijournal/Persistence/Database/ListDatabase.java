package com.s22.digijournal.Persistence.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.s22.digijournal.Model.TaskList;
import com.s22.digijournal.Persistence.DAOs.ListDAO;

@Database(entities = {TaskList.class}, version = 1)
public abstract class ListDatabase extends RoomDatabase
{
	private static ListDatabase instance;

	public static synchronized ListDatabase getInstance(Context context)
	{
		if (instance == null)
		{
			instance = Room.databaseBuilder(context, ListDatabase.class, "list_database").fallbackToDestructiveMigration().build();
		}
		
		return instance;
	}
	
	public abstract ListDAO getListDAO();
}
