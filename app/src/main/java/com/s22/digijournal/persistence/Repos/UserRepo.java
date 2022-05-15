package com.s22.digijournal.persistence.Repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

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
	
	public LiveData<FirebaseUser> getCurrentUserLive()
	{
		return currentUser;
	}
	
	public FirebaseUser getCurrentUser()
	{
		return currentUser.getValue();
	}
	public void logout()
	{
		AuthUI.getInstance().signOut(app.getApplicationContext());
	}

	public String changePassword(FirebaseUser user, String oldPassword, String newPassword)
	{
		final String[] result = new String[1];
		AuthCredential credentials = EmailAuthProvider.getCredential(Objects.requireNonNull(user.getEmail()), oldPassword);
		user.reauthenticate(credentials).addOnCompleteListener(task1 ->
		{
			if (task1.isSuccessful())
			{
				user.updatePassword(newPassword).addOnCompleteListener(task2 ->
				{
					if (!task2.isSuccessful())
					{
						result[0] = "Something went wrong";
					}
				});
				result[0] = "Password changed successfully";
			}
			else
			{
				result[0] = "Wrong username or password";
			}
		});
		
		return result[0];
	}
}
/*

*/
