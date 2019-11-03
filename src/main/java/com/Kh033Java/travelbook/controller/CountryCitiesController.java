package com.Kh033Java.travelbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kh033Java.travelbook.entity.City;
import com.Kh033Java.travelbook.service.CityService;;

/**
 * 
 * @author Anatolii Melchenko
 *
 */
@RestController
public class CountryCitiesController {

	private final CityService cityService;

	@Autowired
	public CountryCitiesController(CityService cityService) {
		this.cityService = cityService;
	}

	@RequestMapping(value = "/country/{countryName}/cities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<City> getCountryCities(@PathVariable String countryName) {
		return cityService.getAllCitiesByCountryName(countryName);
	}

}
