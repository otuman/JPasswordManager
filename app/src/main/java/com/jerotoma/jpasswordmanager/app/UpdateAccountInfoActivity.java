package com.jerotoma.jpasswordmanager.app;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_ACCOUNT_NAME;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_DATE;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_EMAIL;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_ID;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_PASSWORD;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_PHONE;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_USERNAME;
import com.jerotoma.jpasswordmanager.AccountManagerActivity;
import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.accounts.AccountStore;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class UpdateAccountInfoActivity extends AccountManagerActivity {
	

	String acc_ID ="";
	private EditText AccountName;
	private EditText Username;
	private EditText Password;
	private EditText EmailAddress;
	private EditText Phone_number;
	private CheckBox Show_pass;
	private Button saveChanges;
	private String dateCreated;
	private String account_name = "";
	private String username = "";
	private String password = "";
	private String email = "";
	private String phone = "";
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_user_info);
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		 setUpView();
		 	
	}
	
	private void setUpView() {
		
		Intent intent = getIntent();
		if(null != intent){
			 account_name = intent.getStringExtra(KEY_ACCOUNT_NAME);
			 username = intent.getStringExtra(KEY_USERNAME);
			 password = intent.getStringExtra(KEY_PASSWORD);
			 email = intent.getStringExtra(KEY_EMAIL);
			 phone = intent.getStringExtra(KEY_PHONE);
			 acc_ID = intent.getStringExtra(KEY_ID);
			 dateCreated = intent.getStringExtra(KEY_DATE);
			}
		
		AccountName      = (EditText)findViewById(R.id.updateaccount_name);
		AccountName.setText(account_name);
		Username         = (EditText)findViewById(R.id.updateusername); 
		Username.setText(username);
		Password         = (EditText)findViewById(R.id.updatepassword);
		Password.setText(password);
		EmailAddress     = (EditText)findViewById(R.id.updateemail_address);
		EmailAddress.setText(email);
		Phone_number     = (EditText)findViewById(R.id.updatephone_number);
		Phone_number.setText(phone);
		Show_pass        = (CheckBox)findViewById(R.id.updateshow_pass);
		
		Show_pass.setOnCheckedChangeListener(new OnCheckedChangeListener(){
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(!isChecked){
					//show password
					Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
					}
				else{
					//hide password
					Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				}
				
			}});
		saveChanges     = (Button)findViewById(R.id.update_button);
		saveChanges.setOnClickListener(new OnClickListener(){
	  	@Override
		public void onClick(View v) {
	    	long ID = 0;
	 		String AccName = AccountName.getText().toString();
			String user    = Username.getText().toString();
			String pass    = Password.getText().toString();
			String email1   = EmailAddress.getText().toString();
			String phone1   = Phone_number.getText().toString();
			String date    = dateCreated;
		    try{
		    	ID       = Long.parseLong(acc_ID);
		    }
		    catch(NumberFormatException  ex){
		    	ex.getMessage();
		    }
			 				
			//Set the edited information in an object
		    AccountStore u = new AccountStore(AccName); 
		
			
	    	if((account_name.equals(AccName) && (username.equals(user))) && (password.equals(pass)) && (email.equals(email1)) && (phone.equals(phone1)))
	    	{
	    	
	    		Toast.makeText(UpdateAccountInfoActivity.this, " No changes has been made yet ", Toast.LENGTH_LONG).show();
	    		
	    	}
	    	else{
	    		u.setAccount_name(AccName);
				u.setUsername(user);
				u.setPassword(pass);
				u.setEmail_address(email1);
				u.setPhone_number(phone1);
				u.setAcc_ID(ID);
				u.setDates(date);
				getPasswordManagerApplication().AddUpdate(u);
				finish();
            }
	    }
		});
		
		Toast.makeText(this, "The user ID is "+ acc_ID, Toast.LENGTH_LONG).show();
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
			
		case android.R.id.home:
		    this.finish();
		    return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
