package com.privaterestaurant.controller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.privaterestaurant.domain.Restaurant;
import com.privaterestaurant.domain.Vote;
import com.privaterestaurant.domain.VoteDTO;
import com.privaterestaurant.domain.WorkDate;
import com.privaterestaurant.security.Permission;
import com.privaterestaurant.security.SecurityUtil;
import com.privaterestaurant.service.CurrentUser;
import com.privaterestaurant.service.CurrentUserDetailsService;
import com.privaterestaurant.service.RestaurantService;
import com.privaterestaurant.service.VoteService;
import com.privaterestaurant.service.WorkDateService;
import com.privaterestaurant.util.VoteParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Rest Controller for votes.
 * 
 * @author Sergey Stotskiy 
 *
 */
@RestController
@RequestMapping("/rest/")
public class VotesController {

    private static final Logger logger = LoggerFactory.getLogger(VotesController.class);
    
    @Autowired
    VoteService<Vote> voteService;

    @Autowired
    CurrentUserDetailsService currentUserDetailsService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private WorkDateService workdateService;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    /**
     * Voting process for User. VoteParameter contains users login and password.
     *
     * This request is calling from anonymous user.
     * 
     * @param restaurantId
     *            selected restaurant
     * @param workDateId
     *            selected date
     * @param parameters
     *            VoteParameter class contains user login and password
     * @return HTTP response status: OK is all right status, else is BAD_REQUEST
     */
    @RequestMapping(value = "voting/{restaurantId}/{workDateId}/", method = RequestMethod.POST)
    @ResponseStatus
    public Object vote(@PathVariable long restaurantId, @PathVariable long workDateId,
        @RequestBody VoteParameter parameters) {
        // Get context for anonymous user
        SecurityContext context = SecurityContextHolder.getContext();

        // Authentication process for new User
        CurrentUser currentUser = null;
        try {
            currentUser = currentUserDetailsService
                .loadUserByUsername(parameters.getLogon());
        } catch (UsernameNotFoundException uex) {
            logger.info("User not loaded " +  uex.getMessage());
            return HttpStatus.BAD_REQUEST;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        if (!passwordEncoder.matches(parameters.getPassword(),
            currentUser.getPassword())) {
            logger.info("Password incorrect");
            return HttpStatus.BAD_REQUEST;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            currentUser, currentUser.getRoles(), currentUser.getAuthorities());

        if (!SecurityUtil.hasPermission(currentUser, Permission.VOTES_CREATE)) {
            logger.info("User have't permission for create vote ");
            return HttpStatus.BAD_REQUEST;
        }

        context.setAuthentication(authentication); // set new authenticated User in security context

        Optional<Restaurant> restaurant = restaurantService
            .getRestaurantById(restaurantId);

        Optional<WorkDate> workDate = workdateService.getWorkDateById(workDateId);

        if (!restaurant.isPresent()) { 
            logger.info("Restaurant is not found");
            return HttpStatus.BAD_REQUEST;
        }
        if ( !workDate.isPresent()) {
            logger.info("Workdate is not found");
            return HttpStatus.BAD_REQUEST;
        }
        Vote vote = voteService.addVote(currentUser.getUser(), restaurant.get(),
            workDate.get());
        if (vote == null) {
            logger.info("Voting is not complited succesful");
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

    @PreAuthorize("hasAuthority('VOTES_INFO')")
    @RequestMapping(value = "vote/{userId}/{workDateId}/", method = RequestMethod.GET)
    @ResponseBody
    public Object getVoteByUserWorkDate(@PathVariable long userId,
        @PathVariable long workDateId) {
        Optional<Vote> vote = voteService.findByUserIdAndWorkDateId(userId, workDateId);
        if (!vote.isPresent()) {
            logger.info("Vote is not exist");
            return null;
        }
        Vote currentVote = vote.get();
        Map<String, String> responseMap = new HashMap<String, String>();
        responseMap.put("VoteId", String.valueOf(currentVote.getId()));
        responseMap.put("User logon", currentVote.getUser().getLogonName());
        responseMap.put("Restaurant", currentVote.getRestaurant().getName());
        return new VoteDTO(vote.get());
    }
    
    @PreAuthorize("hasAuthority('VOTES_INFO')")
    @RequestMapping(value = "votes/", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllVotes() {
        List<Vote> votes = voteService.findAllVotes();
        if (votes == null || votes.size() == 0) {
            logger.info("Votes is not exist");
            return null;
        }
        
        List<VoteDTO> voteDTOs = new ArrayList<VoteDTO>();
        votes.forEach(u -> voteDTOs.add(new VoteDTO(u)));
        return voteDTOs;
    }
}
