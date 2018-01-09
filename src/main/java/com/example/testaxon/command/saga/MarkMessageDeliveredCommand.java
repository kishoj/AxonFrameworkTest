package com.example.testaxon.command.saga;

import java.util.UUID;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class MarkMessageDeliveredCommand {
	@TargetAggregateIdentifier
    private UUID messageId;
	
	public MarkMessageDeliveredCommand(UUID messageId) {
		this.messageId = messageId;
	}

	public UUID getMessageId() {
		return messageId;
	}

	@Override
	public String toString() {
		return "MarkMessageDeliveredCommand [messageId=" + messageId + "]";
	}	
	
}
