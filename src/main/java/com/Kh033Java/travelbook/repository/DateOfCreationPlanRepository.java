package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.DateOfCreationPlan;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface DateOfCreationPlanRepository extends Neo4jRepository<DateOfCreationPlan, Long> {
    List<DateOfCreationPlan> findAll();
}
