package com.Kh033Java.travelbook.service;

import java.util.List;
import java.util.Set;

import com.Kh033Java.travelbook.dto.PlanDTO;
import com.Kh033Java.travelbook.dto.PlanSearchDTO;
import com.Kh033Java.travelbook.entity.Plan;

public interface PlanService {

    List<PlanDTO> getPlansConnectedToCountryForUnauthorizedUser(String name);

    Set<PlanDTO> getCountryPlansAndUserPrivatePlans(String countryName, String login);

    PlanDTO getPlanById(Long id);

    List<PlanDTO> getPublicAndPrivateUserPlansConnectedToCountry(String countryName, String login);

    List<PlanDTO> getPlansWithFilter(PlanSearchDTO planSearchDTO);

    Plan updatePlan(PlanDTO plan, long id);

    void deletePlan(long id);

    Plan save(PlanDTO planDTO);

    List<PlanDTO> getAllFilteredPlans(String login);

    List<PlanDTO> getAllUserPlans(String login);
}
