package com.Kh033Java.travelbook.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.Kh033Java.travelbook.entity.Role;



public interface RoleRepository extends Neo4jRepository<Role, Long> {
    Role findByType(String name);
}
