package org.example.zadanko.repository;

import org.example.zadanko.model.FoundationAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FoundationAccountRepository extends JpaRepository<FoundationAccount, UUID> {

    Optional<FoundationAccount> findById(UUID id);
}
