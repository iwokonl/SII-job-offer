package org.example.zadanko.controller;


import lombok.RequiredArgsConstructor;
import org.example.zadanko.dto.CreatedBox.CreatedBoxResponseDto;
import org.example.zadanko.dto.GetAllAnonymizedBoxes.GetAllAnonymizedBoxesResponseDto;
import org.example.zadanko.dto.MoneyDonation.MoneyDonationRequestDto;
import org.example.zadanko.service.BoxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/box")
@RequiredArgsConstructor
public class BoxController {

    private final BoxService boxService;

    @PostMapping("/create")
    public ResponseEntity<CreatedBoxResponseDto> createBox() {
        return ResponseEntity.ok(boxService.createBox());
    }

    @GetMapping("/getAllAnonymizedBoxes")
    public ResponseEntity<List<GetAllAnonymizedBoxesResponseDto>> getAllAnonymizedBoxes() {
        return ResponseEntity.ok(boxService.getAllAnonymizedBoxes());
    }

    @PutMapping("/moneyDonation")
    @ResponseStatus(HttpStatus.OK)
    public void moneyDonation(@RequestBody MoneyDonationRequestDto moneyDonationRequestDto) {
        boxService.moneyDonation(moneyDonationRequestDto);

    }

    @PutMapping("/transferToFoundation")
    @ResponseStatus(HttpStatus.OK)
    public void transferToFoundation(@RequestParam("boxId") UUID boxId) {
        boxService.transferToFoundation(boxId);
    }
}
