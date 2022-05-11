package com.s22.digijournal.ui.overview;

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
import com.s22.digijournal.databinding.FragmentTaskAddBinding;
import com.s22.digijournal.databinding.FragmentTasksBinding;
import com.s22.digijournal.ui.task.TaskAdapter;
import com.s22.digijournal.ui.task.TaskAddFragment;
import com.s22.digijournal.ui.task.TaskViewModel;

import java.util.Objects;

public class TasksFragment extends Fragment implements TaskAdapter.TaskOnClickListener
{
    private FragmentTasksBinding binding;
    private TaskViewModel viewModel;
    private TextInputEditText taskName;
    private TextInputEditText description;
    private EditText deadline;
    
    public TasksFragment()
    {
    
    }
    
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        binding = FragmentTasksBinding.inflate(inflater, container, false);
        
        taskName = binding.taskAddTitle;
        description = binding.taskAddDetailText;
        deadline = binding.taskAddDeadlineTextInput;
        
        return binding.getRoot();
    }
    
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        binding.taskAddCreateButton.setOnClickListener(v ->
        {
            ModelTask temp = new ModelTask();
            temp.setName(Objects.requireNonNull(taskName.getText()).toString());
            temp.setDescription(description.getText().toString());
            if (!deadline.getText().equals(null))
            {
                temp.setDeadline(temp.formatDeadline(deadline.getText().toString()));
            }
            viewModel.addTask(temp);
            viewModel.setCurrentTask(temp);
            
            NavHostFragment.findNavController(TaskAddFragment.this).navigate(R.id.action_nav_add_task_fragment_to_nav_task_details);
        });
        
        binding.taskAddCancelButton.setOnClickListener(v -> NavHostFragment.findNavController(TaskAddFragment.this).navigate(R.id.action_nav_add_task_fragment_to_nav_home));
    }
    
    @Override public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
    
    @Override public void onClick(ModelTask task)
    {
        viewModel.setCurrentTask(task);
    }
}