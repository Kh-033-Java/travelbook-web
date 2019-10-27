package com.Kh033Java.travelbook.repository;

import com.Kh033Java.travelbook.entity.Note;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface NoteRepository extends Neo4jRepository<Note, Long> {

    @Query("MATCH (c:Country)<-[v:VISITED]-(u:User)-[cr:CREATED_NOTE]->(n:Note) WHERE n.isPublic=true AND c.name={countryName} return n")
    List<Note> findAllPublicNotesForUnauthorizedUser(@Param("countryName") String countryName);

    @Query("MATCH (c:Country)<-[v:VISITED]-(u:User)-[cr:CREATED_NOTE]->(n:Note) WHERE n.isPublic=true or (u.login = {userLogin} and n.isPublic=false) and c.name={countryName} return n")
    List<Note> findAllNotesForAuthorizedUser(@Param("countryName") String countryName, @Param("userLogin") String userLogin);

    @Query("MATCH (n:Note) where ID(n)={noteId} AND n.isPublic=true return n")
    List<Note> findPublicNoteById(@Param("noteId") int noteId);

    @Query("MATCH (u:User) where u.login = {login}" +
            "CREATE (c:City {name: {name}})<-[:DESCRIBES]-(n:Note {title: {titleName}, isPublic:{privacyValue}, description:{description}, dateOfVisiting:{dateOfVisiting}," +
            "peopleEstimate:{peopleEstimate}, pricesEstimate:{pricesEstimate}, cuisineEstimate:{cuisineEstimate}, commonImpression:{commonImpression}, likes:0})<-[cr:CREATED_NOTE]-(u)" +
            "CREATE (n)-[:HAS_PHOTO]->(ph:Photo {link: {link}})")
    List<Note> createNote(@Param("countryName") String countryName, @Param("titleName") String titleName, @Param("privacyValue") boolean privacyValue,
                          @Param("description") String description, @Param("dateOfVisiting") Date dateOfVisiting, @Param("peopleEstimate") Integer peopleEstimate,
                          @Param("pricesEstimate") Integer pricesEstimate, @Param("cuisineEstimate") Integer cuisineEstimate, @Param("commonImpression") Integer commonImpression,
                          @Param("name") String name, @Param("login") String login, @Param("link") String link);

    @Query("MATCH (u:User {login: {login}})-[cr:CREATED_NOTE]->(n:Note) WHERE u.login = {login} AND ID(n)={noteId} AND n.isPublic=false return n")
    List<Note> findPrivateNoteById(@Param("noteId") int noteId, @Param("login") String login);

    @Query("MATCH (u:User {login: {login}})-[cr:CREATED_NOTE]->(n:Note) WHERE ID(n)={noteId} AND u.login = {login} DETACH DELETE n")
    List<Note> findNoteForDelete(@Param("noteId") int noteId, @Param("login") String login);

    @Query("MATCH (n:Note) WHERE ID(n)={noteId} AND n.title = {titleName} OR n.isPublic = {privacyValue} OR n.description = {description} OR n.dateOfVisiting = {dateOfVisiting} " +
            "OR n.peopleEstimate = {peopleEstimate}" +
            "OR n.pricesEstimate = {pricesEstimate} OR n.cuisineEstimate = {cuisineEstimate} OR n.commonImpression = {commonImpression} " +
            "SET n.title = {titleName}, n.isPublic = {privacyValue}, n.description = {description}, n.dateOfVisiting = {dateOfVisiting}," +
            "n.peopleEstimate = {peopleEstimate}, n.pricesEstimate = {pricesEstimate}, n.cuisineEstimate = {cuisineEstimate}, n.commonImpression = {commonImpression} return n")
    List<Note> findNoteForEdit(@Param("noteId") int noteId, @Param("titleName") String titleName, @Param("privacyValue") boolean privacyValue, @Param("description") String description,
                               @Param("dateOfVisiting") Date dateOfVisiting, @Param("peopleEstimate") Integer peopleEstimate, @Param("pricesEstimate") Integer pricesEstimate,
                               @Param("cuisineEstimate") Integer cuisineEstimate, @Param("commonImpression") Integer commonImpression);

    @Query("MATCH (u:User),(n:Note) WHERE u.login={login} AND ID(n)={noteId} SET n.likes = (n.likes + 1)" +
            "CREATE (u)-[:LIKED]->(n)")
    List<Note> findNoteForLike(@Param("login") String login, @Param("noteId") int noteId);
}
