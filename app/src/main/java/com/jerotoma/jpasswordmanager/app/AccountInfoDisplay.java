package com.jerotoma.jpasswordmanager.app;

import com.jerotoma.jpasswordmanager.AccountManagerActivity;
import com.jerotoma.jpasswordmanager.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.jerotoma.jpasswordmanager.accounts.AccountStore;
import com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_ACCOUNT_NAME;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_DATE;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_EMAIL;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_ID;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_PASSWORD;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_PHONE;
import static com.jerotoma.jpasswordmanager.tabbed.PassManagerHomeActivity.KEY_USERNAME;

public class AccountInfoDisplay extends AccountManagerActivity {
	private TextView acc_info;
	private TextView user_name;
	private TextView Password;
	private TextView emailAddr;
	private TextView phoneN;
	
	private String account_name = "";
	private String username = "";
	private String password = "";
	private String email = "";
	private String phone = "";
	private String acc_id = "";
	private String dateCreated;
	private AlertDialog	  deleteAlert;
	private CheckBox show_pass;
	private Toolbar toolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_info_display);
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
		toolbar.setNavigationIcon(R.drawable.ic_launcher);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setUpView();
	}
	@Override
	protected void onResume(){
		super.onResume();
	}

	private void setUpView() {
	    
		Intent intent = getIntent();
		if(null != intent){
			 account_name = intent.getStringExtra(KEY_ACCOUNT_NAME);
			 username     = intent.getStringExtra(KEY_USERNAME);
			 password     = intent.getStringExtra(KEY_PASSWORD);
			 email        = intent.getStringExtra(KEY_EMAIL);
			 phone        = intent.getStringExtra(KEY_PHONE);
			 acc_id       = intent.getStringExtra(KEY_ID);
			 dateCreated  = intent.getStringExtra(KEY_DATE);
			}
			acc_info  = (TextView)findViewById(R.id.acc_info);
			acc_info.setText("*"+ account_name);
		    user_name = (TextView)findViewById(R.id.username);
		    user_name.setText("*"+username);
		    emailAddr = (TextView)findViewById(R.id.emailAddress);
		    emailAddr.setText("*"+email);
		    Password = (TextView)findViewById(R.id.password);
		    Password.setText(password );
		    Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			show_pass = (CheckBox)findViewById(R.id.show_pass);
			show_pass.setOnCheckedChangeListener(new OnCheckedChangeListener(){
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
		    phoneN = (TextView)findViewById(R.id.phone);
		    phoneN.setText("*"+phone);
		    
		
		
	}
	
    public void DeleteAccount(){
    	AccountStore accDelete = new AccountStore(account_name);
		accDelete.setAccount_name( account_name);
		accDelete.setUsername(username);
		accDelete.setPassword(password );
		accDelete.setPhone_number(phone );
		accDelete.setDates(dateCreated);
		accDelete.setEmail_address(email);
		accDelete.setAcc_ID(Long.parseLong(acc_id));
		getPasswordManagerApplication().AddDeleteAccount(accDelete);
		Intent intent = new Intent(AccountInfoDisplay.this, PassManagerHomeActivity.class);
		startActivity(intent);	
    	finish();
    }
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_account_info, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		int id = item.getItemId();
		switch(id){
		case R.id.Edit:
			Intent intent = new Intent(AccountInfoDisplay.this, UpdateAccountInfoActivity.class);
			intent.putExtra(KEY_ACCOUNT_NAME, account_name);
			intent.putExtra(KEY_USERNAME, username );
			intent.putExtra(KEY_PASSWORD, password);
			intent.putExtra(KEY_EMAIL, email);
			intent.putExtra(KEY_PHONE, phone);
			intent.putExtra(KEY_ID, acc_id);
			intent.putExtra(KEY_DATE, dateCreated);
            startActivity(intent);	
            finish();
	        return true;
		case R.id.delete:
			 
	    	deleteAlert = new AlertDialog.Builder(AccountInfoDisplay.this)
				     .setMessage("Are you sure you want to delete "+ account_name+" account")
				     .setPositiveButton("Yes", new AlertDialog.OnClickListener() {
		
		              @Override
					public void onClick(DialogInterface dialog, int which) {
		              DeleteAccount();
		              }
		              })
				     .setNegativeButton("Cancel", new AlertDialog.OnClickListener() {
		              @Override
					public void onClick(DialogInterface dialog, int which) {
		              deleteAlert.cancel();
		              }
		              })
		              .create();
           deleteAlert.show();
		    return true;     
	       
	        
	   case android.R.id.home:
		    this.finish();
		    return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
}
