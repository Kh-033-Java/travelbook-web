package com.Kh033Java.travelbook.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Note {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @JsonProperty
    private boolean isPublic;

    private String description;

    private String dateOfVisiting;

    private Integer peopleEstimate;

    private Integer pricesEstimate;

    private Integer cuisineEstimate;

    private Integer commonImpression;

    @Relationship(type = "DESCRIBES")
    private String describedCity;
    @Relationship(type = "HAS_PHOTO")
    private String photoLink;

    public Note() {
    }

    public Note(Integer id, String title, boolean isPublic, String description, String dateOfVisiting, Integer peopleEstimate, Integer pricesEstimate, Integer cuisineEstimate, Integer commonImpression, String describedCity, String photoLink) {
        this.id = id;
        this.title = title;
        this.isPublic = isPublic;
        this.description = description;
        this.dateOfVisiting = dateOfVisiting;
        this.peopleEstimate = peopleEstimate;
        this.pricesEstimate = pricesEstimate;
        this.cuisineEstimate = cuisineEstimate;
        this.commonImpression = commonImpression;
        this.describedCity = describedCity;
        this.photoLink = photoLink;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfVisiting() {
        return dateOfVisiting;
    }

    public void setDateOfVisiting(String dateOfVisiting) {
        this.dateOfVisiting = dateOfVisiting;
    }

    public Integer getPeopleEstimate() {
        return peopleEstimate;
    }

    public void setPeopleEstimate(Integer peopleEstimate) {
        this.peopleEstimate = peopleEstimate;
    }

    public Integer getPricesEstimate() {
        return pricesEstimate;
    }

    public void setPricesEstimate(Integer pricesEstimate) {
        this.pricesEstimate = pricesEstimate;
    }

    public Integer getCuisineEstimate() {
        return cuisineEstimate;
    }

    public void setCuisineEstimate(Integer cuisineEstimate) {
        this.cuisineEstimate = cuisineEstimate;
    }

    public Integer getCommonImpression() {
        return commonImpression;
    }

    public void setCommonImpression(Integer commonImpression) {
        this.commonImpression = commonImpression;
    }

    public String getDescribedCity() {
        return describedCity;
    }

    public void setDescribedCity(String describedCity) {
        this.describedCity = describedCity;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getPhotoLink() {
        return photoLink;
    }
}
