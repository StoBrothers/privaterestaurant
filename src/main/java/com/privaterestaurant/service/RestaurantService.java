package com.privaterestaurant.service;

import java.util.Collection;
import java.util.Optional;

import com.privaterestaurant.domain.Restaurant;

/**
 * Service for work with Restaurant entity. 
 * 
 * @author Sergey Stotskiy
 *
 */
public interface RestaurantService {
    /**
     * Get restaurant by id.
     * 
     * @param id
     *            indentificator
     * @return Restaurant
     */
    Optional<Restaurant> getRestaurantById(long id);

    /**
     * Add new restaurant.
     * 
     * @param restaurant
     *            restaurant
     * @return saved restaurant
     */
    Optional<Restaurant> addRestaurant(Restaurant restaurant);

    /**
     * Get restaurant.
     * 
     * @param name
     *            name of restaurant
     * @return restaurant
     */
    Optional<Restaurant> getRestaurantByName(String name);

    /**
     * Get all restaurants.
     * 
     * @return list of restaurants
     */
    Collection<Restaurant> getAllRestaurants();

}
