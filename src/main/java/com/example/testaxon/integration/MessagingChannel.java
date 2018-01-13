package com.example.testaxon.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MessagingChannel {
	String NAME = "message.topic";

	@Input(NAME)
	SubscribableChannel subscriber();
}
