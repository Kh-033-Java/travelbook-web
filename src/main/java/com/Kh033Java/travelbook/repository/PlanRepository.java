package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.model.Plan;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PlanRepository extends Neo4jRepository<Plan, Long> {
    List<Plan> findAll();
}
