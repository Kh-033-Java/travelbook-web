package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Plan;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PlanRepository extends Neo4jRepository<Plan, Long> {

    @Query("MATCH (c:Country)-[:CONTAINS]->(city:City)<-[gt:GO_TO]-(plan:Plan) " +
            "WHERE c.name={country} AND plan.isPublic=true return plan")
    List<Plan> findAllPublicPlansByCountry(@Param("country") String countryName);

    @Query("MATCH (c:Country)-[:CONTAINS]-(city:City)-[:GO_TO]-(plan:Plan)-[:CREATED_PLAN]-(u:User) " +
            "WHERE c.name={country} AND u.login={login} AND plan.isPublic=false return plan")
    List<Plan> findAllUserPrivatePlansByCountry(@Param("country") String name, @Param("login") String login);

    @Query("MATCH (c:Country)-[:CONTAINS]-(city:City)-[:GO_TO]-(plan:Plan)-[:CREATED_PLAN]-(u:User) " +
            "WHERE c.name={country} AND u.login={login} AND plan.isPublic=true return plan")
    List<Plan> findAllUserPublicPlansByCountry(@Param("country") String name, @Param("login") String login);

    Plan findById(@Param("id") long id);

    @Query("MATCH (u:User),(p:Plan) WHERE u.login={login} AND ID(p)={id} CREATE (u)-[r:CREATED_PLAN]->(p)")
    void creatRelationshipBetweenUserAndPlan(@Param("login") String login, @Param("id") long planId);

    @Query("MATCH (n:Plan) WHERE n.isPublic RETURN n")
    List<Plan> getPublicPlans();

}