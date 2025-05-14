package org.example.zadanko.dto.CreateFundationAccount;

import jakarta.validation.constraints.NotBlank;
import org.example.zadanko.model.Currency;
import org.example.zadanko.validation.ValidCurrency;

public record CreateFundationAccountRequestDto(
        @NotBlank(message = "Name cannot be blank")
        String name,
        String description,
        @ValidCurrency(message = "Currency cannot be null or empty")
        Currency currency
) {
}
