package org.example.zadanko.controller;


import org.example.zadanko.dto.CreatedFundrasingEventDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fundraising-event")
public class FundraisingEventController {



    @PostMapping("/create")
    public ResponseEntity<CreatedFundrasingEventDto> crateFundraisingEvent(){
        return ResponseEntity.ok(new CreatedFundrasingEventDto());
    }

    @PutMapping("/assign-box")
    @ResponseStatus(HttpStatus.OK)
    public void assignBoxToFundraisingEvent(){

    }
}
