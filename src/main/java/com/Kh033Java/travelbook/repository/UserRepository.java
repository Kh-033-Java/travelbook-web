<<<<<<< HEAD
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

    @Query("match (u:User)-[:CREATED_PLAN]->(p:Plan) where ID(p)={id} return u")
    User findUserByPlanId(@Param("id") long id);
}
=======
package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends Neo4jRepository<User, Long> {

    Optional<User> findByLogin(String name);

    @Query("match (u:User)-[:CREATED_PLAN]->(p:Plan) where ID(p)={id} return u")
    User findUserByPlanId(@Param("id") long id);
}
>>>>>>> branch 'dev' of https://github.com/Kh-033-Java/travelbook-web
