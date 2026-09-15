package com.galandnoah.mobile_money.transaction.entity;


import com.galandnoah.mobile_money.transaction.enums.LedgerType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "ledger_entries")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LedgerEntry {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String transactionRef;

    private String userPhone;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private LedgerType type;

    private Instant createdAt;
}
