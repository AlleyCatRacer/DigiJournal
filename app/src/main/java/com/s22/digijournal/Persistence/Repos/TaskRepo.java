package com.s22.digijournal.Persistence.Repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.s22.digijournal.Model.Task;
import com.s22.digijournal.Persistence.DAOs.TaskDAO;
import com.s22.digijournal.Persistence.Database.TaskDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepo
{
	private static TaskRepo instance;
	private final TaskDAO taskDAO;
	private final LiveData<List<Task>> tasks;
	private final ExecutorService executorService;
	
	private TaskRepo(Application app)
	{
		executorService = Executors.newFixedThreadPool(2);
		TaskDatabase db = TaskDatabase.getInstance(app);
		taskDAO = db.getTaskDAO();
		tasks = taskDAO.getAllTasks();
	}
	
	public static synchronized TaskRepo getInstance(Application app)
	{
		if (instance == null)
		{
			instance = new TaskRepo(app);
		}
		
		return instance;
	}
	
	public LiveData<List<Task>> getAllTasks()
	{
		return tasks;
	}
	
	public void insertTask(Task task)
	{
		executorService.execute(() -> taskDAO.addTask(task));
	}
	
	public void editTask(Task task)
	{
		executorService.execute(() -> taskDAO.updateTask(task));
	}
	
	public void removeAll()
	{
		executorService.execute(taskDAO::removeAll);
	}
}
