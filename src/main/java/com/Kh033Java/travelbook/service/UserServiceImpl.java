package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.dto.UserDto;
import com.Kh033Java.travelbook.entity.Role;
import com.Kh033Java.travelbook.exception.NotFoundException;
import com.Kh033Java.travelbook.repository.RoleRepository;
import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service

public class UserServiceImpl implements UserService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        List<User> result = (List<User>) userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByLogin(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(String login) {
        User user = userRepository.findByLogin(login);
        userRepository.delete(user);
        log.info("IN delete - user with login: {} successfully deleted", login);
    }

    @Override
    @Transactional
    public User updateUser(final String login, final UserDto user) {
        User currentUser = userRepository.findByLogin(login);
        User result = new User();

        if (!user.getLogin().equals("null")) {
            result.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getLastName() != null) {
            result.setLastName(user.getLastName());
        }

        if (user.getFirstName() != null) {
            result.setFirstName(user.getFirstName());
        }

        if (user.getEmail() != null) {
            result.setEmail(user.getEmail());
        }

        if (user.getDescription() != null) {
            result.setDescription(user.getDescription());
        }

        if (user.getLogin() != null) {
            result.setLogin(user.getLogin());
        }

        result.setLogin(user.getLogin());
        result.setVisitedCountries(currentUser.getVisitedCountries());
        result.setLikedNotes(currentUser.getLikedNotes());
        result.setCreatedNotes(currentUser.getCreatedNotes());
        result.setCreatedPlans(currentUser.getCreatedPlans());
        result.setRoles(currentUser.getRoles());

        userRepository.delete(currentUser);

        return userRepository.save(result);
    }

    @Override
    @Transactional
    public User saveUser(final User user) {
        Role roleUser = roleRepository.findByType("USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        log.info("IN register - user: {} successfully registered", user);
        return userRepository.save(user);
    }
}
