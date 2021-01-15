package com.tech.commons.aop.eventsourcing;

import com.tech.commons.aop.eventsourcing.wp.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class EventProcessor {


    Logger LOG = LoggerFactory.getLogger(EventProcessor.class);

    private Publisher publisher;
    private List<Subscriber> subscribers;

    private Server server;

    public void loadEngine(){

//        server.se
    }

    public void sendEvent(){
        Event ev = new Event();
        ev.setId(UUID.randomUUID().toString());
        ev.setTimestamp(new Date());

        publisher.publish(ev);
    }
    public void loadSubscribers(){

    }

    public void loadEvents(){

    }

    public void Subscribe(Subscriber sub){
        subscribers.add(sub);
    }

    public void fanOut(){
        //for all subscriber
        System.out.println("fanning out");

    }
}
