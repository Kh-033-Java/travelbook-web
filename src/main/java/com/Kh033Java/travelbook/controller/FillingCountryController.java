package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class FillingCountryController {

    private final UserRepository userRepository;

    @Autowired
    public FillingCountryController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping(value = "/country/{name}/visits")
    public void editVisitedCountryForUser(@PathVariable String name, @RequestParam String user){
        userRepository.creatRelationshipBetweenUserAndCountry(user, name);
    }
}
