package org.example.zadanko.designPatterns.strategy.conversionCurrencyStrategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("PLN->USD")
public class PlnToUsdConversion implements CurrencyConversionStrategy {
    private static final BigDecimal PLN_TO_USD = new BigDecimal("0.25");

    @Override
    public BigDecimal convert(BigDecimal ammount) {
        return ammount.multiply(PLN_TO_USD);
    }
}
