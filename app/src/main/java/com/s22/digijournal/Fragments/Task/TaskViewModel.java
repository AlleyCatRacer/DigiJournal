package com.s22.digijournal.Fragments.Task;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.s22.digijournal.Model.Task;
import com.s22.digijournal.Persistence.Repos.TaskRepo;

import java.util.List;

public class TaskViewModel extends AndroidViewModel
{
	private final TaskRepo repo;
	private MutableLiveData<Task> currentTask;
	
	public TaskViewModel(@NonNull Application app)
	{
		super(app);
		repo = TaskRepo.getInstance(app);
		currentTask = new MutableLiveData<>();
	}
	
	public LiveData<List<Task>> getTasks()
	{
		return repo.getAllTasks();
	}
	
	public Task getCurrentTask()
	{
		return currentTask.getValue();
	}
	
	public void addTask(Task task)
	{
		currentTask.setValue(task);
		repo.addTask(task);
	}
	
	public void editTask()
	{
		repo.editTask(getCurrentTask());
	}
	
	public void setCurrentTask(Task task)
	{
		currentTask.setValue(task);
	}
	
	public void removeTask(Task task)
	{
		repo.removeTask(task);
	}
	
	public void deleteAll()
	{
		repo.removeAll();
	}
}
