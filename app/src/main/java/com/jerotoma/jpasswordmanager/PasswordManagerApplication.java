package com.jerotoma.jpasswordmanager;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jerotoma.jpasswordmanager.accounts.AccountStore;
import com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper;
import com.jerotoma.jpasswordmanager.app.NewUserInfo;
import com.jerotoma.jpasswordmanager.confidencials.EventInfo;
import com.jerotoma.jpasswordmanager.confidencials.FinancialInfo;
import java.util.ArrayList;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.ACCOUNTS_TABLE;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.ACCOUNT_DATECREATED;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.ACCOUNT_DOMAIN;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.ACCOUNT_EMAIL;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.ACCOUNT_ID;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.ACCOUNT_PASSWORD;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.ACCOUNT_PHONE;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.ACCOUNT_USERNAME;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.AUTHENTICATE_TABLE;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.AUTH_ID;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.AUTH_PASSWORD;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.AUTH_USERNAME;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.DATE_CREATED;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.EVENT_DISCR;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.EVENT_END;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.EVENT_ID;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.EVENT_NAME;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.EVENT_SINGLE;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.EVENT_START;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.EVENT_TABLE;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.EVENT_TYPE;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.FINANCIAL_ANSWER1;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.FINANCIAL_ANSWER2;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.FINANCIAL_DATECREATED;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.FINANCIAL_ID;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.FINANCIAL_NAME;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.FINANCIAL_NUMBER;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.FINANCIAL_PASSWORD;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.FINANCIAL_SECURITY1;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.FINANCIAL_SECURITY2;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.FINANCIAL_TABLE;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.FINANCIAL_TYPE;
import static com.jerotoma.jpasswordmanager.accounts.AccountsSQLiteOpenHelper.FINANCIAL_VISA;


public class PasswordManagerApplication extends Application{
	
	private ArrayList<AccountStore> currentAccounts;
	private ArrayList<AccountStore> updateAccount;
	private ArrayList<NewUserInfo> newUserInfo;
	private ArrayList<FinancialInfo> financialList;
	private ArrayList<EventInfo> eventList;
	private SQLiteDatabase database;

    @Override
	public void onCreate(){
		super.onCreate();
		
		AccountsSQLiteOpenHelper helper = new AccountsSQLiteOpenHelper(this);
		database = helper.getWritableDatabase();
		
		
		
		
	  if(null == currentAccounts){
			loadAccounts();
		}
	  if(null == newUserInfo){
			loadNewUserInfo();
		}
	  if(null == financialList){
			 loadFinancial();
			}
	  if(null == eventList){
			 loadEvents();
		}
	   
	}
   
	private void loadAccounts() {
		
    	currentAccounts = new ArrayList<>();
        Cursor accountCursor = database.query(ACCOUNTS_TABLE, new String[]{ACCOUNT_ID, ACCOUNT_DOMAIN, ACCOUNT_EMAIL,
        		               ACCOUNT_USERNAME, ACCOUNT_PASSWORD, ACCOUNT_PHONE,  ACCOUNT_DATECREATED},
        		               null, null, null, null, String.format("%s,%s,%s,%s,%s,%s", ACCOUNT_DOMAIN, ACCOUNT_USERNAME,
                		               ACCOUNT_PASSWORD, ACCOUNT_EMAIL, ACCOUNT_PHONE, ACCOUNT_DATECREATED ));
        accountCursor.moveToFirst();
        
        AccountStore a;
        if(!accountCursor.isAfterLast()){
            do{
               long   acc_ID        = accountCursor.getLong(0);
               String account_name  = accountCursor.getString(1);
               String email_address = accountCursor.getString(2);
               String username      = accountCursor.getString(3);
           	   String password      = accountCursor.getString(4);
               String phone_number  = accountCursor.getString(5);
               String dateCreated   = accountCursor.getString(6);
           	 
                a = new AccountStore(account_name);
                a.setAcc_ID(acc_ID);
                a.setAccount_name(account_name);
                a.setEmail_address(email_address);
                a.setUsername(username);
                a.setPassword(password);
                a.setPhone_number(phone_number);
                a.setDates(dateCreated);
                currentAccounts.add(a);
              }while(accountCursor.moveToNext());
            
            }
        accountCursor.close();
        }
	public void setCurrentAccounts(ArrayList<AccountStore> currentAccounts){
	
	    this.currentAccounts = currentAccounts;
	
   }
   public ArrayList<AccountStore> getCurrentAccounts(){
	    return currentAccounts;
   }
    
