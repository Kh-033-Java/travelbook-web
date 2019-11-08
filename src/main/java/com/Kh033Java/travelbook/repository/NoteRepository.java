package com.Kh033Java.travelbook.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.Kh033Java.travelbook.entity.Note;

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
}
