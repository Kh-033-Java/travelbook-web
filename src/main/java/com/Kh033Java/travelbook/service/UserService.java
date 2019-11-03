package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.dto.UserDto;
import com.Kh033Java.travelbook.entity.Role;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.exception.NotFoundException;
import com.Kh033Java.travelbook.responseForm.UserResponseForm;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<UserResponseForm> getAll();

    List<User> getAllUsers();

    User setRole(String login, Role role);

    User getUser(String login) throws NotFoundException;

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    void delete(String login);

    User updateUser(String login , UserDto user);

    User saveUser(User user);

    void addVisitedCountry(String login, String countryName);
}
