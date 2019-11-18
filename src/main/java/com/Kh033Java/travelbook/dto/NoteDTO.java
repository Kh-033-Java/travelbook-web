package com.Kh033Java.travelbook.dto;

import java.util.Date;
import java.util.List;

public class NoteDTO {

    private Long id;
    private String title;
    private boolean isPublic;
    private String description;
    private Date dateOfVisiting;
    private Integer peopleEstimate;
    private Integer pricesEstimate;
    private Integer cuisineEstimate;
    private Integer commonImpression;
    private String login;
    private List<String> photoLink;
    private String describedCity;
    private String linkToUserAvatar;

    public NoteDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<String> getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(List<String> photoLink) {
        this.photoLink = photoLink;
    }

    public String getDescribedCity() {
        return describedCity;
    }

    public void setDescribedCity(String describedCity) {
        this.describedCity = describedCity;
    }

    public String getLinkToUserAvatar() {
        return linkToUserAvatar;
    }

    public void setLinkToUserAvatar(String linkToUserAvatar) {
        this.linkToUserAvatar = linkToUserAvatar;
    }
}
