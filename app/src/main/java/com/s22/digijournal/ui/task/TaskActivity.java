package com.s22.digijournal.ui.task;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.R;
import com.s22.digijournal.viewModels.TaskViewModel;

public class TaskActivity extends AppCompatActivity
{
    private RecyclerView tasks;
    private TaskAdapter adapter;
    private TaskViewModel viewModel;
    private NavController navController;
    
    @Override protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        navController = Navigation.findNavController(this, R.id.main_fragment_container);
        //binding = TaskActivityBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_tasks);
        
        adapter = new TaskAdapter(viewModel.getAllTasks());
        adapter.setOnClickListener(selected ->
        {
            navController.navigate(R.id.action_nav_tasks_to_taskDetailsFragment);
        });
        
        tasks = findViewById(R.id.task_recycler);
        tasks.hasFixedSize();
        tasks.setLayoutManager(new LinearLayoutManager(this));
        tasks.setAdapter(adapter);
        //TODO instantiate adapter (what arg?), tasks.setAdapter, set listener on adapter
    }
}
/*    private void startLoginActivity()
    {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }*/
