package com.privaterestaurant.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository<T extends Vote> extends JpaRepository<T, Long> {

    @Query("select  t from #{#entityName} t " + " where t.user.id  = :userId "
        + " and t.workDate.id = :workDateId)")
    Optional<Vote> findByUserIdAndWorkDateId(@Param(value = "userId") Long userId,
        @Param(value = "workDateId") Long workDateId);

}
