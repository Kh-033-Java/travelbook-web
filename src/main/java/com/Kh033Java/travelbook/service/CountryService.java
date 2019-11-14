package com.Kh033Java.travelbook.service;

import java.util.List;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.entity.Plan;

public interface CountryService {

    List<Country> getAll();

    Country getByName(String name);

    Country getByMapId(String mapId);

    List<Country> getVisitedCountries(String userLogin);

    List<Country> getPlannedCountries(String userLogin);

    List<Plan> getPlansByCountryByUser(String countryName, String login);

    List<Photo> getPhotosByCountryByUser(String countryName, String login);

    List<Note> getNotesByCountryByUser(String countryName, String login);
}
