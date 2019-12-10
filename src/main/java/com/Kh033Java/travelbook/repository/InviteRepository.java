package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Invite;
import com.Kh033Java.travelbook.entity.User;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InviteRepository extends Neo4jRepository<Invite, Long> {

    @Query("match (i:Invite)<-[r]-(p:Plan)\n" +
            "where id(p)={id}\n" +
            "return i")
    Optional<Invite> findInviteByPlanId(@Param("id") Long id);

    @Query("match (i:Invite)-[r:INVITED]->(u:User)\n" +
            "where id(i)={id}\n" +
            "return u")
    List<User> findAllInvitedUsers(@Param("id")Long id);

    @Query("match (i:Invite)<-[r:JOIN]-(u:User)\n" +
            "where id(i)={id}\n" +
            "return u")
    List<User> findAllJoinedUsers(@Param("id")Long id);

    @Query("match (i:Invite)<-[r:JOIN]-()\n" +
            "where id(i)={id}\n" +
            "return count(r) as count")
    int countCompanions(@Param("id") Long id);
}
