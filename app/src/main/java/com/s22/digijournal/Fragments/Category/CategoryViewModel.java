package com.s22.digijournal.Fragments.Category;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.s22.digijournal.Model.Category;
import com.s22.digijournal.Persistence.Repos.CategoryRepo;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel
{
	private final CategoryRepo repo;
	private MutableLiveData<Category> currentCategory;
	
	public CategoryViewModel(@NonNull Application app)
	{
		super(app);
		repo = CategoryRepo.getInstance(app);
		currentCategory = new MutableLiveData<>();
	}
	
	public LiveData<List<Category>> getCategories()
	{
		return repo.getCategories();
	}
	
	public Category getCurrentCategory()
	{
		return currentCategory.getValue();
	}
	
	public void editCategory()
	{
		repo.editCategory(getCurrentCategory());
	}
	
	public void addCategory(Category category)
	{
		currentCategory.setValue(category);
		repo.addCategory(category);
	}
	
	public void removeCategory(Category category)
	{
		repo.removeCategory(category);
	}
}
