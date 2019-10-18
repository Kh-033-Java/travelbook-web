package com.Kh033Java.Kh033Java.travelbook.service;

import com.Kh033Java.Kh033Java.travelbook.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {


    ResponseEntity<Iterable<User>> getAllUsers();

    ResponseEntity<User> getUser(String id);

    ResponseEntity<User>  saveUser(User user);

    ResponseEntity<User> updateUser(String id, User user);

    ResponseEntity<Void> deleteAllUsers();

    ResponseEntity<User> deleteUser(String id);
}
