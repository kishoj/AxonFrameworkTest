package com.example.testaxon.config.upcaster;

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

}
