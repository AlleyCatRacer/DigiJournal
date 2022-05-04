package com.s22.digijournal.Fragments.Task;

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

import com.s22.digijournal.Model.Task;
import com.s22.digijournal.databinding.FragmentTaskItemBinding;

public class TaskItemFragment extends Fragment
{
    private FragmentTaskItemBinding binding;
    private TaskViewModel viewModel;
    private CheckBox isDone;
    private TextView taskName;
    private TextView taskDeadline;

    //Empty constructor for fragment manager to instantiate fragment e.g. screen orientation change
    public TaskItemFragment()
    {
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        
        Task temp = viewModel.getCurrentTask();
        
        if (temp.getTaskID() > 0)
        {
            isDone.setChecked(temp.isDone());
            isDone.setText(temp.getTaskID());
            taskName.setText(temp.getTaskName());
            
            if (temp.getDeadline() == 0)
            {
                return;
            }
            taskDeadline.setText(temp.getDeadlineFormatted());
        }
    }
    
    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = FragmentTaskItemBinding.inflate(inflater, container, false);
        
        isDone = binding.taskCheckBox;
        taskName = binding.taskItemTaskName;
        taskDeadline = binding.taskItemDeadline;
        
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    @Override public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}