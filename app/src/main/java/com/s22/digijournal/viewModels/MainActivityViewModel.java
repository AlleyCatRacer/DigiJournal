package com.s22.digijournal.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.s22.digijournal.repos.UserRepo;

public class MainActivityViewModel extends AndroidViewModel
{
    private final UserRepo repo;
    
    public MainActivityViewModel(@NonNull Application application)
    {
        super(application);
        repo = UserRepo.getInstance(application);
    }
    
    public LiveData<FirebaseUser> getCurrentUser()
    {
        return repo.getCurrentUser();
    }
    
    public void signOut()
    {
        repo.signOut();
    }
}
