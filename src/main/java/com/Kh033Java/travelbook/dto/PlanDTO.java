package com.Kh033Java.travelbook.dto;

import java.util.Date;

public class PlanDTO {
    private Long id;
    private String title;
    private String description;
    private Date date;
    private int budgetMin;
    private int budgetMax;
    private int amountOfPeople;
    private boolean isPublic;
    private String userLoginCreator;
    private String nameCityToGo;
    private String nameCityFrom;
    private String transportType;

    public PlanDTO() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getBudgetMin() {
        return budgetMin;
    }

    public void setBudgetMin(int budgetMin) {
        this.budgetMin = budgetMin;
    }

    public int getBudgetMax() {
        return budgetMax;
    }

    public void setBudgetMax(int budgetMax) {
        this.budgetMax = budgetMax;
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public String getUserLoginCreator() {
        return userLoginCreator;
    }

    public void setUserLoginCreator(String userLoginCreator) {
        this.userLoginCreator = userLoginCreator;
    }

    public String getNameCityToGo() {
        return nameCityToGo;
    }

    public void setNameCityToGo(String nameCityToGo) {
        this.nameCityToGo = nameCityToGo;
    }

    public String getNameCityFrom() {
        return nameCityFrom;
    }

    public void setNameCityFrom(String nameCityFrom) {
        this.nameCityFrom = nameCityFrom;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
