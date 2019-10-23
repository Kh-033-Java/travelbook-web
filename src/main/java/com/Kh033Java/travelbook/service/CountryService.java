package com.Kh033Java.travelbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.repository.CountryRepository;

@Service
public class CountryService {
	
    @Autowired
    CountryRepository countryRepository;

    public Country getByName(String name) {
        return countryRepository.getCountryByName(name);
    }

}
