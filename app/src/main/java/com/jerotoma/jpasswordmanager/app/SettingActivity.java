package com.jerotoma.jpasswordmanager.app;

import com.jerotoma.jpasswordmanager.AccountManagerActivity;
import com.jerotoma.jpasswordmanager.R;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class SettingActivity extends AccountManagerActivity {
	
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_launcher);
		if(getSupportActionBar() != null){
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}


		
		
	}
  
}
