package com.tech.commons.aop.eventsourcing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@SpringBootApplication
public class EventSourcingApplication   {

	@Autowired
	private GenericService service;

	@Autowired
	private Publisher publisher;



	public static void main(String[] args) throws Exception {
		SpringApplication.run(EventSourcingApplication.class, args);
//		EventSourcingApplication eventSourcingApplication = new EventSourcingApplication();
//		eventSourcingApplication.run();
	}


		@Bean
		CommandLineRunner start(EventProcessor ep) {
			return args -> {
//				while (true){
					ep.fanOut();
					Thread.sleep(20000);
					ep.fanOut();
//				}
			};
		}

}
