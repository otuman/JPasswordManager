package com.jerotoma.jpasswordmanager.app;


import java.util.ArrayList;
import java.util.List;

import com.jerotoma.jpasswordmanager.AccountManagerActivity;
import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.tabbed.RandomPasswordGenerator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
/**
 * Created by hp1 on 21-01-2015.
 */
@SuppressLint("DefaultLocale")
public class PasswordGenerator extends AccountManagerActivity implements OnItemSelectedListener {
	private Spinner   spinner;
	private CheckBox  withNumbers;
	private CheckBox  withUpperCaseOn;
	private CheckBox  withLowerCaseOn;
	private CheckBox  charater;
	private EditText  textView3;
	private Button  generateButton;
	private Button  lengthOfPassButton;
	private static Boolean lengthSelected = false;
	private static Boolean numChecked     = false;
	private static Boolean upperChecked   = false;
	private static Boolean lowerChecked   = false;
	private static Boolean charaChecked   = false;
	private static int noOfCAPSAlpha;
	private static int noOfDigits;
	private static int noOfSplChars;
	private static int passlength = 0;
	private static int digit  = 0;
	private static int upper  = 0;
	private static int chara  = 0;
	private AlertDialog.Builder confirmDialog;
	private Toolbar toolbar;
	private Button finishButton;
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password_generator);
		toolbar = (Toolbar)findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
	    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	    variableDeclaration();
        setUpViews();
	}
    
    
	private void variableDeclaration() {
	
		textView3 = (EditText)findViewById(R.id.textView3);
		textView3.setEnabled(true);
		textView3.setKeyListener(null);
		textView3.setClickable(true);
	   		
		spinner          = (Spinner)findViewById(R.id.lengthOfPass);
		spinner.setPrompt("Title");
		
		withNumbers      = (CheckBox)findViewById(R.id.withNumbers);
		withUpperCaseOn  = (CheckBox)findViewById(R.id.withUpperCaseOn);
		withLowerCaseOn  = (CheckBox)findViewById(R.id.withLowerCaseOn);
		charater         = (CheckBox)findViewById(R.id.character);
		generateButton   = (Button)findViewById(R.id.generateButton);
		finishButton     = (Button)findViewById(R.id.finishButton);
		lengthOfPassButton= (Button)findViewById(R.id.lengthOfPassButton);
		
	} 
	
	private void setUpViews() {
		
		finishButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		//setUp the spinner
		
		List<Integer> passLength = new ArrayList<Integer>();
		int i;
		for(i = 6; i<=40; i++){
		
		    passLength.add(i);
		}
		
		ArrayAdapter<Integer> passLengthAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, passLength);
		passLengthAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spinner.setAdapter(passLengthAdapter);
		
		
		spinner.setOnItemSelectedListener(this);
	
		
		spinner.setVisibility(View.INVISIBLE);
		lengthOfPassButton.setVisibility(View.VISIBLE);
		lengthOfPassButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			spinner.performClick();
			spinner.setVisibility(View.VISIBLE);
			lengthOfPassButton.setVisibility(View.INVISIBLE);
			lengthSelected= true;	
			}
		});
		
		addGenerateButtonListener();
				
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
	    passlength = (Integer) parent.getItemAtPosition(position);
	   
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
	public void checkBoxesListener(){
	withNumbers.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					numChecked = true;
			     }
				else{
					  numChecked = false;
				}
					
		}
		});
	withUpperCaseOn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if(isChecked){
				upperChecked = true;
		     }
			else{
				  upperChecked = false;
			}
				
		}
	});
	withLowerCaseOn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if(isChecked){
				lowerChecked= true;
		     }
			else{
				lowerChecked = false;
			}
				
			
		}
	});
	charater.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if(isChecked){
			  charaChecked = true;
		     }
			else{
			  charaChecked = false;
			}
			
		}
   });
   }
	
	private void addGenerateButtonListener() {
	
		checkBoxesListener();
		
		generateButton.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("DefaultLocale")
			@Override
			public void onClick(View v) {
				
				
                if(!(numChecked) && !(upperChecked) && !(lowerChecked) && !(charaChecked) && !lengthSelected){
					
					Toast.makeText(PasswordGenerator.this, "Please select password length and check at least one box", Toast.LENGTH_LONG).show();
					
			    }
			    else if(!(numChecked) && !(upperChecked) && !(lowerChecked) && !(charaChecked) && lengthSelected){
					
					Toast.makeText(PasswordGenerator.this, "Please check atleast one box", Toast.LENGTH_LONG).show();
					
			    }
				
                else if( !(lengthSelected) && ((numChecked) || (upperChecked) ||(lowerChecked) || (charaChecked))){
					
					Toast.makeText(PasswordGenerator.this, "Please select passwor length", Toast.LENGTH_LONG).show();	
					
				}
			    else if((numChecked) && !(upperChecked) && !(lowerChecked) && !(charaChecked) || !lengthSelected){
			    	
			    	String pin = RandomPasswordGenerator.getRandomNumber(passlength);
			    	textView3.setText(pin);
			    	
			    }
					
				else if(!(numChecked) && (upperChecked) && !(lowerChecked) && !(charaChecked) &&lengthSelected ){
					noOfCAPSAlpha  = 0;
					noOfDigits     = 0;
					noOfSplChars   = 0;
					
					char[] pswd = RandomPasswordGenerator.generatePswd(noOfCAPSAlpha, noOfDigits, noOfSplChars, passlength );
					String password = String.valueOf(pswd);
                    
					textView3.setText(password.toUpperCase());
				}
				else if(!(numChecked) && !(upperChecked) && (lowerChecked) && !(charaChecked) &&lengthSelected){
					noOfCAPSAlpha  = 0;
					noOfDigits     = 0;
					noOfSplChars   = 0;
					
					char[] pswd = RandomPasswordGenerator.generatePswd(noOfCAPSAlpha, noOfDigits, noOfSplChars, passlength );
					String password = String.valueOf(pswd);
					textView3.setText(password);
				}
					
				else if(!(numChecked) && !(upperChecked) && !(lowerChecked) && (charaChecked) &&lengthSelected){
					
					Toast.makeText(PasswordGenerator.this, "Does not meet initial pin or password requirement:", Toast.LENGTH_LONG).show();
				}
					
			    else if((numChecked) && (upperChecked) && !(lowerChecked) && !(charaChecked) && lengthSelected){
			    	checkLength();
			    	noOfCAPSAlpha  = 0;
					noOfDigits     = digit;
					noOfSplChars   = 0;
					
					char[] pswd = RandomPasswordGenerator.generatePswd(noOfCAPSAlpha, noOfDigits, noOfSplChars, passlength );
					String password = String.valueOf(pswd);
					textView3.setText(password.toUpperCase());
				}
			    else if((numChecked) && !(upperChecked) && (lowerChecked) && !(charaChecked)&& lengthSelected){
			    	checkLength();
			    	noOfCAPSAlpha  = 0;
					noOfDigits     = digit;
					noOfSplChars   = 0;
					
					char[] pswd = RandomPasswordGenerator.generatePswd(noOfCAPSAlpha, noOfDigits, noOfSplChars, passlength );
					String password = String.valueOf(pswd);
					textView3.setText(password);
			    }
			    else if((numChecked) && !(upperChecked) && !(lowerChecked) && (charaChecked) && lengthSelected){
			        Toast.makeText(PasswordGenerator.this, "Does not meet initial pin or password requirement:", Toast.LENGTH_LONG).show();
			    	
					
				}
			    else if(!(numChecked) && (upperChecked) && (lowerChecked) && !(charaChecked)&& lengthSelected){
			    	checkLength();
			    	noOfCAPSAlpha  = upper;
					noOfDigits     = 0;
					noOfSplChars   = 0;
					
					char[] pswd = RandomPasswordGenerator.generatePswd(noOfCAPSAlpha, noOfDigits, noOfSplChars, passlength );
					String password = String.valueOf(pswd);
					textView3.setText(password);
			    }
			    else if(!(numChecked) && (upperChecked) && !(lowerChecked) && (charaChecked)&& lengthSelected){
			    	checkLength();
			    	noOfCAPSAlpha  = 0;
					noOfDigits     = 0;
					noOfSplChars   = chara;
					
					char[] pswd = RandomPasswordGenerator.generatePswd(noOfCAPSAlpha, noOfDigits, noOfSplChars, passlength );
					String password = String.valueOf(pswd);
					textView3.setText(password.toUpperCase());
				}
			    else if(!(numChecked) && !(upperChecked) && (lowerChecked) && (charaChecked) && lengthSelected){
			    	checkLength();
			    	noOfCAPSAlpha  = 0;
					noOfDigits     = 0;
					noOfSplChars   = chara;
					
					char[] pswd = RandomPasswordGenerator.generatePswd(noOfCAPSAlpha, noOfDigits, noOfSplChars, passlength );
					String password = String.valueOf(pswd);
					textView3.setText(password);
			    }
			    else if((numChecked) && (upperChecked) && (lowerChecked) && !(charaChecked) && lengthSelected){
			    	checkLength();
			    	noOfCAPSAlpha  = upper;
					noOfDigits     = digit;
					noOfSplChars   = 0;
					
					char[] pswd = RandomPasswordGenerator.generatePswd(noOfCAPSAlpha, noOfDigits, noOfSplChars, passlength );
					String password = String.valueOf(pswd);
					textView3.setText(password);
				}
			    else if((numChecked) && !(upperChecked) && (lowerChecked) && (charaChecked) && lengthSelected){
			    	checkLength();
			    	noOfCAPSAlpha  = 0;
					noOfDigits     = digit;
					noOfSplChars   = chara;
					
					char[] pswd = RandomPasswordGenerator.generatePswd(noOfCAPSAlpha, noOfDigits, noOfSplChars, passlength );
					String password = String.valueOf(pswd);
					textView3.setText(password);
			    }
			    else if((numChecked) && (upperChecked) && !(lowerChecked) && (charaChecked) && lengthSelected){
			    	checkLength();
			    	noOfCAPSAlpha  = 0;
					noOfDigits     = digit;
					noOfSplChars   = chara;
					
					char[] pswd = RandomPasswordGenerator.generatePswd(noOfCAPSAlpha, noOfDigits, noOfSplChars, passlength );
					String password = String.valueOf(pswd);
					textView3.setText(password.toUpperCase());
				}
			    else if(!(numChecked) && (upperChecked) && (lowerChecked) && (charaChecked) && lengthSelected){
			    	checkLength();
			    	noOfCAPSAlpha  = upper;
					noOfDigits     = 0;
					noOfSplChars   = chara;
					
					char[] pswd = RandomPasswordGenerator.generatePswd(noOfCAPSAlpha, noOfDigits, noOfSplChars, passlength );
					String password = String.valueOf(pswd);
					textView3.setText(password);
				}
			    else if(numChecked && lowerChecked && upperChecked && charaChecked && lengthSelected){
			    	checkLength();
			    	noOfCAPSAlpha  = upper;
					noOfDigits     = digit;
					noOfSplChars   = chara;
					
					char[] pswd = RandomPasswordGenerator.generatePswd(noOfCAPSAlpha, noOfDigits, noOfSplChars, passlength );
					String password = String.valueOf(pswd);
					textView3.setText(password);
			    }else{
			    	
			    }
				
			}
		});
		
		copyToClipBoard();
	 
		lengthSelected= false;	
	 
	 
	}
	private void copyToClipBoard(){
		   		
		   		textView3.setOnLongClickListener(new OnLongClickListener() {
						@Override
						public boolean onLongClick(View v) {
							
							confirmDialog = new AlertDialog.Builder(PasswordGenerator.this);
							confirmDialog.setTitle("Confirm password");
							confirmDialog.setMessage(textView3.getText());
							confirmDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						          public void onClick(DialogInterface dialog, int id) {
						             
						          }
						       });
							confirmDialog.setPositiveButton("Copy",new DialogInterface.OnClickListener() {
						         public void onClick(DialogInterface dialog, int id) {
						            
						        	android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE); 
						     	    android.content.ClipData clip = android.content.ClipData.newPlainText("Password copied to clipboard", textView3.getText());
						     	    clipboard.setPrimaryClip(clip);
						        	 
						        	 
						        	 Toast.makeText(PasswordGenerator.this,"password copied",Toast.LENGTH_SHORT).show();
						          }
						       });
							
							confirmDialog.show();
						return true;
						}
				});
		
	}
	private void checkLength(){
	if((lengthSelected) && ((numChecked) || (upperChecked) ||(lowerChecked) || (charaChecked))){
		if(passlength <= 10){
		    digit = 1;
		    chara = 1;
		    upper = 2;
		   
		    		
		}
		else if(10< passlength && passlength <= 20){
		    digit = 3;
		    chara = 2;
		    upper = 3;
		    
		}
	      
	   else if(20< passlength && passlength <= 30){
		    digit = 4;
		    chara = 3;
		    upper = 6;
	
		}
	   else if(30< passlength && passlength <= 40){
		    digit = 5;
		    chara = 4;
		    upper = 8;
		}
	   else{
		    digit = 0;
		    chara = 0;
		    upper = 0;
	   }
	}
	}
	
}