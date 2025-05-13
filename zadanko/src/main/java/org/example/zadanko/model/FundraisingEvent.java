package org.example.zadanko.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class FundraisingEvent {

    @Id
    @GeneratedValue
    private UUID id;

    

    @ManyToOne
    @JoinColumn(name = "foundation_account_id")
    private FoundationAccount foundationAccount;
}
