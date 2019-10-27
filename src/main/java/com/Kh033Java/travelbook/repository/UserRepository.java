package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    User getUserByLogin(@Param("login") String login);

    Long deleteUserByLogin(@Param("login") String login);

    @Query("MATCH (u:User), (c:Country) WHERE u.login = {login} AND c.name={country} CREATE (u)-[r:VISITED]->(c)")
    void creatRelationshipBetweenUserAndCountry(@Param("country") String name, @Param("login") String login);
}
