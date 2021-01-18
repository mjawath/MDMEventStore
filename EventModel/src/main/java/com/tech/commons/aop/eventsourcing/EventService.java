package com.tech.commons.aop.eventsourcing;

import com.mycompany.entitybase.dao.BaseDAO;
import com.mycompany.entitybase.service.BaseService;
import org.springframework.stereotype.Component;

@Component
public class EventService extends BaseService<Event> {


    public EventService(EventRepo dao) {
        super(dao);
    }
}
