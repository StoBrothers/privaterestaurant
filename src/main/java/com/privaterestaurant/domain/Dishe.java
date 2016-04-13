package com.privaterestaurant.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Dishes entity for filling restaurant menu per selected date.
 * 
 * @author Sergey Stotskiy
 */
@SuppressWarnings("serial")
@Entity
public class Dishe implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private long id;

    /**
     * Dishes name
     */
    @Column(nullable = false)
    private String name;

    /**
     * Dishes price
     */
    @Column(nullable = false)
    private Double price;

    /**
     * Selected restaurant for dishes
     */
    @ManyToOne
    private Restaurant restaurant;
    /**
     * Selected date
     */
    @ManyToOne
    private WorkDate workDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dishe [id=" + id + ", name=" + name + ", price=" + price + ", restaurant="
            + restaurant + ", workDate=" + workDate + "]";
    }

}
