package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.City;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CityRepository extends Neo4jRepository<City, Long> {	
	
    List<City> findAll();    
    
    @Query("MATCH (country:Country)-[contains:CONTAINS]->(city:City) WHERE country.name={countryName} RETURN city")
    List<City> findAllByCountryName(@Param("countryName") String countryName); 
    
}
