package com.s22.digijournal.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel
{
	private final MutableLiveData<String> subHeader;
	
	public HomeViewModel()
	{
		subHeader = new MutableLiveData<>();
		subHeader.setValue("Your online bullet journal");
	}
	
	public LiveData<String> getText()
	{
		return subHeader;
	}
}