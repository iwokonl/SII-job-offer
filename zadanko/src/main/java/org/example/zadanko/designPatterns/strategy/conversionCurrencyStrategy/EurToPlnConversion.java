package org.example.zadanko.designPatterns.strategy.conversionCurrencyStrategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("EUR->PLN")
public class EurToPlnConversion implements CurrencyConversionStrategy {
    private static final BigDecimal EUR_TO_PLN = new BigDecimal("4.7");

    @Override
    public BigDecimal convert(BigDecimal ammount) {
        return ammount.multiply(EUR_TO_PLN);
    }
}
