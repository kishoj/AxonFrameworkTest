package com.example.testaxon.event;

import java.util.UUID;

import org.axonframework.serialization.Revision;

@Revision("1.0")
public class MessageUpdatedEvent {
	private UUID messageId;

	private String message;

	private String sender;
	
	public MessageUpdatedEvent(UUID messageId, String message, String sender) {
		this.messageId = messageId;
		this.message = message;
		
		this.sender = sender;
	}
	
	public String getSender() {
		return sender;
	}

	public UUID getMessageId() {
		return messageId;
	}

	public String getMessage() {
		return message;
	}
}
