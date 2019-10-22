package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.model.Role;
import com.Kh033Java.travelbook.model.User;
import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping(value = "/allUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get all users", consumes = "application/json")
    public Iterable<User> getAllUsers() {
        LOG.info("get all users");
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get user by id", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "user unique identifier"),
    })
    public User getUser(@PathVariable("id") final String id) {
        LOG.info("get use by id {}", id);
        return userService.getUser(Long.valueOf(id));
    }


    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "create user", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", required = true, value = "user entity"),
    })
    public User createUser(@RequestBody final User user) {
        LOG.info("Create user {}", user);
        System.out.println("create user " + user);
        return userService.saveUser(user);
    }



    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update user by id", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "user unique identifier"),
            @ApiImplicitParam(name = "user", required = true, value = "user entity"),
    })
    public User updateUser(@PathVariable("id") final String id, @RequestBody final User user) {
        LOG.info("update user by id {}, with user {} params", id, user);
        return userService.updateUser(Long.valueOf(id), user);
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove user", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "user unique identifier"),
    })
    public void deleteUser(@PathVariable("id") final String id) {
        LOG.info("Removing user id {}", id);
        userService.deleteUser(Long.valueOf(id));
    }

    @PutMapping(value = "/admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update role of user", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "user unique identifier"),
            @ApiImplicitParam(name = "role", required = true, value = "user unique role")
    })
    public void setRole(@PathVariable final String id,@RequestBody final Role role){
        userService.setRole(Long.valueOf(id), role);
    }
}
