package com.Kh033Java.travelbook.entity;

import org.neo4j.ogm.annotation.*;

@NodeEntity
public class Transport {
    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String type;

    public Transport() {
    }

    public Transport(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
