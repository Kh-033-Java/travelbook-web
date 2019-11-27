package com.Kh033Java.travelbook.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.Kh033Java.travelbook.entity.Message;

/**
 * @author Anatolii Melchenko
 *
 */
public interface MessageRepository extends Neo4jRepository<Message, Long> {

    @Query("")
    void saveMessage();
	
}
