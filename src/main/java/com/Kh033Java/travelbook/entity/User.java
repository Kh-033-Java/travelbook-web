package com.Kh033Java.travelbook.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@NodeEntity
public class User {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long id;

    @JsonProperty("login")
    @Index(unique = true)
    private String login;

    @Index(unique = true)
    private String password;

    private String lastName;

    private String firstName;

    @Index(unique = true)
    private String email;

    private String description;

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

    @Relationship(type = "HAS_ROLE")
    private List<Role> roles;

    @Relationship(type = "FRIENDS")
    private List<User> following;

    @Relationship(type = "FRIENDS", direction = Relationship.INCOMING)
    private List<User> followers;

    @Relationship(type = "HOMELAND")
    private Country homeland;

    @Relationship(type = "SENDED")
    private List<Message> sendedMessages;

    @Relationship(type = "RECEIVED")
    private List<Message> receivedMessages;

    public User() {
    }

    public User(final String login, final String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login,
                String password,
                String lastName,
                String firstName,
                String email,
                String description,
                List<Role> roles,
                Photo avatar,
                Country homeland
    ) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.roles = roles;
        this.avatar = avatar;
        this.homeland = homeland;
    }

    public User(String login,
                String email,
                String password,
                String firstName,
                String lastName,
                String description,
                List<Role> roles,
                Set<Country> visitedCountries,
                Photo avatar,
                Country homeland,
                Set<Note> likedNotes,
                Set<Note> createdNotes,
                Set<Plan> createdPlans,
                List<User> following,
                List<User> followers) {
        this(login, password, lastName, firstName, email, description, roles, avatar, homeland);
        this.visitedCountries = visitedCountries;
        this.likedNotes = likedNotes;
        this.createdNotes = createdNotes;
        this.createdPlans = createdPlans;
        this.following = following;
        this.followers = followers;
    }

    public Country getHomeland() {
        return homeland;
    }

    public void setHomeland(Country homeland) {
        this.homeland = homeland;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
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

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
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

    public void addFollowing(User user) {
        if (following == null) {
            following = new ArrayList<>();
        }
        following.add(user);
    }

    public void addFollowers(User user) {
        if (followers == null) {
            followers = new ArrayList<>();
        }
        followers.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(description, user.description) &&
                Objects.equals(visitedCountries, user.visitedCountries) &&
                Objects.equals(avatar, user.avatar) &&
                Objects.equals(likedNotes, user.likedNotes) &&
                Objects.equals(createdNotes, user.createdNotes) &&
                Objects.equals(createdPlans, user.createdPlans) &&
                roles.equals(user.roles) &&
                homeland.equals(user.homeland);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, lastName, firstName, email, description, visitedCountries, avatar, likedNotes, createdNotes, createdPlans, roles, homeland);
    }
}
