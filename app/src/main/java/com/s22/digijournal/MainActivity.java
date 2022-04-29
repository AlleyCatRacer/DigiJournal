package com.s22.digijournal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.s22.digijournal.ui.task.TaskAdapter;

public class MainActivity extends AppCompatActivity
{
    private TextView subheader;
    
    //TODO sort out context for different containers in XMLs

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        subheader = findViewById(R.id.sub_header);
        subheader.setText(R.string.home_subheader);
    }
}