package com.Kh033Java.travelbook.service.impl;

import com.Kh033Java.travelbook.dto.PlanDTO;
import com.Kh033Java.travelbook.dto.PlanSearchDTO;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.repository.CityRepository;
import com.Kh033Java.travelbook.repository.PlanRepository;
import com.Kh033Java.travelbook.repository.TransportRepository;
import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.service.PlanService;
import com.Kh033Java.travelbook.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final TransportRepository transportRepository;

    @Autowired
    public PlanServiceImpl(PlanRepository planRepository, UserRepository userRepository, CityRepository cityRepository, TransportRepository transportRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.transportRepository = transportRepository;
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
    public List<PlanDTO> getPlansWithFilter(PlanSearchDTO planSearchDTO) {
        List<PlanDTO> planDTOS = new ArrayList<>();
        for (Plan plan : planRepository.findPlansWithFilter(planSearchDTO.getBudgetMin(), planSearchDTO.getBudgetMax(), planSearchDTO.getMinDate(), planSearchDTO.getMaxDate(), planSearchDTO.getAmountOfPeopleMin(), planSearchDTO.getAmountOfPeopleMax(), planSearchDTO.getTransportType(), planSearchDTO.getCityFrom(), planSearchDTO.getCityGoTo())) {
            planDTOS.add(createPlanDTO(plan));
        }

        return planDTOS;
    }

    @Override
    public List<PlanDTO> getPlansByBudget(int minBudget, int maxBudget) {
        List<PlanDTO> planDTOS = new ArrayList<>();
        for (Plan plan : planRepository.findPlansByBudget(minBudget, maxBudget)) {
            planDTOS.add(createPlanDTO(plan));
        }
        return planDTOS;
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
    public Plan updatePlan(PlanDTO planDTO, long id) {
        Plan plan = planRepository.findById(id);
        dtoToPlan(planDTO, plan);
        planRepository.save(plan);

        return plan;
    }

    @Override
    public void deletePlan(long id) {
        planRepository.deleteById(id);
    }

    @Override
    public Plan save(PlanDTO planDTO) {
        Plan plan = new Plan();
        dtoToPlan(planDTO, plan);
        planRepository.save(plan);
        planRepository.creatRelationshipBetweenUserAndPlan(planDTO.getUserLoginCreator(), plan.getId());

        return plan;
    }

    private void dtoToPlan(PlanDTO planDTO, Plan plan) {
        plan.setAmountOfPeople(planDTO.getAmountOfPeople());
        plan.setBudgetMax(planDTO.getBudgetMax());
        plan.setBudgetMin(planDTO.getBudgetMin());
        plan.goFromCity(cityRepository.findByName(planDTO.getNameCityFrom()));
        plan.goToCity(cityRepository.findByName(planDTO.getNameCityToGo()));
        plan.setDate(planDTO.getDate());
        plan.setDescription(planDTO.getDescription());
        plan.setIsPublic(planDTO.getIsPublic());
        plan.setTitle(planDTO.getTitle());
        plan.chooseTransport(transportRepository.findByType(planDTO.getTransportType()));
    }

    private PlanDTO createPlanDTO(Plan plan) {
        Plan planToDTO = ValidationUtil.checkBeforeGet(planRepository.findById(plan.getId()), plan.getId(), Plan.class);
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
        planDTO.setIsPublic(planToDTO.getIsPublic());
        planDTO.setTitle(planToDTO.getTitle());
        planDTO.setUserLoginCreator(userRepository.findUserByPlanId(planToDTO.getId()).getLogin());

        return planDTO;
    }
}
