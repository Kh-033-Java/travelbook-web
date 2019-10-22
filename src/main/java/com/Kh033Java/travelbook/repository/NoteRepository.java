package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Note;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface NoteRepository extends Neo4jRepository<Note, Long> {
    List<Note> findAll();
}
