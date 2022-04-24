package com.s22.digijournal.ui.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.s22.digijournal.R;

public class ListActivity extends AppCompatActivity
{
    RecyclerView lists;
    ListAdapter adapter;
    
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        
        lists = findViewById(R.id.task_recycler);
        lists.hasFixedSize();
        lists.setLayoutManager(new LinearLayoutManager(this));
        //TODO instantiate adapter (what arg?), lists.setAdapter, set listener on adapter
    }
}