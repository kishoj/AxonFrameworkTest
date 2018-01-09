package com.example.testaxon.event.saga;

import java.util.UUID;

public class ReceiverNotFoundEvent {
	private UUID messageId;
	
	public ReceiverNotFoundEvent(UUID messageId) {
		this.messageId = messageId;
	}

	public UUID getMessageId() {
		return messageId;
	}

	@Override
	public String toString() {
		return "ReceiverNotFoundEvent [messageId=" + messageId + "]";
	}
	
}
