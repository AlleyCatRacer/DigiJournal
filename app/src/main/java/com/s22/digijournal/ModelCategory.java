package com.s22.digijournal;

import java.util.ArrayList;

public class ModelCategory
{
    private String categoryName;
    private ArrayList<ModelTaskList> lists;

    public ModelCategory(String name)
    {
        categoryName = name;
        lists = new ArrayList<>();
    }

    public ModelCategory(String name, ArrayList<ModelTaskList> lists)
    {
        categoryName = name;
        this.lists = lists;
    }
}
