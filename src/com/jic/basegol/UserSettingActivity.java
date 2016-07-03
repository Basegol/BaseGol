package com.jic.basegol;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class UserSettingActivity extends PreferenceActivity{
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	 
	  super.onCreate(savedInstanceState);
	  
	  //getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
	  addPreferencesFromResource(R.xml.preferences);
	 }
	
}
