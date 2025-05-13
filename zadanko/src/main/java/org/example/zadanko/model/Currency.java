package org.example.zadanko.model;

import lombok.Getter;

@Getter
public enum Currency {

    USD("USD"),
    PLN("PLN"),
    EUR("EUR");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

}