package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Photo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends Neo4jRepository<Photo, Long> {
    @Query("Match (c:Country)<-[v:VISITED]-(p:User)-[cr:CREATED]->(n:Note)-[h:HAS_PHOTO]->(ph:Photo) where n.isPublic=true  and c.name={countryName} return ph")
    List<Photo> findAllPublicPhotosForUnauthorized(@Param("countryName") String countryName);

    @Query("Match (c:Country)<-[v:VISITED]-(p:User)-[cr:CREATED]->(n:Note)-[h:HAS_PHOTO]->(ph:Photo) where n.isPublic=true or (p.login ={userLogin} and c.name={countryName} and n.isPublic=false ) return ph")
    List<Photo> findAllPublicPhotosForAuthorized(@Param("countryName") String countryName, @Param("userLogin") String userLogin);

    @Query("Match (c:Country)<-[v:VISITED]-(p:User)-[cr:CREATED]->(n:Note)-[h:HAS_PHOTO]->(ph:Photo) where p.login ={userLogin}  and c.name={countryName} return ph")
    List<Photo> findAllUsersPhotosInCountry(@Param("countryName") String countryName, @Param("userLogin") String userLogin);
}
