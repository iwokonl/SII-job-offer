package org.example.zadanko.service;

import org.example.zadanko.dto.CreateFundationAccount.CreateFundationAccountRequestDto;
import org.example.zadanko.dto.CreateFundationAccount.CreateFundationAccountResponseDto;
import org.example.zadanko.model.Currency;
import org.example.zadanko.model.FoundationAccount;
import org.example.zadanko.repository.FoundationAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FundationAccountServiceTest {

    @Mock
    private FoundationAccountRepository foundationAccountRepository;

    @InjectMocks
    private FundationAccountService fundationAccountService;

    @Test
    void createFundationAccountShouldReturnResponseDtoWithZeroBalance() {
        CreateFundationAccountRequestDto requestDto = new CreateFundationAccountRequestDto(
                "Account Name", "Account Description", Currency.USD
        );
        FoundationAccount foundationAccount = new FoundationAccount();
        foundationAccount.setBalance(BigDecimal.ZERO);

        when(foundationAccountRepository.save(any(FoundationAccount.class))).thenReturn(foundationAccount);

        CreateFundationAccountResponseDto responseDto = fundationAccountService.createFundationAccount(requestDto);

        assertNotNull(responseDto);
        assertEquals("0", responseDto.balance()); // Porównanie jako String
        verify(foundationAccountRepository).save(any(FoundationAccount.class));
    }

    @Test
    void createFundationAccountShouldThrowExceptionWhenRequestDtoIsNull() {
        assertThrows(IllegalArgumentException.class, () -> fundationAccountService.createFundationAccount(null));
    }
}