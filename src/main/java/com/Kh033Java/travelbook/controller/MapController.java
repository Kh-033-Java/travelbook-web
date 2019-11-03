package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.dto.CountryDTO;
import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.service.CountryService;
import com.Kh033Java.travelbook.util.JsonConverter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapController {
	
	private final CountryService countryService;

	public MapController(CountryService countryService) {
		this.countryService = countryService;
	}

	@RequestMapping(value = "users/{login}/map", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getUserMap(@PathVariable String login) {
		final List<Country> visited = countryService.getVisitedCountries(login);
		final List<Country> plannedForVisit = countryService.getPlannedCountries(login);
		final CountryDTO countryDTO = new CountryDTO(visited, plannedForVisit);
		return JsonConverter.convertToJson(countryDTO);
	}

}
