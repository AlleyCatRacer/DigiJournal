package com.s22.digijournal;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.s22.digijournal.Fragments.Home.HomeFragment;
import com.s22.digijournal.Fragments.Overview.TasksOverviewFragment;
import com.s22.digijournal.Fragments.Task.TaskAddFragment;
import com.s22.digijournal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
	
	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
		
		BottomNavigationView bottomNav = binding.bottomNavView;
		replaceFragment(new HomeFragment());
		
		bottomNav.setOnItemSelectedListener(item ->
		{
			switch (item.getItemId())
			{
				case R.id.home_fragment:
					replaceFragment(new HomeFragment());
					break;
				case R.id.add_task_fragment:
					replaceFragment(new TaskAddFragment());
					break;
				case R.id.tasks_overview_fragment:
					replaceFragment(new TasksOverviewFragment());
					break;
				default: return false;
			}
			return true;
		});
	}
	
	private void replaceFragment(Fragment fragment)
	{
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
	}
	
	@Override public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.bottom_nav, menu);
		return true;
	}
}