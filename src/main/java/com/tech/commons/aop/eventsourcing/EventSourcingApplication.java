package com.tech.commons.aop.eventsourcing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class EventSourcingApplication implements CommandLineRunner  {

	@Autowired
	private GenericService service;

	public static void main(String[] args) throws Exception {
//		SpringApplication.run(EventSourcingApplication.class, args);
		EventSourcingApplication eventSourcingApplication = new EventSourcingApplication();
		eventSourcingApplication.run();

}


	@Override
	public void run(String... args) throws Exception {

//		service.create(ev);
		EventProcessor ep = new EventProcessor();
		ep.fanOut();

	}
}
