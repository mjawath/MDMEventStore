package com.techstart.poc.mom.amq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class AMQSubscriberApplication {
    public static void main(String[] args) throws Exception {
        if(args.length>0){
            Constants.subid=args[0];
        }
//        Constants.DurableSubscriptionName=args[1];
        SpringApplication application = new SpringApplication(AMQSubscriberApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }


}
