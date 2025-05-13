package org.example.zadanko.controller;


import org.example.zadanko.dto.CreatedBox.CreatedBoxRequestDto;
import org.example.zadanko.dto.GetAllAnonymizedBoxesDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/box")
public class BoxController {


    @PostMapping("/create")
    public ResponseEntity<CreatedBoxRequestDto> createBox() {
        return ResponseEntity.ok(new CreatedBoxRequestDto());
    }

    @GetMapping("/getAllAnonymizedBoxes")
    public ResponseEntity<List<GetAllAnonymizedBoxesDto>> getAllAnonymizedBoxes() {
        return ResponseEntity.ok(List.of(new GetAllAnonymizedBoxesDto()));
    }

    @PutMapping("/moneyDonation")
    @ResponseStatus(HttpStatus.OK)
    public void moneyDonation(){

    }

    @PutMapping("/transferToFoundation")
    @ResponseStatus(HttpStatus.OK)
    public void transferToFoundation() {

    }
}
