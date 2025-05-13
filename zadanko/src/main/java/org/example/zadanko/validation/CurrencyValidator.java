package org.example.zadanko.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.zadanko.model.Currency;

import java.util.Arrays;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, Currency> {

    @Override
    public boolean isValid(Currency value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        // Ensure the value is a valid enum
        return Arrays.stream(Currency.values()).anyMatch(c -> c == value);
    }
}
