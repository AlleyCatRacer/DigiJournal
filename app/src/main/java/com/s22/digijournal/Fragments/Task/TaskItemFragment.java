package com.s22.digijournal.Fragments.Task;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.R;

/**
 * A fragment representing a list of Items.
 */
public class TaskItemFragment extends Fragment
{
    private static final String TASK_COUNT = "column-count";
    private static String IS_DONE;
    private static String TASK_ID;
    private static String TASK_NAME;
    private static String TASK_DEADLINE;
    // TODO: Customize parameters
    private int taskCount;
    private boolean isDone;
    private int taskID;
    private String taskName;
    private String taskDeadline;

    //Empty constructor for fragment manager to instantiate fragment e.g. screen orientation change
    public TaskItemFragment()
    {
    }

    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
        {
            taskCount = getArguments().getInt(TASK_COUNT);
            isDone = getArguments().getBoolean(IS_DONE);
            taskID = getArguments().getInt(TASK_ID);
            taskName = getArguments().getString(TASK_NAME);
            if (getArguments().getString(TASK_DEADLINE) == null)
            {
                return;
            }
            taskDeadline = getArguments().getString(TASK_DEADLINE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_task_item, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
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
            //recyclerView.setAdapter(new TaskAdapter());
        }
        return view;
    }
}