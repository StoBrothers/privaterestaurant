package com.privaterestaurant.config.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.privaterestaurant.domain.Dishe;
import com.privaterestaurant.domain.DisheRepository;
import com.privaterestaurant.domain.Restaurant;
import com.privaterestaurant.domain.RestaurantRepository;
import com.privaterestaurant.domain.WorkDate;
import com.privaterestaurant.domain.WorkDateRepository;

/**
 * Create menu from 3 dishes for 3 days for all restaurants.
 * It's test data.
 *
 * @author Sergey Stotskiy
 *
 */
@Component("dishes")
@DependsOn({ "restaurants", "workdates" })
public class DishesInit extends AbstractInit {

    @Autowired
    DisheRepository disheRepository;

    @Autowired
    WorkDateRepository workDateRepository;

    @Autowired
    RestaurantRepository<Restaurant> restaurantRepository;

    @Override
    protected void init() {
        List<WorkDate> workDates = workDateRepository.findAll();
        for (WorkDate workDate : workDates) {
            List<Restaurant> restaurants = restaurantRepository.findAll();
            for (Restaurant restaurant : restaurants) {
                create(restaurant, workDate, "name1", 1.0);
                create(restaurant, workDate, "name2", 3.0);
                create(restaurant, workDate, "name3", 6.0);
            }
        }
    }

    private void create(Restaurant restaurant, WorkDate workDate, String name,
        Double price) {
        Dishe dishe = new Dishe();
        dishe.setWorkDate(workDate);
        dishe.setRestaurant(restaurant);
        dishe.setName(name);
        dishe.setPrice(price);
        disheRepository.save(dishe);
    }
}
