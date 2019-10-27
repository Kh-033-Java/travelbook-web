package com.Kh033Java.travelbook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class NoteDTO {

    private String title;

    @JsonProperty
    private boolean isPublic;

    private String description;

    private Date dateOfVisiting;

    private Integer peopleEstimate;

    private Integer pricesEstimate;

    private Integer cuisineEstimate;

    private Integer commonImpression;

    private String name;

    private String login;

    private String link;

    private String anotherLogin;

    public NoteDTO() {
    }

    public NoteDTO(String title, boolean isPublic, String description, Date dateOfVisiting, Integer peopleEstimate, Integer pricesEstimate, Integer cuisineEstimate, Integer commonImpression, String name, String login, String link, String anotherLogin) {
        this.title = title;
        this.isPublic = isPublic;
        this.description = description;
        this.dateOfVisiting = dateOfVisiting;
        this.peopleEstimate = peopleEstimate;
        this.pricesEstimate = pricesEstimate;
        this.cuisineEstimate = cuisineEstimate;
        this.commonImpression = commonImpression;
        this.name = name;
        this.login = login;
        this.link = link;
        this.anotherLogin = anotherLogin;
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

    public Date getDateOfVisiting() {
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

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getLink() {
        return link;
    }

    public String getAnotherLogin() {
        return anotherLogin;
    }
}
