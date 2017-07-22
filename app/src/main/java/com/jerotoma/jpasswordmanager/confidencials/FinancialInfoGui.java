package com.jerotoma.jpasswordmanager.confidencials;

import com.jerotoma.jpasswordmanager.CalenderGenerate;
import com.jerotoma.jpasswordmanager.AccountManagerActivity;

import java.util.ArrayList;

import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.app.PasswordGenerator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;


public class FinancialInfoGui extends AccountManagerActivity {
	
	ArrayList<FinancialInfo> listOfFinancialInfo;
	
    public CalenderGenerate calDate;
	private Toolbar toolbar;
	private Spinner financialType;
	private EditText financialName;
	private EditText financialNumber;
	private EditText financialVisa;
	private EditText financialPin;
	private EditText financialPinConfirm;
	private RadioButton moreInfoYes;
	private RadioButton moreInfoNo;
	private EditText ansq1;
	private EditText secq1;
	private EditText ansq2;
	private EditText secq2;
    private Button addFinancialButton;

	private ImageButton fgpassword;
	private static int itemIndex;
	private static String selectedItem = "BANK";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.addfinancial);
			toolbar = (Toolbar)findViewById(R.id.tool_bar);
			setSupportActionBar(toolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			initComponent();
			
	}
   private void initComponent(){
	        calDate               = new CalenderGenerate();
		    financialType         = (Spinner)findViewById(R.id.financialType);
		    financialName         = (EditText)findViewById(R.id.financialName);
		    financialNumber       = (EditText)findViewById(R.id.financialNumber);
		    financialVisa         = (EditText)findViewById(R.id.financialVisa);
		    financialPin          = (EditText)findViewById(R.id.financialPin);
		    ansq1                 = (EditText)findViewById(R.id.ansq1);
		    secq1                 = (EditText)findViewById(R.id.secq1);
		    ansq2                 = (EditText)findViewById(R.id.ansq2);
		    secq2                 = (EditText)findViewById(R.id.secq2);
		    addFinancialButton    = (Button)findViewById(R.id.addFinancialButton);
		    fgpassword            = (ImageButton)findViewById(R.id.fgpassword);

		    moreInfoNo            = (RadioButton)findViewById(R.id.moreInfoNo);
		    moreInfoYes           = (RadioButton)findViewById(R.id.moreInfoYes);
		    financialPinConfirm   = (EditText)findViewById(R.id.financialPinConfirm);
		    spinnerOnClickListener();
		    addFinancialButton();
			
	}
	private void addFinancialButton() {
		fgpassword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent inte = new Intent(FinancialInfoGui.this, PasswordGenerator.class);
	        	startActivity(inte);
				
			}
		});
		
		addFinancialButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String ftype        =   selectedItem;  
				String fname        =   financialName.getText().toString();
				String fnumber      =   financialNumber.getText().toString();
				String fVisa        =   financialVisa.getText().toString();
				String fPin         =   financialPin.getText().toString();
				String fPinConfirm  =   financialPinConfirm.getText().toString();
				String fsecq1       =   secq1.getText().toString();
				String fsecq2       =   secq2.getText().toString();
				String fansq1       =   ansq1.getText().toString();
				String fansq2       =   ansq2.getText().toString();
				String dateCreated  =   calDate.timeElapsed();
				long id = getPasswordManagerApplication().maxFinancialId() + 1;
				
			if(ftype.equals("BANK")){
					if(fname.equals("") && fnumber.equals("")&& fVisa.equals("")&& fPin.equals("")&& fPinConfirm.equals("")){
				     
						Toast.makeText(FinancialInfoGui.this, "Please fill the required space to continue", Toast.LENGTH_LONG).show();	
						
					}
					else if(fname.equals("") || fnumber.equals("")|| fVisa.equals("")|| fPin.equals("")|| fPinConfirm.equals("")){
						Toast.makeText(FinancialInfoGui.this, "Please make sure each space is filled to continue", Toast.LENGTH_LONG).show();	
						
					}
					else{
						if(fPin.equals(fPinConfirm)){
                           if(fsecq1.equals("") ||fsecq2.equals("")|| fansq1.equals("") || fansq2.equals("")){

								fsecq1 = "N/A";
								fsecq2 = "N/A";
								fansq1 = "N/A";
								fansq2 = "N/A";
								
							}
							FinancialInfo finInfo = new FinancialInfo(id,ftype, fname, fnumber, fVisa, fPin, fsecq1, fsecq2, fansq1, fansq2, dateCreated);
							getPasswordManagerApplication().AddFinancial(finInfo);
							Toast.makeText(FinancialInfoGui.this, "You have successifully added "+ftype+" financial info ", Toast.LENGTH_LONG).show();
							finish();
						}
						else{
						Toast.makeText(FinancialInfoGui.this, "The entered pin do not match, Please re-enter again", Toast.LENGTH_LONG).show();
						}
						
					}
					
				}
				else if(ftype.equals("M-PESA")||ftype.equals("TIGO PESA")||ftype.equals("Z-PESA")||ftype.equals("AIRTEL MONEY")){
					if(fnumber.equals("")&& fPin.equals("")&& fPinConfirm.equals("")){
					     
						Toast.makeText(FinancialInfoGui.this, "Please fill the required space to continue", Toast.LENGTH_LONG).show();	
						
					}
					else if(fnumber.equals("")|| fPin.equals("")|| fPinConfirm.equals("")){
						
						Toast.makeText(FinancialInfoGui.this, "Please make sure each space is filled to continue", Toast.LENGTH_LONG).show();	
						
					}
					else{
						if(fPin.equals(fPinConfirm)){
                           if(fsecq1.equals("") ||fsecq2.equals("")|| fansq1.equals("") || fansq2.equals("")|| fname.equals("") || fVisa.equals("")){
								
								fsecq1 = "N/A";
								fsecq2 = "N/A";
								fansq1 = "N/A";
								fansq2 = "N/A";
								fname  = "N/A";
								fVisa  = "N/A";
							}
							FinancialInfo finInfo = new FinancialInfo(id,ftype, fname, fnumber, fVisa, fPin, fsecq1, fsecq2, fansq1, fansq2, dateCreated);
							
							getPasswordManagerApplication().AddFinancial(finInfo);
							Toast.makeText(FinancialInfoGui.this, "You have successifully added "+ftype+" financial info ", Toast.LENGTH_LONG).show();
							finish();
						}
						else{
						Toast.makeText(FinancialInfoGui.this, "The entered pin do not match, Please re-enter again", Toast.LENGTH_LONG).show();
						}
					}
				} 
				else{
					
				}
				
				}
		});
    }  
	
	private void spinnerOnClickListener() {
		String[]  finType = {"BANK","M-PESA","TIGO PESA","Z-PESA","AIRTEL MONEY","OTHER"};
		ArrayAdapter<String> finantialTypeAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, finType);
	    finantialTypeAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		financialType.setAdapter(finantialTypeAdapter);
		financialType.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				itemIndex = (Integer)parent.getSelectedItemPosition();
				selectedItem= (String)financialType.getItemAtPosition(position);
				
				if(itemIndex==0){
					financialName.setVisibility(View.VISIBLE);
					financialVisa.setVisibility(View.VISIBLE);;
					financialName.setHint("Enter Bank name");
					financialNumber.setHint("Enter account number");
					financialVisa.setHint("Enter credit/visa number");
					financialPin.setHint("Enter your PIN");
					moreInfoNo.setEnabled(true);
					moreInfoYes.setEnabled(true);
				}
				else if((itemIndex == 1)|| (itemIndex==2) || (itemIndex == 3) || (itemIndex==4) ){
					moreInfoNo.setChecked(true);
					moreInfoNo.setEnabled(false);
					moreInfoYes.setEnabled(false);
					financialName.setVisibility(View.GONE);
					financialNumber.setHint("Cell phone number");
					financialVisa.setVisibility(View.GONE);
					secq1.setVisibility(View.GONE);
					secq2.setVisibility(View.GONE);
					ansq1.setVisibility(View.GONE);
					ansq2.setVisibility(View.GONE);
					
				}
				else if(itemIndex==5){
					moreInfoNo.setEnabled(true);
					moreInfoYes.setEnabled(true);
					
				}
			}
     		@Override
			public void onNothingSelected(AdapterView<?> parent){
				
				
			}
		});
		
		
	}
	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	      
	    // Check which radio button was clicked
	    switch(view.getId()) {
	    case R.id.moreInfoNo:
             if (checked){
            	secq1.setVisibility(View.GONE);
				secq2.setVisibility(View.GONE);
				ansq1.setVisibility(View.GONE);
				ansq2.setVisibility(View.GONE);
				
	            }
	            break;
	     case R.id.moreInfoYes:
	            if (checked){
	            	secq1.setVisibility(View.VISIBLE);
					secq2.setVisibility(View.VISIBLE);
					ansq1.setVisibility(View.VISIBLE);
					ansq2.setVisibility(View.VISIBLE);
	            }
	            break;
	        
	      default:
	        	secq1.setVisibility(View.GONE);
				secq2.setVisibility(View.GONE);
				ansq1.setVisibility(View.GONE);
				ansq2.setVisibility(View.GONE);
	    }
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









