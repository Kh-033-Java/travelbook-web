package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.model.User;
import com.Kh033Java.travelbook.util.exception.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * User service.
 */
public interface UserService {

    /**
     * Returns user list entity.
     *
     * @return user list
     */
    Iterable<User> getAllUsers();

    /**
     * Returns user entity by id.
     *
     * @param id user identifier
     * @return user entity by id
     * @throws NotFoundException if user not found in db
     */
    User getUser(Long id) throws NotFoundException;

    /**
     * Create new user.
     *
     * @param user user entity.
     * @return created user
     */
    User saveUser(User user);

    /**
     * Update user by id.
     *
     * @param id   user identifier
     * @param user user entity
     * @return updated user
     * @throws NotFoundException if user not found in db
     */
    User updateUser(Long id, User user) throws NotFoundException;

    /**
     * Removing user entity by id from database.
     *
     * @param id user identifier
     * @throws NotFoundException if user not found in db
     */
    void deleteUser(Long id) throws NotFoundException;

    /**
     * Auth user by user name.
     *
     * @param username user name
     * @throws UsernameNotFoundException if user not found in db
     */
    AuthorizedUser loadUserByUsername(String username) throws UsernameNotFoundException;
}
