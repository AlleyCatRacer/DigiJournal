package com.s22.digijournal;

import java.util.Date;

public class ModelTask
{
    private int taskID;
    private String description;
    private Date deadline;
    private boolean isDone = false;

    public ModelTask(String description)
    {
        this.description = description;
        deadline = null;
    }

    public ModelTask(String description, Date deadline)
    {
        this.description = description;
        this.deadline = deadline;
    }

    public int getTaskID()
    {
        return taskID;
    }

    public boolean isDone()
    {
        return isDone;
    }

    public void toggleDone()
    {
        isDone = !isDone;
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
