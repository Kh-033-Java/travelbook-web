package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.dto.PlanDTO;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.service.PlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@RestController
public class PlansController {

    private final PlanServiceImpl planService;

    @Autowired
    public PlansController(PlanServiceImpl planService) {
        this.planService = planService;
    }

    @GetMapping(value = "country/{name}/plans")
    @ResponseStatus(HttpStatus.OK)
    public List<PlanDTO> getAllCountryPlansWithDTO(@PathVariable String name) {
        return planService.getPlansConnectedToCountryForUnauthorizedUser(name);
    }

    @GetMapping(value = "country/{name}/plans/{login}")
    @ResponseStatus(HttpStatus.OK)
    public Set<PlanDTO> getAllCountryPlansAuthorized(@PathVariable String name, @PathVariable String login) {
        return planService.getCountryPlansAndUserPrivatePlans(name, login);
    }

    @GetMapping(value = "country/plans/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlanDTO getPlanWithDTO(@PathVariable Long id) {
        return planService.getPlanById(id);
    }

    @GetMapping(value = "country/{name}/plans/private")
    @ResponseStatus(HttpStatus.OK)
    public List<PlanDTO> getAllUsersPlans(@PathVariable String name, @RequestParam String user) {
        return planService.getPublicAndPrivateUserPlansConnectedToCountry(name, user);
    }

    @GetMapping(value = "country/plans/search")
    @ResponseStatus(HttpStatus.OK)
    public List<PlanDTO> findSpecificPlans(@RequestParam int budget_min, @RequestParam int budget_max, @RequestParam String date_min, @RequestParam String date_max, @RequestParam int amount_of_people_min, @RequestParam int amount_of_people_max, @RequestParam String transport, @RequestParam String start_city, @RequestParam String end_city){
        return planService.getSpecificPlan(budget_min,budget_max,date_min,date_max,amount_of_people_min,amount_of_people_max,transport,start_city,end_city);
    }


    @PostMapping(value = "plans")
    @ResponseStatus(HttpStatus.CREATED)
    public Plan savePlan(@RequestBody PlanDTO planDTO) {
        return planService.save(planDTO);
    }

    @DeleteMapping(value = "plans/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
    }

    @PutMapping(value = "plans/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Plan editPlan(@RequestBody PlanDTO planDTO, @PathVariable Long id) {
        return planService.updatePlan(planDTO, id);
    }
}
