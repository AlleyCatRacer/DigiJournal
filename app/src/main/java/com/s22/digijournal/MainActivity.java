package com.s22.digijournal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.s22.digijournal.ui.login.LoginActivity;
import com.s22.digijournal.viewModels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private TextView header;
    private TextView subheader;
    
    //TODO sort out context for different containers in XMLs

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        isSignedIn();
        
        binding = DataBindingUtil.setContentView(this, )
        setContentView(binding.getRoot());

        
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
}