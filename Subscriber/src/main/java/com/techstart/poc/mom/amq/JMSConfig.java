package com.techstart.poc.mom.amq;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class JMSConfig {


    @Value("${spring.artemis.broker}")
    private String brokerUrl;

    @Value("${eventQ}")
    private String queue;

    @Bean
    public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
        return new ActiveMQConnectionFactory(brokerUrl);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory
                .setConnectionFactory(receiverActiveMQConnectionFactory());
        factory.setConcurrency("3-10");

        return factory;
    }
    @Bean
    public DefaultMessageListenerContainer messageListener() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(this.receiverActiveMQConnectionFactory());
        container.setDestinationName(queue);
//        container.setMessageConverter(jacksonJmsMessageConverter());
        container.setMessageListener(new Consumer());
        container.setPubSubDomain(true);
        container.setSubscriptionDurable(true);
        container.setClientId(Constants.subid);
        return container;
    }
//    @Bean // Serialize message content to json using TextMessage
//    public MessageConverter jacksonJmsMessageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.OBJECT);
//        converter.setTypeIdPropertyName("_type");
//        return converter;
//    }


//durable messages
//    https://activemq.apache.org/how-do-durable-queues-and-topics-work
}
