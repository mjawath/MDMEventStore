package com.techstart.poc;


import com.mycompany.entitybase.BaseEntityString;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Event extends BaseEntityString {

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
