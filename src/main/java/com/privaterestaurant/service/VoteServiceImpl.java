package com.privaterestaurant.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.privaterestaurant.domain.Restaurant;
import com.privaterestaurant.domain.User;
import com.privaterestaurant.domain.Vote;
import com.privaterestaurant.domain.VoteRepository;
import com.privaterestaurant.domain.WorkDate;
import com.privaterestaurant.util.ApplicationProperties;

/**
 * 
 * Vote service. User can change his restaurant vote for selected period. Time period defined
 * between ((0Hr of Selected date + plusHour) AND (0Hr of Selected date - minusHour))
 * 
 * Example: plusHour is value of hours after 0PM 0 Hrs + 11 Hrs = 11AM. 11AM - is end time for
 * update vote. minusHour is value of hours before 0PM 0 Hrs - 1 Hrs = 11PM 11PM 11PM of previous
 * day is start time for update vote.
 * 
 * @author Sergey Stotskiy
 **/
@Service("voteService")
public class VoteServiceImpl implements VoteService {

    private static final Logger logger = LoggerFactory.getLogger(VoteServiceImpl.class);

    @Autowired
    private VoteRepository<Vote> voteRepository;

    @Override
    @Transactional
    public Vote addVote(User user, Restaurant restaurant, WorkDate workDate) {
        Vote vote = null;
        if (!isChange(ApplicationProperties.getPlusHour(),
            ApplicationProperties.getMinusHour(), workDate)) {
            return vote;
        }
        Optional<Vote> valueVote = voteRepository.findByUserIdAndWorkDateId(user.getId(),
            workDate.getId());
        if (valueVote.isPresent()) {
            vote = valueVote.get();
            vote.setRestaurant(restaurant);
            voteRepository.saveAndFlush(vote);
        } else {
            vote = new Vote();
            vote.setUser(user);
            vote.setRestaurant(restaurant);
            vote.setWorkDate(workDate);
            voteRepository.save(vote);
        }
        return vote;
    }

    /**
     * Check for needs update vote in selected time period.
     * 
     * @param plusHour
     *            0 hr + plusHour
     * @param minusHour
     *            0hr - minusHour
     * @return true for update and false for nothing
     */
    private boolean isChange(int plusHour, int minusHour, WorkDate workDate) {
        Date date = workDate.getDate();

        LocalDateTime workDateTime = new LocalDateTime(date);

        LocalDateTime endTimePeriod = workDateTime.plusHours(plusHour);
        LocalDateTime startTimePeriod = workDateTime.minusHours(minusHour);

        LocalDateTime localDateTime = LocalDateTime.now();

        if ((localDateTime.compareTo(startTimePeriod) > 0)
            && (localDateTime.compareTo(endTimePeriod) < 0)) {
            logger.info("Vote can be change");
            return true;
        }
        logger
            .info("Vote can't change because time of voting for this workDay is ended.");
        return false;
    }

    public List<Vote> findAllVotes() {
        return voteRepository.findAll();
    }

    @Override
    public Optional<Vote> findByUserIdAndWorkDateId(Long userId, Long workDateId) {
        return voteRepository.findByUserIdAndWorkDateId(userId, workDateId);
    }

}
