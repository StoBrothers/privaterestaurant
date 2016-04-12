package com.privaterestaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.privaterestaurant.domain.Dishe;
import com.privaterestaurant.domain.Restaurant;
import com.privaterestaurant.domain.WorkDate;
import com.privaterestaurant.service.DisheService;
import com.privaterestaurant.service.RestaurantService;
import com.privaterestaurant.service.WorkDateService;

/**
 * 
 * This service for work with dishes. 
 * Implemented CRUD operations: Add, Update, Delete, View authority.
 * 
 * @author Sergey Stotskiy
 *
 */

@Controller
public class DisheController {

    @Autowired
    private DisheService disheService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private WorkDateService workDateService;

    @PreAuthorize("hasAuthority('DISHE_VIEW')")
    @RequestMapping(value = "/dishe/{disheId}/", method = RequestMethod.GET)
    @ResponseBody
    public Object getDishe(@PathVariable Long disheId) {
        return disheService.getDisheById(disheId).orElseThrow(
            () -> new ParameterNotFoundException(disheId, "Dishe with id # not existed"));
    }

    /**
     * Get all Dishes.
     * 
     * @return
     */
    @PreAuthorize("hasAuthority('DISHE_VIEW')")
    @RequestMapping(value = "/dishe/", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllDishes() {
        return disheService.getAllDishes();
    }

    /**
     * Add Dish by Restaurant and WorkDate.
     * 
     * @param restaurantId
     * @param workDateId
     * @param dishe
     * @return
     */
    @PreAuthorize("hasAuthority('DISHE_CREATE')")
    @RequestMapping(value = "/dishe/{restaurantId}/{workDateId}/", method = RequestMethod.PUT)
    @ResponseBody
    public Object addDishe(@PathVariable Long restaurantId, @PathVariable Long workDateId,
        @RequestBody Dishe dishe) {

        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId)
            .orElseThrow(() -> new ParameterNotFoundException(restaurantId,
                "Restaurant with id # not existed"));

        WorkDate workDate = workDateService.getWorkDateById(workDateId)
            .orElseThrow(() -> new ParameterNotFoundException(workDateId,
                "WorkDate with id # not existed"));

        dishe.setRestaurant(restaurant);
        dishe.setWorkDate(workDate);
        disheService.addDishe(dishe);
        return dishe;
    }

    /**
     * Update Dish By disheId.
     * 
     * @param disheId
     * @param dishe
     *            new Dish
     * @return
     */
    @PreAuthorize("hasAuthority('DISHE_UPDATE')")
    @RequestMapping(value = "/dishe/{disheId}/", method = RequestMethod.POST)
    @ResponseBody
    public Object updateDishe(@PathVariable Long disheId, @RequestBody Dishe dishe) {
        Dishe updateDishe = disheService.getDisheById(disheId).orElseThrow(
            () -> new ParameterNotFoundException(disheId, "Dishe with id # not existed"));
        updateDishe.setName(dishe.getName());
        updateDishe.setPrice(dishe.getPrice());
        if (dishe.getRestaurant() != null && dishe.getWorkDate() != null) {
            updateDishe.setRestaurant(dishe.getRestaurant());
            updateDishe.setWorkDate(dishe.getWorkDate());
        }
        disheService.updateDishe(updateDishe);
        return dishe;
    }

    /**
     * Delete Dish.
     * 
     * @param disheId
     */
    @PreAuthorize("hasAuthority('DISHE_DELETE')")
    @RequestMapping(value = "/dishe/{disheId}/", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteDishe(@PathVariable Long disheId) {
        disheService.deleteDishe(disheId);
    }

    /**
     * Get menu of dishes by restaurant and workDate
     * 
     * @param restaurantId
     * @param workDateId
     * @return
     */
    @PreAuthorize("hasAuthority('DISHE_VIEW')")
    @RequestMapping(value = "/dishe/{restaurantId}/{workDateId}/", method = RequestMethod.GET)
    @ResponseBody
    public Object getDishes(@PathVariable Long restaurantId,
        @PathVariable Long workDateId) {
        return disheService.getDishesByRestaurantIdAndWorkDateId(restaurantId,
            workDateId);
    }

    @SuppressWarnings("serial")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class ParameterNotFoundException extends RuntimeException {

        public ParameterNotFoundException(Object idValue, String message) {
            super(StringUtils.replace(message, "#", idValue.toString()));
        }
    }
}
