package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.model.User;
import com.Kh033Java.travelbook.repository.UserRepository;
import com.Kh033Java.travelbook.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.Kh033Java.travelbook.util.ValidationUtil.checkBeforeGet;

/**
 * Default implementation of UserService interface.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    /**
     * User repository.
     */
    private final UserRepository userRepository;

    /**
     * Autowired repository fields.
     *
     * @param userRepository repository of user
     */
    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User getUser(final Long id) {
        return checkBeforeGet(userRepository.findById(id), id, User.class);
    }

    @Override
    @Transactional
    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(final Long id, final User user) {
        User currentUser = checkBeforeGet(userRepository.findById(id), id, User.class);
        userRepository.delete(currentUser);
        currentUser.setName(user.getName());
        return userRepository.save(currentUser);
    }

    @Override
    @Transactional
    public void deleteUser(final Long id) {
        checkBeforeGet(userRepository.findById(id), id, User.class);
        userRepository.deleteById(id);
    }


    @Override
    @Transactional
    public AuthorizedUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByName(username);
        if (user.isEmpty()) {
            throw new NotFoundException(String.format("user by name [%s] not found", username));
        }
        return new AuthorizedUser(user.get());
    }
}
