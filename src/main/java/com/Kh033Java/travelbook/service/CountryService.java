package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.repository.CountryRepository;
import com.Kh033Java.travelbook.repository.NoteRepository;
import com.Kh033Java.travelbook.repository.PhotoRepository;
import com.Kh033Java.travelbook.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final PlanRepository planRepository;
    private final PhotoRepository photoRepository;
    private final NoteRepository noteRepository;

    public CountryService(CountryRepository countryRepository, PlanRepository planRepository, PhotoRepository photoRepository, NoteRepository noteRepository) {
        this.countryRepository = countryRepository;
        this.planRepository = planRepository;
        this.photoRepository = photoRepository;
        this.noteRepository = noteRepository;
    }

    public Country getByName(String name) {
        return countryRepository.getCountryByName(name);
    }

    public List<Country> getVisitedCountries(String userLogin) {
        return countryRepository.getCountriesVisitedByUser(userLogin);

    }

    public List<Country> getPlannedCountries(String userLogin) {
        return countryRepository.getCountriesThatUserPlansToVisit(userLogin);
    }

    public List<Plan> getPlansByCountryByUser(String countryName, String login) {
        return planRepository.findAllUserPublicPlansByCountry(countryName, login);
    }

    public List<Photo> getPhotosByCountryByUser(String countryName, String login) {
        return photoRepository.findAllUsersPhotosInCountry(countryName, login);
    }

    public List<Note> getNotesByCountryByUser(String countryName, String login) {
        return noteRepository.findAllUsersNotesInCountry(countryName,login);
    }
}