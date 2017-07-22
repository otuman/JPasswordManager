package com.jerotoma.jpasswordmanager.tabbed;

import com.jerotoma.jpasswordmanager.PasswordManagerApplication;
import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.app.AboutPassManager;
import com.jerotoma.jpasswordmanager.app.HelpActivity;
import com.jerotoma.jpasswordmanager.app.PasswordGenerator;
import com.jerotoma.jpasswordmanager.confidencials.FinancialCustomeAdapter;
import com.jerotoma.jpasswordmanager.confidencials.FinancialInfo;
import com.jerotoma.jpasswordmanager.confidencials.FinancialInfoDisplay;
import com.jerotoma.jpasswordmanager.confidencials.FinancialInfoGui;
import com.jerotoma.jpasswordmanager.update.DialogInfo;
import com.jerotoma.jpasswordmanager.update.UpdateFinancialInfoGui;

import java.util.ArrayList;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
 
/**
 * Created by hp1 on 21-01-2015.
 */
public class HomeFinancial extends Fragment{
		
	private ListView financial_listView;
	private FinancialCustomeAdapter adapter;
	FloatingActionButton mFab;
	private AlertDialog emptyField;
    private AlertDialog deleteAlert;
	private  PasswordManagerApplication appFIN;
	public static final String FIN_TYPE  = "type";
	public static final String FIN_ID  = "financialId";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
	}
    
    @Override
	public void onStart() {
		 super.onStart();
		 initListViewComponent();
		 setUpView();
	}
    @Override
	public  void onResume(){
	       super.onResume();
	       adapter.forceReload();
	       
	    }
    
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.home_financial, container,false);
        return v;
    }
	private void initListViewComponent(){
		mFab = (FloatingActionButton) getView().findViewById(R.id.addfinancialbutton);
		financial_listView = (ListView)getView().findViewById(R.id.financial_listView);
		appFIN = (PasswordManagerApplication)getActivity().getApplication();
     }
    private void setUpView(){
		final ArrayList<FinancialInfo> financialList =  appFIN.getFinancialList();
		 
		 
		 		    		        
       	   
	        adapter = new FinancialCustomeAdapter(financialList, getActivity());
	        financial_listView.setAdapter(adapter);
	        
	        //mFab.listenTo(financial_listView);
	        financial_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            @SuppressWarnings("rawtypes")
				@Override
	            public void onItemClick(AdapterView adapterView, View view, int position, long l) {
	            	
	                FinancialInfo finInfo = adapter.getItem(position);
	                Intent intent = new Intent(getActivity(), FinancialInfoDisplay.class);
	            	intent.putExtra(FIN_TYPE, finInfo.getFinancial_type());
	            	intent.putExtra(FIN_ID, String.valueOf(finInfo.getFinancial_id()));
	            	startActivity(intent);
	            	
	            }
	        });
	   
		 financial_listView.setOnItemLongClickListener(new OnItemLongClickListener(){
				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
					DialogInfo infoDialog = new DialogInfo();
					final String[] maintain=infoDialog.getDialogString();
					final FinancialInfo fInfo = adapter.getItem(position);
					ArrayAdapter<String> dialogAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_dailog_info, R.id.dialog_view, infoDialog.getDialogString());
						emptyField = new AlertDialog.Builder(getActivity())
			               .setAdapter(dialogAdapter, new AlertDialog.OnClickListener(){
			                @Override
							public void onClick(DialogInterface dialog, int which){
			                	
			                	if(maintain[which]=="Delete"){
			                	 
			                deleteAlert = new AlertDialog.Builder(getActivity())
			                		    .setMessage("Are you sure you want to delete account")
			                		    .setPositiveButton("Yes", new AlertDialog.OnClickListener() {
			                             @Override
										public void onClick(DialogInterface dialog, int which) {
			                            	 
			                            	 deleteFinancial(fInfo.getFinancial_id());
			                                       	
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
			                		
			                	}
			                	if(maintain[which]=="Edit"){
			                		
			                		
			                		Intent intent = new Intent(getActivity(), UpdateFinancialInfoGui.class);
			                		intent.putExtra(FIN_ID, String.valueOf(fInfo.getFinancial_id()));
			                		intent.putExtra(FIN_TYPE, fInfo.getFinancial_type());
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
	private void  deleteFinancial(long id){
		appFIN.deleteFinancial(id);
        CallThisByItSelf();
         
    }
    public void CallThisByItSelf(){
		
    	HomeFinancial fFrag = new HomeFinancial();
    	FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction  transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.financial_content_frame, fFrag);
		transaction.addToBackStack(null);
		transaction.commit();
	}
    
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		inflater.inflate(R.menu.financial, menu);
	}
        
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
        case R.id.generatepass:
        	Intent inte = new Intent(getActivity(), PasswordGenerator.class);
        	startActivity(inte);
        	break;
        
        case R.id.help:
        	Intent inte1 = new Intent(getActivity(),  HelpActivity.class);
        	startActivity(inte1);
        	
        	break;
        	 
        case R.id.financial:
        	Intent inte3 = new Intent(getActivity(),  FinancialInfoGui.class);
        	startActivity(inte3);
        	
        	break;
        case R.id.aboutb:
        	Intent inte2 = new Intent(getActivity(),  AboutPassManager.class);
        	startActivity(inte2);
        	
        	break;
        
        }

		return super.onOptionsItemSelected(item);
	}
	

	
}


