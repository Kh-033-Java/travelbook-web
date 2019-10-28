package com.Kh033Java.travelbook.entity;

import org.neo4j.ogm.annotation.*;

@NodeEntity
public class Photo {
    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String link;

    public Photo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Photo(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
