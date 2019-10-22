package com.Kh033Java.travelbook.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface DescriptionRepository extends Neo4jRepository<DescriptionRepository, Long> {
    List<DescriptionRepository> findAll();
}
