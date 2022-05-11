package com.s22.digijournal;

import java.util.ArrayList;
import java.util.List;

public class ModelTaskList
{
    private String listName;
    private List<ModelTask> tasks;

    public ModelTaskList(String name)
    {
        listName = name;
        tasks = new ArrayList<>();
    }

    public ModelTaskList(String name, List<ModelTask> tasks)
    {
        listName = name;
        this.tasks = tasks;
    }

    public String getListName()
    {
        return listName;
    }

    public void setListName(String listName)
    {
        this.listName = listName;
    }

    public List<ModelTask> getTasks()
    {
        return tasks;
    }

    public void setTasks(List<ModelTask> tasks)
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
}
