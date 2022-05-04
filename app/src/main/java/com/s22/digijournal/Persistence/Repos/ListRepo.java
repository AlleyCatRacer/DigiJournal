package com.s22.digijournal.Persistence.Repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.s22.digijournal.Model.Task;
import com.s22.digijournal.Model.TaskList;
import com.s22.digijournal.Persistence.DAOs.ListDAO;
import com.s22.digijournal.Persistence.DAOs.TaskDAO;
import com.s22.digijournal.Persistence.Database.ListDatabase;
import com.s22.digijournal.Persistence.Database.TaskDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListRepo
{
	private static ListRepo instance;
	private final ListDAO listDAO;
	private final TaskDAO taskDAO;
	private final LiveData<List<TaskList>> lists;
	private final ExecutorService executorService;
	
	private ListRepo(Application app)
	{
		executorService = Executors.newFixedThreadPool(2);
		ListDatabase db = ListDatabase.getInstance(app);
		listDAO = db.getListDAO();
		TaskDatabase taskDb = TaskDatabase.getInstance(app);
		taskDAO = taskDb.getTaskDAO();
		lists = listDAO.getAllLists();
	}
	
	public static synchronized ListRepo getInstance(Application app)
	{
		if (instance == null)
		{
			instance = new ListRepo(app);
		}
		return instance;
	}
	
	public LiveData<List<TaskList>> getAllLists()
	{
		return lists;
	}
	
	public void addList(TaskList list)
	{
		if (hasList(list))
		{
			return;
		}
		executorService.execute(() -> listDAO.addList(list));
	}
	
	public void addTaskToList(TaskList list, Task task)
	{
		taskDAO.addTask(task);
		addList(list);
		list.addTask(task);
		executorService.execute(() -> listDAO.updateList(list));
	}
	
	public void removeTaskFromList(TaskList list, Task task)
	{
		taskDAO.removeTask(task.getTaskID());
		removeList(list);
		executorService.execute(() -> listDAO.removeList(list.getListName()));
	}
	
	public void removeAllLists()
	{
		executorService.execute(listDAO::removeAll);
	}
	
	public void removeList(TaskList list)
	{
		if (lists.getValue().contains(list))
		{
			executorService.execute(() -> listDAO.removeList(list.getListName()));
		}
	}
	
	private boolean hasList(TaskList list)
	{
		return lists.getValue().contains(list);
	}
}
