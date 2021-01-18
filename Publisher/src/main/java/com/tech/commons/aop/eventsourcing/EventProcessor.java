package com.tech.commons.aop.eventsourcing;

import com.tech.commons.aop.eventsourcing.kafka.KafkaPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

@Component
public class EventProcessor {

    Logger LOG = Logger.getLogger(EventProcessor.class.toString());
    @Autowired
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

    int id=0;
    public void fanOut(){
//        publisher = new KafkaPublisher();
        //for all active subscriber
        System.out.println("fanning out for all active subscribers ");

        for (int x=0;x<10;x++){
        Event event = new Event();
        event.setId(String.valueOf(id++));
        event.setTimestamp(new Date());
        publisher.publish(event);
        LOG.info("event "+event.getId());
        }
    }
}
