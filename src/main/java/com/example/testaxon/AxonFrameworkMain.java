package com.example.testaxon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.example.testaxon.integration.MessagingChannel;

@SpringBootApplication
@EnableBinding(MessagingChannel.class)
/*@EntityScan(basePackages = {"com.example.testaxon",
        "org.axonframework.eventsourcing.eventstore.jpa",
        "org.axonframework.eventhandling.saga.repository.jpa",
        "org.axonframework.eventhandling.tokenstore.jpa"})*/
public class AxonFrameworkMain {

	public static void main(String[] args) {
		SpringApplication.run(AxonFrameworkMain.class, args);
	}
	
}
