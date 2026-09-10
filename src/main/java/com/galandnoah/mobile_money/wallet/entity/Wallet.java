package com.galandnoah.mobile_money.wallet.entity;

import com.galandnoah.mobile_money.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "wallets")
@Builder
public class Wallet {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal balance = new BigDecimal("0.0");

    private String currency = "XAF";

    private Instant createdAt;

    private Instant updatedAt;
}
