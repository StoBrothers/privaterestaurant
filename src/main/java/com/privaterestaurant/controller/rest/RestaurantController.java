package com.privaterestaurant.controller.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.privaterestaurant.service.RestaurantService;

/**
 * Restaurant controller.
 * 
 * @author Sergey Stotskiy
 */

@RestController
@RequestMapping("/rest/")
public class RestaurantController {

	@Autowired
	RestaurantService  restaurantService;
	
    @PreAuthorize("hasAuthority('RESTAURANT_VIEW')")
    @RequestMapping(value = "restaurant/", method = GET)
    @ResponseBody
    public Object get() {
        return restaurantService.getAllRestaurants();
    }
}
