package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.model.Photo;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PhotoRepository extends Neo4jRepository<Photo, Long> {
    List<Photo> findAll();
}
