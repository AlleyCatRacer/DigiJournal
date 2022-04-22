package com.s22.digijournal;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class ModelTask
{
    private int taskID;
    private String description;
    private final Date dateAdded;
    private Date dateEdited;
    private Date deadline;
    private ArrayList<ModelTag> tags;
    private ModelTaskPriority priority;

    public ModelTask(String description)
    {
        taskID = taskID++;
        this.description = description;
        dateAdded = Date.from(Instant.now());
        dateEdited = null;
        deadline = null;
        tags = new ArrayList<>();
        priority = ModelTaskPriority.Default;
    }

    public ModelTask(String description, Date deadline)
    {
        taskID = taskID++;
        this.description = description;
        dateAdded = Date.from(Instant.now());
        dateEdited = null;
        this.deadline = deadline;
        tags = new ArrayList<>();
        priority = ModelTaskPriority.Default;
    }

    public ModelTask(String description, Date deadline, ModelTaskPriority priority)
    {
        taskID = taskID++;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
        dateEdited = Date.from(Instant.now());
    }

    public Date getDateAdded()
    {
        return dateAdded;
    }

    public Date getDateEdited()
    {
        return dateEdited;
    }

    public void setDateEdited()
    {
        this.dateEdited = Date.from(Instant.now());;
    }

    public Date getDeadline()
    {
        return deadline;
    }

    public void setDeadline(Date deadline)
    {
        this.deadline = deadline;
        setDateEdited();
    }

    public ArrayList<ModelTag> getTags()
    {
        return tags;
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

    public ModelTaskPriority getPriority()
    {
        return priority;
    }

    public void setPriority(ModelTaskPriority priority)
    {
        this.priority = priority;
        setDateEdited();
    }
}
