package com.Kh033Java.travelbook.model;

import org.neo4j.ogm.annotation.*;

import java.util.Date;

@RelationshipEntity(type = "CREATED_PLAN")
public class DateOfCreationPlan {
    @Id
    @GeneratedValue
    private Long id;

    private Date dateOfCreation;

    @StartNode
    private User user;

    @EndNode
    private Plan plan;

    public DateOfCreationPlan() {
    }

    public DateOfCreationPlan(Date dateOfCreation, User user, Plan plan) {
        this.dateOfCreation = dateOfCreation;
        this.user = user;
        this.plan = plan;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
