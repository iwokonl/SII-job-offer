package org.example.zadanko.repository;

import org.example.zadanko.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoxRepository extends JpaRepository<Box, UUID> {


}
