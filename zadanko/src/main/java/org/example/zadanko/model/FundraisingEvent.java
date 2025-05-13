package org.example.zadanko.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FundraisingEvent {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "foundation_account_id", nullable = false)
    private FoundationAccount foundationAccount;

    @OneToOne
    @JoinColumn(name = "box_id", nullable = true)
    private Box box;
}
