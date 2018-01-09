package com.example.testaxon.saga;

import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.testaxon.command.saga.MarkMessageDeliveredCommand;
import com.example.testaxon.command.saga.MarkMessageTransferFailedCommand;
import com.example.testaxon.event.MessageSentEvent;
import com.example.testaxon.event.saga.MessageDeliveredMarkedEvent;
import com.example.testaxon.event.saga.ReceiverNotFoundEvent;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

import javax.inject.Inject;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaLifecycle;
import org.axonframework.eventhandling.saga.StartSaga;

@Saga
public class MessageManagementSaga {
	private final static Logger LOGGER = LoggerFactory.getLogger(MessageManagementSaga.class);

	private boolean isDelivered = false;

	@Inject
	private transient CommandBus commandBus;

	@StartSaga
	@SagaEventHandler(associationProperty = "messageId")
	public void on(MessageSentEvent event) {
		LOGGER.info("Saga Event Handler after message received from RabbitMQ");
		commandBus.dispatch(asCommandMessage(new MarkMessageDeliveredCommand(event.getMessageId())), LoggingCallback.INSTANCE);
		LOGGER.info("MarkMessageDeliveredCommand is fired from Saga");
	}

	@SagaEventHandler(associationProperty = "messageId")
	@EndSaga
	public void on(ReceiverNotFoundEvent event) {
		LOGGER.info("Saga Event Ending!.....");
		MarkMessageTransferFailedCommand markFailedCommand = new MarkMessageTransferFailedCommand(event.getMessageId());
		commandBus.dispatch(asCommandMessage(markFailedCommand), LoggingCallback.INSTANCE);
	}

	@SagaEventHandler(associationProperty = "messageId")
	@EndSaga
	public void on(MessageDeliveredMarkedEvent event) {
		LOGGER.info("Saga Event Ending!.....");
		isDelivered = true;
		if (isDelivered) {
			LOGGER.info("Saga Event End after confirm delivered");
			SagaLifecycle.end();
		}
	}

}
