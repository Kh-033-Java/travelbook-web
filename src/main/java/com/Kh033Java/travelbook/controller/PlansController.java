package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.dto.PlanDTO;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class PlansController {

    private final PlanService planService;

    @Autowired
    public PlansController(PlanService planService) {
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

    @PostMapping(value = "plans")
    public Plan savePlan(@RequestBody PlanDTO planDTO) {
        return planService.save(planDTO);
    }

    @DeleteMapping(value = "plans/{id}")
    public void deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
    }

    @PutMapping(value = "plans/{id}")
    public Plan editPlan(@RequestBody PlanDTO planDTO, @PathVariable Long id) {
        return planService.updatePlan(planDTO, id);
    }
}
