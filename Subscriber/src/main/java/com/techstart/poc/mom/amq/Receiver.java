package com.techstart.poc.mom.amq;

import com.techstart.poc.Event;
import org.springframework.jms.annotation.JmsListener;

import org.springframework.stereotype.Component;
import org.springframework.jms.annotation.EnableJms;

//@Component
public class Receiver {

//    @JmsListener(destination = "eventQ")
    public void receiveMessage(Event email) {
        System.out.println("Received <" + email + ">");
    }

}