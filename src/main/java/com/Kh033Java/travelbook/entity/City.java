package com.Kh033Java.travelbook.entity;

import org.neo4j.ogm.annotation.*;

@NodeEntity
public class City {
    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String name;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
