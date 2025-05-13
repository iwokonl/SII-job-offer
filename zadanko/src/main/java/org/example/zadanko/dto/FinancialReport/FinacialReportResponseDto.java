package org.example.zadanko.dto.FinancialReport;

import org.example.zadanko.model.Currency;

import java.math.BigDecimal;

public record FinacialReportResponseDto(
        String fundraisingEventName,
        BigDecimal totalAmountOnFundationAccount,
        Currency currency
) {
}
