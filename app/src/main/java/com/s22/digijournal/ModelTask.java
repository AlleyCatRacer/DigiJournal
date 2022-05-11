package com.s22.digijournal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")
public class ModelTask
{
    @PrimaryKey(autoGenerate = true)
    private int taskID;
    private String description;
    private Date deadline;
    @ColumnInfo(name = "status")
    private boolean completed;

    public ModelTask(String description)
    {
        this.description = description;
        deadline = null;
        completed = false;
    }

    public ModelTask(String description, Date deadline)
    {
        this.description = description;
        this.deadline = deadline;
        completed = false;
    }

    public int getTaskID()
    {
        return taskID;
    }
    
    public void setTaskID(int taskID)
    {
        this.taskID = taskID;
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

    public Date getDeadline()
    {
        return deadline;
    }

    public void setDeadline(Date deadline)
    {
        this.deadline = deadline;
    }
}
