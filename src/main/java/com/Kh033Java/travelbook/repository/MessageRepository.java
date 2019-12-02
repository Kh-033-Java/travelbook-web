package com.Kh033Java.travelbook.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Kh033Java.travelbook.entity.Message;

/**
 * @author Anatolii Melchenko
 *
 */
@Repository
public interface MessageRepository extends Neo4jRepository<Message, Long> {
	
	@Query("MATCH (u:User), (m:Message) " + 
			"WHERE u.login = {sender} AND ID(m) = {id}" + 
			"CREATE (u) - [v:SENDED] -> (m)")
	void creatRelationshipBetweenSenderAndMessage(@Param("sender") String sender, @Param("id") long messageId);
	
	@Query("MATCH (u:User), (m:Message) " + 
			"WHERE u.login = {receiver} AND ID(m) = {id}" + 
			"CREATE (u) - [v:RECEIVED] -> (m)")
	void creatRelationshipBetweenReceiverAndMessage(@Param("receiver") String receiver, @Param("id") long messageId);

	@Query("MATCH (us:User)-[s:SENDED]->(m:Message)<-[r:RECEIVED]-(ur:User)" + 
			"WHERE us.login = {login} AND ur.login = {interlocutor}" + 
			"RETURN m")
	List<Message> getUserSendedMessages(String login, String interlocutor);	
	
	@Query("MATCH (us:User)-[s:SENDED]->(m:Message)<-[r:RECEIVED]-(ur:User)" + 
			"WHERE us.login = {interlocutor} AND ur.login = {login}" + 
			"RETURN m")
	List<Message> getUserReceivedMessages(String login, String interlocutor);	
	
}
