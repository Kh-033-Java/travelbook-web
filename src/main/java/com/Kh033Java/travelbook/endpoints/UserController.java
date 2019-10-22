package com.Kh033Java.travelbook.endpoints;

import com.Kh033Java.travelbook.model.User;
import com.Kh033Java.travelbook.service.AuthorizedUser;
import com.Kh033Java.travelbook.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that contains user-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/users")
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
    public UserController(final UserService userService) {
        this.userService = userService;
    }


    /**
     * Endpoint for getting all users.
     *
     * @return Iterator users
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get all users", consumes = "application/json")
    public Iterable<User> getAllUsers() {
        LOG.info("get all users");
        return userService.getAllUsers();
    }


    /**
     * Endpoint for getting user by id.
     *
     * @param id user id
     * @return retrieved user
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get user by id", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "user unique identifier"),
    })
    public User getUser(@PathVariable("id") final Long id) {
        LOG.info("get use by id {}", id);
        return userService.getUser(id);
    }


    /**
     * Endpoint for creation user.
     *
     * @param user user entity
     * @return created user
     */

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "create user", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", required = true, value = "user entity"),
    })
    public User createUser(@RequestBody final User user) {
        LOG.info("Create user {}", user);
        return userService.saveUser(user);
    }


    /**
     * Endpoint for update user.
     *
     * @param id   user id
     * @param user user entity
     * @return updated user
     */
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update user by id", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "user unique identifier"),
            @ApiImplicitParam(name = "user", required = true, value = "user entity"),
    })
    public User updateUser(@PathVariable("id") final Long id, @RequestBody final User user) {
        LOG.info("update user by id {}, with user {} params", id, user);
        return userService.updateUser(id, user);
    }

    /**
     * Endpoint for delete user.
     *
     * @param id user id
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove user", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "user unique identifier"),
    })
    public void deleteUser(@PathVariable("id") final Long id) {
        LOG.info("Removing user id {}", id);
        userService.deleteUser(id);
    }

    /**
     * Endpoint for login user.
     *
     * @param name user name
     * @return auth user
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "login user", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", required = true, value = "user name"),
    })
    public AuthorizedUser login(@RequestParam final String name) {
        LOG.info("login user name {}", name);
        return userService.loadUserByUsername(name);
    }
}
