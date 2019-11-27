package com.Kh033Java.travelbook.service.impl;

import com.Kh033Java.travelbook.dto.PlanDTO;
import com.Kh033Java.travelbook.dto.PlanSearchDTO;
import com.Kh033Java.travelbook.entity.City;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.recommendations.UserLikedNotesFirstAlgorithm;
import com.Kh033Java.travelbook.repository.CityRepository;
import com.Kh033Java.travelbook.repository.PlanRepository;
import com.Kh033Java.travelbook.repository.TransportRepository;
import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.service.PhotoService;
import com.Kh033Java.travelbook.service.PlanService;
import com.Kh033Java.travelbook.validation.ValidationUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final TransportRepository transportRepository;
    private final PhotoService photoService;
    private final UserLikedNotesFirstAlgorithm algorithm;
    private Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);

    public PlanServiceImpl(PlanRepository planRepository,
                           UserRepository userRepository,
                           CityRepository cityRepository,
                           TransportRepository transportRepository,
                           PhotoService photoService,
                           UserLikedNotesFirstAlgorithm algorithm) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.transportRepository = transportRepository;
        this.photoService = photoService;
        this.algorithm = algorithm;
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
        for (PlanDTO plan : getAllPlansNoFiltered()) {
            if (filterPlanBySearchDTO(plan, planSearchDTO) != null)
                planDTOS.add(plan);
        }
        planDTOS.sort(Comparator.comparing(PlanDTO::getDate));
        return planDTOS;
    }

    private PlanDTO filterPlanBySearchDTO(PlanDTO plan, PlanSearchDTO planSearchDTO) {

        if (plan.getBudgetMin() <= planSearchDTO.getBudgetMin()) {
            return null;
        }
        if (plan.getBudgetMax() >= planSearchDTO.getBudgetMax()) {
            return null;
        }

        if (planSearchDTO.getMinDate() != null) {
            if (plan.getDate().compareTo(planSearchDTO.getMinDate()) < 0 || plan.getDate().compareTo(planSearchDTO.getMinDate()) == 0) {
                return null;
            }
        }

        if (planSearchDTO.getMaxDate() != null) {
            if (plan.getDate().compareTo(planSearchDTO.getMaxDate()) > 0 || plan.getDate().compareTo(planSearchDTO.getMinDate()) == 0) {
                return null;
            }
        }

        if (plan.getAmountOfPeople() < planSearchDTO.getAmountOfPeopleMin()) {
            return null;
        }

        if (plan.getAmountOfPeople() > planSearchDTO.getAmountOfPeopleMax()) {
            return null;
        }

        if (planSearchDTO.getTransportType() != null && !planSearchDTO.getTransportType().equals("")) {
            if (!plan.getTransportType().equalsIgnoreCase(planSearchDTO.getTransportType())) {
                return null;
            }
        }
        if (planSearchDTO.getCityFrom() != null && !planSearchDTO.getCityFrom().equals("")) {
            if (!plan.getNameCityFrom().equalsIgnoreCase(planSearchDTO.getCityFrom())) {
                return null;
            }
        }

        if (planSearchDTO.getCityGoTo() != null && !planSearchDTO.getCityGoTo().equals("")) {
            if (!plan.getNameCityToGo().equalsIgnoreCase(planSearchDTO.getCityGoTo())) {
                return null;
            }
        }
        return plan;
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

    @Override
    public List<PlanDTO> getAllFilteredPlans(String login) {
        List<PlanDTO> result = new LinkedList<>();
        List<PlanDTO> anotherPlans = new LinkedList<>();
        List<PlanDTO> plans = planRepository.getPublicPlans()
                .stream()
                .filter(plan -> plan.getDate()
                        .after(new Date()) && plan.getDate()
                        .before(DateUtils.addMonths(new Date(), 1)))
                .map(this::createPlanDTO).collect(Collectors.toList());
        Map<User, Set<City>> users = algorithm.getAlgorithm(login);
        for (Map.Entry<User, Set<City>> entry : users.entrySet()) {
            for (City city : entry.getValue()) {
                for (PlanDTO plan : plans) {
                    if (plan.getNameCityToGo().equals(city.getName())) {
                        result.add(plan);
                        logger.info("added to result, {}", plan.getNameCityToGo());
                    }
                }
            }
        }
        for(PlanDTO plan: plans){
            if(!result.contains(plan) && !anotherPlans.contains(plan)){
                anotherPlans.add(plan);
                logger.info("added to another, {}", plan.getNameCityToGo());
            }
        }
        result.addAll(anotherPlans);
        return result;
    }


    private List<PlanDTO> getAllPlansNoFiltered() {
        return planRepository.getPublicPlans().stream().map(this::createPlanDTO).collect(Collectors.toList());
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

    private boolean isPlanValid(Plan plan) {
        return plan.getDate() != null &&
                plan.getDescription() != null &&
                plan.getTransport().getType() != null &&
                plan.getCityFrom() != null &&
                plan.getCityToGo() != null &&
                plan.getTitle() != null;
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
        planDTO.setNameCityFrom(planToDTO.getCityFrom() == null ? null : planToDTO.getCityFrom().getName());
        planDTO.setNameCityToGo(planToDTO.getCityToGo() == null ? null : planToDTO.getCityToGo().getName());
        planDTO.setTransportType(planToDTO.getTransport() == null ? null : planToDTO.getTransport().getType());
        planDTO.setIsPublic(planToDTO.getIsPublic());
        planDTO.setTitle(planToDTO.getTitle());
        planDTO.setUserLoginCreator(userRepository.findUserByPlanId(planToDTO.getId()).getLogin());
        planDTO.setLinkToUserAvatar(photoService.findUserAvatarByUserLogin(userRepository.findUserByPlanId(planToDTO.getId()).getLogin()).getLink());

        return planDTO;
    }

}
