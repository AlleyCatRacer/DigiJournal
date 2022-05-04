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

import com.google.android.material.textfield.TextInputEditText;
import com.s22.digijournal.Model.Task;
import com.s22.digijournal.R;
import com.s22.digijournal.databinding.FragmentTaskEditBinding;

public class TaskEditFragment extends Fragment
{
	private FragmentTaskEditBinding binding;
	private TaskViewModel viewModel;
	private Task temp;
	private TextView taskID;
	private TextInputEditText taskName;
	private TextInputEditText description;
	private TextInputEditText deadline;
	
	public TaskEditFragment()
	{
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
		
		temp = viewModel.getCurrentTask();
		taskID.setText(temp.getTaskID());
		taskName.setText(temp.getTaskName());
		description.setText(temp.getDescription());
		if (temp.getDeadline() == 0)
		{
			return;
		}
		deadline.setText(temp.getDeadlineFormatted());
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		binding = FragmentTaskEditBinding.inflate(inflater, container, false);
		
		taskID = binding.taskEditIDLabel;
		taskName = binding.taskEditTaskName;
		description = binding.taskEditDesc;
		deadline = binding.taskEditDeadline;
		
		return binding.getRoot();
	}
	
	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		
		binding.taskEditSaveButton.setOnClickListener(v ->
		{
			temp.setTaskName(taskName.getText().toString());
			temp.setDescription(description.getText().toString());
			temp.setDeadlineString(deadline.getText().toString());
			NavHostFragment.findNavController(TaskEditFragment.this).navigate(R.id.action_task_edit_to_task_details);
		});
		
		binding.taskEditBackButton.setOnClickListener(v -> NavHostFragment.findNavController(TaskEditFragment.this).navigate(R.id.action_task_edit_to_task_details));
	}
}