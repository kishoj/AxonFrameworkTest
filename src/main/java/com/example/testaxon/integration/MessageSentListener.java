package com.example.testaxon.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class MessageSentListener {
	private final static Logger LOGGER = LoggerFactory.getLogger(MessageSentListener.class);
	
	@StreamListener(target = MessagingChannel.SUBSCRIBER)
	public void onReceived(MessageForwarderSrv.MessageSentEvent event) {
		LOGGER.info("Received Message from RabbitMQ.topic " + event.toString());
		LOGGER.info(event.toString());
	}
}
