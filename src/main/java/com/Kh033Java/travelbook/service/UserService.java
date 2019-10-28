package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.dto.UserDto;
import com.Kh033Java.travelbook.entity.User;

import java.util.List;



public interface UserService {

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(String login);

    User updateUser(String login , UserDto user);

    User saveUser(User user);
}
