package com.s22.digijournal.ui.task;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.s22.digijournal.ModelTask;
import com.s22.digijournal.persistence.Repos.TaskRepo;
import com.s22.digijournal.persistence.Repos.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskViewModel extends AndroidViewModel
{
	private final TaskRepo taskRepo;
	private final UserRepo userRepo;
	private MutableLiveData<ModelTask> currentTask;
	private MutableLiveData<FirebaseUser> currentUser;
	
	public TaskViewModel(@NonNull Application app)
	{
		super(app);
		taskRepo = TaskRepo.getInstance(app);
		userRepo = UserRepo.getInstance(app);
		currentTask = new MutableLiveData<>();
		currentUser = new MutableLiveData<>();
	}
	
	public LiveData<List<ModelTask>> getTasks()
	{
		return taskRepo.getAllTasks();
	}
	
	public ModelTask getCurrentTask()
	{
		return currentTask.getValue();
	}
	
	public void addTask(ModelTask task)
	{
		currentTask.setValue(task);
		taskRepo.insert(task);
	}
	
	public void editTask()
	{
		taskRepo.update(getCurrentTask());
	}
	
	public void setCurrentTask(ModelTask task)
	{
		currentTask.setValue(task);
	}
	
	public void removeTask()
	{
		taskRepo.delete(getCurrentTask());
		setCurrentTask(null);
	}
	
	public void deleteAll()
	{
		taskRepo.deleteAll();
	}
	
	public void setStatus(boolean complete)
	{
		getCurrentTask().setCompleted(complete);
		editTask();
	}
	
	public List<ModelTask> filterByStatus(boolean status)
	{
		List<ModelTask> all = new ArrayList<>(Objects.requireNonNull(getTasks().getValue()));
		List<ModelTask> filtered = new ArrayList<>(all);
		
		for (ModelTask t : all)
		{
			if (!t.isCompleted()) filtered.remove(t);
		}
		return status ? filtered : all;
	}
	
	public FirebaseUser getCurrentUser()
	{
		return currentUser.getValue();
	}
	
	public void setCurrentUser(FirebaseUser user)
	{
		currentUser.setValue(user);
	}
	
	public void changePassword(String oldPassword, String newPassword)
	{
		userRepo.changePassword(getCurrentUser(), oldPassword, newPassword);
	}
}
