package com.tech.commons.aop.eventsourcing;

import com.mycompany.entitybase.dao.BaseDAO;
import com.mycompany.entitybase.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventService  {

    private EventRepo dao;

    @Autowired
    public EventService(EventRepo dao) {
        this.dao=dao;
    }
}
