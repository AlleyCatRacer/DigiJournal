package com.s22.digijournal.persistence.Repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.s22.digijournal.ModelTask;
import com.s22.digijournal.persistence.DAO.TaskDAO;
import com.s22.digijournal.persistence.Database.TaskDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepo
{
	private static TaskRepo instance;
	private final TaskDAO dao;
	private final LiveData<List<ModelTask>> tasks;
	private final ExecutorService executorService;
	
	private TaskRepo(Application application)
	{
		executorService = Executors.newFixedThreadPool(2);
		TaskDatabase db = TaskDatabase.getInstance(application);
		dao = db.getTaskDAO();
		tasks = dao.getAllTasks();
	}
	
	public static synchronized TaskRepo getInstance(Application application)
	{
		if (instance == null)
		{
			instance = new TaskRepo(application);
		}
		return instance;
	}
	
	public LiveData<List<ModelTask>> getAllTasks()
	{
		return tasks;
	}

	public void insert(ModelTask task)
	{
		executorService.execute(() -> dao.insert(task));
	}
	
	public void update(ModelTask task)
	{
		executorService.execute(() -> dao.update(task));
	}
	
	public void delete(ModelTask task)
	{
		executorService.execute(() -> dao.delete(task));
	}
	
	public void deleteAll()
	{
		executorService.execute(dao::removeAllTasks);
	}
}
