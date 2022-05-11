package com.s22.digijournal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "task_table")
public class ModelTask
{
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String name;
    private String description;
    private long deadline;
    @ColumnInfo(name = "status")
    private boolean completed;

    public ModelTask()
    {
    
    }

    @Ignore
    public ModelTask(String description, Date deadline)
    {
        this.description = description;
        this.deadline = 0;
        completed = false;
    }

    public int getID()
    {
        return ID;
    }
    
    public void setID(int ID)
    {
        this.ID = ID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean isDone)
    {
        this.completed = isDone;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public long getDeadline()
    {
        return deadline;
    }

    public void setDeadline(long deadline)
    {
        this.deadline = deadline;
    }
    
    public String getDeadlineFormatted()
    {
        if (deadline != 0)
        {
            java.sql.Date date = new java.sql.Date(Long.parseLong(String.valueOf(deadline)));
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String formatted = format.format(date);
            return formatted;
        }
        return "N/A";
    }
    
    public long formatDeadline(String deadline)
    {
        return Long.parseLong(deadline);
    }
}
