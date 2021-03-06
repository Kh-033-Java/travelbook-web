package com.Kh033Java.travelbook.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

@NodeEntity
public class Role {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long id;

    private String type;

    public Role() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
