package com.techstart.poc.mom.amq;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.tech.commons.aop.eventsourcing.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Consumer implements MessageListener{
    private Logger log = LoggerFactory.getLogger(Consumer.class);
    @Override
    public void onMessage(Message message) {
        try {

            if(message instanceof ObjectMessage){
                //convert to event
                ObjectMessage msgObj = (ObjectMessage) message;
                Object obj = msgObj.getObject();
                Event event = (Event) obj;
                log.info(event.toString());
            }

//            log.info("Received message: " + message.toString());


        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}