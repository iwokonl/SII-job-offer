package org.example.zadanko.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.zadanko.dto.CreateFundationAccount.CreateFundationAccountRequestDto;
import org.example.zadanko.dto.CreateFundationAccount.CreateFundationAccountResponseDto;
import org.example.zadanko.dto.FinacialReportResponseDto;
import org.example.zadanko.service.FundationAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/fundationAccount")
@RequiredArgsConstructor
public class FundationAccountController {

    private final FundationAccountService fundationAccountService;

    @GetMapping("/printFinancialReport")
    public ResponseEntity<FinacialReportResponseDto> printFinancialReport(@RequestParam("fundationAccountId") UUID fundationAccountId) {
        return ResponseEntity.ok(fundationAccountService.printFinancialReport(fundationAccountId));
    }

    @PostMapping("/create")
    public ResponseEntity<CreateFundationAccountResponseDto> createFundationAccount(@RequestBody @Valid CreateFundationAccountRequestDto createFundationAccountRequestDto) {
        return ResponseEntity.ok(fundationAccountService.createFundationAccount(createFundationAccountRequestDto));
    }
}
