package com.s22.digijournal.ui.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.ModelTask;
import com.s22.digijournal.R;
import com.s22.digijournal.databinding.FragmentTasksBinding;

import java.util.List;

public class TasksFragment extends Fragment implements TaskAdapter.TaskOnClickListener
{
    private FragmentTasksBinding binding;
    private TaskViewModel viewModel;
    private RecyclerView tasksRecycler;
    private TaskAdapter adapter;
    
    public TasksFragment()
    {
    
    }
    
    @Override public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        
        viewModel.getTasks().observe(this, tasks ->
        {
            adapter = new TaskAdapter(tasks);
            adapter.setTaskListener(this);
        });
    }
    
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentTasksBinding.inflate(inflater, container, false);
        
        tasksRecycler = binding.tasksRecycler;
        tasksRecycler.hasFixedSize();
        tasksRecycler.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));

        tasksRecycler.setAdapter(adapter);
        
        return binding.getRoot();
    }
    
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    
        viewModel.getTasks().observe(getViewLifecycleOwner(), new Observer<List<ModelTask>>()
        {
            @Override public void onChanged(List<ModelTask> modelTasks)
            {
                adapter = new TaskAdapter(modelTasks);
                adapter.setTaskListener(TasksFragment.this);
                tasksRecycler.setAdapter(adapter);
            }
        });
        
        binding.fab.setOnClickListener(v -> NavHostFragment.findNavController(TasksFragment.this).navigate(R.id.action_nav_tasks_to_nav_add_task));
    }
    
    @Override public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
    
    @Override public void onClick(ModelTask task)
    {
        viewModel.setCurrentTask(task);
        NavHostFragment.findNavController(TasksFragment.this).navigate(R.id.action_nav_tasks_to_nav_task_details);
    }
}