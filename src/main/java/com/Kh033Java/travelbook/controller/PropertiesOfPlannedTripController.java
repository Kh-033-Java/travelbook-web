package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.entity.City;
import com.Kh033Java.travelbook.entity.Transport;
import com.Kh033Java.travelbook.service.CityService;
import com.Kh033Java.travelbook.service.TransportService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PropertiesOfPlannedTripController {

	private final CityService cityService;

	private final TransportService transportService;

	public PropertiesOfPlannedTripController(CityService cityService, TransportService transportService) {
		this.cityService = cityService;
		this.transportService = transportService;
	}

	@RequestMapping(value = "/country/{countryName}/cities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<City> getCountryCities(@PathVariable String countryName) {
		return cityService.getAllCitiesByCountryName(countryName);
	}

	@RequestMapping(value = "/transport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transport> getTransport() {
		return transportService.getAllTransport();
	}

}
