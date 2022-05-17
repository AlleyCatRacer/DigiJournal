package com.s22.digijournal.Task;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s22.digijournal.R;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>
{
	private List<ModelTask> tasks;
	private TaskOnClickListener listener;
	
	public TaskAdapter(TaskOnClickListener listener)
	{
		this.tasks = new ArrayList<>();
		this.listener = listener;
	}
	
	@NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.fragment_task_item, parent, false);
		return new ViewHolder(view);
	}
	
	@Override public void onBindViewHolder(@NonNull ViewHolder holder, int position)
	{
		holder.name.setText(tasks.get(position).getName());
		holder.done.setChecked(tasks.get(position).isCompleted());
		holder.deadline.setText(tasks.get(position).getDeadlineFormatted());
	}
	
	@Override public int getItemCount()
	{
		return tasks.size();
	}
	
	public List<ModelTask> getTasks()
	{
		return tasks;
	}
	
	public void setTasks(List<ModelTask> tasks)
	{
		this.tasks = tasks;
		notifyDataSetChanged();
	}

	public class ViewHolder extends RecyclerView.ViewHolder
	{
		CheckBox done;
		TextView name;
		TextView deadline;
		
		ViewHolder(View itemView)
		{
			super(itemView);
			
			done = itemView.findViewById(R.id.task_item_checkBox);
			name = itemView.findViewById(R.id.task_item_header);
			deadline = itemView.findViewById(R.id.task_item_deadline);
			itemView.setOnClickListener(new View.OnClickListener()
			{
				@Override public void onClick(View view)
				{
					listener.onClick(tasks.get(getBindingAdapterPosition()));
					//getBindingAdapterPosition gets the position of the item clicked, identifying it
				}
			});
		}
	}
	
	public interface TaskOnClickListener
	{
		void onClick(ModelTask task);
	}
}