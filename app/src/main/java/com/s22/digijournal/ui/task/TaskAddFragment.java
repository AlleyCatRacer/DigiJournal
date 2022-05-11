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
import com.s22.digijournal.databinding.FragmentTaskAddBinding;

import java.util.Objects;

import kotlinx.coroutines.scheduling.Task;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskAddFragment extends Fragment {
    
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
    
    @Nullable @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        binding.taskAddCreateButton.setOnClickListener(v ->
        {
            ModelTask temp = new ModelTask();
            temp.setName(Objects.requireNonNull(taskName.getText()).toString());
            temp.setDescription(description.getText().toString());
            viewModel.addTask(temp);
            NavHostFragment.findNavController(TaskAddFragment.this).navigate(R.id.action_task_add_to_task_details);
        });
        
        binding.taskAddCancelButton.setOnClickListener(v -> NavHostFragment.findNavController(TaskAddFragment.this).navigate(R.id.action_task_add_to_home));
    }
    
    @Override public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}