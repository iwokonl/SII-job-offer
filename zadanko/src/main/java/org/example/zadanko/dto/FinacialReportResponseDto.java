package org.example.zadanko.dto;

import org.example.zadanko.model.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record FinacialReportResponseDto(String foundationName,
                                        BigDecimal fundationAmmount,
                                        Currency currency,
                                        List<FundraisingEventResponseDto> fundrasingEvents){
    public record FundraisingEventResponseDto(String name, Map<Currency, BigDecimal> amounts) {

    }

}
