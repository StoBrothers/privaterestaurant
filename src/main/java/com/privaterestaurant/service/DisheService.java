package com.privaterestaurant.service;

import java.util.Collection;
import java.util.Optional;

import com.privaterestaurant.domain.Dishe;
import com.privaterestaurant.domain.Restaurant;
import com.privaterestaurant.domain.WorkDate;

/**
 * Service for work with Dishes entity.
 * 
 * @author Sergey Stotskiy
 *
 */
public interface DisheService {
    /**
     * Add dishe
     * 
     * @param dishe
     * @return
     *
     **/
    Optional<Dishe> addDishe(Dishe dishe);

    /**
     * Add dishe
     * 
     * @param dishe
     * @return
     *
     **/
    Optional<Dishe> updateDishe(Dishe dishe);

    /**
     * Get dishe by indentificator
     * 
     * @param id
     *            indentificator
     * @return Dishe
     */
    Optional<Dishe> getDisheById(long id);

    /**
     * Get dishes
     * 
     * @param restaurant
     * @param date
     * @return
     */
    Collection<Dishe> getDishesByRestaurantAndWorkDate(Restaurant restaurant,
        WorkDate date);

    /**
     * Get all Dishes
     * 
     * @return collection
     */
    Collection<Dishe> getAllDishes();

    /**
     * Get Dishes by Restaurant and WorkDate
     * 
     * @param restaurantId
     * @param workDateId
     * @return
     */
    Collection<Dishe> getDishesByRestaurantIdAndWorkDateId(Long restaurantId,
        Long workDateId);

    /**
     * Delete Dishe
     * 
     * @param dishe
     */
    void deleteDishe(Long disheId);

}
