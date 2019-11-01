package com.Kh033Java.travelbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.repository.CountryRepository;

@Service
public class CountryService {
	
    private CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	public Country getByName(String name) {
        return countryRepository.getCountryByName(name);
    }
    
    public List<Country> getVisitedCountries(String userLogin) {
		return countryRepository.getCountriesVisitedByUser(userLogin);
    	
    }

	public List<Country> getPlannedCountries(String userLogin) {
		return countryRepository.getCountriesThatUserPlansToVisit(userLogin);
	}

}
