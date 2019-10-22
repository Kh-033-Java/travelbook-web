package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.model.Role;
import com.Kh033Java.travelbook.model.User;
import com.Kh033Java.travelbook.repository.RoleRepository;
import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.userDetails.DetailsForAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.Kh033Java.travelbook.validation.ValidationUtil.checkBeforeGet;

@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final String ADMIN = "Admin";
    private final String USER = "User";
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository
                           , BCryptPasswordEncoder passwordEncoder
    ) {
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
    public User setRole(Long id, Role role) {
        List<Role> roleList = new ArrayList<>();
        Role roleResult = roleRepository.findByType(role.getType());
        roleList.add(roleResult);
        User user = checkBeforeGet(userRepository.findById(id),id, User.class);
        user.setRoles(roleList);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User getUser(final Long id) {
        List<Role> userRoleNames = roleRepository.findRolesByUsersId(id);
        User result = checkBeforeGet(userRepository.findById(id), id, User.class);
        result.setRoles(userRoleNames);
        return result;
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
    public User updateUser(final Long id, final User user) {
        User currentUser = checkBeforeGet(userRepository.findById(id), id, User.class);
        userRepository.delete(currentUser);
        currentUser.setLogin(user.getLogin());
        return userRepository.save(currentUser);
    }

    @Override
    @Transactional
    public void deleteUser(final Long id) {
        checkBeforeGet(userRepository.findById(id), id, User.class);
        userRepository.deleteById(id);
    }
}