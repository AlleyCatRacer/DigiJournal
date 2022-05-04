package com.s22.digijournal.Fragments.Task;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.Model.Task;
import com.s22.digijournal.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>
{
    private final List<Task> tasks;
    private TaskOnClickListener taskListener;

    public TaskAdapter(List<Task> tasks, TaskOnClickListener listener)
    {
        this.tasks = tasks;
        taskListener = listener;
    }

    @Override public void onBindViewHolder(final ViewHolder holder, int position)
    {
        if (holder != null)
        {
            Task temp = tasks.get(position);
            holder.done.setChecked(temp.isDone());
            holder.name.setText(temp.getTaskName());
        }
    }

    @Override public int getItemCount()
    {
        return tasks.size();
    }
    
    @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_task_item, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final CheckBox done;
        private final TextView name;

        ViewHolder(View itemView)
        {
            super(itemView);
            done = itemView.findViewById(R.id.task_checkBox);
            name = itemView.findViewById(R.id.task_item_taskName);
            
            itemView.setOnClickListener(v -> taskListener.onClick(tasks.get(getBindingAdapterPosition())));
                //getBindingAdapterPosition gets the position of the item clicked, identifying it
        }
    }

    public interface TaskOnClickListener
    {
        void onClick(Task task);
    }
}