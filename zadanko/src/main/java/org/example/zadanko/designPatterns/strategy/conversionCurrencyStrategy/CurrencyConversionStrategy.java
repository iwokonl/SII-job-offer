package org.example.zadanko.designPatterns.strategy.conversionCurrencyStrategy;

import java.math.BigDecimal;

public interface CurrencyConversionStrategy {
    BigDecimal convert(BigDecimal amount);
}
