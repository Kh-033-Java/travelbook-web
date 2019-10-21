package dev.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;


public class Entity {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long id;

    public Entity() {
    }

    public Long getId() {
        return id;
    }
}
