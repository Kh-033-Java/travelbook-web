package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.entity.Map;
import com.Kh033Java.travelbook.entity.Role;
import com.Kh033Java.travelbook.entity.User;
import com.Kh033Java.travelbook.repository.RoleRepository;
import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.userDetails.requestUserDetails.RequestDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final String ADMIN = "Admin";
    private final String USER = "User";
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> result = (List<User>) userRepository.findAll();
        for (User user : result) {
            List<Role> roleNames = roleRepository.findRolesByUsersId(user.getId());
            user.setRoles(roleNames);
        }
        return result;
    }

    @Override
    public User setRole(String login, Role role) {
        List<Role> roleList = new ArrayList<>();
        Role roleResult = roleRepository.findByType(role.getType());
        roleList.add(roleResult);
        User user = userRepository.getUserByLogin(login);
        user.setRoles(roleList);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User getUser(final String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    @Transactional
    public User saveUser(final User user) {
        Role roleUser = roleRepository.findByType(USER);
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(final String login, final RequestDetail user) {
        User currentUser = userRepository.getUserByLogin(login);
        User result = new User();

        if (!user.getPassword().equals("null")) {
            result.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getLastName() != null) {
            result.setLastName(user.getLastName());
        }

        if (user.getFirstName() != null) {
            result.setFirstName(user.getFirstName());
        }

        if (user.getEmail() != null) {
            result.setEmail(user.getEmail());
        }

        if (user.getDescription() != null) {
            result.setDescription(user.getDescription());
        }

        if (user.getLogin() != null) {
            result.setLogin(user.getLogin());
        }

        result.setRoles(currentUser.getRoles());
        result.setLogin(login);
        userRepository.delete(currentUser);

        return userRepository.save(result);
    }

    @Override
    @Transactional
    public void deleteUser(final String login) {
        userRepository.deleteUserByLogin(login);
    }

    @Override
    public Map getMapByUser(String login) {
        return userRepository.getUserByLogin(login).getMap();
    }

    @Override
    public User getUserProfile(String login) {
        return userRepository.getUserByLogin(login);
    }
}