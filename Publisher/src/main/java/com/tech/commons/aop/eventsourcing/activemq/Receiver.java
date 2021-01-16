package com.tech.commons.aop.eventsourcing.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

//@Component
public class Receiver {

    @JmsListener(destination = "eventQ||wrong", containerFactory = "myFactory")
    public void receiveMessage(String  email) {
        System.out.println("Received <" + email + ">");
    }

}