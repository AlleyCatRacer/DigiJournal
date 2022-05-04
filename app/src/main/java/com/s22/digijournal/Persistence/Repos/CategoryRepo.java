package com.s22.digijournal.Persistence.Repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.s22.digijournal.Model.Category;
import com.s22.digijournal.Model.TaskList;
import com.s22.digijournal.Persistence.DAOs.CategoryDAO;
import com.s22.digijournal.Persistence.DAOs.ListDAO;
import com.s22.digijournal.Persistence.Database.CategoryDatabase;
import com.s22.digijournal.Persistence.Database.ListDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoryRepo
{
	private static CategoryRepo instance;
	private final CategoryDAO categoryDAO;
	private final ListDAO listDAO;
	private final LiveData<List<Category>> categories;
	private final ExecutorService executorService;
	
	private CategoryRepo(Application app)
	{
		executorService = Executors.newFixedThreadPool(2);
		CategoryDatabase db = CategoryDatabase.getInstance(app);
		categoryDAO = db.getCategoryDAO();
		ListDatabase listDb = ListDatabase.getInstance(app);
		listDAO = listDb.getListDAO();
		categories = categoryDAO.getAllCategories();
	}
	
	public static synchronized CategoryRepo getInstance(Application app)
	{
		if (instance == null)
		{
			instance = new CategoryRepo(app);
		}
		
		return instance;
	}
	
	public LiveData<List<Category>> getCategories()
	{
		return categories;
	}
	
	public void addCategory(Category category)
	{
		if (hasCategory(category))
		{
			return;
		}
		executorService.execute(() -> categoryDAO.addCategory(category));
	}
	
	public void addListToCategory(Category category, TaskList list)
	{
		listDAO.addList(list);
		addCategory(category);
		category.addList(list);
		executorService.execute(() -> categoryDAO.updateCategory(category));
	}
	
	public void editCategory(Category category)
	{
		executorService.execute(() -> categoryDAO.updateCategory(category));
	}
	
	public void removeListFromCategory(Category category, TaskList list)
	{
		if (category.getLists().contains(list))
		{
			category.removeList(list);
			executorService.execute(() -> removeListFromCategory(category, list));
		}
	}
	
	public void removeAllCategories()
	{
		executorService.execute(categoryDAO::removeAll);
	}
	
	public void removeCategory(Category category)
	{
		if (!hasCategory(category)) return;
		executorService.execute(() -> categoryDAO.removeCategory(category.getCategoryName()));
	}
	
	private boolean hasCategory(Category category)
	{
		return categories.getValue().contains(category);
	}
}
