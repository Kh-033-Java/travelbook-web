package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.dto.UserDto;
import com.Kh033Java.travelbook.entity.Role;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.exception.NotFoundException;
import com.Kh033Java.travelbook.responseForm.UserResponseForm;
import com.Kh033Java.travelbook.service.UserService;
import com.Kh033Java.travelbook.validation.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value = "/users/")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/allUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<UserResponseForm> getAllUsers() {
        LOG.info("get all users");
        return userService.getAll();
    }

    @GetMapping(value = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("login") final String login) {
        LOG.info("get use by login {}", login);

        Optional<User> user = userService.findByUsername(login);

        ValidationUtil.checkBeforeGet(user, User.class);

        return user.get();
    }

    @PutMapping(value = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable("login") final String login, @RequestBody final UserDto user) {
        LOG.info("update user by login {}, with user {} params", login, user);
        return userService.updateUser(login, user);
    }


    @DeleteMapping(value = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("login") final String login) {
        LOG.info("Removing user with login {}", login);
        userService.delete(login);
    }
}
