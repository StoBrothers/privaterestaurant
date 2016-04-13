package com.privaterestaurant.controller.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.privaterestaurant.domain.User;
import com.privaterestaurant.domain.UserRepository;
import com.privaterestaurant.service.UserService;

/**
 * Change User information.
 *
 * @author Sergey Stotskiy
 *
 */
@RestController
@RequestMapping("/rest/users/")
public class UsersRestController {

    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public UsersRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "{id}/", method = GET)
    public User get(@PathVariable("id") Long id) {
        return userService.getUserById(id).get();
    }

    /**
     * Edit User.
     *
     * @param userId
     *            - user id
     * @param user
     *            - user
     * @return - operation status
     */
    @RequestMapping(value = "edit/", method = RequestMethod.POST)
    public Object editUser(@ModelAttribute User user) {
        try {
            User editedUser = userRepository.findOneById(user.getId()).get();
            editedUser.setLogonName(user.getLogonName());
            editedUser.setNewPassword(user.getNewPassword());
            editedUser.setPasswordHash(getHashPassword(user.getNewPassword()));
            userRepository.save(editedUser);
            return true;
        } catch (DataIntegrityViolationException ex) {
            return "User with this email or login entered in system";
        }
    }

    /**
     * Delete user.
     *
     * @param userId
     *            - user id
     * @return - status operation
     */
    @RequestMapping(value = "delete/{userId}/", method = RequestMethod.POST)
    public Object deleteUser(@PathVariable Long userId) {
        try {
            userRepository.delete(userId);
            return true;
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     * Add new user.
     *
     * @param user
     * 
     * @return - operation status
     */
    @RequestMapping(value = "create/", method = RequestMethod.POST)
    public Object createUser(@ModelAttribute User user) {
        try {
            user.setPasswordHash(getHashPassword(user.getNewPassword()));
            userRepository.save(user);
            return true;
        } catch (DataIntegrityViolationException ex) {
            return "User with this id is existing in DB";
        }
    }

    /**
     * Get coded password.
     *
     * @param password
     *            - pure password
     * @return - encoded password (hashPassword)
     */
    private String getHashPassword(String password) {
        String hashPassword = new BCryptPasswordEncoder().encode(password);
        return hashPassword;
    }

}
