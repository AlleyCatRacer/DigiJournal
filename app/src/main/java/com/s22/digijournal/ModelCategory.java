package com.s22.digijournal;

import java.util.ArrayList;
import java.util.List;

public class ModelCategory
{
    private String categoryName;
    private List<ModelTaskList> lists;
    
    public ModelCategory(String name)
    {
        categoryName = name;
        lists = new ArrayList<>();
    }

    public ModelCategory(String name, List<ModelTaskList> lists)
    {
        categoryName = name;
        this.lists = lists;
    }
    
    public String getCategoryName()
    {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }
    
    public List<ModelTaskList> getLists()
    {
        return lists;
    }
    
    public void setLists(List<ModelTaskList> lists)
    {
        this.lists = lists;
    }
    
}
