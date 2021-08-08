package com.example.hr.eventsource;

public abstract class EmployeeEvent {
	protected String eventId;
	protected String identity;
	protected String conversationId;

	public EmployeeEvent() {
	}

	public EmployeeEvent(String eventId, String identity, String conversationId) {
		this.eventId = eventId;
		this.identity = identity;
		this.conversationId = conversationId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

}
