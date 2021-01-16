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
    private String domain;
    private Byte[] payload;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Byte[] getPayload() {
        return payload;
    }

    public void setPayload(Byte[] payload) {
        this.payload = payload;
    }
}
