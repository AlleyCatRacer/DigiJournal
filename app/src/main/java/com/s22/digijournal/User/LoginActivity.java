package com.s22.digijournal.User;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.s22.digijournal.MainActivity;

public class LoginActivity extends AppCompatActivity
{
	private LoginViewModel viewModel;
	private ActivityResultLauncher<Intent> activityResultLauncher;
	
	@Override protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		goToMainActivity();
//		activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->
//		{
//			if (result.getResultCode() == RESULT_OK)
//			{
//				goToMainActivity();
//			}
//			else
//			{
//				Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
//			}
//		});
//
//		viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
//		isSignedIn();
//		setContentView(R.layout.activity_login);
	}
	
//	private void isSignedIn()
//	{
//		viewModel.getCurrentUserLive().observe(this, user ->
//		{
//			if (user!= null)
//			{
//				goToMainActivity();
//			}
//		});
//	}
	
	private void goToMainActivity()
	{
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}
	
//	public void login(View view)
//	{
//		List<AuthUI.IdpConfig> providers = Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());
//
//		Intent loginIntent = AuthUI.getInstance().createSignInIntentBuilder()
//				.setAvailableProviders(providers)
//				.setLogo(R.mipmap.launcher_diary).build();
//
//		activityResultLauncher.launch(loginIntent);
//	}
}