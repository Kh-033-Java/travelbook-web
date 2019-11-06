package com.Kh033Java.travelbook.dto;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;

import com.Kh033Java.travelbook.entity.Country;

/**
 *
 * @author Anatolii Melchenko
 *
 */
@ComponentScan
public class CountryDTO {
	
	private final List<Country> visitedCountries;
    private final List<Country> countriesPlannedToVisit;
    
	public CountryDTO(List<Country> visitedCountries, List<Country> countriesPlannedToVisit) {
		this.visitedCountries = visitedCountries;
		this.countriesPlannedToVisit = countriesPlannedToVisit;
	}

}
