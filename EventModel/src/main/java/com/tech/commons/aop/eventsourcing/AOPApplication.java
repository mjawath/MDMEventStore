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

@SpringBootApplication
public class AOPApplication {
    public static void main(String[] args) {
        SpringApplication.run(AOPApplication.class, args);

    }


}
