package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.dto.PlanDTO;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.repository.PlanRepository;
import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Autowired
    public PlanServiceImpl(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Set<PlanDTO> getCountryPlansAndUserPrivatePlans(String countryName, String login) {
        Set<PlanDTO> allPlans = new HashSet<>();
        List<Plan> countryPlans = planRepository.findAllPublicPlansByCountry(countryName);
        List<Plan> userPrivatePlans = planRepository.findAllUserPrivatePlansByCountry(countryName, login);
        for (Plan plan : countryPlans)
            allPlans.add(createPlanDTO(plan));
        for (Plan plan : userPrivatePlans)
            allPlans.add(createPlanDTO(plan));

        return allPlans;
    }

    @Override
    public PlanDTO getPlanById(Long id) {
        Plan plan = ValidationUtil.checkBeforeGet(planRepository.findById(id), id, Plan.class);
        return createPlanDTO(plan);
    }

    @Override
    public List<PlanDTO> getPublicAndPrivateUserPlansConnectedToCountry(String countryName, String login) {
        List<PlanDTO> allPlans = new ArrayList<>();
        List<Plan> publicPlans = planRepository.findAllUserPublicPlansByCountry(countryName, login);
        List<Plan> privatePlans = planRepository.findAllUserPrivatePlansByCountry(countryName, login);
        for (Plan plan : publicPlans)
            allPlans.add(createPlanDTO(plan));
        for (Plan plan : privatePlans)
            allPlans.add(createPlanDTO(plan));

        return allPlans;
    }

    @Override
    public List<PlanDTO> getPlansConnectedToCountryForUnauthorizedUser(String name) {
        List<PlanDTO> plans = new ArrayList<>();
        for (Plan plan : planRepository.findAllPublicPlansByCountry(name)) {
            PlanDTO planDTO = createPlanDTO(plan);
            plans.add(planDTO);
        }
        return plans;
    }

    @Override
    public void updatePlan(PlanDTO plan) {
        //TODO
    }

    @Override
    public void editPlan(PlanDTO plan) {
        //TODO
    }

    @Override
    public void deletePlan(long id) {
        planRepository.deleteById(id);
    }

    private PlanDTO createPlanDTO(Plan plan) {
        Plan planToDTO = planRepository.findById(plan.getId()).get();
        PlanDTO planDTO = new PlanDTO();
        planDTO.setId(planToDTO.getId());
        planDTO.setAmountOfPeople(planToDTO.getAmountOfPeople());
        planDTO.setBudgetMax(planToDTO.getBudgetMax());
        planDTO.setBudgetMin(planToDTO.getBudgetMin());
        planDTO.setDate(planToDTO.getDate());
        planDTO.setDescription(planToDTO.getDescription());
        planDTO.setNameCityFrom(planToDTO.getCityFrom().getName());
        planDTO.setNameCityToGo(planToDTO.getCityToGo().getName());
        planDTO.setTransportType(planToDTO.getTransport().getType());
        planDTO.setTitle(planToDTO.getTitle());
        planDTO.setUserLoginCreator(userRepository.findUserByPlanId(planToDTO.getId()).getLogin());
        return planDTO;
    }
}
