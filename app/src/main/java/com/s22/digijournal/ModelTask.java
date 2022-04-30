package com.s22.digijournal;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class ModelTask
{
    private int taskID;
    private String taskName;
    private String description;
    private final Date dateAdded;
    private Date dateEdited;
    private Date deadline;
    private ArrayList<ModelTag> tags;
    private ModelTaskPriority priority;
    private boolean isDone = false;

    public ModelTask(String name, String description)
    {
        taskID = taskID++;
        setTaskName(name);
        this.description = description;
        dateAdded = Date.from(Instant.now());
        dateEdited = null;
        deadline = null;
        tags = new ArrayList<>();
        priority = ModelTaskPriority.Default;
    }

    public ModelTask(String name, String description, Date deadline)
    {
        taskID = taskID++;
        setTaskName(name);
        this.description = description;
        dateAdded = Date.from(Instant.now());
        dateEdited = null;
        this.deadline = deadline;
        tags = new ArrayList<>();
        priority = ModelTaskPriority.Default;
    }

    public ModelTask(String name, String description, Date deadline, ModelTaskPriority priority)
    {
        taskID = taskID++;
        setTaskName(name);
        this.description = description;
        dateAdded = Date.from(Instant.now());
        dateEdited = null;
        this.deadline = deadline;
        tags = new ArrayList<>();
        this.priority = priority;
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

    public ArrayList<ModelTag> getTags()
    {
        return tags;
    }

    public ModelTaskPriority getPriority()
    {
        return priority;
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

    public void addTags(ArrayList<ModelTag> tags)
    {
        for (ModelTag t:tags)
        {
            if (!this.tags.contains(t))
            {
                this.tags.add(t);
            }
        }
        setDateEdited();
    }

    public void removeTag(ModelTag tag)
    {
        if (!tags.contains(tag))
        {
            return;
        }
        tags.remove(tag);
        setDateEdited();
    }

    public void clearTags()
    {
        tags.clear();
        setDateEdited();
    }

    public void setPriority(ModelTaskPriority priority)
    {
        this.priority = priority;
        setDateEdited();
    }
}
