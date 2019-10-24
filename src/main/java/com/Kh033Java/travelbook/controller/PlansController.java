package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.dto.PlanDTO;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.service.PlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "")
public class PlansController {

    private final PlanServiceImpl planService;

    @Autowired
    public PlansController(PlanServiceImpl planService) {
        this.planService = planService;
    }

    @GetMapping(value = "country/{name}/plans")
    public List<PlanDTO> getAllCountryPlansWithDTO(@PathVariable String name) {
        return planService.getPlansConnectedToCountryForUnauthorizedUser(name);
    }

    @GetMapping(value = "country/{name}/plans/{login}")
    public Set<PlanDTO> getAllCountryPlansAuthorized(@PathVariable String name, @PathVariable String login) {
        return planService.getCountryPlansAndUserPrivatePlans(name, login);
    }

    @GetMapping(value = "country/plans/{id}")
    public PlanDTO getPlanWithDTO(@PathVariable Long id) {
        return planService.getPlanById(id);
    }

    @GetMapping(value = "country/{name}/plans/private")
    public List<PlanDTO> getAllUsersPlans(@PathVariable String name, @RequestParam String user) {
        return planService.getPublicAndPrivateUserPlansConnectedToCountry(name, user);
    }

    @PostMapping(value = "country/{name}/planes")
    public Plan savePlan() {
        return null;//TODO
    }

    @DeleteMapping(value = "plans/{id}")
    public void deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
    }

    @PutMapping(value = "country/{name}/planes/{id}")
    public Plan editPlan(@PathVariable Long id) {
        return null;//TODO
    }

}
