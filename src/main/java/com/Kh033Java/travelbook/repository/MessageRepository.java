package com.Kh033Java.travelbook.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.Kh033Java.travelbook.entity.Message;

/**
 * @author Anatolii Melchenko
 *
 */
@Repository
public interface MessageRepository extends Neo4jRepository<Message, Long> {

//    @Query("")
//    void saveMessage();
	
}
