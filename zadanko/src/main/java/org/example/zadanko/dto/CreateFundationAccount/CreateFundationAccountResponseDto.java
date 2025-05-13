package org.example.zadanko.dto.CreateFundationAccount;

import org.example.zadanko.model.Currency;

import java.util.List;
import java.util.UUID;

public record CreateFundationAccountResponseDto(
        UUID id,
        String name,
        String description,
        Currency currency,
        String balance,
        List<Object> fundraisingEvents
) {
}
