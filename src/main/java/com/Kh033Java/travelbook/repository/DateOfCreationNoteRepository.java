package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.DateOfCreationNote;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface DateOfCreationNoteRepository extends Neo4jRepository<DateOfCreationNote, Long> {
    List<DateOfCreationNote> findAll();
}
