package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Country;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends Neo4jRepository<Country, Long> {
    List<Country> findAll();
}
