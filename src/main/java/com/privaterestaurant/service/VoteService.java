package com.privaterestaurant.service;

import java.util.List;
import java.util.Optional;

import com.privaterestaurant.domain.Restaurant;
import com.privaterestaurant.domain.User;
import com.privaterestaurant.domain.Vote;
import com.privaterestaurant.domain.WorkDate;

/**
 * Vote service. User can change his restaurant vote for selected period. Time period defined
 * between ((0Hr of Selected date + plusHour) AND (0Hr of Selected date - minusHour))
 * 
 * Example: plusHour is value of hours after 0PM 0 Hrs + 11 Hrs = 11AM. 11AM - is end time for
 * update vote. minusHour is value of hours before 0PM 0 Hrs - 1 Hrs = 11PM 11PM 11PM of previous
 * day is start time for update vote.
 * 
 * @author Sergey Stotskiy
 *
 */
public interface VoteService {

    /**
     * Find vote over userId and Long workDateId.
     * 
     * @param userId
     * @param workDateId
     * @return
     */
    Optional<Vote> findByUserIdAndWorkDateId(Long userId, Long workDateId);

    /**
     * Add vote for selected user and restaurant and workDate.
     * 
     * @param user
     *            selected user
     * @param restaurant
     *            selected restaurant
     * @param workDate
     *            selected date
     * @return
     */
    Vote addVote(User user, Restaurant restaurant, WorkDate workDate);

    /**
     * Get all votes.
     * 
     * @return
     */
    List<Vote> findAllVotes();

}
