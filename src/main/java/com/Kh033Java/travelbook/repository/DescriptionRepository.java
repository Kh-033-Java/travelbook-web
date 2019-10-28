package com.Kh033Java.travelbook.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.Kh033Java.travelbook.entity.Description;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DescriptionRepository extends Neo4jRepository<Description, Long> {
	
    List<Description> findAll();

    @Query("MATCH (country:Country)-[has:HAS_DESCRIPTION]->(desc:Description) WHERE country.name={countryName} RETURN desc")
	Description getDesccriptionByCountryName(@Param("countryName") String countryName);
    
}
