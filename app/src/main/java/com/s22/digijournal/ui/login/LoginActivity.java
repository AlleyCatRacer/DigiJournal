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
		viewModel.getCurrentUserLive().observe(this, user ->
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

/*
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.init();
        checkIfSignedIn();
        setContentView(R.layout.activity_main);
        messageEditText = findViewById(R.id.message_editText);
        messageTextView = findViewById(R.id.message_textView);
        welcomeMessage = findViewById(R.id.welcome_message);

        viewModel.getMessage().observe(this, message -> {
            if (message != null)
                messageTextView.setText(message.getBody());
        });
    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                String message = "Welcome " + user.getDisplayName();
                welcomeMessage.setText(message);
            } else
                startLoginActivity();
        });
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

    public void saveData(View v) {
        viewModel.saveMessage(messageEditText.getText().toString());
    }

    public void signOut(View v) {
        viewModel.signOut();
    }
*/