package com.privaterestaurant.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Restaurant repository.
 * 
 * @author Sergey Stotskiy
 *
 * @param <T>
 */
public interface RestaurantRepository<T extends Restaurant>
    extends JpaRepository<T, Long> {

    Optional<Restaurant> findOneById(Long id);

    Optional<Restaurant> findOneByName(String name);
}
