package com.galandnoah.mobile_money.transaction.dto;

import com.galandnoah.mobile_money.transaction.enums.TransactionStatus;
import com.galandnoah.mobile_money.transaction.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionResponse(
        String ref,
        String initiatorPhone,
        String beneficiaryPhone,
        BigDecimal amount,
        TransactionType type,
        TransactionStatus status,
        Instant createdAt
) {
}
