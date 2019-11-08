package com.Kh033Java.travelbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kh033Java.travelbook.dto.CountryGeneralInfoDTO;
import com.Kh033Java.travelbook.dto.WeatherDTO;
import com.Kh033Java.travelbook.entity.Description;
import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.service.DescriptionService;
import com.Kh033Java.travelbook.service.PhotoService;
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
	private final PhotoService photoService;

	@Autowired
	public GeneralInfoController(DescriptionService descriptionService, PhotoService photoService) {
		this.descriptionService = descriptionService;
		this.photoService = photoService;
	}

	@RequestMapping(value = "/country/{countryName}/description", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getGeneralInfo(@PathVariable String countryName) {
		CountryGeneralInfoDTO countryGeneralInfoDTO = new CountryGeneralInfoDTO(countryName, null, null, null);
		final Description description = descriptionService.getDescriptionByCountryName(countryName);
		final WeatherDTO weather = getWeatherInCapital(description);
		final List<Photo> photos = photoService.findAllCountryPhotos(countryName);
		if(description != null) {
			countryGeneralInfoDTO = new CountryGeneralInfoDTO(countryName, description, weather, photos);
		}
		return JsonConverter.convertToJson(countryGeneralInfoDTO);
	}
	
	public WeatherDTO getWeatherInCapital(Description description) {
		final WeatherProvider weatherProvider = new WeatherProvider(description.getCapital());
		return new WeatherDTO(weatherProvider.getTemperature(), weatherProvider.getWeatherDescription());
	}

}
