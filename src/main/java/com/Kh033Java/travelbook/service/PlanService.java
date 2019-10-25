package com.Kh033Java.travelbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.repository.PlanRepository;

@Service
public class PlanService {
	
    @Autowired
    PlanRepository planRepository;

	public List<Plan> getPlannedTrips() {
		return planRepository.getPublicPlans();
	}

}
