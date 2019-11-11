package com.Kh033Java.travelbook.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import com.Kh033Java.travelbook.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Kh033Java.travelbook.dto.PlanDTO;
import com.Kh033Java.travelbook.dto.PlanSearchDTO;
import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.service.CountryService;
import com.Kh033Java.travelbook.service.PlanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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


    /**
    HTTP Request Example: /plans/search?searchDTO={"budgetMin": 600,"budgetMax": 30000,"minDate":"2019-01-01","maxDate":"2019-03-01","amountOfPeopleMin":1,"amountOfPeopleMax":3,"transportType":"Car","cityFrom":"Nice","cityGoTo":"Krakow"}
     */

    @GetMapping(value = "plans/search")
    @ResponseStatus(HttpStatus.OK)
    public List<PlanDTO> findPlanWithFilters(@RequestParam(value = "searchDTO") String searchDTO){//(@RequestParam int budget_min, @RequestParam int budget_max, @RequestParam String date_min, @RequestParam String date_max, @RequestParam int amount_of_people_min, @RequestParam int amount_of_people_max, @RequestParam String transport, @RequestParam String start_city, @RequestParam String end_city) {
        PlanSearchDTO planSearchDto = new PlanSearchDTO();
        try {
          planSearchDto = new ObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd")).readValue(searchDTO, PlanSearchDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

         return planService.getPlansWithFilter(planSearchDto);
    }

    @GetMapping(value = "country/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Country findCountryByName(@PathVariable("name") String countryName) {
        return countryService.getByName(countryName);
    }

    @GetMapping(value = "country/getAllCountries")
    @ResponseStatus(HttpStatus.OK)
    public List<Country> getAllCountries(){
        return countryService.getAll();
    }


}