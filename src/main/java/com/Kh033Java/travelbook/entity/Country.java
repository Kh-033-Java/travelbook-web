package com.Kh033Java.travelbook.entity;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NodeEntity
public class Country {
    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String name;

    @Relationship(type = "CONTAINS")
    private List<City> cities;

    @Relationship(type = "VISITED", direction = Relationship.INCOMING)
    private Set<User> usersVisited;

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

    public Set<User> getUsersVisited() {
        return usersVisited;
    }

    public void setUsersVisited(Set<User> usersVisited) {
        this.usersVisited = usersVisited;
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
