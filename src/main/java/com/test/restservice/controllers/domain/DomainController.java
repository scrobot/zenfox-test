package com.test.restservice.controllers.domain;

import com.test.restservice.controllers.domain.models.BuyDto;
import com.test.restservice.controllers.domain.models.SearchDto;
import com.test.restservice.services.domain.models.DomainDto;
import com.test.restservice.services.domain.DomainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class DomainController {

    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @GetMapping("/domains/check")
    public Mono<List<DomainDto>> check(SearchDto searchDto) {
        return domainService.searchDomains(searchDto.getSearch());
    }
}
