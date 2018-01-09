package com.example.testaxon.config.saga;

import org.axonframework.config.SagaConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.testaxon.saga.MessageManagementSaga;

@Configuration
public class SagaConfig {

    @SuppressWarnings("rawtypes")
	@Bean
    public SagaConfiguration messageManagementSagaConfiguration() {
        return SagaConfiguration.trackingSagaManager(MessageManagementSaga.class);
    }

}
