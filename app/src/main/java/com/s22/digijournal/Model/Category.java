package com.s22.digijournal.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

@Entity public class Category
{
    @PrimaryKey @NonNull private String categoryName;
    @Relation(parentColumn = "categoryName", entityColumn = "listName")
    private List<TaskList> lists;
    
    public Category()
    {
    
    }
    
    public Category(String name, ArrayList<TaskList> lists)
    {
        setCategoryName(name);
        setLists(lists);
    }
    
    public String getCategoryName()
    {
        return categoryName;
    }
    
    public List<TaskList> getLists()
    {
        return lists;
    }
    
    public int getListCount()
    {
        return lists.size();
    }
    
    public void setCategoryName(String name)
    {
        if (name != null && !name.isEmpty())
        {
            categoryName = name;
        }
    }
    
    public void setLists(ArrayList<TaskList> lists)
    {
        if (lists == null)
        {
            this.lists = new ArrayList<>();
        }
        else
        {
            this.lists = lists;
        }
    }
    
    public void addList(TaskList list)
    {
        if (!lists.contains(list) || list == null)
        {
            return;
        }
        lists.add(list);
    }
    
    public void removeList(TaskList list)
    {
        if (!lists.contains(list) || list == null)
        {
            return;
        }
        lists.remove(list);
    }
    
    public void clearCategory()
    {
        lists.clear();
    }
}
