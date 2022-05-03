package com.s22.digijournal.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity public class Task
{
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "taskID") private int taskID;
    private @ColumnInfo(name = "status") boolean isDone;
    @ColumnInfo(name = "taskName") private String taskName;
    @ColumnInfo(name = "description") private String description;
    @ColumnInfo(name = "dateAdded") private long dateAdded;
    @ColumnInfo(name = "dateEdited") private long dateEdited;
    @ColumnInfo(name = "deadline") private long deadline;

    public Task()
    {

    }

    public int getTaskID()
    {
        return taskID;
    }

    public boolean isDone()
    {
        return isDone;
    }
    
    public String getTaskName()
    {
        return taskName;
    }
    
    public String getDescription()
    {
        return description;
    }

    public long getDateAdded()
    {
        return dateAdded;
    }
    
    public String getDateAddedFormatted()
    {
        Date date = new Date(Long.parseLong(String.valueOf(dateAdded)));
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String formatted = format.format(date);
        return formatted;
    }

    public long getDateEdited()
    {
        return dateEdited;
    }
    
    public String getDateEditedFormatted()
    {
        if (dateEdited != 0)
        {
            Date date = new Date(Long.parseLong(String.valueOf(dateEdited)));
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String formatted = format.format(date);
            return formatted;
        }
        return "N/A";
    }

    public long getDeadline()
    {
        return deadline;
    }
    
    public String getDeadlineFormatted()
    {
        if (deadline != 0)
        {
            Date date = new Date(Long.parseLong(String.valueOf(deadline)));
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String formatted = format.format(date);
            return formatted;
        }
        return "N/A";
    }

    public void setIsDone(boolean done)
    {
        isDone = done;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public void setDateAdded(long dateAdded)
    {
        this.dateAdded = dateAdded;
    }

    public void setDateEdited(long dateEdited)
    {
        //TODO check if before date added
        this.dateEdited = dateEdited;
    }

    public void setDeadline(long deadline)
    {
        //TODO check if before date added
        this.deadline = deadline;
    }
}
