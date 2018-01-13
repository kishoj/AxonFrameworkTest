package com.example.testaxon.config.mongo;

import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
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
	public MongoEventStorageEngine eventStorageEngine() {
		return new MongoEventStorageEngine(axonMongoTemplate());
	}

	@Bean
	public MongoSagaStore sagaRepository() {
		return new MongoSagaStore(axonMongoTemplate());
	}

}
