package com.s22.digijournal.viewModels;

import androidx.lifecycle.ViewModel;

import com.s22.digijournal.ModelTask;
import com.s22.digijournal.repos.TaskRepo;

import java.util.ArrayList;

public class TaskViewModel extends ViewModel
{
    private final TaskRepo repo;
    
    public TaskViewModel()
    {
        repo = TaskRepo.getInstance();
    }
    
    public ArrayList<ModelTask> getAllTasks()
    {
        return repo.getAllTasks();
    }
    
    public void addTask(ModelTask task)
    {
        repo.addTask(task);
    }
}
