package com.test.restservice.services.domain;

import com.test.restservice.services.domain.models.DomainDto;
import reactor.core.publisher.Mono;

import java.util.List;


public interface DomainService {

    Mono<List<DomainDto>> searchDomains(String domain);

}
