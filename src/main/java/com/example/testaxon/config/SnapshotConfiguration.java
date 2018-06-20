package com.example.testaxon.config;

import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.AggregateSnapshotter;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.testaxon.command.Message;

@Configuration
public class SnapshotConfiguration {
	private static final int SNAPSHOT_THRESHOLD = 3;

	@Autowired
	private EventStore eventStore;

	@Bean
	public AggregateFactory<Message> messageAggregateFactory() {
		return new GenericAggregateFactory<Message>(Message.class);
	}

	@Bean
	public EventCountSnapshotTriggerDefinition countSnapshotTriggerDefinition() {
		return new EventCountSnapshotTriggerDefinition(snapShotter(), SNAPSHOT_THRESHOLD);
	}

	@Bean
	public Snapshotter snapShotter() {
		return new AggregateSnapshotter(eventStore, messageAggregateFactory());
	}

	@Bean
	public EventSourcingRepository<Message> messageRepository() {
		return new EventSourcingRepository<>(messageAggregateFactory(), eventStore, countSnapshotTriggerDefinition());
	}

}
