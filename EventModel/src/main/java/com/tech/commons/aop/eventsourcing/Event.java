package com.tech.commons.aop.eventsourcing;


import com.mycompany.entitybase.BaseEntity;
import com.mycompany.entitybase.BaseEntityLong;
import com.mycompany.entitybase.BaseEntityString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Event  extends BaseEntityString {

//    @Id
//    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
}
