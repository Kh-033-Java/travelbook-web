package com.Kh033Java.travelbook.dto;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Plan;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class Map {


    private Set<Country> visitedCountry;

    private Set<Plan> createdPlans;

    public Map(Set<Country> visitedCountry, Set<Plan> createdPlans) {
        this.visitedCountry = visitedCountry;
        this.createdPlans = createdPlans;
    }

    public Set<Country> getVisitedCountry() {
        return visitedCountry;
    }

    public void setVisitedCountry(Set<Country> visitedCountry) {
        this.visitedCountry = visitedCountry;
    }

    public Set<Plan> getCreatedPlans() {
        return createdPlans;
    }

    public void setCreatedPlans(Set<Plan> createdPlans) {
        this.createdPlans = createdPlans;
    }

    @Override
    public String toString() {
        return "Map{" +
                "visitedCountry=" + visitedCountry +
                ", createdPlans=" + createdPlans +
                '}';
    }
}
