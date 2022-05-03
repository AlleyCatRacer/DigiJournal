package com.s22.digijournal.Model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.ArrayList;

@Entity public class TaskList
{
    @PrimaryKey private String listName;
    
    @Relation(parentColumn = "taskName", entityColumn = "taskName")
    @ColumnInfo(name = "tasks")
    @Embedded private ArrayList<Task> tasks;
    
    public TaskList(String name, ArrayList<Task> tasks)
    {
        setListName(name);
        setTasks(tasks);
    }

    public String getListName()
    {
        return listName;
    }

    public ArrayList<Task> getTasks()
    {
        return tasks;
    }

    public void setListName(String listName)
    {
        if (listName != null && !listName.isEmpty())
        {
            this.listName = listName;
        }
    }

    public void setTasks(ArrayList<Task> tasks)
    {
        if (tasks == null)
        {
            this.tasks = new ArrayList<>();
        }
        else
        {
            this.tasks = tasks;
        }
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

    public void clearList()
    {
        tasks.clear();
    }
}
