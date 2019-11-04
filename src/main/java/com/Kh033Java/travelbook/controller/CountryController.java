package com.Kh033Java.travelbook.controller;


import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private static final Logger LOG = LoggerFactory.getLogger(CountryController.class);


    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = "{name}/notes/profile/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getNotesByCountryByUser(@PathVariable("name") final String countryName, @PathVariable("login") String login) {
        LOG.info("get notes by country {}, by user {}", countryName, login);
        return countryService.getNotesByCountryByUser(countryName, login);
    }

    @GetMapping(value = "{name}/plans/profile/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Plan> getPlansByCountryByUser(@PathVariable("name") final String countryName, @PathVariable("login") String login) {
        LOG.info("get plans by country {}, by user {}", countryName, login);
        return countryService.getPlansByCountryByUser(countryName, login);
    }

    @GetMapping(value = "{name}/photos/profile/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Photo> getPhotosByCountryByUser(@PathVariable("name") final String countryName, @PathVariable("login") String login) {
        LOG.info("get photos by country {}, by user {}", countryName, login);
        return countryService.getPhotosByCountryByUser(countryName, login);
    }
}
