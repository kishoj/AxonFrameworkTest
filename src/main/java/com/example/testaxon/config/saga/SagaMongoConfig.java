package com.example.testaxon.config.saga;

import javax.annotation.PostConstruct;

import org.axonframework.config.Configurer;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.testaxon.config.mongo.MongoConfiguration;
import com.example.testaxon.saga.MessageManagementSaga;

@Profile("mongo")
@Configuration
@AutoConfigureAfter(MongoConfiguration.class)
public class SagaMongoConfig {
	
	@Autowired
	private MongoTemplate axonMongoTemplate;

	@PostConstruct
	public void init() {
		Configurer configurer = DefaultConfigurer.defaultConfiguration();
		configurer.registerComponent(SagaStore.class, c -> new MongoSagaStore(axonMongoTemplate));
	}

	@SuppressWarnings("rawtypes")
	@Bean
	public SagaConfiguration messageManagementSagaConfiguration() {
		return SagaConfiguration.trackingSagaManager(MessageManagementSaga.class);
	}

}
