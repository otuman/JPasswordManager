package com.jerotoma.jpasswordmanager.accounts;


public class AccountStore {
	
	private String account_name;
	private String email_address;
	private String password;
	private String phone_number;
	private String username;
	private String dates;
	private long acc_ID;

	public String getDates() {
		return dates;
	}
	public void setDates(String user) {
		this.dates = user;
	}
	public AccountStore(String account_name){
    	this.account_name = account_name;
		
	}
	
	public void setAccount_name(String account_name){
		this.account_name = account_name;
	}
	public String getAccount_name(){
		return account_name;
	}
  
    public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	@Override
	public String toString(){
		   return account_name;
	 }
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public void setAcc_ID(long acc_ID) {
		this.acc_ID = acc_ID;
		
	}
	public long getAcc_ID(){
		return acc_ID;
	} 
	
	
	
}