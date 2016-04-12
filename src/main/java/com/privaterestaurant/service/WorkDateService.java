package com.privaterestaurant.service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import com.privaterestaurant.domain.WorkDate;

/**
 * Service for work with WorkDate entity.  
 * 
 * @author Sergey Stotskiy
 *
 */
public interface WorkDateService {
    /**
     * Get WorkDate by id
     * 
     * @param id
     *            identificator
     * @return
     */
    Optional<WorkDate> getWorkDateById(long id);

    /**
     * Add new workDate
     * 
     * @param workDate
     * @return
     */
    Optional<WorkDate> addWorkDate(WorkDate workDate);

    /**
     * Get WorkDate by Date
     * 
     * @param date
     * @return
     */
    Optional<WorkDate> getWorkDateByDate(Date date);

    /**
     * Get all WorkDates
     * 
     * @return collection
     */
    Collection<WorkDate> getAllWorkDates();

}