  public void addAccount(AccountStore a){
	  assert(null != a);
	  ContentValues values = new  ContentValues();
	  values.put(ACCOUNT_DOMAIN, a.getAccount_name());
	  values.put(ACCOUNT_EMAIL, a.getEmail_address());
	  values.put(ACCOUNT_USERNAME, a.getUsername());
	  values.put(ACCOUNT_PASSWORD, a.getPassword());
	  values.put(ACCOUNT_PHONE, a.getPhone_number());
	  values.put( ACCOUNT_DATECREATED, a.getDates());
	  long id = database.insert(ACCOUNTS_TABLE, null, values);
	  a.setAcc_ID(id);
	  currentAccounts.add(a);
	  
	  }
    public void addNewUserInfo(NewUserInfo ne){
		 assert(null != ne);
		  ContentValues values = new ContentValues();
		  values.put(AUTH_USERNAME, ne.getNewUsername());
		  values.put(AUTH_PASSWORD, ne.getNewPassword());
		  long id = database.insert(AUTHENTICATE_TABLE, null, values);
		  ne.setNewUser_ID(id);
		  newUserInfo.add(ne);  
		  }
	    public ArrayList<NewUserInfo> getNewUserInfo(){
			return newUserInfo;
		}

		public void setNewUserInfo(ArrayList<NewUserInfo> newUserInfo) {
			this.newUserInfo = newUserInfo;
		}
	    
	private void loadNewUserInfo() {
				  	newUserInfo = new ArrayList<NewUserInfo>();
				  	Cursor newUserInfoCursor =database.query(AUTHENTICATE_TABLE, new String[]{AUTH_ID, AUTH_USERNAME,AUTH_PASSWORD},
				               null, null, null, null, String.format("%s,%s", AUTH_USERNAME,AUTH_PASSWORD));
				  	newUserInfoCursor.moveToFirst();
			NewUserInfo n; 
			if(!newUserInfoCursor.isAfterLast()){
			do{
			long   newUser_ID    = newUserInfoCursor.getLong(0);
			String newUsername   = newUserInfoCursor.getString(1);
			String newPassword   = newUserInfoCursor.getString(2);
			
			 n = new NewUserInfo();
			 n.setNewUser_ID(newUser_ID);
			 n.setNewUsername(newUsername);
			 n.setNewPassword(newPassword);
			 
			 newUserInfo.add(n);
		}while(newUserInfoCursor.moveToNext());
		}
		newUserInfoCursor.close();
		} 
    public void updateLoginsInfo(NewUserInfo nu){
    	assert(null!= nu );
    	ContentValues values = new  ContentValues();
    	       values.put(AUTH_USERNAME, nu.getNewUsername());
   	           values.put(AUTH_PASSWORD, nu.getNewPassword());
   	           long userId = nu.getNewUser_ID();
   	           String id = String.valueOf(userId);
   	           String whereArgs = AUTH_ID + " = " +id;
   	        long ids= database.update(AUTHENTICATE_TABLE, values,whereArgs, null); 
   	        
   	     NewUserInfo  ne;
   	     int i;
   	     for(i=0; i<getNewUserInfo().size(); i++){
   	    	 ne = getNewUserInfo().get(i);
   	    	 if(ne.getNewUser_ID()== userId ){
   	    		 newUserInfo.remove(i);
   	    		 nu.setNewUser_ID(ids);
   	    		 newUserInfo.add(i, nu);
   	    	 }
   	     }
    
    }

