package com.example.testaxon.api;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testaxon.command.SendMessageCommand;
import com.example.testaxon.queryobject.MessageQueryObject;
import com.example.testaxon.queryobject.MessageQueryObjectRepository;

@RestController
@RequestMapping("/messages")
public class MessageAPI {
	private static final Logger LOGGER = getLogger(MessageAPI.class);
	
	private final MessageQueryObjectRepository repository;
	private final CommandGateway commandGateway;
	
	public MessageAPI(MessageQueryObjectRepository repository, CommandGateway commandGateway) {
		this.repository = repository;
		this.commandGateway = commandGateway;
	}
	
	@GetMapping
	public List<MessageQueryObject> findAll() {
		LOGGER.info("GET /messages -> All Messages sent");
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public MessageQueryObject findById(@PathVariable String id) {
		LOGGER.info("GET /messages/{id} -> Message sent by id");
		return repository.findOne(UUID.fromString(id));
	}
	
	@PostMapping
	public CompletableFuture<String> sendMessage(@RequestBody Map<String, String> request) {
		LOGGER.info("POST /messages -> POST new message to send");
		return commandGateway.send(
			new SendMessageCommand(
				UUID.randomUUID(), 
				request.get("message"),
				request.get("sender"))
		);
	}
}
