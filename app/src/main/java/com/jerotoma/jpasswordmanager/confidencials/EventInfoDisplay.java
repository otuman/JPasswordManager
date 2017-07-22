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
import com.jerotoma.jpasswordmanager.update.UpdateEventInfoGui;
import static com.jerotoma.jpasswordmanager.tabbed.HomeEvent.EVENT_NAME;
import static com.jerotoma.jpasswordmanager.tabbed.HomeEvent.EVENT_ID;

public class EventInfoDisplay extends AccountManagerActivity {
	private Toolbar toolbar;
	private EditText disp_DateCreated;
	private EditText disp_eventType;
	private EditText event_disp_name;
	private EditText disp_eventEnd;
	private EditText disp_eventStart;
	private EditText disp_singleEvent;
	private EditText disp_discription;
	 String eventName = null;
	 String eventID   = null;
	public  PasswordManagerApplication app;
	private ArrayList<EventInfo> eventList;
	private AlertDialog deleteAlert;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_info_display);
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
		toolbar.setTitle("Event Information");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		initComponents();
		setUpView();
	}

	private void initComponents(){
		disp_DateCreated = (EditText)findViewById(R.id.disp_DateCreated);
		disp_eventType   = (EditText)findViewById(R.id.event_disp_type);
		disp_eventType.setFocusable(false);
		event_disp_name  = (EditText)findViewById(R.id.event_disp_name);
		event_disp_name.setFocusable(false);
		disp_eventEnd    = (EditText)findViewById(R.id.disp_eventEnd);
		disp_eventEnd.setFocusable(false);
		disp_eventStart  = (EditText)findViewById(R.id.disp_eventStart);
		disp_eventStart.setFocusable(false);
		disp_singleEvent = (EditText)findViewById(R.id.disp_singleEvent);
		disp_singleEvent.setFocusable(false);
		disp_discription = (EditText)findViewById(R.id.disp_discription);
		disp_discription.setFocusable(false);
		app              = (PasswordManagerApplication)getApplication();
		
	}

	private void setUpView(){
		
	      Intent intent   = getIntent();
	      if(null != intent){
	    	  
	    	   eventName = intent.getStringExtra(EVENT_NAME);
	    	   eventID   = intent.getStringExtra(EVENT_ID);
	    	  
	      }
	      
	      eventList   = app.getEventList();
	      int i;
	      EventInfo info = null;
	      for(i=0; i<eventList.size(); i++){
	    	  info = eventList.get(i);
	    	  if(String.valueOf(info.getEventId()).equals(eventID) && info.getEventName().equals(eventName)){
	    		  disp_DateCreated.setText("Date created : "+info.getDateCreated());
	    		  disp_eventType.setText("Event type: "+info.getEventType());
	    		  event_disp_name.setText("Event name: "+info.getEventName());
	        	 
	    		  if(info.getSingleEvent().equals("N/A")){
	        	     disp_singleEvent.setVisibility(View.GONE);
	        	  }else{
	        	     disp_singleEvent.setText("Event date: "+info.getSingleEvent());
	        	  }
	    		  
	        	  if(info.getStartEvent().equals("N/A") && info.getEndEvent().equals("N/A")){
	        		 disp_eventEnd.setVisibility(View.GONE);
	        	     disp_eventStart.setVisibility(View.GONE);
	        	  }else{
	        		  disp_eventEnd.setText("End date: "+info.getEndEvent());
		        	  disp_eventStart.setText("Start date: "+info.getStartEvent());
	        	  }
	        	  if(info.getDiscription().equals("N/A")){
	        		  disp_discription.setVisibility(View.GONE);
		        	  }else{
		        	  disp_discription.setText("Discription: "+info.getDiscription());
			      }
	        	 
	    	  }
	    	  
	      }
	 
	 }
	public  void deleteEvent(long id){
   	 app.deleteEvent(id);
   	     
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
			 Intent intent = new Intent(EventInfoDisplay.this, UpdateEventInfoGui.class);
             intent.putExtra(EVENT_NAME, eventName);
             intent.putExtra(EVENT_ID, eventID);
             startActivity(intent);
             break;
	       
	   case R.id.delete:
		   
		 	 
           deleteAlert = new AlertDialog.Builder(this)
           		    .setMessage("Are you sure you want to delete event")
           		    .setPositiveButton("Yes", new AlertDialog.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						              
                       	    deleteEvent(Long.parseLong(eventID));
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
		
}
