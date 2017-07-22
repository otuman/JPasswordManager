package com.jerotoma.jpasswordmanager.tabbed;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jerotoma.jpasswordmanager.PasswordManagerApplication;
import com.jerotoma.jpasswordmanager.R;

import com.jerotoma.jpasswordmanager.accounts.AccountStore;
import com.jerotoma.jpasswordmanager.adapter.AccountAdapter;
import com.jerotoma.jpasswordmanager.app.AboutPassManager;
import com.jerotoma.jpasswordmanager.app.AccountInfoDisplay;
import com.jerotoma.jpasswordmanager.app.AddAccountInfo;
import com.jerotoma.jpasswordmanager.app.PasswordGenerator;
import com.jerotoma.jpasswordmanager.app.ResetNewUserInfo;
import com.jerotoma.jpasswordmanager.app.UpdateAccountInfoActivity;
import com.jerotoma.jpasswordmanager.update.DialogInfo;

import static com.jerotoma.jpasswordmanager.LoginMainActivity.KEY_USER;


public class PassManagerHomeActivity extends Fragment{
	 private String user; 
	 Button addNewAccount;
     TextView accout_new;
     TextView dialog_view;
     private ListView listView;
     private AlertDialog emptyField;
     private AlertDialog deleteAlert;
     public PasswordManagerApplication app;
     
	 private AccountAdapter adapter;
	 public static final   String KEY_ID               = "IDs";
	 public static final   String KEY_DATE             = "date";
     public static final   String KEY_ACCOUNT_NAME     = "account_name";
     public static final   String KEY_USERNAME         = "username";
     public static final   String KEY_PASSWORD         = "password";
     public static final   String KEY_EMAIL            = "email_address";
     public static final   String KEY_PHONE            = "phone_number";
     //public static final   String KEY_CUSER          = "userA";
     
