package com.s22.digijournal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
		replaceFragment(new HomeFragment());
		
		binding.bottomNavView.setOnItemSelectedListener(item ->
		{
			switch (item.getItemId())
			{
				case R.id.nav_home:
					replaceFragment(new HomeFragment());
					break;
				case R.id.nav_add_task:
					replaceFragment(new TaskAddFragment());
					break;
				case R.id.nav_tasks:
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
}
/*



*/