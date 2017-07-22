 package com.jerotoma.jpasswordmanager.app;

import android.support.v7.widget.Toolbar;
import com.jerotoma.jpasswordmanager.AccountManagerActivity;
import com.jerotoma.jpasswordmanager.CalenderGenerate;
import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.accounts.AccountStore;

import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class AddAccountInfo extends AccountManagerActivity {
	
	final Random myRandom = new Random();
		
    private EditText accountName;
    private ImageButton gpassword;
    private EditText username;
    private EditText password;
    private EditText emailAddress;
    private EditText phone_number;
    private CheckBox show_pass;
    private Toolbar toolbar;
	private Button addAccountButton;
	public CalenderGenerate calDate;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//ActionBar actionBar = getSupportActionBar();
		//actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7eb0cc")));
		//actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.add_account_info);
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
	    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
		setUpView();
	}

	private void setUpView() {
		calDate          = new CalenderGenerate();
		gpassword        = (ImageButton)findViewById(R.id.gpassword);
		gpassword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AddAccountInfo.this, PasswordGenerator.class);
				startActivity(intent);
			}
		});
		accountName      = (EditText)findViewById(R.id.account_name);
		username         = (EditText)findViewById(R.id.username); 
		password         = (EditText)findViewById(R.id.password);
		emailAddress     = (EditText)findViewById(R.id.email_address);
		phone_number     = (EditText)findViewById(R.id.phone_number);
		phone_number.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
		phone_number.setHint("Cell phone(Optional)");
		show_pass        = (CheckBox)findViewById(R.id.show_pass);

		show_pass.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(!isChecked){
					//show password
					password.setTransformationMethod(PasswordTransformationMethod.getInstance());
					}
				else{
					//hide password
					password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				}
				
			}});
		addAccountButton = (Button)findViewById(R.id.addAccount_button);
		
		addAccountButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				AddNew_Account();
				
			}
		});
	}
	private void AddNew_Account(){
		String AccountName = 	accountName.getText().toString();
		String Username    = 	username.getText().toString();
		String Password    = 	password.getText().toString();
		String email       = 	emailAddress.getText().toString();
		final String phone       = 	phone_number.getText().toString();
		
	
		
		long id            =    myRandom.nextLong();
		
		String dateCreated =    calDate.timeElapsed();
       
		
        if(AccountName.equals("")||Username.equals("")||Password.equals("")||email.equals("")||phone.equals("")){
        	Toast.makeText(AddAccountInfo.this, "Please make sure all field contains required inforamtion", Toast.LENGTH_LONG).show();
        	
        }else{
        	if(Password.length() > 8){
        		if(email.contains(".")&& email.contains("@")){
            		AccountStore a = new AccountStore(AccountName);
            		a.setAccount_name(AccountName);
            		a.setUsername(Username);
            		a.setPassword(Password);
            		a.setEmail_address(email);
            		a.setPhone_number(phone);
            		a.setDates(dateCreated);
            		a.setAcc_ID(id);
            		getPasswordManagerApplication().addAccount(a);
            		finish();
            		Toast.makeText(AddAccountInfo.this, "You have successifully added "+AccountName+" account", Toast.LENGTH_LONG).show();
            	}else{
            	 Toast.makeText(AddAccountInfo.this, "The email address is invalid, please enter the valid one", Toast.LENGTH_LONG).show();
            	}
        	}
        	else{
        		 Toast.makeText(AddAccountInfo.this, "Passwords should be at least eight (8) characters long", Toast.LENGTH_LONG).show();
             	      		
        	}
        	
        	
    	}
	} 
	public static String formatPhoneNumber(String number){  
        number  =   number.substring(0, number.length()-4) + "-" + number.substring(number.length()-4, number.length());
        number  =   number.substring(0,number.length()-8)+")"+number.substring(number.length()-8,number.length());
        number  =   number.substring(0, number.length()-12)+"("+number.substring(number.length()-12, number.length());
        return number;
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		switch(id){
		case android.R.id.home:
			 finish();	
		     return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
