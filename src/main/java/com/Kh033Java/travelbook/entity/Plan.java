package com.Kh033Java.travelbook.entity;

import java.util.Date;
import java.util.Objects;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

@NodeEntity
public class Plan {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @DateString("yyyy-MM-dd")
    private Date date;

    private int budgetMin;

    private int budgetMax;

    private int amountOfPeople;

    private boolean isPublic;

    @Relationship(type = "GO_TO")
    private City cityToGo;

    @Relationship(type = "GO_FROM")
    private City cityFrom;

    @Relationship(type = "CHOSEN")
    private Transport transport;

    public Plan() {
    	
    }

    public Plan(String title, String description, Date date, int budgetMin, int budgetMax, int amountOfPeople, City cityToGo, City cityFrom, Transport transport) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.budgetMin = budgetMin;
        this.budgetMax = budgetMax;
        this.amountOfPeople = amountOfPeople;
        this.cityToGo = cityToGo;
        this.cityFrom = cityFrom;
        this.transport = transport;
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

    public City getCityToGo() {
        return cityToGo;
    }

    public void setCityToGo(City cityToGo) {
        this.cityToGo = cityToGo;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void goToCity(City city) {
        if (cityToGo == null) {
            cityToGo = new City();
        }
        cityToGo = city;
    }

    public void goFromCity(City city) {
        if (cityFrom == null) {
            cityFrom = new City();
        }
        cityFrom = city;
    }

    public void chooseTransport(Transport transport) {
        if (this.transport == null) {
            this.transport = new Transport();
        }
        this.transport = transport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return budgetMin == plan.budgetMin &&
                budgetMax == plan.budgetMax &&
                amountOfPeople == plan.amountOfPeople &&
                isPublic == plan.isPublic &&
                Objects.equals(id, plan.id) &&
                Objects.equals(title, plan.title) &&
                Objects.equals(description, plan.description) &&
                Objects.equals(date, plan.date) &&
                Objects.equals(cityToGo, plan.cityToGo) &&
                Objects.equals(cityFrom, plan.cityFrom) &&
                Objects.equals(transport, plan.transport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, date, budgetMin, budgetMax, amountOfPeople, isPublic, cityToGo, cityFrom, transport);
    }
}
