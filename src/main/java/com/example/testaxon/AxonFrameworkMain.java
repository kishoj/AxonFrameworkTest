package com.example.testaxon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.example.testaxon.integration.MessagingChannel;

@SpringBootApplication
@EnableBinding(MessagingChannel.class)
public class AxonFrameworkMain 
{
    public static void main( String[] args ) {
    	SpringApplication.run(AxonFrameworkMain.class, args);
    }

}
