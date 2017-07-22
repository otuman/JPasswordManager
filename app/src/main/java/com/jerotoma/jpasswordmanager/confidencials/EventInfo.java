package com.jerotoma.jpasswordmanager.confidencials;

public class EventInfo {
	private String eventType;
	private String eventName;
	private String singleEvent;
	private String startEvent;
	private String endEvent;
	private String discription;
	private long eventId;
	private String dateCreated;

	public EventInfo(long id, String eventType, String eName, String sEvent,
			String startEv, String endEv, String discr, String dateCreated) {
		this.eventId          = id;
		this.eventType   = eventType;
		this.eventName   = eName;
		this.singleEvent = sEvent;
		this.startEvent  = startEv;
		this.endEvent    = endEv;
		this.discription = discr;
		this.dateCreated    =  dateCreated;
		
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getSingleEvent() {
		return singleEvent;
	}

	public void setSingleEvent(String singleEvent) {
		this.singleEvent = singleEvent;
	}

	public String getStartEvent() {
		return startEvent;
	}

	public void setStartEvent(String startEvent) {
		this.startEvent = startEvent;
	}

	public String getEndEvent() {
		return endEvent;
	}

	public void setEndEvent(String endEvent) {
		this.endEvent = endEvent;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}
   public long getEventId(){
	return eventId;
	   
   }
	public void setEventId(long id) {
		
		
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}


}
