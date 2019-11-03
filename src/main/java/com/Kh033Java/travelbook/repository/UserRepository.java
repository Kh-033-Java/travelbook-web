package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends Neo4jRepository<User, Long> {

    Optional<User> findByLogin(String name);

    @Query("MATCH (u:User),(c:Country) WHERE u.login={login} AND c.name={country} CREATE (u)-[:VISITED]->(c)")
    void creatRelationshipBetweenUserAndCountry(@Param("login") String login, @Param("country") String name);

    @Query("match (u:User)-[:CREATED_PLAN]->(p:Plan) where ID(p)={id} return u")
    User findUserByPlanId(@Param("id") long id);
}
