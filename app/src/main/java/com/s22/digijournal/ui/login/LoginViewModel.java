package com.s22.digijournal.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.s22.digijournal.persistence.Repos.UserRepo;

public class LoginViewModel extends AndroidViewModel
{
	private final UserRepo repo;
	
	public LoginViewModel(@NonNull Application application)
	{
		super(application);
		repo = UserRepo.getInstance(application);
	}
	
	public LiveData<FirebaseUser> getCurrentUserLive()
	{
		return repo.getCurrentUserLive();
	}
	
	public FirebaseUser getCurrentUser()
	{
		return repo.getCurrentUser();
	}
	
	public void logout()
	{
		repo.logout();
	}
}
