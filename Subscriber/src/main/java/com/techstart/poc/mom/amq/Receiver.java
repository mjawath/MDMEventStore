package com.techstart.poc.mom.amq;

import org.springframework.jms.annotation.JmsListener;

import org.springframework.stereotype.Component;
import org.springframework.jms.annotation.EnableJms;

@Component
public class Receiver {

    @JmsListener(destination = "eventQ", containerFactory = "myFactory")
    public void receiveMessage(String  email) {
        System.out.println("Received <" + email + ">");
    }

}