package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.entity.City;
import com.Kh033Java.travelbook.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Zhelezniak Dmytro
 */

@RestController
public class CityController {

    private final CityRepository cityRepository;

    @Autowired
    public CityController(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }

    @GetMapping(value = "/cities")
    public List<City> getAllCities(){
        return cityRepository.findAll();
    }
}
