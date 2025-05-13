package org.example.zadanko.dto;

import java.util.UUID;

public record GetAllAnonymizedBoxesResponseDto(
        UUID id,
        Boolean isAssigned,
        Boolean isEmpty
) {
}
