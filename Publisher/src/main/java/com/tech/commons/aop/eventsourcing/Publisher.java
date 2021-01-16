package com.tech.commons.aop.eventsourcing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Publisher {
    //current service is the publisher , who publish the events

    //with current service, other services communicate


    public void publish(Event event) ;


    public static void test(){
//        Publisher publisher = new Publisher();// can have a queue
//        publisher.publish(new Event());// read the events from db and publish
//        //do we need to have list of subscribers ?
//        Subscriber subscriber = new Subscriber(); //should it be subscribing to a queue / topic / publisher
//
//        publisher.addSubscriber(subscriber);

    }


}
