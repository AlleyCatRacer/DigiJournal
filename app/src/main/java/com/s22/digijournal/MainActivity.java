package com.s22.digijournal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.s22.digijournal.ui.login.LoginActivity;
import com.s22.digijournal.viewModels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity
{
    private MainActivityViewModel viewModel;
    private TextView header;
    private FloatingActionButton actionButton;
    private NavController navController;
    private FragmentContainerView fragContainer;
    
    //TODO sort out context for different containers in XMLs

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        isSignedIn();
        
        header = findViewById(R.id.home_header);
        fragContainer = findViewById(R.id.main_fragment_container);
        fragContainer.addView(findViewById(R.id.frag_tasks));
        navController = Navigation.findNavController(this, R.id.main_fragment_container);
        actionButton = findViewById(R.id.fab);
        actionButton.setOnClickListener(a -> fragContainer.startViewTransition(findViewById(R.id.frag_add_task)));
        
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