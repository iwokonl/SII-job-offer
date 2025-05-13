package org.example.zadanko.repository;

import org.example.zadanko.model.FundraisingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FundraisingEventRepository extends JpaRepository<FundraisingEvent, UUID> {
    Optional<FundraisingEvent> findByBoxId(UUID boxId);

    Optional<FundraisingEvent> findById(UUID id);
}
