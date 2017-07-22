package com.jerotoma.jpasswordmanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jerotoma.jpasswordmanager.app.AboutPassManager;
import com.jerotoma.jpasswordmanager.app.HelpActivity;
import com.jerotoma.jpasswordmanager.app.NewUserInfo;
import com.jerotoma.jpasswordmanager.app.RegisterNewUser;
import com.jerotoma.jpasswordmanager.app.ResetNewUserInfo;
import com.jerotoma.jpasswordmanager.app.SettingActivity;

import java.util.ArrayList;

public class LoginMainActivity extends AccountManagerActivity{

	private EditText username;
	private EditText password;
	TextView mTitle;
	private ArrayList<NewUserInfo> users;
	public static final String KEY_USER = "username";
	Toolbar toolbar;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_main);
		
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		toolbar.setTitle("");
		mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
		mTitle.setText(getString(R.string.app_name));
		setSupportActionBar(toolbar);
        users = getPasswordManagerApplication().getNewUserInfo();
		setUpView();
	}

	private void setUpView() {
		CheckBox showMyUsername = (CheckBox) findViewById(R.id.show_username);
		
		
		
		username    = (EditText)findViewById(R.id.usernamei);
    	password    = (EditText)findViewById(R.id.passwordi);
		Button loginButton = (Button) findViewById(R.id.login_button);
    	
    	loginButton.setOnClickListener(new OnClickListener(){
    		@Override
			public void onClick(View view){
    			NewUserInfo nu =null;
				String user = username.getText().toString().trim();
    			String pass = password.getText().toString().trim();
    		if(user.equals("")&& pass.equals("")){
    			Toast.makeText(LoginMainActivity.this, "Please enter username and password", Toast.LENGTH_LONG).show();
    		}
    		else if(user.equals("")){
    			Toast.makeText(LoginMainActivity.this, "Please enter username", Toast.LENGTH_LONG).show();
    		}
    		else if(pass.equals("")){
    			Toast.makeText(LoginMainActivity.this, "Please enter password", Toast.LENGTH_LONG).show();
    		}
    		else if(users.size()==0){
				Toast.makeText(LoginMainActivity.this, "No user has been registered yet, Please register user", Toast.LENGTH_LONG).show();
				
			}
    		else{	
    			for(int i = 0; i < users.size(); i++)
				{
    				nu    = users.get(i);
					
					username.setText(nu.getNewUsername());
					
					if((nu.getNewUsername().toString().trim().equals(user)) && (nu.getNewPassword().toString().trim().equals(pass)))
					{   
						Intent intent = new Intent(LoginMainActivity.this, MainTabbedActivity.class);
						intent.putExtra(KEY_USER, nu.getNewUsername().toString());
		                startActivity(intent);
					}
					
					else{
						Toast.makeText(LoginMainActivity.this, "Please enter a correct username or password", Toast.LENGTH_LONG).show();
					}
				}
    		}
    		}
    	});

		Button newUser_button = (Button) findViewById(R.id.newUser);
		Button reset_button = (Button) findViewById(R.id.reset);
    	   	  
       if( users.size()>0 ){
    		newUser_button.setVisibility(View.INVISIBLE);
    		reset_button.setVisibility(View.INVISIBLE);
    	}
        else{
			newUser_button.setVisibility(View.VISIBLE);
	        newUser_button.setOnClickListener(new OnClickListener(){
	        public void onClick(View view){
						Intent intent = new Intent(LoginMainActivity.this, RegisterNewUser.class);
						startActivity(intent);
						
    	        }
	    	});
	        
		}
    	reset_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginMainActivity.this, ResetNewUserInfo.class);
				startActivity(intent);
			}
		});
    	
    	showMyUsername.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					
					int j;
					for(j=0; j<users.size(); j++){
							if(!users.isEmpty()){
										NewUserInfo n = users.get(j);
										username.setText(n.getNewUsername());
									}
									
								}
				}
				else{
					   username.setText(null);
				 }
				
				
			}
		});
		
    	
    	 
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.login_main, menu);
		if(users.size()>0){
			menu.findItem(R.id.reset).setVisible(true); 
			menu.findItem(R.id.new_user).setVisible(false); 
			}
			else{
			menu.findItem(R.id.new_user).setVisible(true); 
			menu.findItem(R.id.reset).setVisible(false); 
			}
	
		return true;
	}
   
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Intent intent;
		
		int id = item.getItemId();
		switch(id){
		case R.id.action_settings:
			  intent = new Intent(LoginMainActivity.this, SettingActivity.class);
              startActivity(intent);	
		      break;
		case R.id.about:
			  intent = new Intent(LoginMainActivity.this, AboutPassManager.class);
              startActivity(intent);	
              break;
		case R.id.new_user:
			  intent = new Intent(LoginMainActivity.this, RegisterNewUser.class);
	          startActivity(intent);	
   			  break;
		case R.id.help:
			  intent = new Intent(LoginMainActivity.this, HelpActivity.class);
              startActivity(intent);	
			  break;
		case R.id.reset:
			  intent = new Intent(LoginMainActivity.this, ResetNewUserInfo.class);
              startActivity(intent);	
			  break;
   	}
	return super.onOptionsItemSelected(item);
	}
}
