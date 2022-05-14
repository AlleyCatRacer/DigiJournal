package com.s22.digijournal.ui.task;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.s22.digijournal.ModelTask;
import com.s22.digijournal.persistence.Repo.TaskRepo;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class TaskViewModel extends AndroidViewModel
{
	private final TaskRepo repo;
	private MutableLiveData<ModelTask> currentTask;
	
	public TaskViewModel(@NonNull Application app)
	{
		super(app);
		repo = TaskRepo.getInstance(app);
		currentTask = new MutableLiveData<>();
	}
	
	public LiveData<List<ModelTask>> getTasks()
	{
		return repo.getAllTasks();
	}
	
	public ModelTask getCurrentTask()
	{
		return currentTask.getValue();
	}
	
	public void addTask(ModelTask task)
	{
		currentTask.setValue(task);
		repo.insert(task);
	}
	
	public void editTask()
	{
		if (isAfter(getCurrentTask().getDeadline()))
		repo.update(getCurrentTask());
	}
	
	public void setCurrentTask(ModelTask task)
	{
		currentTask.setValue(task);
	}
	
	public void removeTask()
	{
		repo.delete(getCurrentTask());
		setCurrentTask(null);
	}
	
	public void deleteAll()
	{
		repo.deleteAll();
	}
	
	public void setStatus(boolean complete)
	{
		getCurrentTask().setCompleted(complete);
		editTask();
	}
	
	public String getDeadlineFormatted()
	{
		if (getCurrentTask().getDeadline() == 0)
		{
			return "N/A";
		}
		long deadline = getCurrentTask().getDeadline();
		return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date(deadline * 1000));
	}
	
	public long formatDeadline(String stringDeadline)
	{
		if (stringDeadline.isEmpty() || stringDeadline.equals("N/A"))
		{
			return 0;
		}
		String withSecs = stringDeadline + " 23:59:59";
		LocalDate date = LocalDate.parse(withSecs, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		Instant temp = date.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant();
		
		return new java.util.Date(temp.getEpochSecond()).getTime();
	}
	
	public LiveData<List<ModelTask>> getUpcomingTasksWeek()
	{
		Instant now = Instant.now();
		long tempNow = new Date(now.toEpochMilli()).getTime();
		long weekLater = 604800 + tempNow;
		
		
		return repo.getUpcomingTasks(tempNow, weekLater);
	}
	
	public boolean isAfter(long epoch)
	{
		if (getCurrentTask().getDeadline() == 0) return false;
		Date date = new Date(epoch * 1000);
		Instant now = Instant.now();
		Date tempNow = new Date(now.toEpochMilli());
		return date.after(tempNow);
	}
	
	public boolean isBetween(long target, long start, long end)
	{
		return start <= target && target <= end;
	}
}
