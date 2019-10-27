package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.City;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends Neo4jRepository<City, Long> {
    List<City> findAll();
}
