package com.privaterestaurant.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.privaterestaurant.domain.User;

/**
 * Service for work with user entity.
 * 
 *  @author Sergey Stotskiy
 */
public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> updateUser(User user);

    Optional<User> getUserByLogonName(String logonName);

    Collection<User> getAllUsers();

    Page<User> getAllUsers(Pageable pageable);

    Collection<User> getAllAdminUsers();
}
