package com.s22.digijournal.Fragments.Task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.s22.digijournal.Model.Task;
import com.s22.digijournal.R;
import com.s22.digijournal.databinding.FragmentTaskDetailsBinding;

public class TaskDetailsFragment extends Fragment implements TaskAdapter.TaskOnClickListener
{
	private TaskViewModel viewModel;
	private Task temp;
	private FragmentTaskDetailsBinding binding;
	private TextView taskID;
	private TextView taskName;
	private TextView description;
	
	@Override public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
		
		temp = viewModel.getCurrentTask();
		
		if (temp.getTaskID() != 0)
		{
			taskID.setText(temp.getTaskID());
			taskName.setText(temp.getTaskName());
			description.setText(temp.getDescription());
		}
	}
	
	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		binding = FragmentTaskDetailsBinding.inflate(inflater, container, false);
		
		taskID = binding.taskDetailsIDLabel;
		taskName = binding.taskDetailsTaskName;
		description = binding.taskDetailsDesc;
		
		return binding.getRoot();
	}
	
	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		
		binding.taskDetailsEditButton.setOnClickListener(v -> NavHostFragment.findNavController(TaskDetailsFragment.this).navigate(R.id.action_task_details_to_task_edit));
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