package com.s22.digijournal.viewModels;

import com.s22.digijournal.ModelTaskList;

import java.util.ArrayList;

public class CategoryViewModel
{
    private String categoryName;
    private ArrayList<ModelTaskList> lists;
    private int nrOfLists;
    
    public String getCategoryName()
    {
        return categoryName;
    }
    
    public ArrayList<ModelTaskList> getLists()
    {
        return lists;
    }
    
    public int getNrOfLists()
    {
        return nrOfLists = lists.size();
    }
    
    public void addList(ModelTaskList list)
    {
        lists.add(list);
        nrOfLists = lists.size();
    }
    
    public void removeList(ModelTaskList list)
    {
        lists.remove(list);
        nrOfLists = lists.size();
    }
}
