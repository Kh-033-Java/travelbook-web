package com.Kh033Java.travelbook.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Kh033Java.travelbook.entity.Note;

@Repository
public interface NoteRepository extends Neo4jRepository<Note, Long> {
	
    List<Note> findAll();

    @Query("match (n:Note) where n.isPublic=true  return n")
	List<Note> getPublicNotes();

    @Query("Match (c:Country)<-[v:VISITED]-(p:User)-[cr:CREATED]->(n:Note) where p.login ={userLogin}  and c.name={countryName} return n")
    List<Note> findAllUsersNotesInCountry(@Param("countryName") String countryName, @Param("userLogin") String userLogin);
}

