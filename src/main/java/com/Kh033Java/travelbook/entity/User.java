package com.Kh033Java.travelbook.entity;

import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String login;

    @Index(unique = true)
    private String email;

    @Index(unique = true)
    private String password;

    private String firstName;

    private String lastName;

    private String description;

    private String role;

    @Relationship(type = "VISITED")
    private Set<Country> visitedCountries;

    @Relationship(type = "HAS_AVATAR")
    private Photo avatar;

    @Relationship(type = "LIKED")
    private Set<Note> likedNotes;

    @Relationship(type = "CREATED_NOTE")
    private Set<Note> createdNotes;

    @Relationship(type = "CREATED_PLAN")
    private Set<Plan> createdPlans;

    public User() {
    }

    public User(String login, String email, String password, String firstName, String lastName, String description, String role, Photo avatar) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.role = role;
        this.avatar = avatar;
    }

    public User(String login, String email, String password, String firstName, String lastName, String description, String role, Set<Country> visitedCountries, Photo avatar, Set<Note> likedNotes, Set<Note> createdNotes, Set<Plan> createdPlans) {
        this(login, email, password, firstName, lastName, description, role, avatar);
        this.visitedCountries = visitedCountries;
        this.likedNotes = likedNotes;
        this.createdNotes = createdNotes;
        this.createdPlans = createdPlans;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Country> getVisitedCountries() {
        return visitedCountries;
    }

    public void setVisitedCountries(Set<Country> visitedCountries) {
        this.visitedCountries = visitedCountries;
    }

    public Photo getAvatar() {
        return avatar;
    }

    public void setAvatar(Photo avatar) {
        this.avatar = avatar;
    }

    public Set<Note> getLikedNotes() {
        return likedNotes;
    }

    public void setLikedNotes(Set<Note> likedNotes) {
        this.likedNotes = likedNotes;
    }

    public Set<Note> getCreatedNotes() {
        return createdNotes;
    }

    public void setCreatedNotes(Set<Note> createdNotes) {
        this.createdNotes = createdNotes;
    }

    public Set<Plan> getCreatedPlans() {
        return createdPlans;
    }

    public void setCreatedPlans(Set<Plan> createdPlans) {
        this.createdPlans = createdPlans;
    }

    public void visitCountry(Country country) {
        if (visitedCountries == null) {
            visitedCountries = new HashSet<>();
        }
        visitedCountries.add(country);
    }

    public void addAvatar(Photo photo) {
        if (avatar == null) {
            avatar = new Photo();
        }
        avatar = photo;
    }

    public void likeNote(Note note) {
        if (likedNotes == null) {
            likedNotes = new HashSet<>();
        }
        likedNotes.add(note);
    }

    public void createNote(Note note) {
        if (createdNotes == null) {
            createdNotes = new HashSet<>();
        }
        createdNotes.add(note);
    }

    public void createPlan(Plan plan) {
        if (createdPlans == null) {
            createdPlans = new HashSet<>();
        }
        createdPlans.add(plan);
    }
}
