package com.jerotoma.jpasswordmanager.app;

import java.util.ArrayList;

import com.jerotoma.jpasswordmanager.AccountManagerActivity;
import com.jerotoma.jpasswordmanager.R;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ResetNewUserInfo extends AccountManagerActivity {
	private RadioGroup groupRadio;
	private RadioButton resetAllRadio;
	private RadioButton resetUsernameRadio;
	private RadioButton resetPasswordRadio;
	private EditText resetCurrent;
	private EditText resetUsername;
	private EditText resetPassword;
	private EditText resetPassConfirm;
	private Button resetSubmit;
	private static int rbGroup = 0;
	private ArrayList<NewUserInfo> users;
	private Toolbar toolbar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.reset_user_info);
				toolbar = (Toolbar) findViewById(R.id.tool_bar);
				setSupportActionBar(toolbar);
		        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		      
				users = getPasswordManagerApplication().getNewUserInfo();
				setUpViews();
	}

	private void setUpViews(){
		groupRadio    = (RadioGroup)findViewById(R.id.groupradio);
		resetAllRadio = (RadioButton)findViewById(R.id.resetAllRadio);
		resetAllRadio.setChecked(true);
		resetUsernameRadio = (RadioButton)findViewById(R.id.resetUsernameRadio);
		resetPasswordRadio = (RadioButton)findViewById(R.id.resetPasswordRadio);
		resetCurrent = (EditText)findViewById(R.id.reset_current);
		resetUsername = (EditText)findViewById(R.id.reset_username);
		resetPassword = (EditText)findViewById(R.id.reset_password);
		resetPassConfirm = (EditText)findViewById(R.id.reset_passconfirm);
		resetSubmit     = (Button)findViewById(R.id.reset_submit_button);
		groupRadio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup gRadio, int checked) {
				RadioButton  rb = (RadioButton)findViewById(checked);
				if(rb.isChecked()==resetAllRadio.isChecked()){
					resetCurrent.setVisibility(View.VISIBLE);
					resetUsername.setVisibility(View.VISIBLE);
					resetPassword.setVisibility(View.VISIBLE);
					resetPassConfirm.setVisibility(View.VISIBLE);
					rbGroup   = 0;
				}
				else if(rb.isChecked() == resetUsernameRadio.isChecked()){
					resetCurrent.setVisibility(View.VISIBLE);
					resetUsername.setVisibility(View.VISIBLE);
					resetPassword.setVisibility(View.GONE);
					resetPassConfirm.setVisibility(View.GONE);
					rbGroup   = 1;
				}
				else if(rb.isChecked() == resetPasswordRadio.isChecked()){
					resetCurrent.setVisibility(View.VISIBLE);
					resetUsername.setVisibility(View.GONE);
					resetPassword.setVisibility(View.VISIBLE);
					resetPassConfirm.setVisibility(View.VISIBLE);
					rbGroup   = 2;
				}
			}
		});
		resetSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				NewUserInfo resetUserLogins = new NewUserInfo();
				
				String resetname    = resetUsername.getText().toString().trim();
				String resetOldPass = resetCurrent.getText().toString().trim();
				String resetnewPass = resetPassword.getText().toString().trim();
				String resetConfirm = resetPassConfirm.getText().toString().trim();
				NewUserInfo ne = null; 
			   	int i;
				for(i =0; i <users.size(); i++){
					
				    ne = users.get(i);
					if((!ne.getNewUsername().equals(""))&& (!ne.getNewPassword().equals(""))){
						switch(rbGroup){
						default:
							if(resetname.equals("")||resetOldPass.equals("")||resetnewPass.equals("")|| resetConfirm.equals("")){
								Toast.makeText(ResetNewUserInfo.this, "Please make sure all fields are filled to continue", Toast.LENGTH_LONG).show();
								
							}else{
								if(resetOldPass.equals(ne.getNewPassword())&& (resetConfirm.equals(resetnewPass)) ){
									resetUserLogins.setNewUser_ID(ne.getNewUser_ID());
									resetUserLogins.setNewPassword(resetnewPass);
									resetUserLogins.setNewUsername(resetname);
									getPasswordManagerApplication().updateLoginsInfo(resetUserLogins);
									Toast.makeText(ResetNewUserInfo.this, "You have successifully changed both username and password" , Toast.LENGTH_LONG).show();
									resetUsername.setText("");
									resetCurrent.setText("");
									resetPassword.setText("");
									resetPassConfirm.setText("");
								}
							    else{
							    Toast.makeText(ResetNewUserInfo.this, "The old password you entered doesn't  match with the recorded one", Toast.LENGTH_LONG).show();
							    }
						    }
							break;
						case 1:
							if(resetname.equals("")||resetOldPass.equals("")){
								Toast.makeText(ResetNewUserInfo.this, "Please make sure all fields are filled to continue", Toast.LENGTH_LONG).show();
							}else{
								if(resetOldPass.equals(ne.getNewPassword())){
									resetUserLogins.setNewUser_ID(ne.getNewUser_ID());
									resetUserLogins.setNewPassword(ne.getNewPassword().trim());
									resetUserLogins.setNewUsername(resetname);
									getPasswordManagerApplication().updateLoginsInfo(resetUserLogins);
									Toast.makeText(ResetNewUserInfo.this, "You have successifully changed username" , Toast.LENGTH_LONG).show();
									
									resetUsername.setText("");
									resetCurrent.setText("");
								}
							    else{
							    Toast.makeText(ResetNewUserInfo.this,"The old password you entered doesn't  match with the recorded one", Toast.LENGTH_LONG).show();
							    }
							}
							break;
						case 2:
							if(resetOldPass.equals("")||resetnewPass.equals("")|| resetConfirm.equals("")){
								
								Toast.makeText(ResetNewUserInfo.this, "Please make sure all fields are filled to continue", Toast.LENGTH_LONG).show();
								
							}else{
								if(resetOldPass.equals(ne.getNewPassword())&& (resetConfirm.equals(resetnewPass))){
									resetUserLogins.setNewPassword(resetnewPass);
									resetUserLogins.setNewUser_ID(ne.getNewUser_ID());
									resetUserLogins.setNewUsername(ne.getNewUsername().trim());
									getPasswordManagerApplication().updateLoginsInfo(resetUserLogins);
									Toast.makeText(ResetNewUserInfo.this, "You have successifully changed password" , Toast.LENGTH_LONG).show();
									resetCurrent.setText("");
									resetPassword.setText("");
									resetPassConfirm.setText("");
								
								
								}
							    else{
							    Toast.makeText(ResetNewUserInfo.this, "The old password you entered doesn't  match with the recorded one", Toast.LENGTH_LONG).show();
								}
							}
							break;
						}
					
					}
					else{
						Toast.makeText(ResetNewUserInfo.this, "No user exist" , Toast.LENGTH_LONG).show();
						
					}
			}
		}
				
		});
		
		rbGroup = 0;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
}
        
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
        case android.R.id.home:
        	this.finish();
        	break;
        	
        }

		return super.onOptionsItemSelected(item);
	}
	
	
	
}
