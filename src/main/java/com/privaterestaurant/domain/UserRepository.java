package com.privaterestaurant.domain;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.privaterestaurant.security.Role;

/**
 * User repository.
 * 
 * @author Sergey Stotskiy
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByLogonName(String logonName);

    Optional<User> findOneById(Long id);

    Collection<User> findAllByRoles(Role role, Sort sort);

    Collection<User> findAllByAdminRulesIsTrue();

}