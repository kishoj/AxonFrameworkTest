package com.example.testaxon.queryobject;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MessageQueryObject {
	
	@Id
	private UUID id;
	private String value;
	
	private String sender;
	
	MessageQueryObject() { } 
	
	public MessageQueryObject(UUID id, String value, String sender) {
		this.id = id;
		this.value = value;
		
		this.sender = sender;
	}
	
	public UUID getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public String getSender() {
		return sender;
	}	
	
}
