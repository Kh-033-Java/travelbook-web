package com.Kh033Java.travelbook.controller;
import com.Kh033Java.travelbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Zhelezniak Dmytro
 */

@RestController()
public class FillingCountryController {

    private final UserService userService;

    @Autowired
    public FillingCountryController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/country/{name}/visit")
    public void addVisitedCountryForUser(@PathVariable String name, @RequestParam String user){
        userService.addVisitedCountry(user, name);
    }

    @PostMapping(value = "/country/{name}/notvisit")
    public void deleteVisitedCountryForUser(@PathVariable String name, @RequestParam String user){
        userService.deleteVisitedCountry(user, name);
    }
}
