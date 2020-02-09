package com.test.restservice.services.domain.models;

import com.test.restservice.data.domain.Domain;
import com.test.restservice.data.tld.Tld;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class DomainTldMapping {

    private final Set<String> domains;
    private final Iterable<Tld> tlds;

}
