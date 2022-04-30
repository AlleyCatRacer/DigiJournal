package com.s22.digijournal.ui.task;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.ModelTask;
import com.s22.digijournal.R;

import java.util.ArrayList;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>
{
    private final ArrayList<ModelTask> tasks;
    private OnClickListener listener;

    public TaskAdapter(ArrayList<ModelTask> tasks)
    {
        this.tasks = tasks;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_task_item, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        ModelTask temp = tasks.get(position);
        holder.name.setText(temp.getTaskName());
        holder.done.setChecked(temp.isDone());
        if (temp.getDeadline() != null)
        {
            holder.deadline.setText(temp.getDeadlineString());
        }
    }

    @Override
    public int getItemCount()
    {
        return tasks.size();
    }

    public void setOnClickListener(OnClickListener listener)
    {
        this.listener = listener;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder
    {
        private final CheckBox done;
        private final TextView name;
        private final TextView deadline;

        ViewHolder(View itemView)
        {
            super(itemView);
            done = itemView.findViewById(R.id.task_done_checkBox);
            name = itemView.findViewById(R.id.task_item_header);
            deadline = itemView.findViewById(R.id.task_deadline);
            itemView.setOnClickListener(v ->
            {
                listener.onClick(tasks.get(getBindingAdapterPosition()));
                //getBindingAdapterPosition gets the position of the item clicked, identifying it
            });
        }
    }

    public interface OnClickListener
    {
        void onClick(ModelTask task);
    }
}