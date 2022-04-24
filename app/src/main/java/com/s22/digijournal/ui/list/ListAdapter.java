package com.s22.digijournal.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.ModelTaskList;
import com.s22.digijournal.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
{
    private final ArrayList<ModelTaskList> lists;
    private OnClickListener listener;
    
    public ListAdapter(ArrayList<ModelTaskList> lists)
    {
        this.lists = lists;
    }
    
    @Override public void onBindViewHolder(final ViewHolder holder, int position)
    {
        //TODO
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
    
    public void setOnClickListener(OnClickListener listener)
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
    
    private interface OnClickListener
    {
        void onClick(ModelTaskList list);
    }
}
