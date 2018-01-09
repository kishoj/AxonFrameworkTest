package com.example.testaxon.event.saga;

import java.util.UUID;

public class MessageDeliveredMarkedEvent {
	private UUID messageId;
	
	public MessageDeliveredMarkedEvent(UUID messageId) {
		this.messageId = messageId;
	}

	public UUID getMessageId() {
		return messageId;
	}

	@Override
	public String toString() {
		return "MessageDeliveredMarkedEvent [messageId=" + messageId + "]";
	}	
	
}
