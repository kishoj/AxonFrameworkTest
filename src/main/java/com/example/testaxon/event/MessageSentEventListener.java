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
	public void on(MessageUpdatedEvent event) {
		MessageQueryObject object = repository.findOne(event.getMessageId());
		repository.save(object);
	}
	
	@EventHandler
	public void on(MessageSentEvent event) {
		// For Revision 1.0
		/*repository.save(new MessageQueryObject(
			event.getMessageId(), 
			event.getMessage()
			));*/
		
		// For Revision 2.0
		repository.save(new MessageQueryObject(
			event.getMessageId(), 
			event.getMessage(),
			event.getSender()
			));
	}

}
