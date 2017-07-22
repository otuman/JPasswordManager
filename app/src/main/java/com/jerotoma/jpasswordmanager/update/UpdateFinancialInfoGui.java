package com.jerotoma.jpasswordmanager.update;



import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import com.jerotoma.jpasswordmanager.AccountManagerActivity;
import com.jerotoma.jpasswordmanager.PasswordManagerApplication;
import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.confidencials.FinancialInfo;
import static com.jerotoma.jpasswordmanager.tabbed.HomeFinancial.FIN_ID;
import static com.jerotoma.jpasswordmanager.tabbed.HomeFinancial.FIN_TYPE;

public class UpdateFinancialInfoGui extends AccountManagerActivity{
	  private ArrayList<FinancialInfo> financialInfoList;
			private Toolbar toolbar;
			private Spinner fupd_financialType;
			private EditText fupd_DateCreated;
			private EditText  fupd_financialName;
			private EditText  fupd_financialNumber;
			private EditText  fupd_financialVisa;
			private EditText  fupd_financialPin;
			private EditText fupd_ansq1;
			private EditText fupd_secq1;
			private EditText fupd_ansq2;
			private EditText fupd_secq2;
			private TextView textView1;
			private TextView textView3;
			private TextView textView5;
			private TextView textView6;
			private TextView textView7;
			private TextView textView8;
			private String finId;
			private static int itemIndex;
			private static String selectedItem;

			
			public PasswordManagerApplication app;
			private Button fupd_button;
			
		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			setContentView(R.layout.update_financial_info);
			toolbar = (Toolbar) findViewById(R.id.tool_bar);
			setSupportActionBar(toolbar);
			toolbar.setTitle("Financial Information");
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			initComponent();
			spinnerItems();
			setUpView();
			
			
		}
		private void initComponent() {
			fupd_financialType         = (Spinner)findViewById(R.id.fupd_financialType);
			fupd_financialName         = (EditText)findViewById(R.id.fupd_financialName);
			fupd_financialNumber       = (EditText)findViewById(R.id.fupd_financialNumber);
			fupd_financialVisa         = (EditText)findViewById(R.id.fupd_financialVisa);
			fupd_financialPin          = (EditText)findViewById(R.id.fupd_financialPin);
			fupd_DateCreated           = (EditText)findViewById(R.id.fupd_DateCreated);
			fupd_ansq1                 = (EditText)findViewById(R.id.fupd_ansq1);
			fupd_secq1                 = (EditText)findViewById(R.id.fupd_secq1);
			fupd_ansq2                 = (EditText)findViewById(R.id.fupd_ansq2);
			fupd_secq2                 = (EditText)findViewById(R.id.fupd_secq2);
			fupd_button                = (Button)findViewById(R.id.fupd_button);
			textView1                  = (TextView)findViewById(R.id.textView1 );
			textView3                  = (TextView)findViewById(R.id.textView3 );
			textView5                  = (TextView)findViewById(R.id.textView5 );
			textView6                  = (TextView)findViewById(R.id.textView6 );
			textView7                  = (TextView)findViewById(R.id.textView7 );
			textView8                  = (TextView)findViewById(R.id.textView8 );
			app                        = (PasswordManagerApplication)getApplication();
		}

		private void setUpView() {
			
			Intent intent  = getIntent();
			if(null != intent){
				selectedItem = intent.getStringExtra(FIN_TYPE);
				finId        = intent.getStringExtra(FIN_ID);
				if(selectedItem.equals("BANK")){itemIndex = 0;}
				if(selectedItem.equals("M-PESA")){itemIndex = 1;}
				if(selectedItem.equals("TIGO PESA")){itemIndex = 2;}
				if(selectedItem.equals("Z-PESA")){itemIndex = 3;}
				if(selectedItem.equals("AIRTEL MONEY")){itemIndex = 4;}
				if(selectedItem.equals("OTHER")){itemIndex = 5;}
							
			}
			
			financialInfoList = app.getFinancialList();
			
			FinancialInfo finInfo = null;
			int i;
			for(i=0; i<financialInfoList.size(); i++){
				
				finInfo = financialInfoList.get(i);
				
				if(String.valueOf(finInfo.getFinancial_id()).equals(finId) && finInfo.getFinancial_type().equals(selectedItem)){
					fupd_DateCreated.setText("Date Created : "+finInfo.getDate());
					fupd_financialType.setSelection(itemIndex);
					if(selectedItem.equals("BANK")){
						
						fupd_financialName.setText(finInfo.getFinancial_name());
						fupd_financialNumber.setText(finInfo.getFinancial_number());
						fupd_financialVisa.setText(finInfo.getFinancial_visa());
						fupd_financialPin.setText(finInfo.getFinancial_password());
						fupd_secq1.setText(finInfo.getFinancial_security1());
						fupd_ansq1.setText(finInfo.getFinancial_answer1());
						fupd_secq2.setText(finInfo.getFinancial_security2());
					    fupd_ansq2.setText(finInfo.getFinancial_answer2());
						
	     			}
					else if(selectedItem.equals("M-PESA")||selectedItem.equals("TIGO PESA") ||
							selectedItem.equals("Z-PESA")||selectedItem.equals("AIRTEL MONEY")){
						
						fupd_financialName.setVisibility(View.GONE);
						fupd_financialNumber.setText(finInfo.getFinancial_number());
						fupd_financialVisa.setVisibility(View.GONE);
						fupd_financialPin.setText(finInfo.getFinancial_password());
						fupd_secq1.setVisibility(View.GONE);
						fupd_ansq1.setVisibility(View.GONE);
						fupd_secq2.setVisibility(View.GONE);
						fupd_ansq2.setVisibility(View.GONE);
						textView5.setVisibility(View.GONE);
						textView1.setVisibility(View.GONE);
						textView3.setVisibility(View.GONE);
						textView6.setVisibility(View.GONE);
						textView7.setVisibility(View.GONE);
						textView8.setVisibility(View.GONE);
					
					}
					
				}
	     	}
			
			
			fupd_button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					String ftype        =   selectedItem; 
					String fname        =   fupd_financialName.getText().toString();
					String fnumber      =   fupd_financialNumber.getText().toString();
					String fVisa        =   fupd_financialVisa.getText().toString();
					String fPin         =   fupd_financialPin.getText().toString();
					String fsecq1       =   fupd_secq1.getText().toString();
					String fsecq2       =   fupd_secq2.getText().toString();
					String fansq1       =   fupd_ansq1.getText().toString();
					String fansq2       =   fupd_ansq2.getText().toString();
					
					long id = Long.parseLong(finId);
					
					int i;
					FinancialInfo info = null;
					for(i=0; i<financialInfoList.size(); i++){
						
						info = financialInfoList.get(i);
						
						if(id == info.getFinancial_id()){
							
							if(selectedItem.equals("BANK")){
								if(fname.equals(info.getFinancial_name()) && fnumber.equals(info.getFinancial_number())&& fVisa.equals(info.getFinancial_visa())
										&& fPin.equals(info.getFinancial_password())&& fsecq1.equals(info.getFinancial_security1())&&fsecq2.equals(info.getFinancial_security2())
										&& fansq1.equals(info.getFinancial_answer1()) && fansq2.equals(info.getFinancial_answer2())){

									Toast.makeText(UpdateFinancialInfoGui.this, "No changes has been made yet", Toast.LENGTH_LONG).show();
									
								}
								else {
								if(fsecq1.equals(null)){
									
									fsecq1 = "N/A";
									fansq1 = "N/A";
									
								}	
								if(fsecq2.equals(null)){
									fsecq2 = "N/A";
									fansq2 = "N/A";
								}
								if(fVisa.equals(null)){
									fVisa = "N/A";
								}
									FinancialInfo fInfo = new FinancialInfo(id, ftype, fname, fnumber, fVisa, fPin, fsecq1, fsecq2, fansq1, fansq2, info.getDate());
									getPasswordManagerApplication().updateFinancial(fInfo);
									
									Toast.makeText(UpdateFinancialInfoGui.this, "You have successifully updated "+ ftype +" account", Toast.LENGTH_LONG).show();
									
									finish();
									
								}
								
							}else{
								if(fnumber.equals(info.getFinancial_number())&& fPin.equals(info.getFinancial_password())){

									Toast.makeText(UpdateFinancialInfoGui.this, "No changes has been made yet", Toast.LENGTH_LONG).show();
									
								}
								else {
								
									fname ="N/A";
									fVisa ="N/A";
									fsecq1 = "N/A";
									fsecq2 = "N/A";
									fansq1 = "N/A";
									fansq2 = "N/A";
								
									FinancialInfo fInfo = new FinancialInfo(id, ftype, fname, fnumber, fVisa, fPin, fsecq1, fsecq2, fansq1, fansq2, info.getDate());
									getPasswordManagerApplication().updateFinancial(fInfo);
									
									Toast.makeText(UpdateFinancialInfoGui.this, "You have successifully updated "+ ftype +" account", Toast.LENGTH_LONG).show();
									
									finish();
									
								}
								
							}
							

							
						}
					}
							
				}
			});
		}
		private void spinnerItems(){
			String[]  finType = {"BANK","M-PESA","TIGO PESA","Z-PESA","AIRTEL MONEY","OTHER"};
			ArrayAdapter<String> finantialTypeAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, finType);
		    finantialTypeAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		    fupd_financialType.setAdapter(finantialTypeAdapter);
		    fupd_financialType.setOnItemSelectedListener(new OnItemSelectedListener() {
				
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					itemIndex = (Integer)parent.getSelectedItemPosition();
					selectedItem= (String)fupd_financialType.getItemAtPosition(position);
					
				
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
					
				}});
		}
	  @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
				return true;
			}
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item){
				
				int id = item.getItemId();
			switch(id){
				  case android.R.id.home:
				    this.finish();
				    return true;
				}
				
				return super.onOptionsItemSelected(item);
			}
			
	}


