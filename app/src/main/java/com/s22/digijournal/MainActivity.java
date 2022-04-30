package com.s22.digijournal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.s22.digijournal.ui.login.LoginActivity;
import com.s22.digijournal.viewModels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity
{
    private MainActivityViewModel viewModel;
    private TextView header;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawerLayout;
    private NavigationView navigationDrawer;
    private Toolbar toolbar;
    private FloatingActionButton actionButton;
    
    //TODO sort out context for different containers in XMLs

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        isSignedIn();
        initViews();
        setupNav();
        
        //TODO display fragment in container
        // https://github.com/KasperKnop/NavigationExample/blob/master/app/src/main/java/io/github/kasperknop/pokedb/MainActivity.java
        //TODO navigate to add task fragment
        // https://github.com/KasperKnop/NavigationExample/blob/master/app/src/main/java/io/github/kasperknop/pokedb/AbilitiesFragment.java
    }
    
    private void initViews()
    {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.app_bar_navigation_drawer);
        toolbar = findViewById(R.id.toolbar);
        header = findViewById(R.id.home_header);
        actionButton = findViewById(R.id.fab);
    }
    
    private void setupNav()
    {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setSupportActionBar(toolbar);
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_tasks).
                setOpenableLayout(drawerLayout).build();
        
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationDrawer, navController);
    }
    
    @Override public boolean onSupportNavigateUp()
    {
        return NavigationUI.navigateUp(navController, appBarConfiguration);
    }
    
    @Override public void onBackPressed()
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
    
    @Override public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }
    
    @Override public boolean onOptionsItemSelected(MenuItem item)
    {
        return NavigationUI.onNavDestinationSelected(item, navController);
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