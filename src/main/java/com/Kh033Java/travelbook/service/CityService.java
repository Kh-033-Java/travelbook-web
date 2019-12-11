package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.entity.City;

import java.util.List;

public interface CityService {

    List<City> getAllCitiesByCountryName(String name);
}
