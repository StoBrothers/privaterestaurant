package com.privaterestaurant.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.privaterestaurant.domain.User;
import com.privaterestaurant.domain.UserRepository;

/**
 * Service for work with user entity.
 * 
 *  @author Sergey Stotskiy
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> updateUser(User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

    @Override
    public Optional<User> getUserByLogonName(String logonName) {
        return userRepository.findOneByLogonName(logonName);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll(new Sort("logonName"));
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Collection<User> getAllAdminUsers() {
        return userRepository.findAllByAdminRulesIsTrue();
    }

}