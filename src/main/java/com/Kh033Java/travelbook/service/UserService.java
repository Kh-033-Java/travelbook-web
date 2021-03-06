package com.Kh033Java.travelbook.service;

import java.util.List;
import java.util.Optional;

import com.Kh033Java.travelbook.dto.UserDto;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.responseForm.UserResponseForm;

public interface UserService {

    List<UserResponseForm> getAll();

    Optional<User> findByUsername(String username);

    void delete(String login);

    User updateUser(String login, UserDto user);

    void addVisitedCountry(String login, String countryName);

    void deleteVisitedCountry(String login, String countryName);

    User saveUser(UserDto user);

    User addFollowing(String loginFriend, String loginOwner);

    User deleteFollowing(String loginFriend, String loginOwner);

    List<UserResponseForm> getFollowing(String loginOwner);

    List<UserResponseForm> getFollowers(String loginOwner);

    List<UserResponseForm> getFriends(String loginOwner);

    List<UserResponseForm> userRating();
}

