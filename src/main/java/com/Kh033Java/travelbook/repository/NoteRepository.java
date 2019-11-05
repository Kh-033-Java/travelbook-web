package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Note;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends Neo4jRepository<Note, Long> {

    @Query("MATCH (c:Country)<-[v:VISITED]-(u:User)-[cr:CREATED_NOTE]->(n:Note) WHERE n.isPublic=true AND c.name={countryName} return n")
    List<Note> findAllPublicNotesForUnauthorizedUser(@Param("countryName") String countryName);

    @Query("MATCH (c:Country)<-[v:VISITED]-(u:User)-[cr:CREATED_NOTE]->(n:Note) WHERE n.isPublic=true or (u.login = {userLogin} and n.isPublic=false) and c.name={countryName} return n")
    List<Note> findAllNotesForAuthorizedUser(@Param("countryName") String countryName, @Param("userLogin") String userLogin);

    @Query("MATCH (n:Note) where ID(n)={noteId}  return n")
    Note findPublicNoteById(@Param("noteId") int noteId);

    @Query("MATCH (u:User) where u.login = {userLogin}" +
            "CREATE (c:City {city: {describedCity}})<-[:DESCRIBES]-(n:Note {title: {titleName}, isPublic:{privacyValue}, description:{description}, dateOfVisiting:{dateOfVisiting}," +
            "peopleEstimate:{peopleEstimate}, pricesEstimate:{pricesEstimate}, cuisineEstimate:{cuisineEstimate}, commonImpression:{commonImpression}, likes:0, " +
            "photoLink:{photoLink}, userLogin:{userLogin}, describedCity:{describedCity}})<-[cr:CREATED_NOTE]-(u)" +
            "CREATE (n)-[:HAS_PHOTO]->(ph:Photo {photoLink: {photoLink}})")
    void createNote(@Param("countryName") String countryName, @Param("titleName") String titleName, @Param("privacyValue") boolean privacyValue,
                    @Param("description") String description, @Param("dateOfVisiting") String dateOfVisiting, @Param("peopleEstimate") Integer peopleEstimate,
                    @Param("pricesEstimate") Integer pricesEstimate, @Param("cuisineEstimate") Integer cuisineEstimate, @Param("commonImpression") Integer commonImpression,
                    @Param("describedCity") String describedCity, @Param("userLogin") String userLogin, @Param("photoLink") String photoLink);


    @Query("MATCH (u:User {login: {login}})-[cr:CREATED_NOTE]->(n:Note) WHERE u.login = {login} AND ID(n)={noteId} AND (n.isPublic=false OR n.isPublic=true) return n")
    List<Note> findNotesByIdByUser(@Param("noteId") int noteId, @Param("login") String login);

    @Query("MATCH (u:User {login: {login}})-[cr:CREATED_NOTE]->(n:Note) WHERE ID(n)={noteId} AND u.login = {login} DETACH DELETE n")
    void findNoteForDelete(@Param("noteId") int noteId, @Param("login") String login);

    @Query("MATCH (n:Note) WHERE ID(n)={noteId} " +
            "SET n.title = {titleName}, n.isPublic = {privacyValue}, n.description = {description}, n.dateOfVisiting = {dateOfVisiting}," +
            "n.peopleEstimate = {peopleEstimate}, n.pricesEstimate = {pricesEstimate}, n.cuisineEstimate = {cuisineEstimate}, n.commonImpression = {commonImpression}, " +
            "n.describedCity = {describedCity}, n.photoLink = {photoLink} return n")
    Note findNoteForEdit(@Param("noteId") int noteId, @Param("titleName") String titleName, @Param("privacyValue") boolean privacyValue, @Param("description") String description,
                         @Param("dateOfVisiting") String dateOfVisiting, @Param("peopleEstimate") Integer peopleEstimate, @Param("pricesEstimate") Integer pricesEstimate,
                         @Param("cuisineEstimate") Integer cuisineEstimate, @Param("commonImpression") Integer commonImpression, @Param("describedCity") String describedCity,
                         @Param("photoLink") String photoLink);

    @Query("MATCH (u:User),(n:Note) WHERE u.login={login} AND ID(n)={noteId} SET n.likes = (n.likes + 1)" +
            "CREATE (u)-[:LIKED]->(n)")
    void findNoteForLike(@Param("login") String login, @Param("noteId") int noteId);

    List<Note> findAll();

    @Query("Match (c:Country)<-[v:VISITED]-(p:User)-[cr:CREATED]->(n:Note) where p.login ={userLogin}  and c.name={countryName} return n")
    List<Note> findAllUsersNotesInCountry(@Param("countryName") String countryName, @Param("userLogin") String userLogin);
}
