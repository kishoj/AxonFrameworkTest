package com.example.testaxon.command.saga;

import java.util.UUID;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class MarkMessageTransferFailedCommand {
	@TargetAggregateIdentifier
	private UUID messageId;
	
	public MarkMessageTransferFailedCommand(UUID messageId) {
		this.messageId = messageId;
	}

	public UUID getMessageId() {
		return messageId;
	}

	@Override
	public String toString() {
		return "MarkMessageTransferFailedCommand [messageId=" + messageId + "]";
	}
	
}
