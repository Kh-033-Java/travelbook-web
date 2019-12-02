package com.Kh033Java.travelbook.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kh033Java.travelbook.dto.UserDto;
import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Photo;
import com.Kh033Java.travelbook.entity.Role;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.exception.NotFoundException;
import com.Kh033Java.travelbook.repository.CountryRepository;
import com.Kh033Java.travelbook.repository.NoteRepository;
import com.Kh033Java.travelbook.repository.PhotoRepository;
import com.Kh033Java.travelbook.repository.RoleRepository;
import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.responseForm.UserResponseForm;
import com.Kh033Java.travelbook.service.UserService;
import com.Kh033Java.travelbook.validation.ValidationUtil;


@Service

public class UserServiceImpl implements UserService {

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String DEFAULT_PHOTO = "https://storage.googleapis.com/travelbook/2019-10-30 (2).png";


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PhotoRepository photoRepository;
    private final NoteRepository noteRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, PhotoRepository photoRepository, NoteRepository noteRepository, CountryRepository countryRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.photoRepository = photoRepository;
        this.noteRepository = noteRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    @Cacheable("User")
    public List<UserResponseForm> getAll() {
        List<User> listUsers = (List<User>) userRepository.findAll();
        List<UserResponseForm> resultList = new ArrayList<>();
        for (User user : listUsers) {
            UserResponseForm result = new UserResponseForm();
            result.setSumOfPosts(userRepository.sumOfPosts(user.getLogin()));
            result.setLogin(user.getLogin());
            result.setAvatar(user.getAvatar());
            result.setSumOfLikes(userRepository.sumOfLikes(user.getLogin()));
            result.setHomeland(user.getHomeland());
            resultList.add(result);
        }
        log.info("IN getAll - {} users found", listUsers.size());
        return resultList;
    }

    @Override
    @Cacheable("User")
    public List<UserResponseForm> userRating() {
        List<UserResponseForm> allUsers = getAll();
        Collections.sort(allUsers, UserResponseForm.COMPARE_BY_SUM_OF_LIKES);
        return allUsers;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> result = userRepository.findByLogin(username);
        ValidationUtil.checkBeforeGet(result, User.class);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public void delete(String login) {
        Photo defaultPhoto = photoRepository.findPhotoByLink(DEFAULT_PHOTO);
        Optional<User> user = userRepository.findByLogin(login);
        ValidationUtil.checkBeforeGet(user, User.class);

        if (!user.get().getAvatar().equals(defaultPhoto)) {
            photoRepository.delete(user.get().getAvatar());
        }

        userRepository.deleteNotesAndPlansByUserLogin(login);
        userRepository.delete(user.get());
        log.info("IN delete - user with login: {} successfully deleted", login);
    }

    @Override
    @Transactional
    public User updateUser(final String login, final UserDto user) {
        Optional<User> currentUser = userRepository.findByLogin(login);
        User result = user.toUser();
        Photo defaultPhoto = photoRepository.findPhotoByLink(DEFAULT_PHOTO);
        Country homeland = countryRepository.getCountryByName(user.getHomeland().getName());
        if(homeland == null){
            throw new NotFoundException("Country not found");
        }
        ValidationUtil.checkBeforeGet(currentUser, User.class);

        if (user.getPassword() != null) {
            result.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getAvatar() == null) {

            if (!currentUser.get().getAvatar().equals(defaultPhoto)) {
                photoRepository.deletePhoto(currentUser.get().getAvatar().getLink(), user.getLogin());
            }
            result.setAvatar(defaultPhoto);
            log.info("Users avatar: {}", result.getAvatar());
        } else if (user.getAvatar().getLink().equals(currentUser.get().getAvatar().getLink())) {
            result.setAvatar(currentUser.get().getAvatar());
        } else {
            result.setAvatar(user.getAvatar());
        }
        result.setLogin(user.getLogin());
        result.setVisitedCountries(currentUser.get().getVisitedCountries());
        result.setLikedNotes(currentUser.get().getLikedNotes());
        result.setCreatedNotes(currentUser.get().getCreatedNotes());
        result.setCreatedPlans(currentUser.get().getCreatedPlans());
        result.setRoles(currentUser.get().getRoles());
        result.setLikedNotes(currentUser.get().getLikedNotes());
        result.setFollowing(userRepository.getFollowing(user.getLogin()));
        result.setFollowers(userRepository.getFollowers(user.getLogin()));
        result.setHomeland(currentUser.get().getHomeland());

        userRepository.delete(currentUser.get());

        return userRepository.save(result);
    }

    @Override
    @Transactional
    public User saveUser(final UserDto user) {
        Role roleUser = roleRepository.findByType("USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        Country homeland = countryRepository.getCountryByName(user.getHomeland().getName());

        User result = user.toUser();

        if (user.getAvatar() == null) {
            Photo defaultAvatar = photoRepository.findPhotoByLink(DEFAULT_PHOTO);
            result.setAvatar(defaultAvatar);
        }

        result.setHomeland(homeland);
        result.setPassword(passwordEncoder.encode(user.getPassword()));
        result.setRoles(userRoles);

        log.info("IN register - user: {} successfully registered", result.getLogin());
        userRepository.save(result);

        if(homeland != null){
            addVisitedCountry(result.getLogin(), homeland.getName());
        }

        return result;
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

    @Override
    @Transactional
    public User addFollowing(String loginFriend, String loginOwner){
        return userRepository.createRelationshipBetweenUsers(loginFriend, loginOwner);
    }

    @Override
    @Transactional
    public User deleteFollowing(String loginFriend, String loginOwner){
        return userRepository.deleteRelationshipBetweenUsers(loginFriend, loginOwner);
    }

    @Override
    @Transactional
    public List<UserResponseForm> getFollowing(String login) {
        List<User> listUsers = userRepository.getFollowing(login);
        return doResponseForm(listUsers);
    }

    @Override
    @Transactional
    public List<UserResponseForm> getFollowers(String login) {
        List<User> listUsers = userRepository.getFollowers(login);
        return doResponseForm(listUsers);
    }

    @Override
    @Transactional
    public List<UserResponseForm> getFriends(String login) {
        List<User> listUsers = userRepository.getFriends(login);
        return doResponseForm(listUsers);
    }

    private List<UserResponseForm> doResponseForm(List<User> users) {
        List<UserResponseForm> resultList = new ArrayList<>();
        for (User u : users) {
            UserResponseForm result = new UserResponseForm();
            result.setLogin(u.getLogin());
            result.setAvatar(photoRepository.findUserAvatarByUserId(u.getLogin()));
            resultList.add(result);
        }
        return resultList;
    }
}
