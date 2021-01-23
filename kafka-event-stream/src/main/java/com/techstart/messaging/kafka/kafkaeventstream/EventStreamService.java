package com.techstart.messaging.kafka.kafkaeventstream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class EventStreamService {

    @Autowired
    private StreamBridge streamBridge;

    public void produceEvent(Message msg) {
        System.out.println("Producing events --> id: "+ msg.getId() +" name: "+msg.getName()+" Actual message: "+ msg.getData());
        streamBridge.send("mytopic",msg);
    }

}