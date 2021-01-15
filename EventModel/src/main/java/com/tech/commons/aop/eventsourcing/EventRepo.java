package com.tech.commons.aop.eventsourcing;

import com.mycompany.entitybase.dao.BaseDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends BaseDAO<Event,Long> {
}
