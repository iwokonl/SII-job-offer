package org.example.zadanko.service;

import lombok.RequiredArgsConstructor;
import org.example.zadanko.dto.CreatedFundrasingEvent.CreatedFundrasingEventRequestDto;
import org.example.zadanko.dto.CreatedFundrasingEvent.CreatedFundraisingEventResponseDto;
import org.example.zadanko.exception.GeneralAppException;
import org.example.zadanko.mapper.FundrasingEventMapper;
import org.example.zadanko.model.Box;
import org.example.zadanko.model.FoundationAccount;
import org.example.zadanko.model.FundraisingEvent;
import org.example.zadanko.repository.BoxRepository;
import org.example.zadanko.repository.FoundationAccountRepository;
import org.example.zadanko.repository.FundraisingEventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FundraisingEventService {

    private final FundraisingEventRepository fundraisingEventRepository;
    private final FoundationAccountRepository foundationAccountRepository;
    private final BoxRepository boxRepository;

    @Transactional
    public CreatedFundraisingEventResponseDto createFundraisingEvent(CreatedFundrasingEventRequestDto createdFundrasingEventRequestDto) {
        FundraisingEvent foundrasingEvent = FundrasingEventMapper.
                createdFundrasingEventRequestDtoToFundraisingEvent(
                        createdFundrasingEventRequestDto
                );
        FoundationAccount foundationAccount = foundationAccountRepository.findById(createdFundrasingEventRequestDto.foundationAccountId())
                .orElseThrow(() -> new GeneralAppException("Foundation account not found", HttpStatus.INTERNAL_SERVER_ERROR));

        foundrasingEvent.setFoundationAccount(foundationAccount);
        fundraisingEventRepository.save(foundrasingEvent);

        return FundrasingEventMapper.
                FundraisingEventToCreatedFundrasingEventResponseDto(
                        foundrasingEvent
                );
    }

    @Transactional
    public void removeBox(UUID boxId) {
        FundraisingEvent fundraisingEvent = fundraisingEventRepository.findByBoxId(boxId).orElseThrow(
                () -> new GeneralAppException("Fundraising event not found", HttpStatus.INTERNAL_SERVER_ERROR)
        );
        if (fundraisingEvent.getBox() == null) {
            throw new GeneralAppException("Box not assigned to this fundraising event", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Box box = fundraisingEvent.getBox();
        box.getBallanceMap().clear();
        box.setFundraisingEvent(null);
        fundraisingEvent.setBox(null);
    }

    @Transactional
    public void assignBox(UUID boxId, UUID fundraisingEventId) {
        FundraisingEvent fundraisingEvent = fundraisingEventRepository.findById(fundraisingEventId).orElseThrow(
                () -> new GeneralAppException("Fundraising event not found", HttpStatus.INTERNAL_SERVER_ERROR)
        );

        Box box = boxRepository.findById(boxId).orElseThrow(
                () -> new GeneralAppException("Box not found", HttpStatus.INTERNAL_SERVER_ERROR)
        );

        if (fundraisingEvent.getBox() != null) {
            throw new GeneralAppException("Box already assigned to this fundraising event", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!box.getBallanceMap().isEmpty()) {
            throw new GeneralAppException("Box is not empty", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        fundraisingEvent.setBox(box);
        box.setFundraisingEvent(fundraisingEvent);
    }

}
