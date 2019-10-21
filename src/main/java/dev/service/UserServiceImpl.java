package dev.service;

import dev.model.Role;
import dev.model.User;
import dev.repository.RoleRepository;
import dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static dev.validation.ValidationUtil.checkBeforeGet;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final String ADMIN = "ADMIN_ROLE";
    private final String USER = "USER_ROLE";
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
            List<Role> roleNames = roleRepository.findAllRolesOfUser(user.getId());
            user.setRoles(roleNames);
        }
        return result;
    }

    @Override
    public User setRole(Long id, Role role) {
        List<Role> roleList = new ArrayList<>();
        Role roleResult = roleRepository.findByName(role.getName());
        roleList.add(roleResult);
        User user = checkBeforeGet(userRepository.findById(id),id, User.class);
        user.setRoles(roleList);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User getUser(final Long id) {
        List<Role> userRoleNames = roleRepository.findAllRolesOfUser(id);
        User result = checkBeforeGet(userRepository.findById(id), id, User.class);
        result.setRoles(userRoleNames);
        return result;
    }

    @Override
    @Transactional
    public User saveUser(final User user) {
        Role roleUser = roleRepository.findByName("User");
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
        currentUser.setUsername(user.getUsername());
        return userRepository.save(currentUser);
    }

    @Override
    @Transactional
    public void deleteUser(final Long id) {
        checkBeforeGet(userRepository.findById(id), id, User.class);
        userRepository.deleteById(id);
    }


}