package com.jerotoma.jpasswordmanager.tabbed;


import android.app.AlertDialog;
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
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jerotoma.jpasswordmanager.PasswordManagerApplication;
import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.app.AboutPassManager;
import com.jerotoma.jpasswordmanager.app.HelpActivity;
import com.jerotoma.jpasswordmanager.app.PasswordGenerator;
import com.jerotoma.jpasswordmanager.confidencials.EventCustomeAdapter;
import com.jerotoma.jpasswordmanager.confidencials.EventInfo;
import com.jerotoma.jpasswordmanager.confidencials.EventInfoDisplay;
import com.jerotoma.jpasswordmanager.confidencials.EventInfoGuiActivity;
import com.jerotoma.jpasswordmanager.update.DialogInfo;
import com.jerotoma.jpasswordmanager.update.UpdateEventInfoGui;

import java.util.ArrayList;

/**
 * Created by hp1 on 21-01-2015.
 */
public class HomeEvent extends Fragment{
	
	
	private ListView event_listView;
	private EventCustomeAdapter adapter;
	private AlertDialog emptyField;
    private AlertDialog deleteAlert;
	private FloatingActionButton mFab;
	public PasswordManagerApplication appEVEN;
	public static final String EVENT_NAME = "name";
	public static final String EVENT_ID  = "eventId";
	
	
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.home_event, container,false);
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
		 initListViewComponent();
		 setUpView();
		
		
		
	}
    @Override
	public  void onResume(){
	       super.onResume();
	       adapter.forceReload();
	       
	    }
    private void initListViewComponent(){
		mFab             = (FloatingActionButton) getView().findViewById(R.id.addeventbutton);
		event_listView   = (ListView)getView().findViewById(R.id.event_listView);
		appEVEN          = (PasswordManagerApplication)getActivity().getApplication();
	}
    private void setUpView(){
    	    ArrayList<EventInfo> eventList    =  appEVEN.getEventList();
    	     		  
	        adapter = new EventCustomeAdapter(getActivity(), eventList );
	        event_listView.setAdapter(adapter);
	       // mFab.listenTo(event_listView);
	        event_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            @SuppressWarnings("rawtypes")
				@Override
	            public void onItemClick(AdapterView adapterView, View view, int position, long l) {
	            	
	            	final EventInfo event     = adapter.getItem(position);
	                
	                Intent intent = new Intent(getActivity(), EventInfoDisplay.class);
	                       intent.putExtra(EVENT_NAME, event.getEventName());
	                       intent.putExtra(EVENT_ID, String.valueOf(event.getEventId()));
	                       startActivity(intent);
	                
	                
	            }
	        });
	        
	  	    event_listView.setOnItemLongClickListener(new OnItemLongClickListener(){
			
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				DialogInfo infoDialog = new DialogInfo();
				final String[] maintain  = infoDialog.getDialogString();
				final EventInfo ev = adapter.getItem(position);  
				ArrayAdapter<String> dialogAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_dailog_info, R.id.dialog_view, infoDialog.getDialogString());
					emptyField = new AlertDialog.Builder(getActivity())
		               .setAdapter(dialogAdapter, new AlertDialog.OnClickListener(){
		                @Override
						public void onClick(DialogInterface dialog, int which){
		                	
		                	if(maintain[which]=="Delete"){
		                	 
		                deleteAlert = new AlertDialog.Builder(getActivity())
		                		    .setMessage("Are you sure you want to delete event")
		                		    .setPositiveButton("Yes", new AlertDialog.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										
								        
		                         
		                            	deleteEvent(ev.getEventId());
		                                 
		                                }
		                             })
		                		     .setNegativeButton("Cancel", new AlertDialog.OnClickListener() {
                                      @Override
									public void onClick(DialogInterface dialog, int which) {
                                      deleteAlert.cancel();
                                      event_listView.cancelLongPress();
                                      CallThisByItSelf();
                                      }
		                              })
		                              .create();
		                   deleteAlert.show();
		                		
		                	}
		                	if(maintain[which]=="Edit"){
		                		Intent intent = new Intent(getActivity(), UpdateEventInfoGui.class);
		                		intent.putExtra(EVENT_ID, String.valueOf(ev.getEventId()));
		                		startActivity(intent);
		                			
		                	}
		                	if(maintain[which]=="About Manager"){
		                		Toast.makeText(getActivity(), "This is About Manager", Toast.LENGTH_LONG).show();
		                	}
		                    }
			                })
                     .create();
               emptyField.show();
				 
		
		        return true;
				
				
			}
		});
    }
    public  void deleteEvent(long id){
    	 appEVEN.deleteEvent(id);
    	 CallThisByItSelf();
         
    }
    public void CallThisByItSelf(){
		
    	HomeEvent eventFrag = new HomeEvent();
    	FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction  transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.event_content_frame, eventFrag);
		transaction.addToBackStack(null);
		transaction.commit();
	}
    

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		inflater.inflate(R.menu.events, menu);
		 
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
        case R.id.generatepass:
        	Intent inte = new Intent(getActivity(), PasswordGenerator.class);
        	startActivity(inte);
        	break;
        case R.id.event:
        	Intent inte1 = new Intent(getActivity(), EventInfoGuiActivity.class);
        	startActivity(inte1);
        	
        	break;
        case R.id.help:
        	Intent inte4 = new Intent(getActivity(), HelpActivity.class);
        	startActivity(inte4);
        	
        	break;
        
       case R.id.aboutb:
        	Intent inte2 = new Intent(getActivity(),  AboutPassManager.class);
        	startActivity(inte2);
        	
        	break;
        
        }
	 return super.onOptionsItemSelected(item);
	}
	

	
}


