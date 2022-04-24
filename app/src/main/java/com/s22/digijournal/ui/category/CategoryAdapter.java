package com.s22.digijournal.ui.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.ModelCategory;
import com.s22.digijournal.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{
    private final ArrayList<ModelCategory> categories;
    private OnClickListener listener;
    
    public CategoryAdapter(ArrayList<ModelCategory> categories)
    {
        this.categories = categories;
    }
    
    @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        //TODO
    }
    
    @Override public int getItemCount()
    {
        return categories.size();
    }
    
    @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_category_item, parent, false);
        return new ViewHolder(view);
    }
    
    
    protected class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView name;
        private final TextView listCount;
        
        ViewHolder(View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.category_title);
            listCount = itemView.findViewById(R.id.task_count_textView);
            itemView.setOnClickListener(v ->
            {
                listener.onClick(categories.get((getBindingAdapterPosition())));
            });
        }
    }
    
    private interface OnClickListener
    {
        void onClick(ModelCategory category);
    }
}
