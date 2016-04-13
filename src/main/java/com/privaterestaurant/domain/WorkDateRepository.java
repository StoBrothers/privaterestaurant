package com.privaterestaurant.domain;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * WorkDate repository.
 * 
 * @author Sergey Stotskiy
 *
 */
public interface WorkDateRepository extends JpaRepository<WorkDate, Long> {

    Optional<WorkDate> findOneByDate(Date date);

    Optional<WorkDate> findOneById(Long id);
}