	public void AddUpdate(AccountStore upd) {
		
		assert(null != upd);
	    ContentValues values = new  ContentValues();
		  values.put(ACCOUNT_DOMAIN, upd.getAccount_name());
		  values.put(ACCOUNT_EMAIL, upd.getEmail_address() );
		  values.put(ACCOUNT_USERNAME, upd.getUsername() );
		  values.put(ACCOUNT_PASSWORD, upd.getPassword());
		  values.put(ACCOUNT_PHONE, upd.getPhone_number());
		  values.put(ACCOUNT_DATECREATED , upd.getDates());
		   long userId = upd.getAcc_ID();
		  String id = String.valueOf(userId);
		  
		  String whereArgs = ACCOUNT_ID + "=" + id;
		  
     long ids= database.update(ACCOUNTS_TABLE, values,whereArgs, null); 
     AccountStore a;
      int i;
      for(i=0; i<getCurrentAccounts().size(); i++){
    	  a = getCurrentAccounts().get(i);
      if(a.getAcc_ID()== userId){
       currentAccounts.remove(i); 
       upd.setAcc_ID(ids);
       currentAccounts.add(i,upd);
 	   }
      }
	}
	
	public ArrayList<AccountStore> getUpdateAccount() {
		return updateAccount;
	}

	public void setUpdateAccount(ArrayList<AccountStore> updateAccount) {
		this.updateAccount = updateAccount;
	}

	public void AddDeleteAccount( AccountStore de) {
		String id = String.valueOf(de.getAcc_ID());
		long userId = de.getAcc_ID();
		String whereArgs = ACCOUNT_ID + "=" + id;
	    database.delete(ACCOUNTS_TABLE, whereArgs, null);
	    //database.close();
	    AccountStore a;
	      int i;
	      for(i=0; i<getCurrentAccounts().size(); i++){
	    	  a = getCurrentAccounts().get(i);
	      if(a.getAcc_ID()== userId){
	       currentAccounts.remove(i); 
	      }
	      }
	}
	
	public void loadFinancial(){
	    
		financialList = new ArrayList<FinancialInfo>();
		
		Cursor financilaCursor = database.query(FINANCIAL_TABLE, new String[]{FINANCIAL_ID, FINANCIAL_TYPE, FINANCIAL_NAME,
        		               FINANCIAL_NUMBER, FINANCIAL_VISA, FINANCIAL_PASSWORD,  FINANCIAL_SECURITY1, FINANCIAL_SECURITY2,
        		               FINANCIAL_ANSWER1, FINANCIAL_ANSWER2, FINANCIAL_DATECREATED},
        		               null, null, null, null, String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", FINANCIAL_TYPE, FINANCIAL_NAME,
                		               FINANCIAL_NUMBER, FINANCIAL_VISA, FINANCIAL_PASSWORD,  FINANCIAL_SECURITY1, FINANCIAL_SECURITY2,
                		               FINANCIAL_ANSWER1, FINANCIAL_ANSWER2, FINANCIAL_DATECREATED));
        financilaCursor.moveToFirst();
        
        FinancialInfo financial; 
        //String info = null;
       if(!financilaCursor.isAfterLast()){
            do{
               long   fin_ID        = financilaCursor.getLong(0);
               String fin_type  = financilaCursor.getString(1);
               String fin_name = financilaCursor.getString(2);
               String fin_number      = financilaCursor.getString(3);
           	   String fin_visa      = financilaCursor.getString(4);
               String fin_pass  = financilaCursor.getString(5);
               String fin_sec1  = financilaCursor.getString(6);
               String fin_sec2 = financilaCursor.getString(7);
               String fin_answe1      = financilaCursor.getString(8);
           	   String fin_answe2     = financilaCursor.getString(9);
               String dateCreated   = financilaCursor.getString(10);
               
               
               financial = new FinancialInfo(fin_ID, fin_type, fin_name, fin_number, fin_visa, fin_pass, fin_sec1, fin_sec2, fin_answe1,fin_answe2, dateCreated);
               financial.setFinancial_id(fin_ID);
               financial.setFinancial_type(fin_type);
               financial.setFinancial_name(fin_name);
               financial.setFinancial_number(fin_number);
               financial.setFinancial_visa(fin_visa);
               financial.setFinancial_password(fin_pass);
               financial.setFinancial_security1(fin_sec1);
               financial.setFinancial_security2(fin_sec2);
               financial.setFinancial_answer1(fin_answe1);
               financial.setFinancial_answer2(fin_answe2);
               financial.setDate(dateCreated);
               
               
               financialList.add(financial);
               //info= " id : "+ fin_ID +" type :"+ fin_type +" name :"+ fin_name +" number :"+ fin_number+" visa :"+ fin_visa+" pass :"+ fin_pass +" sec1 :"+ fin_sec1 +" sec2 :"+ fin_sec2 +" ans1 :"+ fin_answe1+" ans2 :"+fin_answe2+" date created :"+ dateCreated;
            	        
              }while(financilaCursor.moveToNext());
            
            }
        financilaCursor.close();
        
	
	}
