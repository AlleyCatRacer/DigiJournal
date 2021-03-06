package com.s22.digijournal.Task;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.R;

import java.util.List;

public class TaskItemFragment extends Fragment implements TaskAdapter.TaskOnClickListener
{
    private TextView name;
    //Editing not allowed in this view so this checkbox is immutable
    //Changing task status is only allowed from the task details view
    private CheckBox status;
    private TextView deadline;
    private TaskViewModel viewModel;
    private TaskAdapter.TaskOnClickListener listener;
    private List<ModelTask> tasks;
    private int taskCount;
    private static String TASK_COUNT;
    private static String TASK_STATUS;
    private static String TASK_NAME;
    private static String TASK_DEADLINE;
    
    public TaskItemFragment()
    {
    
    }
    
    public static TaskItemFragment newInstance(int taskCount, boolean status, String deadline, String name)
    {
        TaskItemFragment fragment = new TaskItemFragment();
        Bundle args = new Bundle();
        args.putInt(TASK_COUNT, taskCount);
        args.putBoolean(TASK_STATUS, status);
        args.putString(TASK_DEADLINE, deadline);
        args.putString(TASK_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        tasks = viewModel.getTasks().getValue();
        listener = this;
        
        if (getArguments() != null)
        {
            taskCount = tasks.size();
            name.setText(getArguments().getString(TASK_NAME));
            status.setChecked(getArguments().getBoolean(TASK_STATUS));
    
            for (int i = 0; i < tasks.size(); i++)
            {
                newInstance(i, tasks.get(i).isCompleted(), tasks.get(i).getDeadlineFormatted(), tasks.get(i).getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_task_item, container, false);

        // Set the adapter
        if (view instanceof RecyclerView)
        {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (taskCount <= 1)
            {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }
            else
            {
                recyclerView.setLayoutManager(new GridLayoutManager(context, taskCount));
            }
            TaskAdapter adapter = new TaskAdapter(listener);
            adapter.setTasks(tasks);
            recyclerView.setAdapter(adapter);
        }
        
        return view;
    }
    
    @Override public void onClick(ModelTask task)
    {
        viewModel.setCurrentTask(task);
    }
}