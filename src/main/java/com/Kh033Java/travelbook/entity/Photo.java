package com.Kh033Java.travelbook.entity;

import org.neo4j.ogm.annotation.*;

@NodeEntity
public class Photo {
    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String link;

    @Relationship(type = "HAS_AVATAR", direction = Relationship.INCOMING)
    private User user;

    @Relationship(type = "DEPICTED", direction = Relationship.INCOMING)
    private Description description;

    @Relationship(type = "HAS_PHOTO", direction = Relationship.INCOMING)
    private Note note;

    public Photo() {
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
