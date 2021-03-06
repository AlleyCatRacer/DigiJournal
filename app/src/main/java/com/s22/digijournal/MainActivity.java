package com.s22.digijournal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.s22.digijournal.databinding.ActivityMainBinding;
import com.s22.digijournal.User.LoginActivity;
import com.s22.digijournal.User.LoginViewModel;

public class MainActivity extends AppCompatActivity
{
	private ActivityMainBinding binding;
	private LoginViewModel loginViewModel;
	private AppBarConfiguration mAppBarConfiguration;
	
	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
		
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		
		checkIfSignedIn();
		
		setContentView(binding.getRoot());
		setSupportActionBar(findViewById(R.id.toolbar));
		DrawerLayout drawer = binding.drawerLayout;
		NavigationView navigationView = binding.navView;
		mAppBarConfiguration = new AppBarConfiguration.Builder(
				R.id.nav_home, R.id.nav_tasks, R.id.nav_add_task)
				.setOpenableLayout(drawer).build();
		
		NavController navController = Navigation
				.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main);
		
		NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, mAppBarConfiguration);
		NavigationUI.setupWithNavController(navigationView, navController);
		
		TextView username = navigationView.getHeaderView(0).findViewById(R.id.nav_header_username);
		username.setText(loginViewModel.getCurrentUser().getDisplayName());
		TextView email = navigationView.getHeaderView(0).findViewById(R.id.nav_header_main_email);
		email.setText(loginViewModel.getCurrentUser().getEmail());
	}
	
	@Override public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		menu.findItem(R.id.action_logout).setOnMenuItemClickListener(v1 ->
		{
			onLogoutClicked();
			return true;
		});
		return true;
	}
	
	@Override public boolean onSupportNavigateUp()
	{
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
		return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
	}
	
	private void checkIfSignedIn()
	{
		loginViewModel.getCurrentUserLive().observe(this, user ->
		{
			if (user == null)
			{
				goToLogin();
			}
		});
	}
	
	private void goToLogin()
	{
		startActivity(new Intent(this, LoginActivity.class));
		finish();
	}
	
	public void onLogoutClicked()
	{
		loginViewModel.logout();
		goToLogin();
	}
}