package com.tech.commons.aop.eventsourcing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.entitybase.BaseEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
//@Order(Integer.MAX_VALUE)
public class EventAOPProcessor {


    Logger logger = LoggerFactory.getLogger(EventAOPProcessor.class);

    @Autowired
    private EventRepo repo;

    @Autowired
    private ObjectMapper mapper;

    @Around("execution(* com.mycompany.entitybase.service.BaseService.create(*))")
    public Object aroundCreate(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        logger.info("Before - create save of BaseService");

        Object value;
        try {
            Object[] args = proceedingJoinPoint.getArgs();

            Object per= args[0];

                value=proceedingJoinPoint.proceed();
                Event event = new Event();
                event.setTimestamp(new Date());
                String domainObjPayload = mapper.writeValueAsString(per);
                event.setPayload(domainObjPayload);
                if(value instanceof BaseEntity) {
                    final Object id = ((BaseEntity) value).getId();
                    event.setDomainId(String.valueOf(id));
                    event.setDomain(value.getClass().toString());
                }
                final Event save = repo.save(event);
            final String id = save.getId();
            logger.info("Event is saved "+id);
            logger.info("AOP proceeded to create ");
        } catch (Exception e) {
            logger.error("AOP event store had a issue ",e);
            throw new RuntimeException(e);
        } finally {
//            long duration = System.currentTimeMillis() - start;
        }
        logger.info("After - create save of BaseService");
        return value;
    }
}

