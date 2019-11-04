package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.entity.Plan;

import java.util.List;
import java.util.stream.Collectors;

public interface CountryService {

    Country getByName(String name);

    List<Country> getVisitedCountries(String userLogin);

    List<Country> getPlannedCountries(String userLogin);

    List<Plan> getPlansByCountryByUser(String countryName, String login);

    List<Photo> getPhotosByCountryByUser(String countryName, String login);

    List<Note> getNotesByCountryByUser(String countryName, String login);
}
