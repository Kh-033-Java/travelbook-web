package com.Kh033Java.travelbook.service.impl;

import java.util.List;

import com.Kh033Java.travelbook.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.City;
import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.repository.CityRepository;
import com.Kh033Java.travelbook.repository.CountryRepository;

@Service
public class CityServiceImpl implements CityService {
	
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    @Cacheable("City")
    public List<City> getAllCitiesByCountryName(String name) {
        return cityRepository.findAllByCountryName(name);
    }

}
