package com.Kh033Java.Kh033Java.travelbook.endpoints;

import com.Kh033Java.Kh033Java.travelbook.model.User;
import com.Kh033Java.Kh033Java.travelbook.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/user/")
    public ResponseEntity<Iterable<User>> listAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping(value = "/user/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }


    @PostMapping(value = "/user/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }


    @PutMapping(value = "/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
        return userService.deleteUser(id);
    }


    @DeleteMapping(value = "/user/")
    public ResponseEntity<User> deleteAllUsers() {
        return userService.deleteAllUsers();
    }
}
