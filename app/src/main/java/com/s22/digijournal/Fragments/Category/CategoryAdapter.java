package com.s22.digijournal.Fragments.Category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.Model.Category;
import com.s22.digijournal.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{
    private final List<Category> categories;
    private CategoryOnClickListener categoryListener;
    
    public CategoryAdapter(List<Category> categories, CategoryOnClickListener listener)
    {
        this.categories = categories;
        categoryListener = listener;
    }
    
    @Override public void onBindViewHolder(ViewHolder holder, int position)
    {
        if (holder != null)
        {
            Category temp = categories.get(position);
            holder.name.setText(temp.getCategoryName());
            holder.listCount.setText(temp.getListCount());
        }
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
    
    
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView name;
        private final TextView listCount;
        
        ViewHolder(View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.category_title);
            listCount = itemView.findViewById(R.id.task_count_textView);
            itemView.setOnClickListener(v -> categoryListener.onClick(categories.get((getBindingAdapterPosition()))));
        }
    }
    
    public interface CategoryOnClickListener
    {
        void onClick(Category category);
    }
}
