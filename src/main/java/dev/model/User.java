package dev.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity("User")
public class User extends Entity {

    @Property("username")
    private String username;

    @Property("password")
    private String password;

    @Property("last_name")
    private String lastName;

    @Property("first_name")
    private String firstName;

    @Property("email")
    private String email;

    @Relationship(type = "HAS_ROLE", direction = Relationship.OUTGOING)
    private List<Role> roles;

    public User() {
    }


    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String lastName, String firstName, String email) {
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
