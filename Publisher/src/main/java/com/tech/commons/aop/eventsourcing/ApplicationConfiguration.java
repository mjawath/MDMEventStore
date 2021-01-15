package com.tech.commons.aop.eventsourcing;

import com.mycompany.entitybase.spring.BaseRepositoryImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAspectJAutoProxy
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class )
@EntityScan(basePackages = "com.tech.commons.aop.eventsourcing")
@ComponentScan(basePackages = {"com.tech.commons.aop"})
public class ApplicationConfiguration {

}
