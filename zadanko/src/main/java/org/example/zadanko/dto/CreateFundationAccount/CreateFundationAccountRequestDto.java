package org.example.zadanko.dto.CreateFundationAccount;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.zadanko.model.Currency;

public record CreateFundationAccountRequestDto(
        @NotBlank(message = "Name cannot be blank")
        String name,
        String description,
        @NotNull(message = "Currency cannot be null")
        Currency currency
) {
}