        private String AccName;
        private String user1;
        private String pass;
        private String email1;
        private String phone1;
        private String date;
        private long ID;
        FloatingActionButton mFab;
	    Context context;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.context  = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	    
    	View v =inflater.inflate(R.layout.pass_manager_home,container,false);
	    return v;
	}
     
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onStart(){
		super.onStart();
		showTasks();
		currentUser();
	}
	@Override
	public void onResume(){
	       super.onResume();
	       adapter.forceReload();
	}
	
	public void currentUser(){
		Intent inte = getActivity().getIntent();

		if(null != inte)
		{ 
		 user = inte.getStringExtra(KEY_USER);	 
		}
				
	}
	protected void  showTasks(){
		    
		    mFab = (FloatingActionButton) getView().findViewById(R.id.fabbutton);
		
		    app = (PasswordManagerApplication)getActivity().getApplication();
			adapter = new AccountAdapter(app.getCurrentAccounts(),context);
			//setListAdapter(adapter);
			listView = (ListView)getView().findViewById(R.id.listView1);
			listView.setAdapter(adapter);
			//mFab.listenTo(listView);
			
			listView.setOnItemLongClickListener(new OnItemLongClickListener(){
			
					@Override
					public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
						
						DialogInfo infoDialog = new DialogInfo();
						final String[] maintain=infoDialog.getDialogString();
						final AccountStore accStore = adapter.getItem(position);
								
						AccName  =   accStore.getAccount_name();
						user1  	 =   accStore.getUsername();
						pass     =	 accStore.getPassword();
						email1   =	 accStore.getEmail_address();
						phone1   =	 accStore.getPhone_number();
						date     =	 accStore.getDates();
						ID       =    accStore.getAcc_ID();
						ArrayAdapter<String> dialogAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_dailog_info, R.id.dialog_view, infoDialog.getDialogString());
						emptyField = new AlertDialog.Builder(getActivity())
						               .setAdapter(dialogAdapter, new AlertDialog.OnClickListener(){
						                @Override
										public void onClick(DialogInterface dialog, int which){
						                	
						                	if(maintain[which]=="Delete"){
						                	 
						                deleteAlert = new AlertDialog.Builder(getActivity())
						                		    .setMessage("Are you sure you want to delete "+ AccName+" account")
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
                                                      listView.cancelLongPress();
                                                  	  CallThisByItSelf();
                                                      }
						                              })
						                              .create();
						                   deleteAlert.show();
						                		
						                	}
						                	if(maintain[which]=="Edit"){
						                		
								                	AccountStore accStore = adapter.getItem(position);
													String IDs =String.valueOf(accStore.getAcc_ID()).trim();
													   
													Intent intent = new Intent(getActivity(), UpdateAccountInfoActivity.class);
													intent.putExtra(KEY_ACCOUNT_NAME, accStore.getAccount_name());
													intent.putExtra(KEY_USERNAME, accStore.getUsername());
													intent.putExtra(KEY_PASSWORD, accStore.getPassword() );
													intent.putExtra(KEY_EMAIL, accStore.getEmail_address());
													intent.putExtra(KEY_PHONE, accStore.getPhone_number());
													intent.putExtra(KEY_ID, IDs);
													intent.putExtra(KEY_DATE, accStore.getDates());
													getActivity().startActivity(intent);
													
						                	}
						                	if(maintain[which]=="About Manager"){
						                		Toast.makeText(getActivity(), "This is About Manager", Toast.LENGTH_LONG).show();
						                	}
						                    }
							                })
			                         .create();
			               emptyField.show();
		
					return true;
					}});
			listView.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					AccountStore accStore = adapter.getItem(position);
					String IDs =String.valueOf(accStore.getAcc_ID()).trim();
					   
						Intent intent = new Intent(getActivity(), AccountInfoDisplay.class);
						intent.putExtra(KEY_ACCOUNT_NAME, accStore.getAccount_name());
						intent.putExtra(KEY_USERNAME, accStore.getUsername());
						intent.putExtra(KEY_PASSWORD, accStore.getPassword() );
						intent.putExtra(KEY_EMAIL, accStore.getEmail_address());
						intent.putExtra(KEY_PHONE, accStore.getPhone_number());
						intent.putExtra(KEY_ID, IDs);
						intent.putExtra(KEY_DATE, accStore.getDates());
						startActivity(intent);
						
				}
			});	
	}
	
	public void DeleteAccount(){
		AccountStore accDelete = new AccountStore(AccName);
		accDelete.setAccount_name(AccName);
		accDelete.setUsername(user1);
		accDelete.setPassword(pass);
		accDelete.setPhone_number(phone1);
		accDelete.setDates(date);
		accDelete.setEmail_address(email1);
		accDelete.setAcc_ID(ID);
		app.AddDeleteAccount(accDelete);
		CallThisByItSelf();
	}

	public void CallThisByItSelf(){
		PassManagerHomeActivity passhome = new PassManagerHomeActivity();
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction  transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.content_frame, passhome);
		//transaction.addToBackStack(null);
		getActivity().getSupportFragmentManager().popBackStack();
		transaction.commit();
	}

	//pass_manager_home_menu
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		inflater.inflate(R.menu.pass_manager_home_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		Intent intent;
		switch(id){
		case R.id.generatepass:
        	intent = new Intent(getActivity(), PasswordGenerator.class);
        	startActivity(intent);
        	break;
		case R.id.new_account:
			intent = new Intent(getActivity(), AddAccountInfo.class);
			intent.putExtra(KEY_USER, user);
	        startActivity(intent);
	        return true;
		case R.id._help:
			intent = new Intent(getActivity(), AboutPassManager.class);
            startActivity(intent);	
        break;
		case R.id.about:
			intent = new Intent(getActivity(), AboutPassManager.class);
            startActivity(intent);	
        break;
		case R.id.reset:
			intent = new Intent(getActivity(), ResetNewUserInfo.class);
            startActivity(intent);	
        break;
	   	}
		return super.onOptionsItemSelected(item);
		
	}
	
	
	 /*//trial 
	 AccountsSQLiteOpenHelper accsqlite = null;
	 String Sqluser ="select * from accounts where user = ? ";
	 @SuppressWarnings("null")
	 Cursor use= accsqlite.getReadableDatabase().rawQuery(Sqluser, new String[] { user });
	 

	 //trial
*/
	
}
	
 