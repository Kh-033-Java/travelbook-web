package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.entity.Note;
import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.entity.Plan;
import com.Kh033Java.travelbook.repository.CountryRepository;
import com.Kh033Java.travelbook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;
    private UserRepository userRepository;

    public CountryServiceImpl(CountryRepository countryRepository, UserRepository userRepository) {
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
    }

    public Set<Note> getNotesByCountryByUser(String countryName, String login) {
        return countryRepository.findByName(countryName)
                .getUsersVisited()
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .get()
                .getCreatedNotes();
    }

    @Override
    public Set<Plan> getPlansByCountryByUser(String countryName, String login) {
        return countryRepository
                .findByName(countryName)
                .getCities()
                .stream()
                .flatMap(city -> userRepository
                        .getUserByLogin(login)
                        .getCreatedPlans()
                        .stream()
                        .filter(plan -> plan == city.getPlanTo()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Photo> getPhotosByCountryByUser(String countryName, String login) {
        Set<Photo> photos = new HashSet<>();
        Set<Note> notes = getNotesByCountryByUser(countryName, login);
        if (notes != null) {
            notes.stream()
                    .filter(Objects::nonNull)
                    .forEach(note -> photos.addAll(note.getPhotos()));
        }
        return photos;
    }
}
