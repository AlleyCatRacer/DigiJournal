package com.s22.digijournal.Persistence.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.s22.digijournal.Model.Category;
import com.s22.digijournal.Persistence.DAOs.CategoryDAO;

@Database(entities = {Category.class}, version = 1)
public abstract class CategoryDatabase extends RoomDatabase
{
	private static  CategoryDatabase instance;
	
	public static synchronized CategoryDatabase getInstance(Context context)
	{
		if (instance == null)
		{
			instance = Room.databaseBuilder(context, CategoryDatabase.class, "category_database").fallbackToDestructiveMigration().build();
		}
		
		return instance;
	}
	
	public abstract CategoryDAO getCategoryDAO();
}
