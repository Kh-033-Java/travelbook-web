package com.Kh033Java.travelbook.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Description {
    @Id
    @GeneratedValue
    private Long id;

    private String commonInformation;

    private String capital;

    private String cuisine;

    private String climate;

    @Relationship(type = "DEPICTED")
    private Set<Photo> descriptionPhotos;

    public Description() {
    }

    public Description(String commonInformation, String capital, String cuisine, String climate) {
        this.commonInformation = commonInformation;
        this.capital = capital;
        this.cuisine = cuisine;
        this.climate = climate;
    }

    public Description(String commonInformation, String capital, String cuisine, String climate, Set<Photo> descriptionPhotos) {
        this.commonInformation = commonInformation;
        this.capital = capital;
        this.cuisine = cuisine;
        this.climate = climate;
        this.descriptionPhotos = descriptionPhotos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommonInformation() {
        return commonInformation;
    }

    public void setCommonInformation(String commonInformation) {
        this.commonInformation = commonInformation;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public Set<Photo> getDescriptionPhotos() {
        return descriptionPhotos;
    }

    public void setDescriptionPhotos(Set<Photo> descriptionPhotos) {
        this.descriptionPhotos = descriptionPhotos;
    }

    public void addPhotoToDescription(Photo photo) {
        if (descriptionPhotos == null) {
            descriptionPhotos = new HashSet<>();
        }
        descriptionPhotos.add(photo);
    }
}
