package com.tech.commons.aop.eventsourcing;


import com.mycompany.entitybase.BaseEntityString;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Event  extends BaseEntityString {

    private static final  int  serialVersionUID = 12546;
//    @Id
//    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    private String domain;
    private String domainId;
    private String payload;

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

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", domain='" + domain + '\'' +
                ", domainId='" + domainId + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }
}
