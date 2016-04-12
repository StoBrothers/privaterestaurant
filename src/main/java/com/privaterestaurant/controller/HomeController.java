package com.privaterestaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.privaterestaurant.service.CurrentUser;

/**
 * Home controller.
 *
 * @author Sergey Stotskiy
 *
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String getHomePage(@ModelAttribute CurrentUser currentUser) {
        if (currentUser == null) {
            return "redirect:/login";
        }
        return "home";
    }
}
