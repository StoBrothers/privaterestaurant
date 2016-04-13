package com.privaterestaurant.service;

import java.util.Set;

import org.springframework.security.core.authority.AuthorityUtils;

import com.privaterestaurant.domain.User;
import com.privaterestaurant.security.Role;

/**
 *  Wrapper for authenticated User.
 *  
 *  @author Sergey Stotskiy
 */
@SuppressWarnings("serial")
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getLogonName(), user.getPasswordHash(), true, true, true, true,
            AuthorityUtils.createAuthorityList(
                user.getRoles().stream().flatMap(role -> role.getPermissions().stream())
                    .map(permission -> permission.toString()).toArray(String[]::new)));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    /**
     * Get logon name.
     * @return
     * @see User#getLogonName()
     */
    public String getLogonName() {
        return user.getLogonName();
    }

    /**
     * Get roles.
     * @return
     */
    public Set<Role> getRoles() {
        return user.getRoles();
    }
}
