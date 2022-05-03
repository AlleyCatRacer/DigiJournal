package com.s22.digijournal.Fragments.Home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel
{
	//TODO
	private final MutableLiveData<String> mText;
	
	public HomeViewModel()
	{
		mText = new MutableLiveData<>();
	}
	
	public LiveData<String> getText()
	{
		return mText;
	}
}