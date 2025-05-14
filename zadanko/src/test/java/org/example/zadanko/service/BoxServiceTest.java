package org.example.zadanko.service;

import org.example.zadanko.designPatterns.factory.conversionCurrencyFactory.CurrencyConversionFactory;
import org.example.zadanko.designPatterns.strategy.conversionCurrencyStrategy.CurrencyConversionStrategy;
import org.example.zadanko.dto.CreatedBox.CreatedBoxResponseDto;
import org.example.zadanko.dto.MoneyDonation.MoneyDonationRequestDto;
import org.example.zadanko.exception.GeneralAppException;
import org.example.zadanko.model.Box;
import org.example.zadanko.model.Currency;
import org.example.zadanko.model.FoundationAccount;
import org.example.zadanko.model.FundraisingEvent;
import org.example.zadanko.repository.BoxRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoxServiceTest {

    @Mock
    private BoxRepository boxRepository;

    @Mock
    private CurrencyConversionFactory currencyConversionFactory;

    @InjectMocks
    private BoxService boxService;

    @Test
    void createBoxShouldReturnCreatedBoxResponseDto() {
        Box box = new Box();
        box.setId(UUID.randomUUID());
        when(boxRepository.save(any(Box.class))).thenReturn(box);

        CreatedBoxResponseDto response = boxService.createBox();

        assertNotNull(response);
        assertEquals(box.getId(), response.id());
        verify(boxRepository).save(any(Box.class));
    }

    @Test
    void moneyDonationShouldAddMoneyToBoxBalance() {
        UUID boxId = UUID.randomUUID();
        MoneyDonationRequestDto requestDto = new MoneyDonationRequestDto(BigDecimal.valueOf(100), boxId, Currency.USD);
        Box box = new Box();
        box.setId(boxId);
        box.setBallanceMap(new HashMap<>());

        when(boxRepository.findById(boxId)).thenReturn(Optional.of(box));

        boxService.moneyDonation(requestDto);

        assertEquals(BigDecimal.valueOf(100), box.getBallanceMap().get(Currency.USD));
        verify(boxRepository).findById(boxId);
    }
    @Test
    void moneyDonationShouldThrowExceptionWhenBoxNotFound() {
        UUID boxId = UUID.randomUUID();
        MoneyDonationRequestDto requestDto = new MoneyDonationRequestDto(BigDecimal.valueOf(100), boxId, Currency.USD);

        when(boxRepository.findById(boxId)).thenReturn(Optional.empty());

        assertThrows(GeneralAppException.class, () -> boxService.moneyDonation(requestDto));
        verify(boxRepository).findById(boxId);
    }

    @Test
    void transferToFoundationShouldTransferAllMoneyToFoundationAccount() {
        UUID boxId = UUID.randomUUID();
        Box box = new Box();
        box.setId(boxId);
        Map<Currency, BigDecimal> balanceMap = new HashMap<>();
        balanceMap.put(Currency.USD, BigDecimal.valueOf(100));
        box.setBallanceMap(balanceMap);

        FoundationAccount foundationAccount = new FoundationAccount();
        foundationAccount.setCurrencyType(Currency.EUR);
        foundationAccount.setBalance(BigDecimal.ZERO);

        box.setFundraisingEvent(new FundraisingEvent());
        box.getFundraisingEvent().setFoundationAccount(foundationAccount);

        CurrencyConversionStrategy conversionStrategy = mock(CurrencyConversionStrategy.class);
        when(conversionStrategy.convert(BigDecimal.valueOf(100))).thenReturn(BigDecimal.valueOf(85));
        when(currencyConversionFactory.getStrategy(Currency.USD, Currency.EUR)).thenReturn(Optional.of(conversionStrategy));
        when(boxRepository.findById(boxId)).thenReturn(Optional.of(box));

        boxService.transferToFoundation(boxId);

        assertEquals(BigDecimal.valueOf(85), foundationAccount.getBalance());
        assertTrue(box.getBallanceMap().isEmpty());
        verify(boxRepository).findById(boxId);
        verify(currencyConversionFactory).getStrategy(Currency.USD, Currency.EUR);
    }

    @Test
    void transferToFoundationShouldThrowExceptionWhenBoxNotFound() {
        UUID boxId = UUID.randomUUID();

        when(boxRepository.findById(boxId)).thenReturn(Optional.empty());

        assertThrows(GeneralAppException.class, () -> boxService.transferToFoundation(boxId));
        verify(boxRepository).findById(boxId);
    }

    @Test
    void transferToFoundationShouldThrowExceptionWhenFoundationAccountNotFound() {
        UUID boxId = UUID.randomUUID();
        Box box = new Box();
        box.setId(boxId);
        box.setBallanceMap(new HashMap<>());
        box.setFundraisingEvent(new FundraisingEvent());

        when(boxRepository.findById(boxId)).thenReturn(Optional.of(box));

        assertThrows(GeneralAppException.class, () -> boxService.transferToFoundation(boxId));
        verify(boxRepository).findById(boxId);
    }
}