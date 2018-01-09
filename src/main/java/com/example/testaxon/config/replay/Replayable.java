package com.example.testaxon.config.replay;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface Replayable {
	// Used with Event class that have to replay
	// Clear the Read Model for replay
	// Delete the record for the event token_entry
}
