package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.dto.PlanDTO;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class PlansController {

    private static final Logger LOG = LoggerFactory.getLogger(CountryController.class);

    private final PlanService planService;

    @Autowired
    public PlansController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping(value = "/country/{name}/plans")
    @ResponseStatus(HttpStatus.OK)
    public List<PlanDTO> getAllCountryPlansWithDTO(@PathVariable String name) {
        return planService.getPlansConnectedToCountryForUnauthorizedUser(name);
    }

    @GetMapping(value = "country/{name}/plans/{login}")
    @ResponseStatus(HttpStatus.OK)
    public Set<PlanDTO> getAllCountryPlansAuthorized(@PathVariable String name, @PathVariable String login) {
        return planService.getCountryPlansAndUserPrivatePlans(name, login);
    }

    @GetMapping(value = "/country/plans/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlanDTO getPlanWithDTO(@PathVariable Long id) {
        return planService.getPlanById(id);
    }

    @GetMapping(value = "/country/{name}/plans/private")
    @ResponseStatus(HttpStatus.OK)
    public List<PlanDTO> getAllUsersPlans(@PathVariable String name, @RequestParam String user) {
        return planService.getPublicAndPrivateUserPlansConnectedToCountry(name, user);
    }

    @PostMapping(value = "/plans")
    public Plan savePlan(@RequestBody PlanDTO planDTO) {
        return planService.save(planDTO);
    }

    @DeleteMapping(value = "/plans/{id}")
    public void deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
    }

    @PutMapping(value = "/plans/{id}")
    public Plan editPlan(@RequestBody PlanDTO planDTO, @PathVariable Long id) {
        return planService.updatePlan(planDTO, id);
    }

    @GetMapping(value = "/user/{login}/recommendation/plans")
    @ResponseStatus(HttpStatus.OK)
    public List<PlanDTO> getAllPlans(@PathVariable String login) {
        LOG.info("get all plans by user {}", login);
        return planService.getAllPlans();
    }

    @GetMapping(value = "/user/{login}/plans")
    @ResponseStatus(HttpStatus.OK)
    public List<PlanDTO> getAllUserPlans(@PathVariable String login){
        return planService.getAllUserPlans(login);
    }
}
