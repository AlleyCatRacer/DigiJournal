package com.s22.digijournal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.s22.digijournal.ui.task.TaskAdapter;

public class MainActivity extends AppCompatActivity
{
    RecyclerView tasks;
    TaskAdapter taskAdapter;
    RecyclerView lists;
    RecyclerView categories;
    
    //TODO sort out context for different containers in XMLs

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        tasks = findViewById(R.id.list_recycler);
        tasks.hasFixedSize();
        tasks.setLayoutManager(new LinearLayoutManager(this));
        //TODO figure out how to get and pass created tasks from storage
        taskAdapter = new TaskAdapter();
        tasks.setAdapter(taskAdapter);

        taskAdapter.setOnClickListener(task ->
        {
            //TODO how to switch between activities
        });
    }
}