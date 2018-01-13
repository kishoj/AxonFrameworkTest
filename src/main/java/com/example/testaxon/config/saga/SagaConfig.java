package com.example.testaxon.config.saga;

import javax.annotation.PostConstruct;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.config.Configurer;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventhandling.saga.repository.jpa.JpaSagaStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.testaxon.saga.MessageManagementSaga;

@Configuration
public class SagaConfig {

	@Autowired
	private EntityManagerProvider entityManagerProvider;

	@PostConstruct
	public void init() {
		Configurer configurer = DefaultConfigurer.defaultConfiguration();
		/*configurer.registerModule(SagaConfiguration.subscribingSagaManager(MessageManagementSaga.class)
				.configureSagaStore(c -> new JpaSagaStore(entityManagerProvider)));*/
		configurer.registerComponent(SagaStore.class, c -> new JpaSagaStore(entityManagerProvider));
	}

	@SuppressWarnings("rawtypes")
	@Bean
	public SagaConfiguration messageManagementSagaConfiguration() {
		return SagaConfiguration.trackingSagaManager(MessageManagementSaga.class);
	}

}
