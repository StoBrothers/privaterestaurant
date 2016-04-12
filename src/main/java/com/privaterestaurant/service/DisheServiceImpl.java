package com.privaterestaurant.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privaterestaurant.domain.Dishe;
import com.privaterestaurant.domain.DisheRepository;
import com.privaterestaurant.domain.Restaurant;
import com.privaterestaurant.domain.WorkDate;

/**
 * Service for work with Dishes entity.
 * 
 * @author Sergey Stotskiy
 *
 */
@Service("disheService")
public class DisheServiceImpl implements DisheService {

    @Autowired
    private DisheRepository disheRepository;

    @Override
    public Optional<Dishe> addDishe(Dishe dishe) {
        return Optional.ofNullable(disheRepository.save(dishe));
    }

    @Override
    public Optional<Dishe> updateDishe(Dishe dishe) {
        return Optional.ofNullable(disheRepository.saveAndFlush(dishe));
    }

    @Override
    public Optional<Dishe> getDisheById(long id) {
        return Optional.ofNullable(disheRepository.findOne(id));
    }

    @Override
    public Collection<Dishe> getDishesByRestaurantAndWorkDate(Restaurant restaurant,
        WorkDate workDate) {
        return disheRepository.findAllByRestaurantAndWorkDate(restaurant, workDate);
    }

    @Override
    public Collection<Dishe> getDishesByRestaurantIdAndWorkDateId(Long restaurantId,
        Long workDateId) {
        return disheRepository.findAllByRestaurantIdAndWorkDateId(restaurantId,
            workDateId);
    }

    @Override
    public Collection<Dishe> getAllDishes() {
        return disheRepository.findAll();
    }

    @Override
    public void deleteDishe(Long disheId) {
        disheRepository.delete(disheId);
    }

}
