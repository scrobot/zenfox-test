package com.test.restservice.services.domain;

import com.test.restservice.data.domain.Domain;
import com.test.restservice.data.domain.DomainRepository;
import com.test.restservice.data.tld.TldRepository;
import com.test.restservice.services.domain.models.DomainDto;
import com.test.restservice.services.domain.models.DomainTldMapping;
import lombok.var;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DomainServiceImpl implements DomainService {

    private final DomainRepository domainRepository;
    private final TldRepository tldRepository;

    public DomainServiceImpl(DomainRepository domainRepository, TldRepository tldRepository) {
        this.domainRepository = domainRepository;
        this.tldRepository = tldRepository;
    }

    @Override
    public Mono<List<DomainDto>> searchDomains(String domain) {
        return Flux.zip(
                Flux.just(findAllDomainsBySubname(domain)),
                Flux.just(tldRepository.findAll(Sort.by(Sort.Direction.ASC, "price"))),
                DomainTldMapping::new
            )
            .map(mapping -> convertToDomainDto(domain, mapping))
            .flatMap(Flux::fromIterable)
            .collectList();
    }

    private List<DomainDto> convertToDomainDto(String domain, DomainTldMapping mapping) {
        return StreamSupport
            .stream(mapping.getTlds().spliterator(), false)
            .map(tld -> {
                var fullDomain = domain + "." + tld.getName();

                return new DomainDto()
                    .setDomain(fullDomain)
                    .setPrice(tld.getPrice())
                    .setTld(tld.getName())
                    .setAvailable(!mapping.getDomains().contains(fullDomain));
            })
            .collect(Collectors.toList());
    }

    private Set<String> findAllDomainsBySubname(String domain) {
        return domainRepository
            .findAllByNameContains(domain)
            .stream()
            .map(Domain::getName)
            .collect(Collectors.toSet());
    }

}
