package com.example.testaxon.config.mongo;

import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.StorageStrategy;
import org.axonframework.mongo.eventsourcing.eventstore.documentpercommit.DocumentPerCommitStorageStrategy;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.axonframework.mongo.DefaultMongoTemplate;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
@Profile("mongo")
public class MongoConfiguration {

	@Value("${mongodb.host}")
	private String mongoHost;

	@Value("${mongodb.port}")
	private int mongoPort;

	@Value("${mongodb.database.name}")
	private String mongoDatabaseName;

	@Bean
	public MongoClient mongoClient() {
		return new MongoClient(mongoHost, mongoPort);
	}

	@Bean(name = "axonMongoTemplate")
	public MongoTemplate axonMongoTemplate() {
		return new DefaultMongoTemplate(mongoClient(), mongoDatabaseName);
	}
	
	@Bean
	public StorageStrategy storageStrategy() {
		return new DocumentPerCommitStorageStrategy();
	}

	@Bean
	public MongoEventStorageEngine eventStorageEngine(Serializer serializer, SingleEventUpcaster upcaster) {
		// If we dont use upcaster
		//return new MongoEventStorageEngine(axonMongoTemplate());
		return new MongoEventStorageEngine(serializer, upcaster, axonMongoTemplate(), storageStrategy());
	}

	@Bean
	public MongoSagaStore sagaRepository() {
		return new MongoSagaStore(axonMongoTemplate());
	}

}