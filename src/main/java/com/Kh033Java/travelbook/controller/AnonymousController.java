package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.security.TokenProvider;
import com.Kh033Java.travelbook.dto.AuthenticationRequestDto;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.service.UserService;
import com.Kh033Java.travelbook.validation.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(value = "/anonymous/")
public class AnonymousController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);


    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    private final UserService userService;

    @Autowired
    public AnonymousController(AuthenticationManager authenticationManager, TokenProvider tokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            Optional<User> user = userService.findByUsername(username);

            ValidationUtil.checkBeforeGet(user, User.class);

            String token = tokenProvider.createToken(username, user.get().getRoles());

            return formResponse(username, user, token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    private ResponseEntity formResponse(String username, Optional<User> user, String token) {
        Map<Object, Object> response = new HashMap<>();
        response.put("login", username);
        response.put("token", token);
        response.put("avatar", user.get().getAvatar().getLink());
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public User createUser(@RequestBody final UserDto user) {
        LOG.info("Create user {}", user);
        System.out.println("create user " + user);
        return userService.saveUser(user);
    }

}
