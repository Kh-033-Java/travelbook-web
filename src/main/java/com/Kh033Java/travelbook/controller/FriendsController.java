package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.responseForm.UserResponseForm;
import com.Kh033Java.travelbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Zhelezniak Dmytro
 */

@RestController
@RequestMapping(value = "/users/")
public class FriendsController {

    private final UserService userService;

    @Autowired
    public FriendsController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/following")
    public List<UserResponseForm> getFollowing(@RequestParam final String user){
        return userService.getFollowing(user);
    }

    @GetMapping(value = "/followers")
    public List<UserResponseForm> getFollowers(@RequestParam final String user){
        return userService.getFollowers(user);
    }

    @GetMapping(value = "/friends")
    public List<UserResponseForm> getFriends(@RequestParam final String user){
        return userService.getFriends(user);
    }

    @PostMapping(value = "/addfollow/{login}")
    public void addToFollowing(@PathVariable final String login, @RequestParam final String user){
        userService.addFollowing(login, user);
    }

    @PostMapping(value = "/deletefollow/{login}")
    public void deleteFollowing(@PathVariable final String login, @RequestParam final String user) {
        userService.deleteFollowing(login, user);
    }
}
