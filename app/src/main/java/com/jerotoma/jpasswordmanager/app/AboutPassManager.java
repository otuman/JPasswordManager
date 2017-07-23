package com.jerotoma.jpasswordmanager.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.support.v7.widget.Toolbar;

import com.jerotoma.jpasswordmanager.AccountManagerActivity;
import com.jerotoma.jpasswordmanager.R;

public class AboutPassManager extends AccountManagerActivity {
	private WebView myView;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.about_manager);
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_launcher);
		if(getSupportActionBar() != null){
		  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		setUpView();
	}

	private void setUpView() {

		String aboutManager = "<ul><li>Jerotoma Password Manager (JOMPAMA) is an android application developed by JEROTOMA ICT COMPANY LTD. The company is resided in the city of Dar es Salaam, Tanzania.</li></ul>"
				              + "<ul><li>JOMPAMA application provides a secure way of storing passwords,PINs as well as other confidential information </li></ul>" 
				              +"<ul><li>Ideally password should be unique for every service we use that requires password or PIN "
				              + " and it should probably be as complex as it can be just to insure security caution is implemented.</li></ul>";
       String aboutManager1 =  "<ul><li>JOMPAMA has the ability to generate a complex password for you and It provides users with the ability to copy the password or PIN of their choice and paste at specified field, then save the it in this secure application "
				              + "</li></ul> <ul><li>JOMPAMA gives users ability to store their  confidencial event in this secure application, and then it will remind you if you tell it to do so."
				              + "JOMPAMA is not online application based it stores all information lacally on your phone, which makes more secure than storing infomation online."
				              + "</li></ul>" ;
	   String aboutManager2	= "<ul><li>Forsure, if your disire is to store password or PIN lacally then JOMPAMA is the better choice for you. It is a solid password manager "
				              + "that is the most pain-free and secure method for stepping up your security on Android.</li></ul>"
				              + "<ul><li>While other competitors have good solutions by storing data online, JOMPAMA focuses only in storing data locally so that hackers would have very small pacentage of hacking your passwords and PIN as well as confidential information  "
				              + ".</li></ul> ";
	
	          myView   = (WebView)findViewById(R.id.about_manager);
	   String htmlText = "<html><body style = "+"background-color:lightgrey;" + ">" + aboutManager + aboutManager1 +  aboutManager2 + " </body></html>";
		
		myView.loadData(String.format(htmlText , aboutManager), "text/html", "utf-8");
		
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
