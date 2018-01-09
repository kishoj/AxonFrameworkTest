package com.example.testaxon.command;

import java.util.UUID;

public class SendMessageCommand {
	
	private UUID messageId;
	private String message;
	
	private String sender;

	public SendMessageCommand(UUID messageId, String message, String sender) {
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
