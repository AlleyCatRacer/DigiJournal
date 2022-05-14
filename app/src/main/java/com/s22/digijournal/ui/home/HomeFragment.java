package com.s22.digijournal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.ModelTask;
import com.s22.digijournal.R;
import com.s22.digijournal.databinding.FragmentHomeBinding;
import com.s22.digijournal.ui.task.TaskAdapter;
import com.s22.digijournal.ui.task.TaskViewModel;

public class HomeFragment extends Fragment implements TaskAdapter.TaskOnClickListener
{
	private FragmentHomeBinding binding;
	private TaskViewModel viewModel;
	private TaskAdapter adapter;
	private RecyclerView upcomingTasks;
	
	@Override public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		viewModel.getTasks().observe(this, tasks ->
		{
			adapter = new TaskAdapter(viewModel.getUpcomingTasksWeek());
			adapter.setTaskListener(this);
		});
	}
	
	@Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
		adapter = new TaskAdapter(viewModel.getUpcomingTasksWeek());
		
		binding = FragmentHomeBinding.inflate(inflater, container, false);
		
		upcomingTasks = binding.homeUpcomingDeadlines;

		return binding.getRoot();
	}
	
	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		binding.fab.setOnClickListener(v -> NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_nav_home_to_nav_add_task));
	}
	
	@Override public void onDestroyView()
	{
		super.onDestroyView();
		binding = null;
	}
	
	@Override public void onClick(ModelTask task)
	{
		viewModel.setCurrentTask(task);
		NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_nav_home_to_nav_task_details);
	}
}