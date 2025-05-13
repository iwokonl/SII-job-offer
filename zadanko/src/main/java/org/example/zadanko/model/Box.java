package org.example.zadanko.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Box {

    @Id
    @GeneratedValue
    private UUID id;
    @ElementCollection
    private Map<Currency, BigDecimal> ballanceMap;

    @OneToOne(mappedBy = "box", cascade = CascadeType.ALL)
    private FundraisingEvent fundraisingEvent;

    @PreUpdate
    private void checkAssignmentBeforeUpdate() {
        if (fundraisingEvent == null) {
            ballanceMap.clear();
        }
    }
}
