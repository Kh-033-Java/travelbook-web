package com.Kh033Java.Kh033Java.travelbook.endpoints;

import com.Kh033Java.Kh033Java.travelbook.model.User;
import com.Kh033Java.Kh033Java.travelbook.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    /**
     * User service.
     */
    private final UserService userService;


    /**
     * Autowired service field.
     *
     * @param userService user service.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Endpoint for getting all users.
     *
     * @return ResponseEntity Iterator users
     */
    @GetMapping(value = "/user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get all users", consumes = "application/json")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        LOG.info("get all users");
        return userService.getAllUsers();
    }


    /**
     * Endpoint for getting user by id.
     *
     * @param id user id
     * @return ResponseEntity retrieved user
     */
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get user by id", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "user unique identifier"),
    })
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        LOG.info("get use by id {}", id);
        return userService.getUser(id);
    }


    /**
     * Endpoint for creation user.
     *
     * @param user user entity
     * @return ResponseEntity created user
     */
    @PostMapping(value = "/user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "create user", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", required = true, value = "user entity"),
    })
    public ResponseEntity<User> createUser(@RequestBody User user) {
        LOG.info("create user {}", user);
        return userService.saveUser(user);
    }


    /**
     * Endpoint for update user.
     *
     * @param id   user id
     * @param user user entity
     * @return ResponseEntity updated user
     */
    @PutMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update user by id", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "user unique identifier"),
            @ApiImplicitParam(name = "user", required = true, value = "user entity"),
    })
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        LOG.info("update user by id {}, with user {} params", id, user);
        return userService.updateUser(id, user);
    }

    /**
     * Endpoint for delete user.
     *
     * @param id user id
     * @return ResponseEntity deleted user
     */
    @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove user", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "user unique identifier"),
    })
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
        LOG.info("Removing user id {}", id);
        return userService.deleteUser(id);
    }


    /**
     * Endpoint for delete all users.
     *
     * @return void ResponseEntity
     */
    @DeleteMapping(value = "/user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove all users", consumes = "application/json")
    public ResponseEntity<Void> deleteAllUsers() {
        LOG.info("Removing all users");
        return userService.deleteAllUsers();
    }
}
