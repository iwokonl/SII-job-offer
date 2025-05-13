package org.example.zadanko.designPatterns.strategy.conversionCurrencyStrategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("DEFAULT")
public class SameCurrencyConversion implements CurrencyConversionStrategy {
    @Override
    public BigDecimal convert(BigDecimal amount) {
        return amount; // bez konwersji
    }
}

