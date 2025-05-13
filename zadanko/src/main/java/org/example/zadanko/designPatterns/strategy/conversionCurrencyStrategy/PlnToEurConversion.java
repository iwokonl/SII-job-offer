package org.example.zadanko.designPatterns.strategy.conversionCurrencyStrategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("PLN->EUR")
public class PlnToEurConversion implements CurrencyConversionStrategy {
    private static final BigDecimal PLN_TO_EUR = new BigDecimal("0.21");

    @Override
    public BigDecimal convert(BigDecimal ammount) {
        return ammount.multiply(PLN_TO_EUR);
    }
}


