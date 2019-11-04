package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.dto.UserDto;
import com.Kh033Java.travelbook.entity.Role;
import com.Kh033Java.travelbook.repository.RoleRepository;
import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.responseForm.UserResponseForm;
import com.Kh033Java.travelbook.validation.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public List<UserResponseForm> getAll() {
        List<User> listUsers = (List<User>) userRepository.findAll();
        List<UserResponseForm> resultList = new ArrayList<>();
        for (User user : listUsers) {
            UserResponseForm result = new UserResponseForm();
            result.setLogin(user.getLogin());
            result.setAvatar(user.getAvatar());
            resultList.add(result);
        }
        log.info("IN getAll - {} users found", listUsers.size());
        return resultList;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> result = userRepository.findByLogin(username);
        ValidationUtil.checkBeforeGet(result, User.class);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> result = userRepository.findById(id);

        ValidationUtil.checkBeforeGet(result, User.class);

        log.info("IN findById - user: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        ValidationUtil.checkBeforeGet(user, User.class);
        userRepository.delete(user.get());
        log.info("IN delete - user with login: {} successfully deleted", login);
    }

    @Override
    @Transactional
    public User updateUser(final String login, final UserDto user) {
        Optional<User> currentUser = userRepository.findByLogin(login);
        User result = user.toUser();

        ValidationUtil.checkBeforeGet(currentUser, User.class);

        if (user.getPassword() != null) {
            result.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        result.setLogin(user.getLogin());
        result.setVisitedCountries(currentUser.get().getVisitedCountries());
        result.setLikedNotes(currentUser.get().getLikedNotes());
        result.setCreatedNotes(currentUser.get().getCreatedNotes());
        result.setCreatedPlans(currentUser.get().getCreatedPlans());
        result.setRoles(currentUser.get().getRoles());

        userRepository.delete(currentUser.get());

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

    @Override
    @Transactional
    public void addVisitedCountry(String login, String countryName) {
        userRepository.creatRelationshipBetweenUserAndCountry(login, countryName);
    }

    @Override
    @Transactional
    public void deleteVisitedCountry(String login, String countryName) {
        userRepository.deleteRelationshipBetweenUserAndCountry(login, countryName);
    }
}
