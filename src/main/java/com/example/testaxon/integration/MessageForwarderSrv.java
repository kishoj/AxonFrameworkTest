package com.example.testaxon.integration;

import java.io.Serializable;
import java.util.UUID;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MessageForwarderSrv {

	private final static Logger LOGGER = LoggerFactory.getLogger(MessageForwarderSrv.class);
	
	@Autowired
	private Source source;

	@EventHandler
	public void on(com.example.testaxon.event.MessageSentEvent event) {
		LOGGER.info("Message prepare to keep in messaging queue");
		source.output()
				.send(MessageBuilder
						.withPayload(new MessageSentEvent(event.getMessageId(), event.getMessage(), event.getSender()))
						.build());
		LOGGER.info("Message is kept in messaging queue");
	}

	public static class MessageSentEvent implements Serializable {		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -6421813111096003118L;
		
		private UUID messageId;
		private String message;
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

		@Override
		public String toString() {
			return "MessageSentEvent [messageId=" + messageId + ", message=" + message + ", sender=" + sender + "]";
		}		
		
	}
}
