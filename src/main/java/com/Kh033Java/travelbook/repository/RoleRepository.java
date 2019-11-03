package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Role;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface RoleRepository extends Neo4jRepository<Role, Long> {
    Role findByType(String name);

    @Query("match (n:Role)<-[r:HAS_ROLE]-(a:User) " +
            "where ID(a)= {0} " +
            "return n")
    List<Role> findRolesByUsersId(Long id);
}
