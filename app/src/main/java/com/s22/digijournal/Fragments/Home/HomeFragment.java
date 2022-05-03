package com.s22.digijournal.Fragments.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.s22.digijournal.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment
{
	private FragmentHomeBinding binding;
	private HomeViewModel viewModel;
	
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
		
		binding = FragmentHomeBinding.inflate(inflater, container, false);
		View root = binding.getRoot();
		
		final TextView textView = binding.headerTextView;
		viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		return root;
	}
	
	@Override public void onDestroyView()
	{
		super.onDestroyView();
		binding = null;
	}
}