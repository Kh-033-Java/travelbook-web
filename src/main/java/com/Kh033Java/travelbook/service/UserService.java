package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.dto.CountryDTO;
import com.Kh033Java.travelbook.dto.Map;
import com.Kh033Java.travelbook.exception.NotFoundException;
import com.Kh033Java.travelbook.entity.Role;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.userDetails.requestUserDetails.RequestDetail;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User setRole(String login, Role role);

    User getUser(String login) throws NotFoundException;

    User saveUser(User user);

    User updateUser(String login, RequestDetail user) throws NotFoundException;

    void deleteUser(String login) throws NotFoundException;

    CountryDTO getMapByUser(String login);

    User getUserProfile(String login);
}
