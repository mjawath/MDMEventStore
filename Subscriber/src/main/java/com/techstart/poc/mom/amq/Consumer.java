package com.techstart.poc.mom.amq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Consumer implements MessageListener{
    private Logger log = LoggerFactory.getLogger(Consumer.class);
    @Override
    public void onMessage(Message message) {
        try {
            log.info("Received message: " + message.toString());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}