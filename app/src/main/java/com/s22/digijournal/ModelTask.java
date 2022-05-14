package com.s22.digijournal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity(tableName = "task_table")
public class ModelTask
{
	@PrimaryKey(autoGenerate = true)
	private int ID;
	private String name;
	private String description;
	private long deadline;
	@ColumnInfo(name = "status")
	private boolean completed;
	
	public ModelTask()
	{
	
	}
	
	public ModelTask(String name, String description, String deadline)
	{
		this.name = name;
		this.description = description;
		this.deadline = formatDeadline(deadline);
		completed = false;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public boolean isCompleted()
	{
		return completed;
	}
	
	public void setCompleted(boolean isDone)
	{
		this.completed = isDone;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public long getDeadline()
	{
		return deadline;
	}
	
	public void setDeadline(long deadline)
	{
		if (isAfter(deadline))
		{
			this.deadline = deadline;
		}
	}
	
	public String getDeadlineFormatted()
	{
		if (getDeadline() <= 0)
		{
			return "N/A";
		}
		
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
	
	public static List<ModelTask> getUpcomingTasksWeek(List<ModelTask> tasks)
	{
		ArrayList<ModelTask> temp = new ArrayList<>(tasks);
		
		Instant now = Instant.now();
		long tempNow = new Date(now.toEpochMilli()).getTime();
		long weekLater = (604800000 + tempNow);
		//604.800.000 is the number of milliseconds in a week
		
		for (ModelTask t : tasks)
		{
			if (!isBetween(t.getDeadline() * 1000, tempNow, weekLater))
				temp.remove(t);
		}
		
		return temp;
	}
	
	private boolean isAfter(long epoch)
	{
		Date date = new Date(epoch * 1000);
		Instant now = Instant.now();
		Date tempNow = new Date(now.toEpochMilli());
		return date.after(tempNow);
	}
	
	public static boolean isBetween(long target, long start, long end)
	{
		return target > start && target < end;
	}
}