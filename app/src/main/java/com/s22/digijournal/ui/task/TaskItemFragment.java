package com.s22.digijournal.ui.task;

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

import com.s22.digijournal.ModelTask;
import com.s22.digijournal.R;

import java.util.List;

public class TaskItemFragment extends Fragment implements TaskAdapter.TaskOnClickListener
{
    private TextView name;
    private CheckBox status;
    private TextView deadline;
    private TaskViewModel viewModel;
    private List<ModelTask> tasks;
    private int taskCount;
    private int taskID;
    private static String TASK_COUNT;
    private static String TASK_ID;
    private static String TASK_STATUS;
    private static String TASK_NAME;
    private static String TASK_DEADLINE;
    
    public TaskItemFragment()
    {
    
    }
    
    public static TaskItemFragment newInstance(int taskCount, int taskID, boolean status, String deadline, String name)
    {
        TaskItemFragment fragment = new TaskItemFragment();
        Bundle args = new Bundle();
        args.putInt(TASK_COUNT, taskCount);
        args.putInt(TASK_ID, taskID);
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
        
        if (getArguments() != null)
        {
            taskCount = tasks.size();
            taskID = getArguments().getInt(TASK_ID);
            name.setText(getArguments().getString(TASK_NAME));
            status.setChecked(getArguments().getBoolean(TASK_STATUS));
            deadline.setText(getArguments().getString(TASK_DEADLINE));
    
            for (int i = 0; i < tasks.size(); i++)
            {
                newInstance(i, tasks.get(i).getID(), tasks.get(i).isCompleted(), tasks.get(i).getDeadlineFormatted(), tasks.get(i).getName());
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
            TaskAdapter adapter = new TaskAdapter(tasks);
            recyclerView.setAdapter(adapter);
            adapter.setTaskListener(this);
        }
        
        return view;
    }
    
    @Override public void onClick(ModelTask task)
    {
        viewModel.setCurrentTask(task);
    }
}