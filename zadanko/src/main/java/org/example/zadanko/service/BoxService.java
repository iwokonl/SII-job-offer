package org.example.zadanko.service;

import lombok.RequiredArgsConstructor;
import org.example.zadanko.designPatterns.factory.conversionCurrencyFactory.CurrencyConversionFactory;
import org.example.zadanko.dto.CreatedBox.CreatedBoxResponseDto;
import org.example.zadanko.dto.GetAllAnonymizedBoxesResponseDto;
import org.example.zadanko.dto.MoneyDonation.MoneyDonationRequestDto;
import org.example.zadanko.exception.GeneralAppException;
import org.example.zadanko.mapper.BoxMapper;
import org.example.zadanko.model.Box;
import org.example.zadanko.model.Currency;
import org.example.zadanko.model.FoundationAccount;
import org.example.zadanko.repository.BoxRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoxService {

    private final BoxRepository boxRepository;
    private final CurrencyConversionFactory currencyConversionFactory;


    @Transactional
    public CreatedBoxResponseDto createBox() {

        Box box = boxRepository.save(new Box());

        return BoxMapper.BoxToCratedBoxDto(box);
    }

    @Transactional
    public void moneyDonation(MoneyDonationRequestDto moneyDonationRequestDto) {
        Box box = boxRepository.findById(moneyDonationRequestDto.boxId()).orElseThrow(
                () -> new GeneralAppException("Box not found", HttpStatus.INTERNAL_SERVER_ERROR)
        );
        Map<Currency, BigDecimal> ballanceMap = box.getBallanceMap();
        if (ballanceMap == null) {
            ballanceMap = new HashMap<>();
            box.setBallanceMap(ballanceMap);
        }
        ballanceMap.compute(moneyDonationRequestDto.currency(), (key, value) ->
                value == null ? moneyDonationRequestDto.moneyAmmount() : value.add(moneyDonationRequestDto.moneyAmmount())
        );
    }

    @Transactional(readOnly = true)
    public List<GetAllAnonymizedBoxesResponseDto> getAllAnonymizedBoxes() {
        return boxRepository.findAll().stream()
                .map(box -> new GetAllAnonymizedBoxesResponseDto(
                        box.getId(),
                        box.getFundraisingEvent() != null,
                        box.getBallanceMap().isEmpty()
                ))
                .toList();
    }

    @Transactional
    public void transferToFoundation(UUID boxId) {
        Box box = boxRepository.findById(boxId).orElseThrow(
                () -> new GeneralAppException("Box not found", HttpStatus.INTERNAL_SERVER_ERROR)
        );
        Map<Currency, BigDecimal> ballanceMap = box.getBallanceMap();


        FoundationAccount foundationAccount = box.getFundraisingEvent().getFoundationAccount();
        if (foundationAccount == null) {
            throw new GeneralAppException("Foundation account not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        for (Currency currency : ballanceMap.keySet()) {
            currencyConversionFactory.getStrategy(currency, foundationAccount.getCurrencyType())
                    .ifPresent(strategy -> {
                        BigDecimal convertedAmount = strategy.convert(ballanceMap.get(currency));
                        foundationAccount.setBalance(
                                foundationAccount.getBalance().add(convertedAmount)
                        );
                    });
        }
        ballanceMap.clear();
    }

}
