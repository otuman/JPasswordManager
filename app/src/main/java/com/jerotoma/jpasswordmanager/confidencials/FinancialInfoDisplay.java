package com.jerotoma.jpasswordmanager.confidencials;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.jerotoma.jpasswordmanager.AccountManagerActivity;
import com.jerotoma.jpasswordmanager.PasswordManagerApplication;
import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.update.UpdateFinancialInfoGui;
import static com.jerotoma.jpasswordmanager.tabbed.HomeFinancial.FIN_ID;
import static com.jerotoma.jpasswordmanager.tabbed.HomeFinancial.FIN_TYPE;

public class FinancialInfoDisplay extends AccountManagerActivity {
	
	    
	    private ArrayList<FinancialInfo> financialInfoList;
		private Toolbar toolbar;
		private EditText disp_financialType;
		private EditText disp_DateCreated;
		private EditText  disp_financialName;
		private EditText  disp_financialNumber;
		private EditText  disp_financialVisa;
		private EditText  disp_financialPin;
		private EditText disp_ansq1;
		private EditText disp_secq1;
		private EditText disp_ansq2;
		private EditText disp_secq2;
		String fintype = null;
		String finId   = null;
		public PasswordManagerApplication app;
		private AlertDialog deleteAlert;
		
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.financial_info_display);
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
		toolbar.setTitle("Financial Information");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		initComponent();
		setUpView();
		
	}
	private void initComponent() {
		disp_financialType         = (EditText)findViewById(R.id.disp_financialType);
		disp_financialName         = (EditText)findViewById(R.id.disp_financialName);
		disp_financialNumber       = (EditText)findViewById(R.id.disp_financialNumber);
		disp_financialVisa         = (EditText)findViewById(R.id.disp_financialVisa);
		disp_financialPin          = (EditText)findViewById(R.id.disp_financialPin);
		disp_DateCreated           = (EditText)findViewById(R.id.disp_DateCreated);
		disp_ansq1                 = (EditText)findViewById(R.id.disp_ansq1);
		disp_secq1                 = (EditText)findViewById(R.id.disp_secq1);
		disp_ansq2                 = (EditText)findViewById(R.id.disp_ansq2);
		disp_secq2                 = (EditText)findViewById(R.id.disp_secq2);
		app                        = (PasswordManagerApplication)getApplication();
	}

	private void setUpView() {
		Intent intent  = getIntent();
		
		
		if(null != intent){
			fintype = intent.getStringExtra(FIN_TYPE);
			finId   = intent.getStringExtra(FIN_ID);
		}
		financialInfoList = app.getFinancialList();
		
		FinancialInfo finInfo = null;
		int i;
		for(i=0; i<financialInfoList.size(); i++){
			
			finInfo = financialInfoList.get(i);
			
			if(String.valueOf(finInfo.getFinancial_id()).equals(finId) && finInfo.getFinancial_type().equals(fintype)){
				disp_DateCreated.setText("Date Created : "+finInfo.getDate());
				disp_financialType.setText("Financial type : "+fintype);
				if(fintype.equals("BANK")){
					
					disp_financialName.setText("Bank name : "+finInfo.getFinancial_name());
					disp_financialNumber.setText("Account number : "+finInfo.getFinancial_number());
					
					if(finInfo.getFinancial_visa().equals("N/A")){
						disp_financialVisa.setVisibility(View.GONE);
						
					}else{
						disp_financialVisa.setText("Visa/Credit # : "+finInfo.getFinancial_visa());
					}
					disp_financialPin.setText("PIN/Password : "+finInfo.getFinancial_password());
					if(finInfo.getFinancial_security1().equals("N/A")){
						disp_secq1.setVisibility(View.GONE);
						disp_ansq1.setVisibility(View.GONE);
					}else{
						disp_secq1.setText("Security qn 1 : "+finInfo.getFinancial_security1());
						disp_ansq1.setText("Security ans 1 : "+finInfo.getFinancial_answer1());
					}
					if(finInfo.getFinancial_security2().equals("N/A")){
						disp_secq2.setVisibility(View.GONE);
						disp_ansq2.setVisibility(View.GONE);
					}else{
						disp_secq2.setText("Security qn 2 : "+finInfo.getFinancial_security2());
						disp_ansq2.setText("Security ans 2 : "+finInfo.getFinancial_answer2());
					}
     			}
				else if(fintype.equals("M-PESA")||fintype.equals("TIGO PESA") ||
						fintype.equals("Z-PESA")||fintype.equals("AIRTEL MONEY")){
					
					disp_financialName.setVisibility(View.GONE);
					disp_financialNumber.setText("Phone number : "+finInfo.getFinancial_number());
					disp_financialVisa.setVisibility(View.GONE);
					disp_financialPin.setText("PIN/Password : "+finInfo.getFinancial_password());
					disp_secq1.setVisibility(View.GONE);
					disp_ansq1.setVisibility(View.GONE);
					disp_secq2.setVisibility(View.GONE);
					disp_ansq2.setVisibility(View.GONE);
					
					
				}
				
			}
     	}
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
        		Intent intent = new Intent(FinancialInfoDisplay.this, UpdateFinancialInfoGui.class);
        		intent.putExtra(FIN_ID, finId);
        		intent.putExtra(FIN_TYPE, fintype);
        		startActivity(intent);
				break;
		   case R.id.delete:
			   deleteAlert = new AlertDialog.Builder(this)
   		    .setMessage("Are you sure you want to delete account")
   		    .setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                @Override
				public void onClick(DialogInterface dialog, int which) {
               	 
               	 deleteFinancial(Long.parseLong(finId));
               	 finish();
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
				 
              break;
		   case android.R.id.home:
			    this.finish();
			    return true;
			}
			
			return super.onOptionsItemSelected(item);
		}
		
		private void deleteFinancial(long finId) {
			app.deleteFinancial(finId);
			
		}
		
}
