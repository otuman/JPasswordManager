package com.jerotoma.jpasswordmanager.confidencials;

public class FinancialListViewItem {
	private int id;
	private String name;
	private String type;
	private String date;
    private long financialId;   
        
    public FinancialListViewItem(int id,long financialId, String type, String name, String date) {
		this.name         = name;
		this.type         = type;
		this.date         = date;
		this.id           = id;
		this.financialId  = financialId;
	}
    
   
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getFinancialId() {
		return financialId;
	}


	public void setFinancialId(long financialId) {
		this.financialId = financialId;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

