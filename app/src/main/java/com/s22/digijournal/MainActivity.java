package com.s22.digijournal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.s22.digijournal.ui.login.LoginActivity;
import com.s22.digijournal.ui.task.TaskActivity;
import com.s22.digijournal.viewModels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity
{
    private MainActivityViewModel viewModel;
    private TextView header;
    private TextView subheader;
    private FloatingActionButton actionButton;
    //private NavController navController;
    
    //TODO sort out context for different containers in XMLs

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        isSignedIn();
        
        //navController = Navigation.findNavController(this, R.id.main_fragment_container);
        actionButton = findViewById(R.id.fab);
        //actionButton.setOnClickListener(a -> navController.navigate(R.id.action_nav_home_to_taskAddFragment));
        actionButton.setOnClickListener(a -> startTaskActivity());
        
        header = findViewById(R.id.home_header);
        subheader = findViewById(R.id.home_subheader);
        subheader.setText(R.string.home_subheader);
    }
    
    private void isSignedIn()
    {
        viewModel.getCurrentUser().observe(this, user ->
        {
            if (user != null)
            {
                String message = "Welcome " + user.getDisplayName();
                header.setText(message);
            }
            else
            {
                startLoginActivity();
            }
        });
    }
    
    private void startLoginActivity()
    {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
    
    private void startTaskActivity()
    {
        startActivity(new Intent(this, TaskActivity.class));
    }
}