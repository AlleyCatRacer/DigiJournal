package com.s22.digijournal.ui.task;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.s22.digijournal.ModelTask;
import com.s22.digijournal.persistence.Repo.TaskRepo;

import java.util.List;

public class TaskViewModel extends AndroidViewModel
{
	private final TaskRepo repo;
	private MutableLiveData<ModelTask> currentTask;
	
	public TaskViewModel(@NonNull Application app)
	{
		super(app);
		repo = TaskRepo.getInstance(app);
		currentTask = new MutableLiveData<>();
	}
	
	public LiveData<List<ModelTask>> getTasks()
	{
		return repo.getAllTasks();
	}
	
	public ModelTask getCurrentTask()
	{
		return currentTask.getValue();
	}
	
	public void addTask(ModelTask task)
	{
		currentTask.setValue(task);
		repo.insert(task);
	}
	
	public void editTask()
	{
		repo.update(getCurrentTask());
	}
	
	public void setCurrentTask(ModelTask task)
	{
		currentTask.setValue(task);
	}
	
	public void removeTask()
	{
		repo.delete(getCurrentTask());
		setCurrentTask(null);
	}
	
	public void deleteAll()
	{
		repo.deleteAll();
	}
	
	public void setStatus(boolean complete)
	{
		getCurrentTask().setCompleted(complete);
		editTask();
	}
}
