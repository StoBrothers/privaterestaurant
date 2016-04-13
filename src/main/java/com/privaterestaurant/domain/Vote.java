package com.privaterestaurant.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * User votes per restaurant and date.
 * 
 * @author Sergey Stotskiy
 */
@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "work_date_id" }))
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private WorkDate workDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public WorkDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(WorkDate workDate) {
        this.workDate = workDate;
    }

}
