package com.s22.digijournal.ui.task;

import static com.s22.digijournal.R.color.red;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.s22.digijournal.ModelTask;
import com.s22.digijournal.R;
import com.s22.digijournal.databinding.FragmentTasksBinding;

public class TasksFragment extends Fragment implements TaskAdapter.TaskOnClickListener
{
    private FragmentTasksBinding binding;
    private TaskViewModel viewModel;
    private RecyclerView tasksRecycler;
    private TaskAdapter adapter;
    private TaskAdapter.TaskOnClickListener listener;
    
    public TasksFragment()
    {
    
    }
    
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        
        binding = FragmentTasksBinding.inflate(inflater, container, false);
        
        tasksRecycler = binding.tasksRecycler;
        tasksRecycler.hasFixedSize();
        tasksRecycler.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        listener = this;
        
        return binding.getRoot();
    }
    
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        
        adapter = new TaskAdapter(listener);
        tasksRecycler.setAdapter(adapter);
        
        viewModel.getTasks().observe(getViewLifecycleOwner(), modelTasks -> adapter.setTasks(modelTasks));
        
        binding.tasksStatusFilter.setOnClickListener(v ->
                {
                    if (!binding.tasksStatusFilter.isChecked())
                    {
                        viewModel.getTasks().observe(getViewLifecycleOwner(), modelTasks -> adapter.setTasks(modelTasks));
                    }
                    else
                    {
                        adapter.setTasks(viewModel.filterByStatus(binding.tasksStatusFilter.isChecked(), adapter.getTasks()));
                    }
                });
        binding.tasksDeleteAllButton.setOnClickListener(v ->
        {
            Snackbar snackbar = Snackbar.make(view, "Are you sure you want to delete this task?", Snackbar.LENGTH_LONG);
            snackbar.setAction("YES", v1 -> viewModel.deleteAll());
            snackbar.setActionTextColor((int) red);
            snackbar.show();
        });
    }
    
    @Override public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
    
    @Override public void onClick(ModelTask task)
    {
        viewModel.setCurrentTask(task);
        NavHostFragment.findNavController(TasksFragment.this).navigate(R.id.nav_task_details);
    }
}