package org.example.zadanko.dto.CreatedFundrasingEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreatedFundrasingEventResponseDto(
        UUID id,
        String name,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
