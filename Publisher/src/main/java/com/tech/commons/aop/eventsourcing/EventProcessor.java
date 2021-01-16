package com.tech.commons.aop.eventsourcing;

import com.tech.commons.aop.eventsourcing.kafka.KafkaPublisher;

import java.util.*;
import java.util.logging.Logger;

public class EventProcessor {

    Logger LOG = Logger.getLogger(EventProcessor.class.toString());
    private Publisher publisher;
//    private List<Subscriber> subscribers;

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
        //forget about this
    }

    public void loadEvents(){

        //last events


    }



    public void fanOut(){
        publisher = new KafkaPublisher();
        //for all active subscriber
        System.out.println("fanning out for all active subscribers ");
        Event event = new Event();
        event.setId("1");
        event.setTimestamp(new Date());
        publisher.publish(event);
        LOG.info("event ");
    }
}
