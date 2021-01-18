package com.tech.commons.aop.eventsourcing;

import com.mycompany.dummy.Dummy;
import com.mycompany.dummy.DummyService;
import com.mycompany.entitybase.spring.BaseRepositoryImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.mycompany.dummy"})
@EnableJpaRepositories(basePackages = {"com.mycompany.dummy","com.tech.commons.aop.eventsourcing"},repositoryBaseClass = BaseRepositoryImpl.class)
@EntityScan(basePackages = {"com.mycompany.dummy","com.tech.commons.aop.eventsourcing"})
public class AOPApplication {
    public static void main(String[] args) {
        SpringApplication.run(AOPApplication.class, args);

    }

    @Bean
    CommandLineRunner start(DummyService ds,EventRepo ev) {
        return args -> {

            Dummy d = ds.create(new Dummy());
            System.out.println(d.getId());
            for(Event e:ev.findAll()){
                System.out.println(e.getTimestamp());
            }
        };
    }
}
