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
}

