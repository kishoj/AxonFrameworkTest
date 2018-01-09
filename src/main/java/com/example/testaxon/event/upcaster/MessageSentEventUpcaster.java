package com.example.testaxon.event.upcaster;

import org.axonframework.serialization.SimpleSerializedType;
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;

import com.example.testaxon.event.MessageSentEvent;

public class MessageSentEventUpcaster extends SingleEventUpcaster {

	private static final String FIELD_SENDER = "sender";
	private static final String VALUE_NOT_AVAILABLE = "N/A";
	private static final String UPCAST_VERSION_FROM = "1.0";
	private static final String UPCAST_VERSION_TO = "2.0";

	private static SimpleSerializedType targetType = new SimpleSerializedType(MessageSentEvent.class.getTypeName(),
			UPCAST_VERSION_FROM);

	@Override
	protected boolean canUpcast(IntermediateEventRepresentation intermediateRepresentation) {
		return intermediateRepresentation.getType().equals(targetType);
	}

	@Override
	protected IntermediateEventRepresentation doUpcast(IntermediateEventRepresentation intermediateRepresentation) {
		return intermediateRepresentation.upcastPayload(
				new SimpleSerializedType(targetType.getName(), UPCAST_VERSION_TO), org.dom4j.Document.class,
				document -> {
					document.getRootElement().addElement(FIELD_SENDER);
					document.getRootElement().element(FIELD_SENDER).setText(VALUE_NOT_AVAILABLE);
					return document;
				});
	}

}
