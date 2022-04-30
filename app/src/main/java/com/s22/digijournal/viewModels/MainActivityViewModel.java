package com.s22.digijournal.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.s22.digijournal.ModelTask;
import com.s22.digijournal.repos.TaskRepo;
import com.s22.digijournal.repos.UserRepo;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel
{
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;
    
    public MainActivityViewModel(@NonNull Application application)
    {
        super(application);
        userRepo = UserRepo.getInstance(application);
        taskRepo = TaskRepo.getInstance();
    }
    
    public LiveData<FirebaseUser> getCurrentUser()
    {
        return userRepo.getCurrentUser();
    }
    
    public void signOut()
    {
        userRepo.signOut();
    }
    
    public ArrayList<ModelTask> getAllTasks()
    {
        return taskRepo.getAllTasks();
    }
    
    public void addTask(ModelTask task)
    {
        taskRepo.addTask(task);
    }
}
