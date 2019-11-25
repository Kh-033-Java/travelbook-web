package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.responseForm.UserResponseForm;
import com.Kh033Java.travelbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Zhelezniak Dmytro
 */

@RestController
@RequestMapping(value = "/users/")
public class FriendsController {

    private UserService userService;

    @Autowired
    public FriendsController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/following")
    public Iterable<UserResponseForm> getFollowing(@RequestParam final String user){
        return userService.getFollowing(user);
    }

    @GetMapping(value = "/followers")
    public Iterable<UserResponseForm> getFollowers(@RequestParam final String user){
        return userService.getFollowers(user);
    }

    @GetMapping(value = "/friends")
    public Iterable<UserResponseForm> getFriends(@RequestParam final String user){
        return userService.getFriends(user);
    }

    @PutMapping(value = "/addfollow/{login}")
    public ResponseEntity addToFollowing(@PathVariable final String login, @RequestParam final String user){
        return ResponseEntity.ok(userRepository.createRelationshipBetweenUsers(login, user));
    }

    @PutMapping(value = "/deletefollow/{login}")
    public ResponseEntity deleteFollowing(@PathVariable final String login, @RequestParam final String user) {
        return ResponseEntity.ok(userRepository.deleteRelationshipBetweenUsers(login, user));
    }
}
