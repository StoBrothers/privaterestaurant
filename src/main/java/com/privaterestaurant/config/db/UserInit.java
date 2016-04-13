
package com.privaterestaurant.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.privaterestaurant.domain.User;
import com.privaterestaurant.domain.UserRepository;
import com.privaterestaurant.util.ApplicationProperties;

/**
 * Initialization Users.
 * 
 * @author Sergey Stotskiy
 */
@Component
@DependsOn({ "applicationProperties" })
public class UserInit extends AbstractInit {

    /**
     * 12345
     */
    private static final String PASS_DEMO = "$2a$10$lGQ0FEvIVA/6mJor8rYK.eUpbPqFuKbkDzdk4BI13E/2.YD8uOeaO";

    /**
     * 123
     */
    private static final String PASS_TEST = "$2a$10$lGQ0FEvIVA/6mJor8rYK.eOoHmvi9lp84lnbMV1098cgL4agQNM/i";

    @Autowired
    UserRepository userRepository;

    @Override
    protected void init() {
        String userPass = ApplicationProperties.isTestserver() ? PASS_TEST : PASS_DEMO;

        create("admin", "admin@privaterestaurant.com", userPass, true);
        // -------------------
        create("app1", "app1@privaterestaurant.com", userPass, false);
        create("app2", "app2@privaterestaurant.com", userPass, false);
    }

    /**
     * Create one User
     * 
     * @param logonName
     * @param email
     * @param passwordHash
     * @param adminRules
     */
    private void create(String logonName, String email, String passwordHash,
        Boolean adminRules) {
        User user = new User();
        user.setLogonName(logonName);
        user.setPasswordHash(passwordHash);
        user.setAdminRules(adminRules);
        userRepository.save(user);
    }
}
