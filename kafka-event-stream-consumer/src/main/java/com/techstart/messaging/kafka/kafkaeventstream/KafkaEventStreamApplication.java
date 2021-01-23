package com.techstart.messaging.kafka.kafkaeventstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
public class KafkaEventStreamApplication {

	@Bean
	public Consumer<Message> mytopic() {
		return value -> {
			System.out.println("Received: " + value.getId());
		};
	}
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(KafkaEventStreamApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

}
