package Kh033Java.travelbook.repository;

import Kh033Java.travelbook.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository<User, String> {


}
