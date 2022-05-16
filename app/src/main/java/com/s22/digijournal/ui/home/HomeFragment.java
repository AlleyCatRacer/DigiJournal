package com.s22.digijournal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.s22.digijournal.R;
import com.s22.digijournal.databinding.FragmentHomeBinding;
import com.s22.digijournal.ui.login.LoginViewModel;

public class HomeFragment extends Fragment
{
	private FragmentHomeBinding binding;
	private LoginViewModel loginViewModel;
	
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
		
		binding = FragmentHomeBinding.inflate(inflater, container, false);
		binding.homeSubHeader.setText(homeViewModel.getText().getValue());
		
		return binding.getRoot();
	}
	
	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		binding.fab.setOnClickListener(v -> NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.nav_add_task));
	}
	
	@Override public void onDestroyView()
	{
		super.onDestroyView();
		binding = null;
	}
}