package com.jerotoma.jpasswordmanager.confidencials;

public class EventListViewItem {
	private int id;
	private String name;
	private String type;
	private String date;
	private long eventId;
        
        
    public EventListViewItem(int id, long eventId, String type, String name, String date) {
		this.name     = name;
		this.type     = type;
		this.date     = date;
		this.id       = id;
		this.eventId  = eventId;
	}
    
   
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
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

