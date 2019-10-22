package com.Kh033Java.travelbook.model;

import org.neo4j.ogm.annotation.*;

import java.util.Date;

@RelationshipEntity(type = "CREATED_NOTE")
public class DateOfCreationNote {
    @Id
    @GeneratedValue
    private Long id;
    private Date dateOfCreation;

    @StartNode
    private User user;

    @EndNode
    private Note note;

    public DateOfCreationNote() {
    }

    public DateOfCreationNote(Date dateOfCreation, User user, Note note) {
        this.dateOfCreation = dateOfCreation;
        this.user = user;
        this.note = note;
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

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
