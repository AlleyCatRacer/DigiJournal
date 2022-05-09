package com.s22.digijournal.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

@Entity public class TaskList
{
    @PrimaryKey @NonNull private String listName;
    
    @ColumnInfo(name = "categoryName") private String categoryName;
    
    @Relation(parentColumn = "listName", entityColumn = "listName")
    private List<Task> tasks;
    
    public TaskList()
    {
    
    }
    
    public TaskList(String name, List<Task> tasks)
    {
        setListName(name);
        setTasks(tasks);
    }

    public String getListName()
    {
        return listName;
    }

    public List<Task> getTasks()
    {
        return tasks;
    }
    
    public String getCategoryName()
    {
        return categoryName;
    }
    
    public int getTaskCount()
    {
        return tasks.size();
    }

    public void setListName(String listName)
    {
        if (listName != null && !listName.isEmpty())
        {
            this.listName = listName;
        }
    }

    public void setTasks(List<Task> tasks)
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
    
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
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
