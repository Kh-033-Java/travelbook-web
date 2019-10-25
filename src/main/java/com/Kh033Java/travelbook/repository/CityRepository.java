package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.City;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends Neo4jRepository<City, Long> {
    List<City> findAll();

    City findByName(@Param("name") String name);
}
