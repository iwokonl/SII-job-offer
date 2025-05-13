package org.example.zadanko.service;

import lombok.RequiredArgsConstructor;
import org.example.zadanko.dto.CreateFundationAccount.CreateFundationAccountRequestDto;
import org.example.zadanko.dto.CreateFundationAccount.CreateFundationAccountResponseDto;
import org.example.zadanko.mapper.FoundationAccountMapper;
import org.example.zadanko.model.FoundationAccount;
import org.example.zadanko.repository.FoundationAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
}
