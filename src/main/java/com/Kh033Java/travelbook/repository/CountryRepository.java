package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.model.Country;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface CountryRepository extends Neo4jRepository<Country, Long> {
    List<Country> findAll();
}
