package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Country;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends Neo4jRepository<Country, Long> {
	
    List<Country> findAll();
    
    @Query("MATCH (n) WHERE n.name={countryName} RETURN n")
    Country getCountryByName(@Param("countryName") String countryName);

    @Query("match (c:Country)<-[v:VISITED]-(u:User)-[cr:CREATED_NOTE]->(n:Note) where u.login={userLogin} return c")
	List<Country> getCountriesVisitedByUser(@Param(value = "userLogin") String userLogin);
    
}
