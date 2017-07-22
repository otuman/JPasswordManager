package com.jerotoma.jpasswordmanager.app;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jerotoma.jpasswordmanager.AccountManagerActivity;
import com.jerotoma.jpasswordmanager.LoginMainActivity;
import com.jerotoma.jpasswordmanager.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class RegisterNewUser extends AccountManagerActivity {
	
	private EditText new_user_name;
	private EditText new_user_pass;
	private EditText new_user_pass_confirm;
	private Button   submit_button;
	private Boolean  exists = false;
	private Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState){
				super.onCreate(savedInstanceState);
				
				setContentView(R.layout.new_user_layout);
				toolbar = (Toolbar) findViewById(R.id.tool_bar);
				setSupportActionBar(toolbar);
		        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
				setUpView();
				}
	protected void setUpView(){
		
		//new_user_full_name     = (EditText)findViewById(R.id.new_user_full_name);
		new_user_name          = (EditText)findViewById(R.id.new_user_name);
		new_user_pass          = (EditText)findViewById(R.id.new_user_pass);
		new_user_pass_confirm  = (EditText)findViewById(R.id.new_user_pass_confirm);
		submit_button          = (Button)findViewById(R.id.submit_button);

		submit_button.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
				
				
				
				NewUserInfo n = new NewUserInfo();	
				String newUsername  = new_user_name.getText().toString().trim();
				String newUserpass  = new_user_pass.getText().toString().trim();
				String confirmPass  = new_user_pass_confirm.getText().toString().trim();
				
				
				ArrayList<NewUserInfo> users = getPasswordManagerApplication().getNewUserInfo();
				NewUserInfo ne;
				for(int i = 0; i < users.size(); i++)
				{
					ne = users.get(i);
					if(ne.getNewUsername().equals(newUsername))
					{
						exists = true;
					}
				}
				if(!exists && users.isEmpty())
				{
						if(newUsername.equals("")||confirmPass.equals("")||newUserpass.equals(""))
						{
							Toast.makeText(RegisterNewUser.this, "Please enter information on a missed field", Toast.LENGTH_LONG).show();
						}
						else
						{
							    n.setNewUsername(newUsername);
							
							if(newUserpass.equals(confirmPass))
							{
								Pattern p = Pattern.compile("(([a-zA-Z].*[0-9])|([0-9].*[a-zA-Z]))");
								
								Matcher m = p.matcher(newUserpass);
								
								
								if(newUserpass.length()>6){
									if(m.find()){
										n.setNewPassword(newUserpass);
										getPasswordManagerApplication().addNewUserInfo(n);
										Toast.makeText(RegisterNewUser.this, "Registration is successfully, login to manage passwords", Toast.LENGTH_LONG).show();
										
										
										 Intent intent = new Intent(RegisterNewUser.this, LoginMainActivity.class);
										 startActivity(intent);
										 finish();
										 
										 new_user_name.setText("");
										 new_user_pass.setText("");
										 new_user_pass_confirm.setText("");
										 
									}else{
										Toast.makeText(RegisterNewUser.this, "Password must contain atleast letters and numbers", Toast.LENGTH_LONG).show();
										
									}
								
								}else{
									Toast.makeText(RegisterNewUser.this, "The minimum password length must be six (6)", Toast.LENGTH_LONG).show();
								}
															
							}
							else
							{
								Toast.makeText(RegisterNewUser.this, "Please enter a correct confirm password", Toast.LENGTH_LONG).show();
							}
						}
						
				}
				else
				{
					
					 exists = false;
					 
					
				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_user, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id){
		case R.id.action_settings:
			return true;
			
		case R.id.back_home:
			Intent intent = new Intent(RegisterNewUser.this, LoginMainActivity.class);
			startActivity(intent);
			return true;
		case android.R.id.home:
		    this.finish();
		    return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

