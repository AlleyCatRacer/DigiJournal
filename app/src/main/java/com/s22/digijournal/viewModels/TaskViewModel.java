package com.s22.digijournal.viewModels;

import androidx.lifecycle.MutableLiveData;

public class TaskViewModel
{
    private final MutableLiveData<Integer> taskID;
    private final MutableLiveData<String> taskName;
    private final MutableLiveData<String> description;
    private MutableLiveData<String> dateAdded;
    private MutableLiveData<String> dateEdited;
    private MutableLiveData<String> deadline;
    private boolean isDone;
    
    public TaskViewModel(MutableLiveData<Integer> id, MutableLiveData<String> name,
                         MutableLiveData<String> description, MutableLiveData<String> dateAdded,
                         MutableLiveData<String> deadline, MutableLiveData<String> dateEdited,
                         MutableLiveData<Boolean> isDone)
    {
        taskID = id;
        taskName = name;
        this.description = description;
        this.dateAdded = dateAdded;
        this.dateEdited = dateEdited;
        this.deadline = deadline;
        this.isDone = isDone;
    }
    
    public void createTask()
}
