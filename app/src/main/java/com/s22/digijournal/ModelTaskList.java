package com.s22.digijournal;

import java.util.ArrayList;

public class ModelTaskList
{
    private String listName;
    private ArrayList<ModelTask> tasks;
    boolean isDefault;

    public ModelTaskList(String name)
    {
        listName = name;
        tasks = new ArrayList<>();
        isDefault = false;
    }

    public ModelTaskList(String name, boolean isDefault)
    {
        listName = name;
        tasks = new ArrayList<>();
        this.isDefault = isDefault;
    }

    public ModelTaskList(String name, ArrayList<ModelTask> tasks)
    {
        listName = name;
        this.tasks = tasks;
        isDefault = false;
    }

    public String getListName()
    {
        return listName;
    }

    public ArrayList<ModelTask> getTasks()
    {
        return tasks;
    }
    
    public int getNrOfTasks()
    {
        return tasks.size();
    }

    public void setListName(String listName)
    {
        this.listName = listName;
    }

    public void setTasks(ArrayList<ModelTask> tasks)
    {
        this.tasks = tasks;
    }

    public void addTask(ModelTask task)
    {
        if (tasks.contains(task))
        {
            return;
        }
        tasks.add(task);
    }

    public void removeTask(ModelTask task)
    {
        if (!tasks.contains(task))
        {
            return;
        }
        tasks.remove(task);
    }

    public void clearTasks()
    {
        tasks.clear();
    }

    public boolean isDefaultList()
    {
        return isDefault;
    }

    public void setDefault(boolean aDefault)
    {
        isDefault = aDefault;
    }
}
