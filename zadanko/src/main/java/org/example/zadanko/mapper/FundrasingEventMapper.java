package org.example.zadanko.mapper;

import org.example.zadanko.dto.CreatedFundrasingEvent.CreatedFundrasingEventRequestDto;
import org.example.zadanko.dto.CreatedFundrasingEvent.CreatedFundraisingEventResponseDto;
import org.example.zadanko.model.FundraisingEvent;

public class FundrasingEventMapper {


    public static FundraisingEvent createdFundrasingEventRequestDtoToFundraisingEvent(CreatedFundrasingEventRequestDto createdFundrasingEventRequestDto) {
        return new FundraisingEvent(
                null,
                createdFundrasingEventRequestDto.name(),
                createdFundrasingEventRequestDto.description(),
                createdFundrasingEventRequestDto.startDate(),
                createdFundrasingEventRequestDto.endDate(),
                null,
                null

        );
    }

    public static CreatedFundraisingEventResponseDto FundraisingEventToCreatedFundrasingEventResponseDto(FundraisingEvent foundrasingEvent) {
        return new CreatedFundraisingEventResponseDto(
                foundrasingEvent.getId(),
                foundrasingEvent.getName(),
                foundrasingEvent.getDescription(),
                foundrasingEvent.getStartDate(),
                foundrasingEvent.getEndDate()
        );
    }
}
