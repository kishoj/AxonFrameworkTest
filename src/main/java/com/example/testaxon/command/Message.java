package com.example.testaxon.command;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.testaxon.command.saga.MarkMessageDeliveredCommand;
import com.example.testaxon.command.saga.MarkMessageTransferFailedCommand;
import com.example.testaxon.event.MessageSentEvent;
import com.example.testaxon.event.MessageUpdatedEvent;
import com.example.testaxon.event.saga.MessageDeliveredMarkedEvent;

@Aggregate(repository = "messageRepository")
public class Message {
	private final static Logger LOGGER = LoggerFactory.getLogger(Message.class);

	@AggregateIdentifier
	private UUID messageId;
	
	@SuppressWarnings("unused")
	private Status status;
	
	public Message() {
	
	}
	
	@CommandHandler
	Message(SendMessageCommand command) {
		// Version 1
		/*apply(new MessageSentEvent(
				command.getMessageId(),
				command.getMessage()
				));*/
		// Version 2
		apply(new MessageSentEvent(
			command.getMessageId(),
			command.getMessage(),
			command.getSender()
			));
	}
	
	@CommandHandler
	public void on(UpdateMessageCommand updateMessage) {
		apply(new MessageUpdatedEvent(
				updateMessage.getMessageId(),
				updateMessage.getMessage(),
				updateMessage.getSender()
				));
	}
	
	@EventSourcingHandler
	public void on(MessageUpdatedEvent event) {
		this.messageId = event.getMessageId();
		this.status = Status.STARTED;
		LOGGER.info("MessageUpdatedEvent is started!.....");
	}
	
	@EventSourcingHandler
	public void on(MessageSentEvent event) {
		this.messageId = event.getMessageId();
		this.status = Status.STARTED;
		LOGGER.info("MessageSentEvent is started!.....");
	}
	
	@CommandHandler
    public void handle(MarkMessageDeliveredCommand command) {
		LOGGER.info("MarkMessageDeliveredCommand is handle here!.....");
        apply(new MessageDeliveredMarkedEvent(command.getMessageId()));
    }
	
	@CommandHandler
    public void handle(MarkMessageTransferFailedCommand command) {
		LOGGER.info("MarkMessageTransferFailedCommand is handle here!.....");
        apply(new MessageDeliveredMarkedEvent(command.getMessageId()));
    }
	
	@EventHandler
    public void on(MessageDeliveredMarkedEvent event) {
		LOGGER.info("MessageDeliveredMarkedEvent is handle here!.....");
        this.status = Status.COMPLETED;
    }

    @EventHandler
    public void on(MarkMessageTransferFailedCommand event) {
    	LOGGER.info("MarkMessageTransferFailedCommand is handle here!.....");
        this.status = Status.FAILED;
    }
    
    @EventHandler
    protected void applySnapshot(MessageDeliveredMarkedEvent event) {
    	LOGGER.info("Apply Snapshot!.....");
        this.status = Status.COMPLETED;
    }
	
	private enum Status {
        STARTED,
        FAILED,
        COMPLETED
    }
}
