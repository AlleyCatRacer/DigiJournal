package com.s22.digijournal.Model;

import java.util.ArrayList;

public class Category
{
    private String categoryName;
    private ArrayList<TaskList> lists;

    public Category(String name)
    {
        categoryName = name;
        lists = new ArrayList<>();
    }

    public Category(String name, ArrayList<TaskList> lists)
    {
        categoryName = name;
        this.lists = lists;
    }
}
