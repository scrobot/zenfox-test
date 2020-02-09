package com.test.restservice.services.domain.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DomainDto {
    private String domain;
    private String tld;
    private boolean available;
    private double price;
}
