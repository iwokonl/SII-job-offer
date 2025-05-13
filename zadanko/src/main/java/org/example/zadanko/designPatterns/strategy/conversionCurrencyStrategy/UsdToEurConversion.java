package org.example.zadanko.designPatterns.strategy.conversionCurrencyStrategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("USD->EUR")
public class UsdToEurConversion implements CurrencyConversionStrategy {
    private static final BigDecimal USD_TO_EUR = new BigDecimal("0.85");

    @Override
    public BigDecimal convert(BigDecimal ammount) {
        return ammount.multiply(USD_TO_EUR);
    }
}
