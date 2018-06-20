package com.example.testaxon.command;

import java.util.UUID;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class UpdateMessageCommand {
	@TargetAggregateIdentifier
	private UUID messageId;
	private String message;	
	
	private String sender;
	
	public UpdateMessageCommand(UUID messageId, String message, String sender) {
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
