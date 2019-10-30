package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.dto.PlanDTO;
import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.service.CountryService;
import com.Kh033Java.travelbook.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
    @Author Dmytro Starostin
    @see CountryService
    @see PlanService

 */

@RestController
public class SearchController {

    private final PlanService planService;
    private final CountryService countryService;

    @Autowired
    SearchController(PlanService planService, CountryService countryService) {
        this.planService = planService;
        this.countryService = countryService;
    }


    @GetMapping(value = "plans/search")
    @ResponseStatus(HttpStatus.OK)
    public List<PlanDTO> findPlanWithFilters(@RequestParam int budget_min, @RequestParam int budget_max, @RequestParam String date_min, @RequestParam String date_max, @RequestParam int amount_of_people_min, @RequestParam int amount_of_people_max, @RequestParam String transport, @RequestParam String start_city, @RequestParam String end_city) {
        return planService.getPlansWithFilter(budget_min, budget_max, date_min, date_max, amount_of_people_min, amount_of_people_max, transport, start_city, end_city);
    }

    @GetMapping(value = "country/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Country findCountryByName(@PathVariable("name") String countryName) {
        return countryService.getByName(countryName);
    }


}