package com.Kh033Java.travelbook.controller;
import com.Kh033Java.travelbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class FillingCountryController {

    private final UserService userService;

    @Autowired
    public FillingCountryController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "/country/{name}/visit/{user}")
    public void addVisitedCountryForUser(@PathVariable String name, @RequestParam String user){
        userService.addVisitedCountry(user, name);
    }

    @PutMapping(value = "/country/{name}/notvisit/{user}")
    public void deleteVisitedCountryForUser(@PathVariable String name, @RequestParam String user){
        userService.deleteVisitedCountry(user, name);
    }
}
