package com.s22.digijournal.ui.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.s22.digijournal.ModelTask;
import com.s22.digijournal.R;
import com.s22.digijournal.databinding.FragmentTaskEditBinding;

import java.util.Objects;

public class TaskEditFragment extends Fragment
{
	private FragmentTaskEditBinding binding;
	private TaskViewModel viewModel;
	private TextInputEditText taskName;
	private TextInputEditText description;
	private EditText deadline;
	
	public TaskEditFragment()
	{
	
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
		binding = FragmentTaskEditBinding.inflate(inflater, container, false);
		
		taskName = binding.taskEditTitle;
		description = binding.taskEditDetailText;
		deadline = binding.taskEditDeadline;
		
		return binding.getRoot();
	}
	
	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		seedView();
		binding.taskEditSaveButton.setOnClickListener(v ->
		{
			ModelTask temp = viewModel.getCurrentTask();
			temp.setName(Objects.requireNonNull(taskName.getText()).toString()); ;
			temp.setDescription(description.getText().toString());
			temp.setDeadline(temp.formatDeadline(deadline.getText().toString()));
			viewModel.setCurrentTask(temp);
			viewModel.editTask();
			
			NavHostFragment.findNavController(TaskEditFragment.this).navigate(R.id.action_nav_task_edit_to_nav_task_details);
		});
		
		binding.taskEditCancelButton.setOnClickListener(v -> NavHostFragment.findNavController(TaskEditFragment.this).navigate(R.id.action_nav_task_edit_to_nav_task_details));
	}
	
	private void seedView()
	{
		ModelTask temp = viewModel.getCurrentTask();
		taskName.setText(temp.getName());
		description.setText(temp.getDescription());
		deadline.setText(temp.getDeadlineFormatted());
	}
	
	@Override public void onStart()
	{
		super.onStart();
		seedView();
	}
	
	@Override public void onDestroyView()
	{
		super.onDestroyView();
		binding = null;
	}
}