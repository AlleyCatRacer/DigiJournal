package com.s22.digijournal.ui.task;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.R;

public class TaskActivity extends AppCompatActivity
{
    RecyclerView tasks;
    TaskAdapter adapter;
    
    @Override protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        
        tasks = findViewById(R.id.list_details_tasks_recycler);
        tasks.hasFixedSize();
        tasks.setLayoutManager(new LinearLayoutManager(this));
        //TODO instantiate adapter (what arg?), tasks.setAdapter, set listener on adapter
    }
}
