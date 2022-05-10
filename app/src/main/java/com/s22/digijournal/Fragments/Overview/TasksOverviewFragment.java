package com.s22.digijournal.Fragments.Overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.s22.digijournal.Fragments.Task.TaskAdapter;
import com.s22.digijournal.Fragments.Task.TaskViewModel;
import com.s22.digijournal.Model.Task;
import com.s22.digijournal.R;
import com.s22.digijournal.databinding.FragmentTasksOverviewBinding;

public class TasksOverviewFragment extends Fragment implements TaskAdapter.TaskOnClickListener
{
	private TaskViewModel viewModel;
	private FragmentTasksOverviewBinding binding;
	
	@Override public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
	}
	
	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
												 @Nullable ViewGroup container,
												 @Nullable Bundle savedInstanceState)
	{
		binding = FragmentTasksOverviewBinding.inflate(inflater, container, false);
		
		return binding.getRoot();
	}
	
	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		
		binding.tasksOverviewRecycler.setOnClickListener(v ->
				NavHostFragment.findNavController(TasksOverviewFragment.this)
						.navigate(R.id.action_tasks_to_task_details));
	}
	
	@Override public void onDestroyView()
	{
		super.onDestroyView();
		binding = null;
	}
	
	@Override public void onClick(Task task)
	{
		viewModel.setCurrentTask(task);
	}
}