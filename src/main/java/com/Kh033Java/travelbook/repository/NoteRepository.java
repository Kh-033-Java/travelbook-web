package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Note;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends Neo4jRepository<Note, Long> {

    @Query("MATCH (c:Country)-[:CONTAINS]->(city:City)<-[gt:DESCRIBES]-(note:Note) " +
            "WHERE c.name={country} AND note.isPublic=true return note")
    List<Note> findAllPublicNotesByCountry(@Param("country") String countryName);

    @Query("MATCH (c:Country)-[:CONTAINS]-(city:City)-[:DESCRIBES]-(note:Note)-[:CREATED_NOTE]-(u:User) " +
            "WHERE c.name={country} AND u.login={login} AND note.isPublic=false return note")
    List<Note> findAllUserPrivateNotesByCountry(@Param("country") String name, @Param("login") String login);

    @Query("MATCH (c:Country)-[:CONTAINS]-(city:City)-[:DESCRIBES]-(note:Note)-[:CREATED_NOTE]-(u:User) " +
            "WHERE c.name={country} AND u.login={login} AND note.isPublic=true return note")
    List<Note> findAllUserPublicNotesByCountry(@Param("country") String name, @Param("login") String login);

    @Query("MATCH (u:User),(n:Note) WHERE u.login={login} AND ID(n)={id} CREATE (u)-[r:CREATED_NOTE]->(n)")
    void creatRelationshipBetweenUserAndNote(@Param("login") String login, @Param("id") long noteId);

    @Query("Match (c:Country)<-[v:VISITED]-(p:User)-[cr:CREATED]->(n:Note) where p.login ={userLogin}  and c.name={countryName} return n")
    List<Note> findAllUsersNotesInCountry(@Param("countryName") String countryName, @Param("userLogin") String userLogin);

    @Query("match (n:Note), (u:User{login: {login}})\n" +
            "where id(n)={id}\n" +
            "create unique (u)-[r:LIKED]->(n)\n" +
            "return n, r, u")
    Note findNoteForLike(@Param("login")String login,@Param("id") int id);

    @Query("MATCH (u:User{login:{login}})-[r:LIKED]->(n:Note)\n" +
            "where id(n)={id}\n" +
            "delete r")
    void disLikeNote(@Param("login")String login,@Param("id") int id);

    @Query("match (n:Note)<-[r:LIKED]-() where id(n)={id} return count(r) as count")
    int findNumberOfLikes(@Param("id")int id);

    @Query("MATCH (u:User{login:{login}})-[r:LIKED]->(n:Note)\n" +
            "where id(n)={id}\n" +
            "return n")
    Note checkIfExistLike(@Param("login") String login, @Param("id") int id);

}
