package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Country;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends Neo4jRepository<Country, Long> {
	
    List<Country> findAll();
    
//    @Query("Match (c:Country)<-[v:VISITED]-(p:User) where c.name={countryName} return c,p")
    @Query("MATCH (n) WHERE n.name={countryName} RETURN n")
    Country getCountryByName(@Param("countryName") String countryName);
    
}
