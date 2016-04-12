package com.privaterestaurant.domain;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DisheRepository extends JpaRepository<Dishe, Long> {

    Optional<Dishe> findOneById(Long id);

    Collection<Dishe> findAllByRestaurantAndWorkDate(Restaurant restaurant,
        WorkDate workDate);

    Collection<Dishe> findAllByRestaurantIdAndWorkDateId(Long restaurantId,
        Long workDateId);

}
