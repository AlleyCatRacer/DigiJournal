package com.s22.digijournal.viewModels;

import androidx.lifecycle.ViewModel;

import com.s22.digijournal.ModelTaskList;
import com.s22.digijournal.repos.ListRepo;

import java.util.ArrayList;

public class ListViewModel extends ViewModel
{
    private final ListRepo repo;
    
    public ListViewModel()
    {
        repo = ListRepo.getInstance();
    }
    
    public ArrayList<ModelTaskList> getAllLists()
    {
        return repo.getAllLists();
    }
    
    public void addList(ModelTaskList list)
    {
        repo.addList(list);
    }
}
