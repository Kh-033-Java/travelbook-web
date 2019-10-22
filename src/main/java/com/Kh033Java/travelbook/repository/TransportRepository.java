package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.model.Transport;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface TransportRepository extends Neo4jRepository<Transport, Long> {
    List<Transport> findAll();
}
