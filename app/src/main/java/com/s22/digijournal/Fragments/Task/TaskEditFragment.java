package com.s22.digijournal.Fragments.Task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.s22.digijournal.R;

public class TaskEditFragment extends Fragment
{
	
	
	public TaskEditFragment()
	{
		// Required empty public constructor
	}
	
	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * Parameter 1.
	 * Parameter 2.
	 * @return A new instance of fragment TaskEditFragment.
	 */
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_task_edit, container, false);
	}
}