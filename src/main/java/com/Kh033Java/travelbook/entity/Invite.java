package com.Kh033Java.travelbook.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;
import java.util.List;
import java.util.Set;

@NodeEntity
public class Invite {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(value = "CREATED_INVITE", direction = Relationship.INCOMING)
    private Plan plan;

    @Relationship(value = "INVITED")
    private List<User> invited;

    @Relationship(value = "JOIN", direction = Relationship.INCOMING)
    private List<User> accepted;

    public Invite() {
    }

    public Invite(Long id, Plan plan, List<User> invited, List<User> accepted) {
        this.id = id;
        this.plan = plan;
        this.invited = invited;
        this.accepted = accepted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public List<User> getInvited() {
        return invited;
    }

    public void setInvited(List<User> invited) {
        this.invited = invited;
    }

    public List<User> getAccepted() {
        return accepted;
    }

    public void setAccepted(List<User> accepted) {
        this.accepted = accepted;
    }
}
