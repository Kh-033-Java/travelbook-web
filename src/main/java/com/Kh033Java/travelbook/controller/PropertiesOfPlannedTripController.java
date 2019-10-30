package com.Kh033Java.travelbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kh033Java.travelbook.entity.City;
import com.Kh033Java.travelbook.entity.Transport;
import com.Kh033Java.travelbook.service.CityService;
import com.Kh033Java.travelbook.service.TransportService;
//todo wtf?
@RestController
public class PropertiesOfPlannedTripController {

	@Autowired
	CityService cityService;

	@Autowired
	TransportService transportService;

	@RequestMapping(value = "/country/{countryName}/cities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<City> getCountryCities(@PathVariable String countryName) {
		return cityService.getAllCitiesByCountryName(countryName);
	}

	@RequestMapping(value = "/transport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transport> getTransport() {
		return transportService.getAllTransport();
	}

}
