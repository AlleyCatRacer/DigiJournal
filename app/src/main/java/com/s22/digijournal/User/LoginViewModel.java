package com.s22.digijournal.User;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.s22.digijournal.Persistence.Repos.UserRepo;

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
	
	public String getUsername()
	{
		return "Aldís Eir Hansen";
	}
	
	public String getEmail()
	{
		return "aldis_eir@hotmail.com";
	}
	
//	public void logout()
//	{
//		repo.logout();
//	}
	
	public void updateDisplayName(String displayName)
	{
		repo.updateDisplayName(getCurrentUser(), displayName);
	}
	
	public void updateEmail(String email)
	{
		repo.updateEmail(getCurrentUser(), email);
	}
}
