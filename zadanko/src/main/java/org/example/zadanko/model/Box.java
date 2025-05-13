package org.example.zadanko.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
public class Box {

    @Id
    @GeneratedValue
    private UUID id;
    @ElementCollection
    private Map<Currency, BigDecimal> currencyMap;


}
