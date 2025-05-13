package org.example.zadanko.controller;

import org.example.zadanko.dto.FinacialReportDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fundation-account")
public class FundationAccountController {

    @GetMapping("/printFinancialReport")
    public ResponseEntity<FinacialReportDto> printFinancialReport() {
        return ResponseEntity.ok(new FinacialReportDto());
    }
}
