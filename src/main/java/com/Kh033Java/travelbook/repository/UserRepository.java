package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends Neo4jRepository<User, Long> {

    Optional<User> findByLogin(String name);

    @Query("match (u:User)-[:CREATED_PLAN]->(p:Plan) where ID(p)={id} return u")
    User findUserByPlanId(@Param("id") long id);

    @Query("match (u:User)-[:CREATED_NOTE]->(n:Note) where ID(n)={id} return u")
    User findUserByNoteId(@Param("id") long id);

    @Query("MATCH (u:User),(c:Country) WHERE u.login={login} AND c.name={country} CREATE (u)-[:VISITED]->(c)")
    void creatRelationshipBetweenUserAndCountry(@Param("login") String login, @Param("country") String name);

    @Query("MATCH (u:User)-[r:VISITED]->(c:Country) WHERE u.login={login} AND c.name={country} DELETE r")
    void deleteRelationshipBetweenUserAndCountry(@Param("login") String login, @Param("country") String name);

    @Query("MATCH (u1:User),(u2:User) WHERE u1.login={loginOwner} AND u2.login={loginFriend} CREATE (u1)-[:FRIENDS]->(u2)")
    void createRelationshipBetweenUsers(@Param("loginOwner") String loginOwner, @Param("loginFriend") String loginFriend);

    @Query("MATCH (u1:User)-[f:FRIENDS]->(u2:User) WHERE u1.login={loginOwner} AND u2.login={loginFriend} DELETE f")
    void deleteRelationshipBetweenUsers(@Param("loginOwner") String loginOwner, @Param("LoginFriend") String loginFriend);

    @Query("MATCH (u:User)-[:FRIENDS]->(friends:User)-[:HAS_AVATAR]-(a:Photo) WHERE u.login={login} RETURN friends")
    List<User> getFollowings(@Param("login") String login);

    @Query("MATCH (u:User)<-[:FRIENDS]-(friends:User) WHERE u.login={login} RETURN friends")
    List<User> getFollowers(@Param("login") String login);

    @Query("MATCH (u:User)<-[:FRIENDS]-(friends:User)<-[:FRIENDS]-(u:User) WHERE u.login={login} RETURN friends")
    List<User> getFriends(@Param("login") String login);

    @Query("MATCH (u:User)-[createdNote:CREATED_NOTE]->(note:Note)-[describes:DESCRIBES]-()\n" +
            "match (u:User)-[createdPlan:CREATED_PLAN]->(plan:Plan)-[relationships]-()\n" +
            "match (u:User)-[hasRole:HAS_ROLE]->()\n" +
            "where u.login={login}\n" +
            "delete hasRole, createdNote, createdPlan, describes, relationships, note, plan")
    void deleteNotesAndPlansByUserLogin(@Param("login") String login);

    @Query("match (n:Note)<-[r:CREATED_NOTE]-(u:User)\n" +
            "match (n)<-[l:LIKED]-()\n" +
            "where u.login={login}\n" +
            "return count(l) as count")
    int sumOfLikes(@Param("login")String login);

    @Query("match (n:Note)<-[r:CREATED_NOTE]-(u:User)\n" +
            "where u.login={login}\n" +
            "return count(n) as count")
    int sumOfPosts(@Param("login")String login);
}

