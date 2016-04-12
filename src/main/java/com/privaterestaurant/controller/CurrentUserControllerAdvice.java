package com.privaterestaurant.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.privaterestaurant.service.CurrentUser;

/**
 * Store current user for show at page.
 *
 * @author Sergey Stotskiy
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null
            : (CurrentUser) authentication.getPrincipal();
    }

}