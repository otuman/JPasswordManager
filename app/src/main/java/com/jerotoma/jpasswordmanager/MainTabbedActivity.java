package com.jerotoma.jpasswordmanager;

import com.jerotoma.jpasswordmanager.app.AddAccountInfo;
import com.jerotoma.jpasswordmanager.confidencials.EventInfoGuiActivity;
import com.jerotoma.jpasswordmanager.confidencials.FinancialInfoGui;
import com.jerotoma.jpasswordmanager.tabbed.SlidingTabLayout;
import com.jerotoma.jpasswordmanager.tabbed.ViewPagerAdapter;
import android.support.v4.view.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
 
 

public class MainTabbedActivity extends AppCompatActivity{
 
    // Declaring Your View and Variables
 
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"MyAccounts","Financials","Events"};
    int Numboftabs =3;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tabbed_activity);
 
 
        // Creating The Tool bar and setting it as the Tool bar for the activity
 
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
 
        

 
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles for the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles, Numboftabs);
 
        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
 
        // Assigning the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
 
        // Setting Custom color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
 
        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
 
 
 
    }
    
    public void fabClicked(View view) {
    	
    	switch(view.getId()){
    	
    	 case R.id.fabbutton:
    	   Intent intent2 = new Intent(this, AddAccountInfo.class);
      	   startActivity(intent2);
      	   break;   
      	   
    	case R.id.addfinancialbutton:
    	   Intent intent = new Intent(this, FinancialInfoGui.class);
   	       startActivity(intent);
   	       break;
   	       
    	case R.id.addeventbutton:
     	   Intent intent1 = new Intent(this, EventInfoGuiActivity.class);
    	       startActivity(intent1);
    	    break;
     	}
   	    
    }
 
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
 
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
 
        return super.onOptionsItemSelected(item);
    }
}