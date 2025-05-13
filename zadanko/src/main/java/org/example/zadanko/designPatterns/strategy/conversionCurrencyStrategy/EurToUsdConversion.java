package org.example.zadanko.designPatterns.strategy.conversionCurrencyStrategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("EUR->USD")
public class EurToUsdConversion implements CurrencyConversionStrategy {
    private static final BigDecimal EUR_TO_USD = new BigDecimal("1.18");

    @Override
    public BigDecimal convert(BigDecimal ammount) {
        return ammount.multiply(EUR_TO_USD);
    }
}
