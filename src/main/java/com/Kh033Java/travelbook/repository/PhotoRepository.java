package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Photo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends Neo4jRepository<Photo, Long> {
    @Query("Match (c:Country{name:{countryName}})-[v:CONTAINS]->(city:City)<-[cr:DESCRIBES]-(n:Note)-[h:HAS_PHOTO]->(ph:Photo) where n.isPublic=true return ph")
    List<Photo> findAllPublicPhotosForUnauthorized(@Param("countryName") String countryName);

    @Query("Match(p:User)-[vis:VISITED]->(c:Country)-[v:CONTAINS]->(city:City)<-[cr:DESCRIBES]-(n:Note)-[h:HAS_PHOTO]->(ph:Photo) where c.name={countryName} and(n.isPublic=true or (p.login ={userLogin} and n.isPublic=false) ) return ph")
    List<Photo> findAllPublicPhotosForAuthorized(@Param("countryName") String countryName, @Param("userLogin") String userLogin);

    @Query("Match(p:User)-[vis:VISITED]->(c:Country)-[v:CONTAINS]->(city:City)<-[cr:DESCRIBES]-(n:Note)-[h:HAS_PHOTO]->(ph:Photo) where c.name={countryName} and p.login ={userLogin} return ph")
    List<Photo> findAllUsersPhotosInCountry(@Param("countryName") String countryName, @Param("userLogin") String userLogin);

    Photo findPhotoByLink(@Param("link")String link);

    @Query("Match (n:Photo{link:{link}})<-[r:HAS_AVATAR]-(u:User{login:{login}}) delete r delete n")
    void deletePhoto(@Param("link") String link, @Param("login") String login);
}
