package com.s22.digijournal.ui.overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.ModelTask;
import com.s22.digijournal.R;
import com.s22.digijournal.databinding.FragmentTasksBinding;
import com.s22.digijournal.ui.task.TaskAdapter;
import com.s22.digijournal.ui.task.TaskViewModel;

public class TasksFragment extends Fragment implements TaskAdapter.TaskOnClickListener
{
    private FragmentTasksBinding binding;
    private TaskViewModel viewModel;
    private RecyclerView taskRecycler;
    private TaskAdapter.TaskOnClickListener listener;
    
    public TasksFragment()
    {
    
    }
    
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        binding = FragmentTasksBinding.inflate(inflater, container, false);
        listener = this;
        taskRecycler = binding.tasksActivityTaskRecycler;
        
        return binding.getRoot();
    }
    
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    
        viewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> taskRecycler.setAdapter(new TaskAdapter(tasks, listener)));
        
        binding.fab.setOnClickListener(v -> NavHostFragment.findNavController(TasksFragment.this).navigate(R.id.action_nav_tasks_to_nav_add_task));
    }
    
    @Override public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
    
    @Override public void onClick(ModelTask task)
    {
        NavHostFragment.findNavController(TasksFragment.this).navigate(R.id.action_nav_tasks_to_nav_task_details);
    }
}