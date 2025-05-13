package org.example.zadanko.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoundationAccount {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Currency currencyType;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToMany(mappedBy = "foundationAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FundraisingEvent> fundraisingEvents;


    @PrePersist
    @PreUpdate
    private void formatBalance() {
        if (balance != null) {
            balance = balance.setScale(2, RoundingMode.HALF_UP);
        }
    }
}