public long maxEventId(){
		
		Cursor cursor = database.rawQuery("SELECT MAX(" + EVENT_ID + ") FROM " + EVENT_TABLE, null);
		long id = 0;
		if (cursor != null) {
		    try {
		        if (cursor.moveToFirst() && !cursor.isNull(0)) {
		            id = cursor.getLong(0);
		        }
		    } finally {
		        cursor.close();
		    }
		}
     	return id;
   }
	public long maxFinancialId(){
		
		Cursor cursor = database.rawQuery("SELECT MAX(" + FINANCIAL_ID + ") FROM " + FINANCIAL_TABLE, null);
		long id = 0;
		if (cursor != null) {
		    try {
		        if (cursor.moveToFirst() && !cursor.isNull(0)) {
		            id = cursor.getLong(0);
		        }
		    } finally {
		        cursor.close();
		    }
		}
     	return id;
	}
   public void AddFinancial(FinancialInfo financial){
	   
	   assert(null != financial);
	   ContentValues values = new  ContentValues();
	   values.put(FINANCIAL_TYPE, financial.getFinancial_type());
	   values.put(FINANCIAL_NAME, financial.getFinancial_name());
	   values.put(FINANCIAL_NUMBER, financial.getFinancial_number());
	   values.put(FINANCIAL_VISA, financial.getFinancial_visa());
	   values.put(FINANCIAL_PASSWORD, financial.getFinancial_password());
	   values.put(FINANCIAL_SECURITY1, financial.getFinancial_security1());
	   values.put(FINANCIAL_SECURITY2, financial.getFinancial_security2());
	   values.put(FINANCIAL_ANSWER1, financial.getFinancial_answer1());
	   values.put(FINANCIAL_ANSWER2, financial.getFinancial_answer2());
	   values.put(FINANCIAL_DATECREATED , financial.getDate());
	   
	   
	  
       long id = database.insert(FINANCIAL_TABLE, null, values);
	   financial.setFinancial_id(id);
	   financialList.add(financial);
       
	}
    public ArrayList<FinancialInfo> getFinancialList() {
		return financialList;
	}

	public void setFinancialList(ArrayList<FinancialInfo> financialList) {
		this.financialList = financialList;
	}
   public void updateFinancial(FinancialInfo fInfo){
	   assert(null != fInfo);
	   ContentValues values = new  ContentValues();
	   values.put(FINANCIAL_TYPE, fInfo.getFinancial_type());
	   values.put(FINANCIAL_NAME, fInfo.getFinancial_name());
	   values.put(FINANCIAL_NUMBER, fInfo.getFinancial_number());
	   values.put(FINANCIAL_VISA, fInfo.getFinancial_visa());
	   values.put(FINANCIAL_PASSWORD, fInfo.getFinancial_password());
	   values.put(FINANCIAL_SECURITY1, fInfo.getFinancial_security1());
	   values.put(FINANCIAL_SECURITY2, fInfo.getFinancial_security2());
	   values.put(FINANCIAL_ANSWER1, fInfo.getFinancial_answer1());
	   values.put(FINANCIAL_ANSWER2, fInfo.getFinancial_answer2());
	   values.put(FINANCIAL_DATECREATED , fInfo.getDate());
	   
	   long ids  =  fInfo.getFinancial_id();
	   String IDs = String.valueOf(ids);
	   String whereargs = FINANCIAL_ID +"="+ IDs;
	  
       long id = database.update(FINANCIAL_TABLE, values, whereargs, null);
	  FinancialInfo f;
	  int i;
	  
	  for(i=0; i<getFinancialList().size(); i++){
		  
		  f = getFinancialList().get(i);
		 if(ids == f.getFinancial_id()){
			 
			 financialList.remove(i);
			 fInfo.setFinancial_id(id);
			 financialList.add(i, fInfo);
			 
		 } 
		  
	  }
		
  	}
   public void deleteFinancial(long id){
		String fID = String.valueOf(id);
		String whereArgs = FINANCIAL_ID +"="+ fID;
		database.delete(FINANCIAL_TABLE, whereArgs, null );
		
		FinancialInfo f = null;
		int i;
		for(i=0; i<getFinancialList().size(); i++){
			
			f = getFinancialList().get(i);
			if(f.getFinancial_id()== id){
				financialList.remove(i);
			}
		}
  	}
   public void loadEvents(){
	   eventList     = new ArrayList<EventInfo>();
	   
	   Cursor cu  = database.query(EVENT_TABLE, new String[] {EVENT_ID, EVENT_TYPE, EVENT_NAME, EVENT_SINGLE,
			                 EVENT_START, EVENT_END, EVENT_DISCR,DATE_CREATED}, null, null, null, null, String.format("%s,%s,%s,%s, %s,%s,%S",
					         EVENT_TYPE, EVENT_NAME, EVENT_SINGLE, EVENT_START, EVENT_END, EVENT_DISCR, DATE_CREATED));
       EventInfo eInfo;
       cu.moveToFirst();
	   if(!cu.isAfterLast()){
		   do{
			    long id          = cu.getLong(0);         
				String eventType = cu.getString(1);
				String eName     = cu.getString(2);
				String sEvent    = cu.getString(3);
				String startEv   = cu.getString(4);
				String endEv     = cu.getString(5);
				String discr     = cu.getString(6);
				String dateCreated  = cu.getString(7);
				eInfo = new EventInfo(id, eventType, eName, sEvent, startEv, endEv, discr, dateCreated);
				eventList.add(eInfo);
			   
		   }while(cu.moveToNext());
		  
	   }
	   cu.close();
   }

   public void addEventInfo(EventInfo eInfo) {
	    assert(null != eInfo);
	  
      ContentValues values   = new ContentValues();
            values.put(EVENT_TYPE, eInfo.getEventType());
            values.put(EVENT_NAME, eInfo.getEventName());
            values.put(EVENT_SINGLE, eInfo.getSingleEvent());
            values.put(EVENT_START, eInfo.getStartEvent());
            values.put(EVENT_END, eInfo.getEndEvent());
            values.put(EVENT_DISCR, eInfo.getDiscription());
            values.put(DATE_CREATED, eInfo.getDateCreated());
           
            long id = database.insert(EVENT_TABLE, null, values);
            
            eInfo.setEventId(id);
            eventList.add(eInfo);
   }

	public ArrayList<EventInfo> getEventList() {
		return eventList;
	}

	public void setEventList(ArrayList<EventInfo> eventList) {
		this.eventList = eventList;
	}

	public void updateEventInfo(EventInfo eInfo) {
		assert(null != eInfo);
		 ContentValues values   = new ContentValues();
		 values.put(EVENT_TYPE, eInfo.getEventType());
         values.put(EVENT_NAME, eInfo.getEventName());
         values.put(EVENT_SINGLE, eInfo.getSingleEvent());
         values.put(EVENT_START, eInfo.getStartEvent());
         values.put(EVENT_END, eInfo.getEndEvent());
         values.put(EVENT_DISCR, eInfo.getDiscription());
         values.put(DATE_CREATED, eInfo.getDateCreated());
          long eId   = eInfo.getEventId();
         String id = String.valueOf(eId);
		  
		 String whereArgs = EVENT_ID + "=" + id;
         long eID = database.update(EVENT_TABLE, values, whereArgs,null);
         
         EventInfo e = null;
           int i;
          for(i=0; i<getEventList().size(); i++){
        	  e = getEventList().get(i);
            if(e.getEventId() == eId){
              eventList.remove(i);
              eInfo.setEventId(eID);
              eventList.add(i, eInfo);
              
            }
        	  
          }
         
		
		
	}

	public void deleteEvent(long id) {
		
		String whereArgs = EVENT_ID + "=" + id;
				
	    database.delete(EVENT_TABLE, whereArgs, null);
	       
		EventInfo e = null;
	      int i;
	      for(i=0; i<getEventList().size(); i++){
	    	  
	    	  e = getEventList().get(i);
	     
	    	if(e.getEventId() == id){
	    	 eventList.remove(i); 
		  }
	      }
	}
	
  
}
	
