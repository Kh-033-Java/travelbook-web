package com.Kh033Java.travelbook.controller;

import com.Kh033Java.travelbook.exception.NotFoundException;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.userDetails.TokenProvider;
import com.Kh033Java.travelbook.userDetails.requestUserDetails.RequestDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/anonymous")
public class AnonymousController {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AnonymousController(UserRepository userRepository, TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody RequestDetail requestDetail){
        String username = requestDetail.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDetail.getUsername(), requestDetail.getPassword()));
        User user = userRepository.getUserByLogin(requestDetail.getUsername());

        if(user ==null){
            throw new NotFoundException("User not found");
        }

        String token = tokenProvider.createToken(username, user.getRoles());

        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        response.put("passwordInDB", user.getPassword());

        return ResponseEntity.ok(response);
    }

}
