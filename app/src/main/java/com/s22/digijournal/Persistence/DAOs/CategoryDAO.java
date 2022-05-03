package com.s22.digijournal.Persistence.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.s22.digijournal.Model.Category;

import java.util.List;

@Dao
public interface CategoryDAO
{
	@Insert void addCategory(Category category);
	@Update void updateCategory(Category category);
	@Transaction
	@Query("SELECT * FROM category") LiveData<List<Category>> getAllCategories();
	@Query("DELETE FROM category") void removeAll();
	@Query("DELETE FROM category WHERE categoryName = :name") void removeCategory(String name);
}
