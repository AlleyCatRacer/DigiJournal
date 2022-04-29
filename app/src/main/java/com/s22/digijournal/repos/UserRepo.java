package com.s22.digijournal.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;

public class UserRepo
{
    private final Application app;
    private final UserLiveData currentUser;
    private static UserRepo repo;
    
    private UserRepo(Application app)
    {
        this.app = app;
        currentUser = new UserLiveData();
    }
    
    public static synchronized UserRepo getInstance(Application app)
    {
        if (repo == null) repo = new UserRepo(app);
        return repo;
    }
    
    public LiveData<FirebaseUser> getCurrentUser()
    {
        return currentUser;
    }
    
    public void signOut()
    {
        AuthUI.getInstance().signOut(app.getApplicationContext());
    }
}
