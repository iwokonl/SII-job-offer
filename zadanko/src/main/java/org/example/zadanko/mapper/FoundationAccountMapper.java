package org.example.zadanko.mapper;

import org.example.zadanko.dto.CreateFundationAccount.CreateFundationAccountRequestDto;
import org.example.zadanko.dto.CreateFundationAccount.CreateFundationAccountResponseDto;
import org.example.zadanko.model.FoundationAccount;

import java.util.Collections;

public class FoundationAccountMapper {

    public static FoundationAccount CreateFundationAccountRequestDtoToFoundationAccount(CreateFundationAccountRequestDto createFundationAccountRequestDto) {
        return new FoundationAccount(
                null,
                createFundationAccountRequestDto.name(),
                createFundationAccountRequestDto.description(),
                createFundationAccountRequestDto.currency(),
                null,
                null
        );
    }

    public static CreateFundationAccountResponseDto FoundationAccountToCreateFundationAccountResponseDto(FoundationAccount foundationAccount) {
        return new CreateFundationAccountResponseDto(
                foundationAccount.getId(),
                foundationAccount.getName(),
                foundationAccount.getDescription(),
                foundationAccount.getCurrencyType(),
                foundationAccount.getBalance().toString(),
                Collections.emptyList()
        );
    }
}
