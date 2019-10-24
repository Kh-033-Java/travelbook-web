package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.entity.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends Neo4jRepository<Plan, Long> {

    List<Plan> findAll();

    @Query("match (c:Country)-[:CONTAINS]->(city:City)<-[gt:GO_TO]-(plan:Plan) " +
            "WHERE c.name={country} AND plan.isPublic=true return plan")
    List<Plan> findAllPublicPlansByCountry(@Param("country") String countryName);

    @Query("match (c:Country)-[:CONTAINS]-(city:City)-[:GO_TO]-(plan:Plan)-[:CREATED_PLAN]-(u:User) " +
            "WHERE c.name={country} AND u.login={login} AND plan.isPublic=false return plan")
    List<Plan> findAllUserPrivatePlansByCountry(@Param("country") String name, @Param("login") String login);

    @Query("match (c:Country)-[:CONTAINS]-(city:City)-[:GO_TO]-(plan:Plan)-[:CREATED_PLAN]-(u:User) " +
            "WHERE c.name={country} AND u.login={login} AND plan.isPublic=true return plan")
    List<Plan> findAllUserPublicPlansByCountry(@Param("country") String name, @Param("login") String login);

    Plan findById(@Param("id") long id);

}
