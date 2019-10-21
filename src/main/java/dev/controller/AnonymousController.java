package dev.controller;

import dev.exception.NotFoundException;
import dev.model.User;
import dev.repository.UserRepository;
import dev.userDetails.TokenProvider;
import dev.userDetails.requestUserDetails.RequestDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
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
        User user = userRepository.getUserByUsername(requestDetail.getUsername());

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
