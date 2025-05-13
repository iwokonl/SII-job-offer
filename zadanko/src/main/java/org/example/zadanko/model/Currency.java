package org.example.zadanko.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import org.example.zadanko.config.CurrencyDeserializer;

@Getter
@JsonDeserialize(using = CurrencyDeserializer.class)
public enum Currency {

    USD("USD"),
    PLN("PLN"),
    EUR("EUR");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

}