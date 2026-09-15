package com.galandnoah.mobile_money.transaction.entity;


import com.galandnoah.mobile_money.transaction.enums.TransactionStatus;
import com.galandnoah.mobile_money.transaction.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal amount;

    private String reference;

    private String initiatorPhone;

    private String beneficiaryPhone;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private UUID idempotencyKey;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
