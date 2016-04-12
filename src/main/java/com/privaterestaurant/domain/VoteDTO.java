package com.privaterestaurant.domain;

import java.util.Date;


/**
 * Use DTO Object for hide User security property (password and any).  
 *  
 * @author Sergey Stotskiy
 *
 */
public class VoteDTO {
    
    public VoteDTO(Vote vote) {
      this.id = vote.getId();
      this.userName = vote.getUser().getFullName();
      this.restaurantName = vote.getRestaurant().getName();
      this.workDate = vote.getWorkDate().getDate();
    }

    private long id;

    private String userName;

    private String restaurantName;

    private Date workDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }
    
    @Override
    public String toString() {
        return "VoteDTO [id=" + id + ", userName=" + userName + ", restaurantName="
            + restaurantName + ", workDate=" + workDate + "]";
    }
}
