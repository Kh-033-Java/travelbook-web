package com.Kh033Java.travelbook.controller.map_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.service.CountryService;
import com.Kh033Java.travelbook.service.NoteService;
import com.Kh033Java.travelbook.service.PlanService;

@RestController
public class MapController {
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	PlanService planService;
	
	@Autowired
	NoteService noteService;

	@RequestMapping(value = "users/{login}/map", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Country> getUserMap(@PathVariable String login) {
		return countryService.getVisitedCountries(login);
	}
	
	@RequestMapping(value = "users/{login}/map/notes/public", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Note> getNotes(@PathVariable String login) {
		return noteService.getNotes();
	}
	
	@RequestMapping(value = "users/{login}/map/plans/public", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Plan> getPlannedTrips(@PathVariable String login) {
		return planService.getPlannedTrips();
	}

}
