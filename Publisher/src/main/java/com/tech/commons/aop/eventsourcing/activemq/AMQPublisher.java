package com.tech.commons.aop.eventsourcing.activemq;

import com.tech.commons.aop.eventsourcing.Event;
import com.tech.commons.aop.eventsourcing.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class AMQPublisher implements Publisher {
    @Value("${eventQ}")
    private String queue;
    @Autowired
    private JmsTemplate template;


    @Override
    public void publish(Event event) {
        template.convertAndSend(queue, "Hello World from Spring Boot!");

    }
}
