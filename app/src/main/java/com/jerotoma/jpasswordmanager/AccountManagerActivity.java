package com.jerotoma.jpasswordmanager;


import android.support.v7.app.AppCompatActivity;

public class AccountManagerActivity extends AppCompatActivity {
	
	protected PasswordManagerApplication getPasswordManagerApplication () {
		PasswordManagerApplication  tma = (PasswordManagerApplication)getApplication();
	    return tma;
	 }

}