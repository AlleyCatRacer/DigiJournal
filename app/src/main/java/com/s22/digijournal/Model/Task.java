package com.s22.digijournal.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task
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

    public long getDateEdited()
    {
        return dateEdited;
    }

    public long getDeadline()
    {
        return deadline;
    }

    public void setIsDone(boolean done)
    {
        isDone = done;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setDateEdited(long dateEdited)
    {
        this.dateEdited = dateEdited;
    }

    public void setDeadline(long deadline)
    {
        this.deadline = deadline;
    }
}
