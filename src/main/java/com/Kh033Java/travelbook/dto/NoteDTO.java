package com.Kh033Java.travelbook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NoteDTO {

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

    private String login;

    private String photoLink;

    private String describedCity;

    public NoteDTO() {
    }

    public NoteDTO(Integer id, String title, boolean isPublic, String description, String dateOfVisiting, Integer peopleEstimate, Integer pricesEstimate, Integer cuisineEstimate, Integer commonImpression, String login, String photoLink, String describedCity) {
        this.id = id;
        this.title = title;
        this.isPublic = isPublic;
        this.description = description;
        this.dateOfVisiting = dateOfVisiting;
        this.peopleEstimate = peopleEstimate;
        this.pricesEstimate = pricesEstimate;
        this.cuisineEstimate = cuisineEstimate;
        this.commonImpression = commonImpression;
        this.login = login;
        this.photoLink = photoLink;
        this.describedCity = describedCity;
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

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getDescription() {
        return description;
    }

    public String getDateOfVisiting() {
        return dateOfVisiting;
    }

    public Integer getPeopleEstimate() {
        return peopleEstimate;
    }

    public Integer getPricesEstimate() {
        return pricesEstimate;
    }

    public Integer getCuisineEstimate() {
        return cuisineEstimate;
    }

    public Integer getCommonImpression() {
        return commonImpression;
    }

    public String getLogin() {
        return login;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateOfVisiting(String dateOfVisiting) {
        this.dateOfVisiting = dateOfVisiting;
    }

    public void setPeopleEstimate(Integer peopleEstimate) {
        this.peopleEstimate = peopleEstimate;
    }

    public void setPricesEstimate(Integer pricesEstimate) {
        this.pricesEstimate = pricesEstimate;
    }

    public void setCuisineEstimate(Integer cuisineEstimate) {
        this.cuisineEstimate = cuisineEstimate;
    }

    public void setCommonImpression(Integer commonImpression) {
        this.commonImpression = commonImpression;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getDescribedCity() {
        return describedCity;
    }

    public void setDescribedCity(String describedCity) {
        this.describedCity = describedCity;
    }
}
