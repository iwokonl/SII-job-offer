package org.example.zadanko.service;

import lombok.RequiredArgsConstructor;
import org.example.zadanko.dto.CreateFundationAccount.CreateFundationAccountRequestDto;
import org.example.zadanko.dto.CreateFundationAccount.CreateFundationAccountResponseDto;
import org.example.zadanko.dto.FinacialReportResponseDto;
import org.example.zadanko.exception.GeneralAppException;
import org.example.zadanko.mapper.FoundationAccountMapper;
import org.example.zadanko.model.FoundationAccount;
import org.example.zadanko.model.FundraisingEvent;
import org.example.zadanko.repository.FoundationAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FundationAccountService {

    private final FoundationAccountRepository foundationAccountRepository;



    @Transactional
    public CreateFundationAccountResponseDto createFundationAccount(CreateFundationAccountRequestDto createFundationAccountRequestDto) {

        FoundationAccount foundationAccount = FoundationAccountMapper.
                CreateFundationAccountRequestDtoToFoundationAccount(
                        createFundationAccountRequestDto
                );
        foundationAccount.setBalance(BigDecimal.ZERO);
        foundationAccountRepository.save(foundationAccount);

        return FoundationAccountMapper.
                FoundationAccountToCreateFundationAccountResponseDto(
                        foundationAccount
                );

    }

    @Transactional(readOnly = true)
    public FinacialReportResponseDto printFinancialReport(UUID fundationAccountId) {
        FoundationAccount foundationAccount = foundationAccountRepository.findById(fundationAccountId)
                .orElseThrow(
                        () -> new GeneralAppException("Foundation account not found", HttpStatus.NOT_FOUND));

        List<FinacialReportResponseDto.FundraisingEventResponseDto> fundraisingEventResponseDtoList = foundationAccount.getFundraisingEvents().stream()
                .filter(fundraisingEvent -> fundraisingEvent.getBox() != null)
                .map(fundraisingEvent -> new FinacialReportResponseDto.FundraisingEventResponseDto(
                        fundraisingEvent.getName(),
                        fundraisingEvent.getBox().getBallanceMap()
                ))
                .toList();

        return new FinacialReportResponseDto(
                foundationAccount.getName(),
                foundationAccount.getBalance(),
                foundationAccount.getCurrencyType(),
                fundraisingEventResponseDtoList
        );
    }
}
