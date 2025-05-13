package org.example.zadanko.dto.MoneyDonation;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.example.zadanko.model.Currency;

import java.math.BigDecimal;
import java.util.UUID;

public record MoneyDonationRequestDto(
        @NotNull(message = "moneyAmmount cannot be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "moneyAmmount must be greater than 0")
        BigDecimal moneyAmmount,
        @NotNull(message = "boxId cannot be null")
        UUID boxId,
        @NotNull(message = "currency cannot be null")
        Currency currency) {
}
