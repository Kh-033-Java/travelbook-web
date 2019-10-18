package com.Kh033Java.Kh033Java.travelbook.repository;

import com.Kh033Java.Kh033Java.travelbook.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<User, String> {


}
