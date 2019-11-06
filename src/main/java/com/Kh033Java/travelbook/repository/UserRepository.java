package com.Kh033Java.travelbook.repository;

import java.util.Optional;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.Kh033Java.travelbook.entity.User;


public interface UserRepository extends Neo4jRepository<User, Long> {

    Optional<User> findByLogin(String name);

    @Query("match (u:User)-[:CREATED_PLAN]->(p:Plan) where ID(p)={id} return u")
    User findUserByPlanId(@Param("id") long id);

    @Query("match (u:User)-[:CREATED_NOTE]->(n:Note) where ID(n)={id} return u")
    User findUserByNoteId(@Param("id") long id);

    @Query("MATCH (u:User),(c:Country) WHERE u.login={login} AND c.name={country} CREATE (u)-[:VISITED]->(c)")
    void creatRelationshipBetweenUserAndCountry(@Param("login") String login, @Param("country") String name);

    @Query("MATCH (u:User)-[r:VISITED]->(c:Country) WHERE u.login={login} AND c.name={country} DELETE r")
    void deleteRelationshipBetweenUserAndCountry(@Param("login") String login, @Param("country") String name);
}

