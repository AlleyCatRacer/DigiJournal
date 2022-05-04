package com.s22.digijournal.Model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.ArrayList;

@Entity public class Category
{
    @PrimaryKey private String categoryName;
    @Relation(parentColumn = "listName", entityColumn = "listName")
    @ColumnInfo(name = "lists")
    @Embedded private ArrayList<TaskList> lists;
    
    public Category(String name, ArrayList<TaskList> lists)
    {
        setCategoryName(name);
        setLists(lists);
    }
    
    public String getCategoryName()
    {
        return categoryName;
    }
    
    public ArrayList<TaskList> getLists()
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
