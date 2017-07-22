package com.jerotoma.jpasswordmanager;

import java.util.Calendar;

public class CalenderGenerate extends AccountManagerActivity{
	
	public String timeElapsed(){
		  
           Calendar c        = Calendar.getInstance();
		   int seconds       = c.get(Calendar.SECOND);
	       int minutes       = c.get(Calendar.MINUTE);
	       int hour          = c.get(Calendar.HOUR);
	       
	       String time       = hour +":"+ minutes + ":"+ seconds;
	       
		   int day           = c.get(Calendar.DAY_OF_MONTH);
	       int monthOfYear   = c.get(Calendar.MONTH);
	       int year          = c.get(Calendar.YEAR);
	       
	       String month = null;
	       
	       switch(monthOfYear){
					case  1:
				   month = "January";
				   break;
			   case  2:
				   month = "February";
				   break;
			   case  3:
				   month = "March";
				   break;
			   case  4:
				   month = "April";
				   break;
			   case  5:
				   month = "May";
				   break;
			   case  6:
				   month = "June";
				   break;
			   case 7:
				   month = "July";
				   break;
			   case  8:
				   month = "August";
				   break;
			   case  9:
				   month = "September";
				   break;
			   case 10:
				   month = "October";
				   break;
			   case  11:
				   month = "November";
				   break;
			   case  12:
				   month = "December";
				   break;
			}
	       String date = month+" "+ day + ", "+year;
	       
	       String dateCreated = time+" "+date;
		
	      
	       return dateCreated;
		
	}

}
