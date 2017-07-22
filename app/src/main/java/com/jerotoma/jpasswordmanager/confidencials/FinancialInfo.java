package com.jerotoma.jpasswordmanager.confidencials;

public class FinancialInfo {
	
	private String financial_name;
	private String financial_type;
	private String financial_number;
	private String financial_visa;
	private long financial_id;
	private String financial_security1;
	private String financial_security2;
	private String financial_answer1;
	private String financial_answer2;
	private String financial_password;
	private String date;
	
	
	
	public FinancialInfo(long financial_id,String finacial_type, String financial_name, String financial_number, 
			String financial_visa,String financial_password, String financial_security1, String financial_security2, 
			String financial_answer1, String financial_answer2, String date){ 
		
		this.financial_name       = financial_name;
		this.financial_type       = finacial_type;
		this.financial_number     = financial_number;
		this.financial_visa       = financial_visa;
		this.financial_answer1    = financial_answer1;
		this.financial_answer2    = financial_answer2;
		this.financial_security1  = financial_security1;
		this.financial_security2  = financial_security2;
		this.financial_password   = financial_password;
		this.date                 = date;
		this.financial_id         = financial_id;
		
		
		
	}
  	
	

	public String getFinancial_name() {
		return financial_name;
	}
	public void setFinancial_name(String financial_name) {
		this.financial_name = financial_name;
	}
	public String getFinancial_type() {
		return financial_type;
	}
	public void setFinancial_type(String financial_type) {
		this.financial_type = financial_type;
	}
	public String getFinancial_number() {
		return financial_number;
	}
	public void setFinancial_number(String financial_number) {
		this.financial_number = financial_number;
	}
	public String getFinancial_visa() {
		return financial_visa;
	}
	public void setFinancial_visa(String financial_visa) {
		this.financial_visa = financial_visa;
	}
	public long getFinancial_id() {
		return financial_id;
	}
	public void setFinancial_id(long financial_id) {
		this.financial_id = financial_id;
	}
	public String getFinancial_security1() {
		return financial_security1;
	}
	public void setFinancial_security1(String financial_security1) {
		this.financial_security1 = financial_security1;
	}
	public String getFinancial_security2() {
		return financial_security2;
	}
	public void setFinancial_security2(String financial_security2) {
		this.financial_security2 = financial_security2;
	}
	public String getFinancial_answer1() {
		return financial_answer1;
	}
	public void setFinancial_answer1(String financial_answer1) {
		this.financial_answer1 = financial_answer1;
	}
	public String getFinancial_answer2() {
		return financial_answer2;
	}
	public void setFinancial_answer2(String financial_answer2) {
		this.financial_answer2 = financial_answer2;
	}
	public String getFinancial_password() {
		return financial_password;
	}
	public void setFinancial_password(String financial_password) {
		this.financial_password = financial_password;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
