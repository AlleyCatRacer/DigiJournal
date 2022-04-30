package com.s22.digijournal.repos;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.s22.digijournal.ModelTask;

import java.util.ArrayList;

public class TaskRepo
{
    private static TaskRepo instance;
    private FirebaseFirestore db;
    private final Source src = Source.CACHE;
    private CollectionReference docTasks;
    private boolean updated = false;
    private ArrayList<ModelTask> tasks;
    
    private TaskRepo()
    {
        db = FirebaseFirestore.getInstance();
        tasks = new ArrayList<>();
        getOnlineUpdate();
        if (docTasks == null && !updated)
        {
            checkCache();
        }
    }
    
    public static TaskRepo getInstance()
    {
        if (instance == null)
        {
            instance = new TaskRepo();
        }
        
        return instance;
    }
    
    public ArrayList<ModelTask> getAllTasks()
    {
        getOnlineUpdate();
        return tasks;
    }
    
    public void getOnlineUpdate()
    {
        docTasks = db.collection("Tasks");
        docTasks.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot docSnap : task.getResult())
                    {
                        tasks.add(docSnap.toObject(ModelTask.class));
                    }
                    updated = true;
                }
                else
                {
                    checkCache();
                }
            }
        });
    }
    
    private void checkCache()
    {
        docTasks.get(src).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if (task.isSuccessful())
                {
                    //Means the data was found in the offline cache
                    for (QueryDocumentSnapshot docSnap : task.getResult())
                    {
                        tasks.add(docSnap.toObject(ModelTask.class));
                    }
                    updated = true;
                }
            }
        });
    }
    
    public void addTask(ModelTask task)
    {
        tasks.add(task);
        db.collection("Tasks").add(task).addOnFailureListener(new OnFailureListener()
        {
            @Override public void onFailure(@NonNull Exception e)
            {
                System.out.println("Error adding task: " + e.getCause() + " | " + e.getMessage());
            }
        });
    }
}
