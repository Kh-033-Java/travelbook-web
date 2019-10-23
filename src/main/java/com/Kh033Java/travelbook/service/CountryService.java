package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.entity.Plan;

import java.util.Set;

public interface CountryService {

    Set<Note> getNotesByCountryByUser(String countryName, String login);

    Set<Plan> getPlansByCountryByUser(String countryName, String login);

    Set<Photo> getPhotosByCountryByUser(String countryName, String login);
}
