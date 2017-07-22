package com.jerotoma.jpasswordmanager.app;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import com.jerotoma.jpasswordmanager.AccountManagerActivity;
import com.jerotoma.jpasswordmanager.R;

public class HelpActivity extends AccountManagerActivity{
	
	private Toolbar toolbar;
	private WebView helptips;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help_activity);
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
		toolbar.setTitle("Event Information");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setUpView();
	}
	private void setUpView() {
		helptips         = (WebView)findViewById(R.id.helptips);
		String helpInfo  = "<h2 style ="+" "+">"+"Help Tips"+"</h2>";
		String helpInfo1 = "<ul><li>"+"<h3>"+"New user"+"</h3>"+"</li></ul>";
		String helpInfo11 = "For a new user, please sign up first in order to use this app. It is easy and quick to do it. On the login page "
				          + "click on the 'new user' button to the bottom right, then you will be prompted to type in the required information."
				          + " You should be able to login and manage you password after signing up";
		
		String helpInfo2 = "<ul><li>"+"<h3>"+"Adding new account info"+"</h3>"+"</li></ul>";
		String helpInfo22 ="After logging in, there are three tabs these include MYACCOUNT, FINANCIALS, and EVENTS. "
				          + "To add new account click on the floating button with plus sign on it, then it will take you to the activity that will require you to enter information to add your accont information";
		
		String helpInfo3 = "<ul><li>"+"<h3>"+"Adding new financial Info"+"</h3>"+"</li></ul>";
		String helpInfo33 = "To add financial information, please swip to the left, or click on the FINANCIALS tab, the floating add financial information button will appear. Click on the floating button."
				          + "This will provide you with the options to add a financial information of your choices. Fill in the required information as you prompted by add financial information section activiy";
		
		String helpInfo4 = "<ul><li>"+"<h3>"+"Adding new event info"+"</h3>"+"</li></ul>";
		String helpInfo44 = "To add event information, please swip two times to the left, or click on the EVENTS tab, the floating add event information button will appear. Click on the floating button."
		          + "This will provide you with the options to add a event information of your choices. Fill in the required information as you prompted by add event information section activiy";

		
		String helpInfo5 = "<ul><li>"+"<h3>"+"Edit, Delete"+"</h3>"+"</li></ul>";
		String helpInfo55 ="There are two options to Edit or Delete the specific account, events, or financials information."
				          + "<ol><li>On a listview press and hold, the dialog will popup with the Edit, Delete, and About manager options. Select your disirable choice to perform a specific task.</li> "
				          + "<li>On a list view click and then on the action menu bar the Edit, Delete, and About manager are options. Click on the option of your choice to perform a specific task.</li></ol>";
		
		String helpInfo6 = "<ul><li>"+"<h3>"+"Generate Password"+"</h3>"+"</li></ul>";
		String helpInfo66 ="When you add Account or Financial you have the choice of using JEROTOMA tool to generate password. "
				+ "It is a nice tool for generating complex password, that would be hard for the hacker to guess it."
				+ "Use the generate password tool to generate the password of you choice, then click on the generated password to copy and paste to a specific password field";
		
		String contactus = "<ul><li>"+"<h3>"+"Get Help"+"</h3>"+"</li></ul>";
		String contactus1 = "JEROTOMA ICT LTD<br>"
							 +" P.O.BOX 22368,<br>"
							 +" DAR ES SALAAM,<br>"
							 +" Mwl. Nyerere Pension Towers,<br>"
							 +"Phone: +1 289-775-1667,<br> "
							 +"Email: softwaredevelopers@jerotomaict.co.tz<br>"
							 +"Website: www.jerotomaict.co.tz";
		
		String htmlInfo = "<html><body style = "+"background-color:lightgrey;"+">"
		        +  helpInfo + helpInfo1 + helpInfo11 + helpInfo2 
		        +  helpInfo22 + helpInfo3 + helpInfo33 + helpInfo4 + helpInfo44 + helpInfo5 + helpInfo55
				+  helpInfo6+ helpInfo66 + contactus + contactus1 +"</body></html>";
		
		helptips.loadData(htmlInfo, "text/html", "utf-8");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.helpactivity, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		int id = item.getItemId();
	switch(id){
	  case R.id.about:
		    return true;
	   case android.R.id.home:
		    this.finish();
		    return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
		
}
