package com.s22.digijournal;

import java.time.Instant;
import java.util.Date;

public class ModelTask
{
    private int taskID;
    private String taskName;
    private String description;
    private final Date dateAdded;
    private Date dateEdited;
    private Date deadline;
    private boolean isDone = false;

    public ModelTask(String name, String description)
    {
        taskID = taskID++;
        setTaskName(name);
        this.description = description;
        dateAdded = Date.from(Instant.now());
        dateEdited = null;
        deadline = null;
    }

    public ModelTask(String name, String description, Date deadline)
    {
        taskID = taskID++;
        setTaskName(name);
        this.description = description;
        dateAdded = Date.from(Instant.now());
        dateEdited = null;
        this.deadline = deadline;
    }

    public int getTaskID()
    {
        return taskID;
    }
    
    public String getTaskName()
    {
        return taskName;
    }

    public String getDescription()
    {
        return description;
    }
    
    public Date getDateAdded()
    {
        return dateAdded;
    }

    public Date getDateEdited()
    {
        return dateEdited;
    }

    public Date getDeadline()
    {
        return deadline;
    }
    
    public String getDeadlineString()
    {
        return deadline.toString();
    }
    
    public boolean isDone()
    {
        return isDone;
    }

    public void toggleDone()
    {
        isDone = !isDone;
    }

    public void setDescription(String description)
    {
        this.description = description;
        dateEdited = Date.from(Instant.now());
    }
    public void setTaskName(String name)
    {
        if (name.isEmpty())
        {
            return;
        }
        taskName = name;
    }

    public void setDateEdited()
    {
        this.dateEdited = Date.from(Instant.now());;
    }

    public void setDeadline(Date deadline)
    {
        this.deadline = deadline;
        setDateEdited();
    }
}
