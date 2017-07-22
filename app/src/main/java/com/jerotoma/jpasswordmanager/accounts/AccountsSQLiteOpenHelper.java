package com.jerotoma.jpasswordmanager.accounts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountsSQLiteOpenHelper extends SQLiteOpenHelper {
    

	public static final String DB_NAME             = "accounts_db.sqlite";
	public static final int VERSION                = 1;
	public static final String ACCOUNTS_TABLE      = "accounts";
	public static final String ACCOUNT_DATECREATED = "dateCreated";
	public static final String ACCOUNT_ID          = "ACC_ID";
	public static final String ACCOUNT_DOMAIN      = "Domain_Name";
	public static final String ACCOUNT_EMAIL       = "email_address";
	public static final String ACCOUNT_USERNAME    = "username";
	public static final String ACCOUNT_PASSWORD    = "password";
	public static final String ACCOUNT_PHONE       = "phone";
	public static final String CREATE_TABLE_1      = "CREATE TABLE "
	                                            + ACCOUNTS_TABLE +"("
			                                    + ACCOUNT_ID +" integer primary key autoincrement not null, "
	                                            + ACCOUNT_DOMAIN +" text, "
	                                            + ACCOUNT_EMAIL +" text, "
	                                            + ACCOUNT_USERNAME +" text, "
	                                            + ACCOUNT_PASSWORD +"  text, "
	                                            + ACCOUNT_PHONE + " text, "
	                                            +  ACCOUNT_DATECREATED +"  text "+")";
	
	public static final String AUTHENTICATE_TABLE = "authenticate";
	public static final String AUTH_ID            =  "auth_ID";
	public static final String AUTH_USERNAME      =  "username";
	public static final String AUTH_PASSWORD      =  "password";
	public static final String CREATE_TABLE_2     = "CREATE TABLE "
			                                      + AUTHENTICATE_TABLE + "("
			                                      + AUTH_ID +" integer primary key autoincrement not null, "
			                                      + AUTH_USERNAME +" text, "
			                                      + AUTH_PASSWORD +" text "+")"; 
	
	
	
	public static final String FINANCIAL_TABLE        = "financial";
	public static final String FINANCIAL_DATECREATED  = "dateCreated";
	public static final String FINANCIAL_ID           = "financial_Id";
	public static final String FINANCIAL_TYPE         = "financial_type";
	public static final String FINANCIAL_NAME         = "financial_name";
	public static final String FINANCIAL_PASSWORD     = "financial_password";
	public static final String FINANCIAL_NUMBER       = "finacial_number";
	public static final String FINANCIAL_VISA         = "finacial_visa";
	public static final String FINANCIAL_SECURITY1    = "securityQ1";
	public static final String FINANCIAL_ANSWER1      = "answerQ1";
	public static final String FINANCIAL_SECURITY2    = "securityQ2";
	public static final String FINANCIAL_ANSWER2      = "answerQ2";
	public static final String CREATE_TABLE_3         = "CREATE TABLE "
														+ FINANCIAL_TABLE +"("
														+ FINANCIAL_ID  +" integer primary key autoincrement not null, "
														+ FINANCIAL_TYPE +" text, "
														+ FINANCIAL_NAME  +" text, "
														+ FINANCIAL_NUMBER +"  text, "
														+ FINANCIAL_VISA  + " text, "
														+ FINANCIAL_PASSWORD +" text, "
														+ FINANCIAL_SECURITY1 +" text, "
														+ FINANCIAL_ANSWER1  +" text, "
														+ FINANCIAL_SECURITY2  +" text, "
														+ FINANCIAL_ANSWER2 +"  text, "
														+ FINANCIAL_DATECREATED +"  text "+")";
	                                           
	
	public static final String EVENT_TABLE      = "events";
	public static final String EVENT_ID         = "eId";
	public static final String EVENT_TYPE       = "eventType";
	public static final String EVENT_NAME       = "eName";
	public static final String EVENT_SINGLE     = "eSingle";
	public static final String EVENT_START      = "eStart";
	public static final String EVENT_END        = "eEnd";
	public static final String EVENT_DISCR      = "eDiscription";
	public static final String DATE_CREATED        = "dateCreated";
	public static final String CREATE_TABLE_4   = "CREATE TABLE "
													+ EVENT_TABLE  +"("
													+ EVENT_ID     +" integer primary key autoincrement not null, "
													+ EVENT_TYPE   +" text, "
													+ EVENT_NAME   +" text, "
													+ EVENT_SINGLE +" text, "
													+ EVENT_START  +"  text, "
													+ EVENT_END    + " text, "
													+ EVENT_DISCR  +"  text, "
													+ DATE_CREATED    +"  text "+")";
	

	public AccountsSQLiteOpenHelper(Context context){
		super(context, DB_NAME, null, VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		createTable1(db);	
		createTable2(db);
		createTable3(db);
		createTable4(db);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
	    db.execSQL("DROP TABLE IF EXISTS " + AUTHENTICATE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FINANCIAL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE);
        onCreate(db);
	}
	private void createTable1(SQLiteDatabase db){
		 db.execSQL(CREATE_TABLE_1);
		}
	private void createTable2(SQLiteDatabase db){
		 db.execSQL(CREATE_TABLE_2);
		 }
	private void createTable3(SQLiteDatabase db){
		 db.execSQL(CREATE_TABLE_3);
		 }
	private void createTable4(SQLiteDatabase db){
		 db.execSQL(CREATE_TABLE_4);
		 }
}
