package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends Neo4jRepository<User, Long> {
    User findByLogin(@Param("login") String login);
}
