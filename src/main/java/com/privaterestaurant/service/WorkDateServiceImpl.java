package com.privaterestaurant.service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privaterestaurant.domain.WorkDate;
import com.privaterestaurant.domain.WorkDateRepository;
/**
 * Service for work with WorkDate entity.  
 * 
 * @author Sergey Stotskiy
 *
 */
@Service("workDateService")
public class WorkDateServiceImpl implements WorkDateService {

    @Autowired
    private WorkDateRepository workDateRepository;

    @Override
    public Optional<WorkDate> getWorkDateById(long id) {
        return Optional.ofNullable(workDateRepository.getOne(id));
    }

    @Override
    public Optional<WorkDate> addWorkDate(WorkDate workDate) {
        return Optional.ofNullable(workDateRepository.save(workDate));
    }

    @Override
    public Optional<WorkDate> getWorkDateByDate(Date date) {
        return workDateRepository.findOneByDate(date);
    }

    @Override
    public Collection<WorkDate> getAllWorkDates() {
        return workDateRepository.findAll();
    }

}
