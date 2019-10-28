package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Role;
import org.springframework.data.neo4j.repository.Neo4jRepository;



public interface RoleRepository extends Neo4jRepository<Role, Long> {
    Role findByType(String name);
}
