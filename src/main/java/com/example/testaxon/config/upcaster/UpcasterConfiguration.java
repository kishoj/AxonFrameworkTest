package com.example.testaxon.config.upcaster;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.testaxon.event.upcaster.MessageSentEventUpcaster;

@Configuration
public class UpcasterConfiguration {

	@Bean
	public SingleEventUpcaster myUpcaster() {
		return new MessageSentEventUpcaster();
	}

	@Bean
	public JpaEventStorageEngine eventStorageEngine(Serializer serializer, DataSource dataSource,
			SingleEventUpcaster myUpcaster, EntityManagerProvider entityManagerProvider,
			TransactionManager transactionManager) throws SQLException {
		return new JpaEventStorageEngine(serializer, myUpcaster::upcast, dataSource, entityManagerProvider,
				transactionManager);
	}
}
