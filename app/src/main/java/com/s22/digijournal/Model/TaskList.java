package com.s22.digijournal.Model;

import java.util.ArrayList;

public class TaskList
{
    private String listName;
    private ArrayList<Task> tasks;
    boolean isDefault;

    public TaskList(String name)
    {
        listName = name;
        tasks = new ArrayList<>();
        isDefault = false;
    }

    public TaskList(String name, boolean isDefault)
    {
        listName = name;
        tasks = new ArrayList<>();
        this.isDefault = isDefault;
    }

    public TaskList(String name, ArrayList<Task> tasks)
    {
        listName = name;
        this.tasks = tasks;
        isDefault = false;
    }

    public String getListName()
    {
        return listName;
    }

    public void setListName(String listName)
    {
        this.listName = listName;
    }

    public ArrayList<Task> getTasks()
    {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks)
    {
        this.tasks = tasks;
    }

    public void addTask(Task task)
    {
        if (tasks.contains(task))
        {
            return;
        }
        tasks.add(task);
    }

    public void removeTask(Task task)
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
