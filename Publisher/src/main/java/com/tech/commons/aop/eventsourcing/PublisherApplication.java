package com.tech.commons.aop.eventsourcing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PublisherApplication {


	@Autowired
	private Publisher publisher;



	public static void main(String[] args) throws Exception {
		SpringApplication.run(PublisherApplication.class, args);
//		EventSourcingApplication eventSourcingApplication = new EventSourcingApplication();
//		eventSourcingApplication.run();
	}


		@Bean
		CommandLineRunner start() {
			return args -> {

			};
		}

}
