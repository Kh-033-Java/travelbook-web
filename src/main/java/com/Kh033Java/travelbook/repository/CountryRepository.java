package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Country;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends Neo4jRepository<Country, Long> {
	
    List<Country> findAll();
    
    @Query("MATCH (c:Country) WHERE c.name={countryName} RETURN c")
    Country getCountryByName(@Param("countryName") String countryName);

    @Query("match (c:Country)<-[v:VISITED]-(u:User) where u.login={userLogin} return c")
	  List<Country> getCountriesVisitedByUser(@Param(value = "userLogin") String userLogin);

    @Query("match (u:User)-[cr:CREATED_PLAN]->(p:Plan)-[gt:GO_TO]->(city:City)<-[con:CONTAINS]-(country:Country) where u.login={userLogin} return country") 
	  List<Country> getCountriesThatUserPlansToVisit(@Param(value = "userLogin") String userLogin);
    
}
