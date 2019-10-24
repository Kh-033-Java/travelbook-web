package com.Kh033Java.travelbook.controller.general_info_controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kh033Java.travelbook.entity.City;
import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Description;
import com.Kh033Java.travelbook.repository.CountryRepository;
import com.Kh033Java.travelbook.service.CityService;
import com.Kh033Java.travelbook.service.CountryService;
import com.Kh033Java.travelbook.service.DescriptionService;

@RestController
public class GeneralInfoController {

	@Autowired
	CountryService countryService;

	@Autowired
	CityService cityService;
	
	@Autowired
	DescriptionService descriptioService;

	@RequestMapping(value = "/country/{location}/weather", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Weather getWeather(@PathVariable String location) {
		final WeatherProvider weatherProvider = new WeatherProvider(location);
		return new Weather(weatherProvider.getTemperature(), weatherProvider.getWeatherDescription());
	}

	@RequestMapping(value = "/country/{countryName}/description", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Country getGeneralInfo(@PathVariable String countryName) {
		Country country = countryService.getByName(countryName);
		fillCountryFields(country);
		return country;
	}

	private void fillCountryFields(Country country) {
		
		List<City> cityList = new ArrayList<City>();
		for (City city : cityService.getAllCitiesByCountryName(country.getName())) {
			cityList.add(city);
		}
		country.setCities(cityList);
		
		Description description = descriptioService.getDesccriptionByCountryName(country.getName());
		country.setDescription(description);		
		
	}

	@RequestMapping(value = "/greeting")
	public String getGreeting() {
		return "Hello Traveller!";
	}

}
