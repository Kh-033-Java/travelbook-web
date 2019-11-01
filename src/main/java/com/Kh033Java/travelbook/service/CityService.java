package com.Kh033Java.travelbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.City;
import com.Kh033Java.travelbook.repository.CityRepository;

@Service
public class CityService {
	
    private CityRepository cityRepository;    

    @Autowired
    public CityService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	public List<City> getAllCitiesByCountryName(String name) {
        return cityRepository.findAllByCountryName(name);
    }

}
