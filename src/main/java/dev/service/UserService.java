package dev.service;

import dev.exception.NotFoundException;
import dev.model.Role;
import dev.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User setRole(Long id, Role role);

    User getUser(Long id) throws NotFoundException;

    User saveUser(User user);

    User updateUser(Long id, User user) throws NotFoundException;

    void deleteUser(Long id) throws NotFoundException;
}
