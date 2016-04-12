package com.privaterestaurant.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privaterestaurant.domain.Restaurant;
import com.privaterestaurant.domain.RestaurantRepository;

/**
 * Service for work with Restaurant entity. 
 * 
 * @author Sergey Stotskiy
 *
 */
@Service("restaurantservice")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository<Restaurant> restaurantRepository;

    @Override
    public Optional<Restaurant> getRestaurantById(long id) {
        return restaurantRepository.findOneById(id);
    }

    @Override
    public Optional<Restaurant> addRestaurant(Restaurant restaurant) {
        return Optional.ofNullable(restaurantRepository.save(restaurant));
    }

    @Override
    public Optional<Restaurant> getRestaurantByName(String name) {
        return restaurantRepository.findOneByName(name);
    }

    @Override
    public Collection<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

}
