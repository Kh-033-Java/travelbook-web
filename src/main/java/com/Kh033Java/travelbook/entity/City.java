package com.Kh033Java.travelbook.entity;

import org.neo4j.ogm.annotation.*;

@NodeEntity
public class City {
    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String name;

    @Relationship(type = "CONTAINS", direction = Relationship.INCOMING)
    private Country country;

    @Relationship(type = "DESCRIBES", direction = Relationship.INCOMING)
    private Note note;

//    @Relationship(type = "GO_TO", direction = Relationship.INCOMING)
//    private Plan planTo;

//    @Relationship(type = "GO_FROM", direction = Relationship.INCOMING)
//    private Plan planFrom;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

//    public Plan getPlanTo() {
//        return planTo;
//    }
//
//    public void setPlanTo(Plan planTo) {
//        this.planTo = planTo;
//    }

//    public Plan getPlanFrom() {
//        return planFrom;
//    }
//
//    public void setPlanFrom(Plan planFrom) {
//        this.planFrom = planFrom;
//    }
}
