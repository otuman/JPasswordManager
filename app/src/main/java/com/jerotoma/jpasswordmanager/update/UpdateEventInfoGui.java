package com.jerotoma.jpasswordmanager.update;

import static com.jerotoma.jpasswordmanager.tabbed.HomeEvent.EVENT_ID;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.jerotoma.jpasswordmanager.AccountManagerActivity;
import com.jerotoma.jpasswordmanager.PasswordManagerApplication;
import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.confidencials.EventInfo;

public class UpdateEventInfoGui extends AccountManagerActivity implements OnClickListener{

	private Toolbar toolbar;
	private Button upd_button;
	private EditText upd_DateCreated;
	private EditText upd_eventType;
	private EditText event_upd_name;
	private EditText upd_eventEnd;
	private EditText upd_eventStart;
	private EditText upd_singleEvent;
	private EditText upd_discription;
	private ImageButton eventButton1, eventButton2, eventButton3;
	public  PasswordManagerApplication app;
	private ArrayList<EventInfo> eventList;
	private int mYear;
	private int mMonth;
	private int mDay;
	
	private static String eventID   = null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_event_info_gui);
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
		toolbar.setTitle("Event Information");
		if(getSupportActionBar() != null){
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		initComponents();
		setUpView();
	}

	private void initComponents() {
		upd_DateCreated  = (EditText)findViewById(R.id.upd_DateCreated);
		upd_button       = (Button)findViewById(R.id.upd_button);
		upd_eventType    = (EditText)findViewById(R.id.event_upd_type);
		event_upd_name   = (EditText)findViewById(R.id.event_upd_name);
		upd_eventEnd     = (EditText)findViewById(R.id.upd_eventEnd);
		upd_eventStart   = (EditText)findViewById(R.id.upd_eventStart);
		upd_singleEvent  = (EditText)findViewById(R.id.upd_singleEvent);
		upd_discription  = (EditText)findViewById(R.id.upd_discription);
		eventButton1     = (ImageButton)findViewById(R.id.eventButton1);
	    eventButton2     = (ImageButton)findViewById(R.id.eventButton2);
		eventButton3     = (ImageButton)findViewById(R.id.eventButton3);
		app              = (PasswordManagerApplication)getApplication();
		
		eventButton1.setOnClickListener(this);
		eventButton2.setOnClickListener(this);
		eventButton3.setOnClickListener(this);
		
	}
   
	private void setUpView() {
		Intent intent = getIntent();
		
	         
		if(intent != null){
			 eventID   = intent.getStringExtra(EVENT_ID);
			 
		}
		eventList = app.getEventList();
		EventInfo eInfo = null;
		int i;
		for(i=0; i< eventList.size(); i++){
			
			eInfo = eventList.get(i);
			if(String.valueOf(eInfo.getEventId()).equals(eventID)){
				upd_DateCreated.setText(eInfo.getDateCreated());
				upd_eventType.setText(eInfo.getEventType());
				event_upd_name.setText(eInfo.getEventName());
				upd_singleEvent.setText(eInfo.getSingleEvent());
				upd_eventStart.setText(eInfo.getStartEvent());
				upd_eventEnd.setText(eInfo.getEndEvent());
				upd_discription.setText(eInfo.getDiscription());
				
			}
		}
		
		upd_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String eventType   = upd_eventType.getText().toString();
				String eName       = event_upd_name.getText().toString();
				String sEvent      = upd_singleEvent.getText().toString();
				String startEv     = upd_eventStart.getText().toString();
				String endEv       = upd_eventEnd.getText().toString();
				String eDiscr      = upd_discription.getText().toString();
				long  eID          = Integer.parseInt(eventID);
		
				
				
				int i;
				EventInfo eIn = null;
				for(i=0; i<eventList.size(); i++){
					eIn = eventList.get(i);
                   if(eID == eIn.getEventId()){
					if(eventType.equals(eIn.getEventType())&& eName.equals(eIn.getEventName()) && sEvent.equals(eIn.getSingleEvent())
							&& startEv.equals(eIn.getStartEvent())&& endEv.equals(eIn.getEndEvent())&& eDiscr.equals(eIn.getDiscription())){
						Toast.makeText(UpdateEventInfoGui.this, "No changes has been made yet", Toast.LENGTH_LONG).show();
					
				    }else{
						 if((startEv.equals("N/A")&& !endEv.equals("N/A"))||(!startEv.equals("N/A")&& endEv.equals("N/A"))){

							 Toast.makeText(UpdateEventInfoGui.this, "Start date or End date field must be filled", Toast.LENGTH_LONG).show();

						 }else{

								EventInfo eInfo  = new EventInfo(eID, eventType, eName, sEvent, startEv, endEv, eDiscr, eIn.getDateCreated());
								getPasswordManagerApplication().updateEventInfo(eInfo);
								Toast.makeText(UpdateEventInfoGui.this, "You have successfully updated the event", Toast.LENGTH_LONG).show();
								finish();

						 }

				    }
						
			    }
				}
				
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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

	@Override
	public void onClick(final View v) {

		final Calendar c = Calendar.getInstance();
        mYear  = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay   = c.get(Calendar.DAY_OF_MONTH);
       
    
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth){
				String month = null;
		  switch(monthOfYear){
				case  0:
					month = "January";
					break;
				case  1:
					month = "February";
					break;
				case  2:
					month = "March";
					break;
				case  3:
					month = "April";
					break;
				case  4:
					month = "May";
					break;
				case  5:
					month = "June";
					break;
				case 6:
					month = "July";
					break;
				case  7:
					month = "August";
					break;
				case  8:
					month = "September";
					break;
				case 9:
					month = "October";
					break;
				case  10:
					month = "November";
					break;
				case  11:
					month = "December";
					break;
				}
		  
			  if(v == eventButton1){
					
					upd_singleEvent.setText(month+" "+dayOfMonth+ ", " +year);
					
				}
                if(v == eventButton2){
					
                	upd_eventStart.setText(month+" "+dayOfMonth+ ", " +year);
					
				}
                if(v == eventButton3){
					
                	upd_eventEnd.setText(month+" "+dayOfMonth+ ", " +year);
					
				}
     		}
		}, mYear, mMonth, mDay);
	    
	    dpd.show();
	}
		

}
