package com.privaterestaurant.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.privaterestaurant.domain.Restaurant;
import com.privaterestaurant.domain.RestaurantRepository;

/**
 * Initialization 3 restaurants with test data.
 * 
 * @author Sergey Stotskiy
 * 
 */
@Component("restaurants")
@DependsOn({ "applicationProperties" })
public class RestorantInit extends AbstractInit {

    @Autowired
    RestaurantRepository<Restaurant> restaurantRepository;

    @Override
    protected void init() {
        create("KFC");
        create("McDonalds");
        create("BurgerKing");
    }

    /**
     * Create one restaurant
     * 
     * @param restaurantName
     */
    private void create(String restaurantName) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantName);
        restaurantRepository.save(restaurant);
    }
}