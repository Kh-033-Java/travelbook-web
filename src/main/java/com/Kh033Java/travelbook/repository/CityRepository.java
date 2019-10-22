package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.model.City;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface CityRepository extends Neo4jRepository<City, Long> {
    List<City> findAll();
}
