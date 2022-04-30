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
import com.s22.digijournal.ModelTaskList;

import java.util.ArrayList;

public class ListRepo
{
    private static ListRepo instance;
    private FirebaseFirestore db;
    private final Source src = Source.CACHE;
    private CollectionReference docLists;
    private boolean updated = false;
    private ArrayList<ModelTaskList> lists;
    
    private ListRepo()
    {
        db = FirebaseFirestore.getInstance();
        lists = new ArrayList<>();
        getOnlineUpdate();
        if (docLists == null && !updated)
        {
            checkCache();
        }
    }
    
    public static ListRepo getInstance()
    {
        if (instance == null)
        {
            instance = new ListRepo();
        }
        
        return instance;
    }
    
    public ArrayList<ModelTaskList> getAllLists()
    {
        getOnlineUpdate();
        return lists;
    }
    
    public void getOnlineUpdate()
    {
        docLists = db.collection("Lists");
        docLists.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot docSnap : task.getResult())
                    {
                        lists.add(docSnap.toObject(ModelTaskList.class));
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
        docLists.get(src).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if (task.isSuccessful())
                {
                    //Means the data was found in the offline cache
                    for (QueryDocumentSnapshot docSnap : task.getResult())
                    {
                        lists.add(docSnap.toObject(ModelTaskList.class));
                    }
                    updated = true;
                }
            }
        });
    }
    
    public void addList(ModelTaskList list)
    {
        lists.add(list);
        db.collection("Lists").add(list).addOnFailureListener(new OnFailureListener()
        {
            @Override public void onFailure(@NonNull Exception e)
            {
                System.out.println("Error adding list: " + e.getCause() + " | " + e.getMessage());
            }
        });
    }
}
