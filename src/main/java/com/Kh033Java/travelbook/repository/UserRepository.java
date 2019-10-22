package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.model.User;
import com.Kh033Java.travelbook.util.exception.NotFoundException;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;


/**
 * User repository.
 */
@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    default User findByName(String name){
        for (User user : findAll()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw  new NotFoundException(String.format("User with name [%s] not found", name));
    }


}
