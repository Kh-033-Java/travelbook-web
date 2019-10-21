package dev.repository;

import dev.model.Role;
import dev.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends Neo4jRepository<Role, Long> {

     Role findByName(String role);

     @Query("match (n:Role)<-[r:HAS_ROLE]-(a:User) " +
             "where ID(a)= {0} " +
             "return n")
     List<Role> findAllRolesOfUser(Long id);

}
