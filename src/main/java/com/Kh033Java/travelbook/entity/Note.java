package com.Kh033Java.travelbook.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Note {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private boolean isPublic;
    private String description;
    @DateString("yyyy-MM-dd")
    private Date dateOfVisiting;
    private Integer peopleEstimate;
    private Integer pricesEstimate;
    private Integer cuisineEstimate;
    private Integer commonImpression;

    @Relationship(type = "LIKED", direction = Relationship.INCOMING)
    private User userThatLiked;
    @Relationship(type = "CREATED_NOTE", direction = Relationship.INCOMING)
    private User creatorUser;
    @Relationship(type = "DESCRIBES")
    private City describedCity;
    @Relationship(type = "HAS_PHOTO")
    private Set<Photo> photos;

    public Note() {
    	
    }

    public Note(String title, boolean isPublic, String description, Date dateOfVisiting, Integer peopleEstimate, Integer pricesEstimate, Integer cuisineEstimate, Integer commonImpression, City describedCity, Set<Photo> photos) {
        this.title = title;
        this.isPublic = isPublic;
        this.description = description;
        this.dateOfVisiting = dateOfVisiting;
        this.peopleEstimate = peopleEstimate;
        this.pricesEstimate = pricesEstimate;
        this.cuisineEstimate = cuisineEstimate;
        this.commonImpression = commonImpression;
        this.describedCity = describedCity;
        this.photos = photos;
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

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfVisiting() {
        return dateOfVisiting;
    }

    public void setDateOfVisiting(Date dateOfVisiting) {
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

    public City getDescribedCity() {
        return describedCity;
    }

    public void setDescribedCity(City describedCity) {
        this.describedCity = describedCity;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public void describeCity(City city) {
        if (describedCity == null) {
            describedCity = new City();
        }
        describedCity = city;
    }

    public void hasPhoto(Photo photo) {
        if (photos == null) {
            photos = new HashSet<>();
        }
        photos.add(photo);
    }

}
