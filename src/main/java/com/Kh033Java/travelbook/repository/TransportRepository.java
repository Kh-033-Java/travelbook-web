package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Transport;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends Neo4jRepository<Transport, Long> {

    @Query("MATCH (t:Transport) RETURN t")
    List<Transport> findAll();

    Transport findByType(@Param("type") String type);
}