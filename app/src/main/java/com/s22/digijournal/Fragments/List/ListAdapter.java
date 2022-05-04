package com.s22.digijournal.Fragments.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.Model.TaskList;
import com.s22.digijournal.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
{
    private final List<TaskList> lists;
    private ListOnClickListener listener;
    
    public ListAdapter(List<TaskList> lists, ListOnClickListener listener)
    {
        this.lists = lists;
        this.listener = listener;
    }
    
    @Override public void onBindViewHolder(final ViewHolder holder, int position)
    {
        if (holder != null)
        {
            TaskList temp = lists.get(position);
            holder.name.setText(temp.getListName());
            holder.taskCount.setText(temp.getTaskCount());
        }
    }
    
    @Override public int getItemCount()
    {
        return lists.size();
    }
    
    @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_list_item, parent, false);
        return new ViewHolder(view);
    }
    
    public void setOnClickListener(ListOnClickListener listener)
    {
        this.listener = listener;
    }
    
    protected class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private TextView taskCount;
        
        ViewHolder(View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.list_name_textView);
            taskCount = itemView.findViewById(R.id.task_count_textView);
            
            itemView.setOnClickListener(v ->
            {
                listener.onClick(lists.get(getBindingAdapterPosition()));
            });
        }
    }
    
    public interface ListOnClickListener
    {
        void onClick(TaskList list);
    }
}
