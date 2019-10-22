package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.exception.NotFoundException;
import com.Kh033Java.travelbook.entity.Role;
import com.Kh033Java.travelbook.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User setRole(Long id, Role role);

    User getUser(Long id) throws NotFoundException;

    User saveUser(User user);

    User updateUser(Long id, User user) throws NotFoundException;

    void deleteUser(Long id) throws NotFoundException;
}
