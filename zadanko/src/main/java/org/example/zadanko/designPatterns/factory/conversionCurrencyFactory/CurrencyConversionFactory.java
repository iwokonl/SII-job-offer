package org.example.zadanko.designPatterns.factory.conversionCurrencyFactory;

import org.example.zadanko.designPatterns.strategy.conversionCurrencyStrategy.CurrencyConversionStrategy;
import org.example.zadanko.model.Currency;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class CurrencyConversionFactory {

    private final Map<String, CurrencyConversionStrategy> strategies;
    private final CurrencyConversionStrategy defaultStrategy;

    public CurrencyConversionFactory(Map<String, CurrencyConversionStrategy> strategies) {
        this.strategies = strategies;
        this.defaultStrategy = strategies.get("DEFAULT");
    }

    public Optional<CurrencyConversionStrategy> getStrategy(Currency from, Currency to) {
        if (from.equals(to)) {
            return Optional.of(defaultStrategy);
        }

        String key = from.name() + "->" + to.name();
        return Optional.ofNullable(strategies.get(key));
    }
}