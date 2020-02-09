package com.test.restservice.data.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface DomainRepository extends CrudRepository<Domain, Long> {

    Set<Domain> findAllByNameContains(String name);

}
