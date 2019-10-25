package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Note;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface NoteRepository extends Neo4jRepository<Note, Long> {
	
    List<Note> findAll();

    @Query("match (n:Note) where n.isPublic=true  return n")
	List<Note> getPublicNotes();
}
