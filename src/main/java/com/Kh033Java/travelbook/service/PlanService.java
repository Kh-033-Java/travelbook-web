package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.dto.PlanDTO;
import com.Kh033Java.travelbook.entity.Plan;

import java.util.List;
import java.util.Set;

public interface PlanService {

    List<PlanDTO> getPlansConnectedToCountryForUnauthorizedUser(String name);

    Set<PlanDTO> getCountryPlansAndUserPrivatePlans(String countryName, String login);

    PlanDTO getPlanById(Long id);

    List<PlanDTO> getPublicAndPrivateUserPlansConnectedToCountry(String countryName, String login);

    Plan updatePlan(PlanDTO plan, long id);

    void deletePlan(long id);

    Plan save(PlanDTO planDTO);
}
