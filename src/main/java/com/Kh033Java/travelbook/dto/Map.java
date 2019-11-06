package com.Kh033Java.travelbook.dto;

import java.util.Set;

import com.Kh033Java.travelbook.entity.Country;

public class Map {


    private Set<Country> visitedCountry;

    private Set<Country> plannedCountry;


    public Map(Set<Country> visitedCountry, Set<Country> plannedCountry) {
        this.visitedCountry = visitedCountry;
        this.plannedCountry = plannedCountry;
    }

    public Set<Country> getVisitedCountry() {
        return visitedCountry;
    }

    public void setVisitedCountry(Set<Country> visitedCountry) {
        this.visitedCountry = visitedCountry;
    }

    public Set<Country> getPlannedCountry() {
        return plannedCountry;
    }

    public void setPlannedCountry(Set<Country> plannedCountry) {
        this.plannedCountry = plannedCountry;
    }
}
