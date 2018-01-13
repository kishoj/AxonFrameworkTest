package com.example.testaxon.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.example.testaxon.queryobject.MessageQueryObject;
import com.example.testaxon.queryobject.MessageQueryObjectRepository;

@Component
public class MessageSentEventListener {
	
	private final MessageQueryObjectRepository repository;
	
	public MessageSentEventListener(MessageQueryObjectRepository repository) {
		this.repository = repository;
	}
	
	@EventHandler
	public void on(MessageSentEvent event) {
		repository.save(new MessageQueryObject(
			event.getMessageId(), 
			event.getMessage(),
			event.getSender()
			));
	}

}
