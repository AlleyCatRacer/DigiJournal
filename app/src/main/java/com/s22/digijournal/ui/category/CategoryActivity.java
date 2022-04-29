package com.s22.digijournal.ui.category;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.R;

public class CategoryActivity extends AppCompatActivity
{
    RecyclerView categories;
    CategoryAdapter adapter;
    
    
    @Override protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        
        categories = findViewById(R.id.task_recycler);
        categories.hasFixedSize();
        categories.setLayoutManager(new LinearLayoutManager(this));
        
        
    }
}
