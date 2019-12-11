package com.Kh033Java.travelbook.dto;

import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;

/**
 * @author Dmytro Starostin
 */

public class PlanSearchDTO {

    private int budgetMin;
    private int budgetMax;

    @DateString("yyyy-MM-dd")
    private Date minDate;

    @DateString("yyyy-MM-dd")
    private Date maxDate;

    private int amountOfPeopleMin;
    private int amountOfPeopleMax;
    private String transportType;
    private String cityFrom;
    private String cityGoTo;


    public PlanSearchDTO() {
    }

    public PlanSearchDTO(int budgetMin, int budgetMax, Date minDate, Date maxDate, int amountOfPeopleMin, int amountOfPeopleMax, String transportType, String cityFrom, String cityGoTo) {
        this.budgetMin = budgetMin;
        this.budgetMax = budgetMax;
        this.minDate = minDate;
        this.maxDate = maxDate;
        this.amountOfPeopleMin = amountOfPeopleMin;
        this.amountOfPeopleMax = amountOfPeopleMax;
        this.transportType = transportType;
        this.cityFrom = cityFrom;
        this.cityGoTo = cityGoTo;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }


    public int getAmountOfPeopleMin() {
        return amountOfPeopleMin;
    }

    public void setAmountOfPeopleMin(int amountOfPeopleMin) {
        this.amountOfPeopleMin = amountOfPeopleMin;
    }


    public int getAmountOfPeopleMax() {
        return amountOfPeopleMax;
    }

    public void setAmountOfPeopleMax(int amountOfPeopleMax) {
        this.amountOfPeopleMax = amountOfPeopleMax;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityGoTo() {
        return cityGoTo;
    }

    public void setCityGoTo(String cityGoTo) {
        this.cityGoTo = cityGoTo;
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

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    @Override
    public String toString() {
        return "PlanSearchDTO{" +
                "budgetMin=" + budgetMin +
                ", budgetMax=" + budgetMax +
                ", minDate=" + minDate +
                ", maxDate=" + maxDate +
                ", amountOfPeopleMin=" + amountOfPeopleMin +
                ", amountOfPeopleMax=" + amountOfPeopleMax +
                ", transportType='" + transportType + '\'' +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityGoTo='" + cityGoTo + '\'' +
                '}';
    }
}
