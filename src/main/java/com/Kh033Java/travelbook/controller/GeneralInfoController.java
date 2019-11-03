package com.Kh033Java.travelbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kh033Java.travelbook.dto.CountryGeneralInfoDTO;
import com.Kh033Java.travelbook.dto.WeatherDTO;
import com.Kh033Java.travelbook.entity.Description;
import com.Kh033Java.travelbook.service.CountryService;
import com.Kh033Java.travelbook.service.DescriptionService;
import com.Kh033Java.travelbook.util.JsonConverter;
import com.Kh033Java.travelbook.util.WeatherProvider;

/**
 * 
 * @author Anatolii Melchenko
 *
 */
@RestController
public class GeneralInfoController {
	
	private final DescriptionService descriptionService;
	
	@Autowired
	public GeneralInfoController(CountryService countryService, DescriptionService descriptionService) {
		this.descriptionService = descriptionService;
	}

	@RequestMapping(value = "/country/{countryName}/description", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getGeneralInfo(@PathVariable String countryName) {
		final Description description = descriptionService.getDescriptionByCountryName(countryName);
		final WeatherDTO weather = getWeatherInCapital(description);
		final CountryGeneralInfoDTO countryGeneralInfoDTO = new CountryGeneralInfoDTO(countryName, description, weather);
		return JsonConverter.convertToJson(countryGeneralInfoDTO);
	}
	
	public WeatherDTO getWeatherInCapital(Description description) {
		final WeatherProvider weatherProvider = new WeatherProvider(description.getCapital());
		return new WeatherDTO(weatherProvider.getTemperature(), weatherProvider.getWeatherDescription());
	}

}
