package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.dto.CountryGeneralInfoDTO;
import com.Kh033Java.travelbook.dto.Weather;
import com.Kh033Java.travelbook.entity.Description;
import com.Kh033Java.travelbook.service.DescriptionService;
import com.Kh033Java.travelbook.util.JsonConverter;
import com.Kh033Java.travelbook.util.WeatherProvider;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralInfoController {


    private final DescriptionService descriptionService;

    public GeneralInfoController(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    @RequestMapping(value = "/country/{countryName}/description", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGeneralInfo(@PathVariable String countryName) {
        final Description description = descriptionService.getDescriptionByCountryName(countryName);
        final Weather weather = getWeatherInCapital(description);
        final CountryGeneralInfoDTO countryGeneralInfoDTO = new CountryGeneralInfoDTO(countryName, description, weather);
        return JsonConverter.convertToJson(countryGeneralInfoDTO);
    }

    public Weather getWeatherInCapital(Description description) {
        final WeatherProvider weatherProvider = new WeatherProvider(description.getCapital());
        return new Weather(weatherProvider.getTemperature(), weatherProvider.getWeatherDescription());
    }

}
