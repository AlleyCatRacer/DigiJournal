package com.s22.digijournal.Fragments.List;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.s22.digijournal.Model.Task;
import com.s22.digijournal.Model.TaskList;
import com.s22.digijournal.Persistence.Repos.ListRepo;

import java.util.List;

public class ListViewModel extends AndroidViewModel
{
	private final ListRepo repo;
	private MutableLiveData<TaskList> currentList;
 
	public ListViewModel(@NonNull Application app)
	{
		super(app);
		repo = ListRepo.getInstance(app);
		currentList = new MutableLiveData<>();
	}
	
	public LiveData<List<TaskList>> getLists()
	{
		return repo.getAllLists();
	}
	
	public TaskList getCurrentList()
	{
		return currentList.getValue();
	}
	
	public void addList(TaskList list)
	{
		if (list.getListName() != null && !list.getListName().isEmpty())
		{
			repo.addList(list);
		}
	}
	
	public void addTaskToList(TaskList list, Task task)
	{
		if (task.getTaskID() != 0)
		{
			repo.addTaskToList(list, task);
		}
	}
	
	public void removeTaskFromList(TaskList list, Task task)
	{
		if (task.getTaskID() != 0)
		{
			repo.removeTaskFromList(list, task);
		}
	}
	
	public void setCurrentList(TaskList list)
	{
		currentList.setValue(list);
	}
	
	public void deleteAll()
	{
		repo.removeAllLists();
	}
}
