package com.privaterestaurant.config.db;

import java.util.Date;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.privaterestaurant.domain.WorkDate;
import com.privaterestaurant.domain.WorkDateRepository;

/**
 * Calendar initialization with work dates (current date and previous and next days).
 * 
 * @author Sergey Stotskiy
 */
@Component("workdates")
@DependsOn({ "restaurants" })
public class WorkDateInit extends AbstractInit {

    @Autowired
    WorkDateRepository workDateRepository;

    @Override
    protected void init() {

        LocalDate localDate = new LocalDate();

        Date currentDate = localDate.toDate();
        create(currentDate);

        Date prevDate = localDate.minusDays(1).toDate();
        create(prevDate);

        Date nextDate = localDate.plusDays(1).toDate();
        create(nextDate);
    }

    /**
     * Create one WorkDate
     * 
     * @param curDate
     */
    private void create(Date curDate) {
        WorkDate workDate = new WorkDate();
        workDate.setDate(curDate);
        workDateRepository.save(workDate);
    }
}
