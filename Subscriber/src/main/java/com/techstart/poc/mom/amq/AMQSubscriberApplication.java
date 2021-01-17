package com.techstart.poc.mom.amq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class AMQSubscriberApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(AMQSubscriberApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }


}
