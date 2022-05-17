package com.s22.digijournal.persistence.Repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.s22.digijournal.ModelTask;
import com.s22.digijournal.persistence.DAO.TaskDAO;
import com.s22.digijournal.persistence.Database.TaskDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepo
{
	private static TaskRepo instance;
	private final TaskDAO dao;
	private final ExecutorService executorService;
	
	private TaskRepo(Application application)
	{
		TaskDatabase db = TaskDatabase.getInstance(application);
		dao = db.taskDAO();
		executorService = Executors.newFixedThreadPool(2);
		seed();
	}
	
	public static synchronized TaskRepo getInstance(Application application)
	{
		if (instance == null)
		{
			instance = new TaskRepo(application);
		}
		return instance;
	}
	
	public LiveData<List<ModelTask>> getAllTasks()
	{
		return dao.getAllTasks();
	}

	public void insert(ModelTask task)
	{
		executorService.execute(() -> dao.insert(task));
	}
	
	public void update(ModelTask task)
	{
		executorService.execute(() -> dao.update(task));
	}
	
	public void delete(ModelTask task)
	{
		executorService.execute(() -> dao.delete(task));
	}
	
	public void deleteAll()
	{
		executorService.execute(dao::removeAllTasks);
	}
	
	private void seed()
	{
		ModelTask t1 = new ModelTask("AND project", "Semester project for Android Development", "17/05/2022");
		ModelTask t2 = new ModelTask("AND Video Demo", "Record demo for handin", "17/05/2022");
		ModelTask t3 = new ModelTask("Log Work Hours", "", "31/05/2022");
		ModelTask t4 = new ModelTask("Reply To Boligstøtte", "", "01/06/2022");
		ModelTask t5 = new ModelTask("F1 Spain", "Catalunya circuit", "22/05/2022");
		ModelTask t6 = new ModelTask("Daily Scrum Meeting", "C.08.03 at 9:00 AM", "18/05/2022");
		ModelTask t7 = new ModelTask("Prison Ink", "Fængslet", "28/05/2022");
		ModelTask t8 = new ModelTask("Jailbreak", "Fængslet", "13/08/2022");
		ModelTask t9 = new ModelTask("Switch BulletJournal", "", "31/12/2022");
		ModelTask t10 = new ModelTask("Register Internship", "praktikportal.dk", "01/08/2022");
		ModelTask t11 = new ModelTask("Laundry", "", "");
		ModelTask t12 = new ModelTask("Test PayloadDecoder", "", "");
		
		ArrayList<ModelTask> seedTasks = new ArrayList<>();
		seedTasks.add(t1);
		seedTasks.add(t2);
		seedTasks.add(t3);
		seedTasks.add(t4);
		seedTasks.add(t5);
		seedTasks.add(t6);
		seedTasks.add(t7);
		seedTasks.add(t8);
		seedTasks.add(t9);
		seedTasks.add(t10);
		seedTasks.add(t11);
		seedTasks.add(t12);
		
		for (ModelTask t : seedTasks)
		{
			insert(t);
		}
	}
}
