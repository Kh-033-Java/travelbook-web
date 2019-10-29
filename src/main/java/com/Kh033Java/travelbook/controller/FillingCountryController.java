package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController()
public class FillingCountryController {

    private final UserRepository userRepository;

    @Autowired
    public FillingCountryController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping(value = "/country/{name}/visits", produces = MediaType.APPLICATION_JSON_VALUE)
    public void editVisitedCountryForUser(@PathVariable String country, @RequestParam String login){
        userRepository.creatRelationshipBetweenUserAndCountry(login, country);
    }
}
