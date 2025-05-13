package org.example.zadanko.service;

import lombok.RequiredArgsConstructor;
import org.example.zadanko.dto.CreatedFundrasingEvent.CreatedFundrasingEventRequestDto;
import org.example.zadanko.dto.CreatedFundrasingEvent.CreatedFundrasingEventResponseDto;
import org.example.zadanko.dto.FinancialReport.FinacialReportResponseDto;
import org.example.zadanko.mapper.FundrasingEventMapper;
import org.example.zadanko.model.Box;
import org.example.zadanko.model.FoundationAccount;
import org.example.zadanko.model.FundraisingEvent;
import org.example.zadanko.repository.BoxRepository;
import org.example.zadanko.repository.FoundationAccountRepository;
import org.example.zadanko.repository.FundraisingEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FundrasingEventService {

    private final FundraisingEventRepository fundraisingEventRepository;
    private final FoundationAccountRepository foundationAccountRepository;
    private final BoxRepository boxRepository;

    @Transactional
    public CreatedFundrasingEventResponseDto createFundraisingEvent(CreatedFundrasingEventRequestDto createdFundrasingEventRequestDto) {
        FundraisingEvent foundrasingEvent = FundrasingEventMapper.
                createdFundrasingEventRequestDtoToFundraisingEvent(
                        createdFundrasingEventRequestDto
                );
        FoundationAccount foundationAccount = foundationAccountRepository.findById(createdFundrasingEventRequestDto.foundationAccountId())
                .orElseThrow(() -> new RuntimeException("Foundation account not found"));

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
                () -> new RuntimeException("Fundraising event not found")
        );
        if (fundraisingEvent.getBox() == null) {
            throw new RuntimeException("Box not assigned to this fundraising event");
        }
        Box box = fundraisingEvent.getBox();
        box.getBallanceMap().clear();
        box.setFundraisingEvent(null);
        fundraisingEvent.setBox(null);
    }

    @Transactional
    public void assignBox(UUID boxId, UUID fundraisingEventId) {
        FundraisingEvent fundraisingEvent = fundraisingEventRepository.findById(fundraisingEventId).orElseThrow(
                () -> new RuntimeException("Fundraising event not found")
        );

        Box box = boxRepository.findById(boxId).orElseThrow(
                () -> new RuntimeException("Box not found")
        );

        if (fundraisingEvent.getBox() != null) {
            throw new RuntimeException("Box already assigned to this fundraising event");
        }
        if (!box.getBallanceMap().isEmpty()) {
            throw new RuntimeException("Box is not empty");
        }
        fundraisingEvent.setBox(box);
        box.setFundraisingEvent(fundraisingEvent);
    }

    @Transactional(readOnly = true)
    public List<FinacialReportResponseDto> createFinancialReport() {
        return fundraisingEventRepository.findAll().stream()
                .map(fundraisingEvent -> new FinacialReportResponseDto(
                        fundraisingEvent.getName(),
                        fundraisingEvent.getFoundationAccount().getBalance(),
                        fundraisingEvent.getFoundationAccount().getCurrencyType()
                ))
                .toList();
    }
}
