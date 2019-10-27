package com.Kh033Java.travelbook.entity;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Country {
    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String name;

    @Relationship(type = "CONTAINS")
    private List<City> cities;

    @Relationship(type = "HAS_DESCRIPTION")
    private Description description;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, List<City> cities, Description description) {
        this.name = name;
        this.cities = cities;
        this.description = description;
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

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void hasCity(City city) {
        if (cities == null) {
            cities = new ArrayList<>();
        }
        cities.add(city);
    }

    public void describe(Description description) {
        if (this.description == null) {
            this.description = new Description();
        }
        this.description = description;
    }
}
