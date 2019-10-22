package com.Kh033Java.travelbook.userDetails.requestUserDetails;

import com.Kh033Java.travelbook.entity.*;

import java.util.List;
import java.util.Set;

public class RequestDetail {
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private String email;
    private String description;
    private Set<Country> visitedCountries;
    private Photo avatar;
    private Set<Note> likedNotes;
    private Set<Note> createdNotes;
    private Set<Plan> createdPlans;
    private List<Role> roles;

    public RequestDetail() {
    }

    public RequestDetail(String login, String password, String lastName, String firstName,
                         String email, String description, Set<Country> visitedCountries,
                         Photo avatar, Set<Note> likedNotes, Set<Note> createdNotes,
                         Set<Plan> createdPlans, List<Role> roles) {
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.description = description;
        this.visitedCountries = visitedCountries;
        this.avatar = avatar;
        this.likedNotes = likedNotes;
        this.createdNotes = createdNotes;
        this.createdPlans = createdPlans;
        this.roles = roles;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
