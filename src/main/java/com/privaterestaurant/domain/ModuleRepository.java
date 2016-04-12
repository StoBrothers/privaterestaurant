package com.privaterestaurant.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    Optional<Module> findOneByName(String name);

    Optional<Module> findOneById(Long id);

}
