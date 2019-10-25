package com.Kh033Java.travelbook.dto;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Plan;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

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
