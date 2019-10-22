package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.model.User;
import com.Kh033Java.travelbook.util.exception.NotFoundException;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * User repository.
 */
@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    Optional<User> findUserByName(String name);
}
