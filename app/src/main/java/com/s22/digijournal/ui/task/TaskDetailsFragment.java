package com.s22.digijournal.ui.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;
import com.s22.digijournal.R;
import com.s22.digijournal.databinding.FragmentTaskDetailsBinding;

public class TaskDetailsFragment extends Fragment
{
	private FragmentTaskDetailsBinding binding;
    private TaskViewModel viewModel;
    private TextView taskName;
    private CheckBox status;
    private TextView description;
    private TextView deadline;
    
    public TaskDetailsFragment()
    {
    
    }
    
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        binding = FragmentTaskDetailsBinding.inflate(inflater, container, false);
        
        taskName = binding.taskDetailsTaskName;
        status = binding.taskDetailsStatusCheckbox;
        description = binding.taskDetailsDetailText;
        deadline = binding.taskDetailsDeadline;
        
        return binding.getRoot();
    }
    
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        taskName.setText(viewModel.getCurrentTask().getName());
        status.setChecked(viewModel.getCurrentTask().isCompleted());
        description.setText(viewModel.getCurrentTask().getDescription());
        deadline.setText(viewModel.getCurrentTask().getDeadlineFormatted());
        
        binding.taskDetailsEditButton.setOnClickListener(v -> toEdit());
        
        binding.taskDetailsDeleteButton.setOnClickListener(v ->
        {
            Snackbar snackbar = Snackbar.make(view, "Are you sure you want to delete this task?", Snackbar.LENGTH_SHORT);
            snackbar.setAction("YES", v1 ->
            {
                viewModel.removeTask();
                toTasks();
            });
            snackbar.show();
        });
        
        binding.taskDetailsStatusCheckbox.setOnCheckedChangeListener((buttonView, isChecked) ->
                viewModel.setStatus(status.isChecked()));
    }
    
    @Override public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
    
    private void toEdit()
    {
        NavHostFragment.findNavController(TaskDetailsFragment.this).navigate(R.id.action_nav_task_details_to_nav_task_edit);
    }
    
    private void toTasks()
    {
        NavHostFragment.findNavController(TaskDetailsFragment.this).navigate(R.id.action_nav_task_details_to_nav_tasks);
    }
}