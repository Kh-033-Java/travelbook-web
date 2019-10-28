package com.Kh033Java.travelbook.security;

import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("jwtUserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    private final UserService userService;

    @Autowired
    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        DetailsForAuthentication detailsForAuthentication = UserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return detailsForAuthentication;
    }
}
