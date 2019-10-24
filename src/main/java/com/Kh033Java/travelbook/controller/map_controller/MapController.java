package com.Kh033Java.travelbook.controller.map_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.service.CountryService;

@RestController
public class MapController {
	
	@Autowired
	CountryService countryService;

	@RequestMapping(value = "users/{login}/map", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Country> getCountryCities(@PathVariable String login) {
		return countryService.getVisitedCountries(login);
	}

}
