package com.Kh033Java.travelbook.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * User entity.
 */
@NodeEntity("user")
public class User {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long id;

    private String name;

    /**
     * Default constructor needed for Jackson.
     */
    public User() {
    }

    /**
     * Constructor with parameter.
     *
     * @param name name of user
     */
    public User(final String name) {
        this.name = name;
    }

    /**
     * Getter for id field.
     *
     * @return user unique identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Initialize or update id field.
     *
     * @param id user unique identifier
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Getter for name field.
     *
     * @return name of user
     */
    public String getName() {
        return name;
    }


    /**
     * Setter for name field.
     *
     * @param name name of user
     */
    public void setName(final String name) {
        this.name = name;
    }
}
