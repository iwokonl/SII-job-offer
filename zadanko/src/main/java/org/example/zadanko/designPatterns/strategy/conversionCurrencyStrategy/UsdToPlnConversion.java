package org.example.zadanko.designPatterns.strategy.conversionCurrencyStrategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("USD->PLN")
public class UsdToPlnConversion implements CurrencyConversionStrategy {
    private static final BigDecimal USD_TO_PLN = new BigDecimal("4.0");

    @Override
    public BigDecimal convert(BigDecimal ammount) {
        return ammount.multiply(USD_TO_PLN);
    }
}
