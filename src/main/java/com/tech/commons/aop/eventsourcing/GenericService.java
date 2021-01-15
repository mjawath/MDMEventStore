package com.tech.commons.aop.eventsourcing;

import com.mycompany.entitybase.dao.BaseDAO;
import com.mycompany.entitybase.service.BaseService;
import org.springframework.stereotype.Component;

@Component
public class GenericService extends BaseService<Event> {


    public GenericService(EventRepo dao) {
        super(dao);
    }
}
