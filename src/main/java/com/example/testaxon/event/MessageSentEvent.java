package com.example.testaxon.event;

import java.util.UUID;

import org.axonframework.serialization.Revision;

import com.example.testaxon.config.replay.Replayable;

@Replayable
@Revision("2.0")
public class MessageSentEvent {
	// For Revision 1.0
	private UUID messageId;
	
	private String message;
	
	// For Revision 2.0
	private String sender;

	public MessageSentEvent(UUID messageId, String message, String sender) {
		this.messageId = messageId;
		this.message = message;
		
		this.sender = sender;
	}

	public UUID getMessageId() {
		return messageId;
	}

	public String getMessage() {
		return message;
	}

	public String getSender() {
		return sender;
	}
	
}