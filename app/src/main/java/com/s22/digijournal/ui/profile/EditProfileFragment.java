package com.s22.digijournal.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.s22.digijournal.R;
import com.s22.digijournal.databinding.FragmentProfileEditBinding;
import com.s22.digijournal.ui.login.LoginViewModel;

import java.util.Objects;

public class EditProfileFragment extends Fragment
{
	private FragmentProfileEditBinding binding;
	private LoginViewModel viewModel;
	private TextInputEditText displayName;
	private TextInputEditText email;
	private Button saveButton;
	
	public EditProfileFragment()
	{
	
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
		binding = FragmentProfileEditBinding.inflate(inflater, container, false);
		
		displayName = binding.profileName;
		email = binding.profileEmail;
		saveButton = binding.profileSaveButton;
		
		return binding.getRoot();
	}
	
	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		
		saveButton.setOnClickListener(v ->
		{
			if (!Objects.requireNonNull(displayName.getText()).toString().equals(viewModel.getCurrentUser().getDisplayName()))
			{
				viewModel.updateDisplayName(displayName.getText().toString());
			}
			if (!Objects.requireNonNull(email.getText()).toString().equals(viewModel.getCurrentUser().getEmail()))
			{
				viewModel.updateEmail(email.getText().toString());
			}
			
			toHome();
		});
	}
	
	private void toHome()
	{
		NavHostFragment.findNavController(EditProfileFragment.this).navigate(R.id.nav_home);
	}
}