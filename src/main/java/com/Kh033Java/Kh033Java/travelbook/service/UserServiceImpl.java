package com.Kh033Java.Kh033Java.travelbook.service;

import com.Kh033Java.Kh033Java.travelbook.model.User;
import com.Kh033Java.Kh033Java.travelbook.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(String id) {
        System.out.println("Fetching User with id " + id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> saveUser(User user) {
        System.out.println("Creating User " + user.getName());
        if (userRepository.findById(user.getId()).isPresent()) {
            System.out.println("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> updateUser(String id, User user) {
        System.out.println("Updating User " + id);

        Optional<User> currentUser = userRepository.findById(id);
        if (currentUser.isEmpty()) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.delete(currentUser.get());
        currentUser.get().setName(user.getName());
        userRepository.save(currentUser.get());
        return new ResponseEntity<>(currentUser.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteAllUsers() {
        System.out.println("Deleting All Users");
        userRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<User> deleteUser(String id) {
        System.out.println("Fetching & Deleting User with id " + id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
