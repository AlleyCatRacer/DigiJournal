package com.s22.digijournal.Task;

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
import com.s22.digijournal.R;
import com.s22.digijournal.databinding.FragmentTaskAddBinding;

import java.util.Objects;

public class TaskAddFragment extends Fragment implements TaskAdapter.TaskOnClickListener
{
    private FragmentTaskAddBinding binding;
    private TaskViewModel viewModel;
    private TextInputEditText taskName;
    private TextInputEditText description;
    private EditText deadline;
    
    public TaskAddFragment()
    {
    
    }
    
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        binding = FragmentTaskAddBinding.inflate(inflater, container, false);
        
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
            String name = Objects.requireNonNull(taskName.getText()).toString();
            String desc = Objects.requireNonNull(description.getText()).toString();
            String dline = deadline.getText().toString();
            ModelTask temp = new ModelTask(name, desc, dline);
            viewModel.addTask(temp);
            
            NavHostFragment.findNavController(TaskAddFragment.this).navigate(R.id.nav_task_details);
        });
        
        binding.taskAddTitle.requestFocus();
    }
    
    @Override public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
    
    @Override public void onStop()
    {
        super.onStop();
        taskName.setText("");
        description.setText("");
        deadline.setText("");
    }
    
    @Override public void onClick(ModelTask task)
    {
        viewModel.setCurrentTask(task);
    }
}