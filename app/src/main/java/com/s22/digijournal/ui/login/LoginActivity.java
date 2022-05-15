package com.s22.digijournal.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.firebase.ui.auth.AuthUI;
import com.s22.digijournal.MainActivity;
import com.s22.digijournal.R;
import java.util.Collections;
import java.util.List;

public class LoginActivity extends AppCompatActivity
{
	private LoginViewModel viewModel;
	private ActivityResultLauncher<Intent> activityResultLauncher;
	
	@Override protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->
		{
			if (result.getResultCode() == RESULT_OK)
			{
				goToMainActivity();
			}
			else
			{
				Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
			}
		});
		
		viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
		isSignedIn();
		setContentView(R.layout.activity_login);
	}
	
	private void isSignedIn()
	{
		viewModel.getCurrentUser().observe(this, user ->
		{
			if (user!= null)
			{
				goToMainActivity();
			}
		});
	}
	
	private void goToMainActivity()
	{
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}
	
	public void login(View view)
	{
		List<AuthUI.IdpConfig> providers = Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());
		
		Intent loginIntent = AuthUI.getInstance().createSignInIntentBuilder()
				.setAvailableProviders(providers)
				.setLogo(R.drawable.launcher_diary).build();
		
		activityResultLauncher.launch(loginIntent);
	}
}