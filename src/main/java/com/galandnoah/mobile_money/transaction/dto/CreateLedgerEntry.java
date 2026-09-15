package com.galandnoah.mobile_money.transaction.dto;

import com.galandnoah.mobile_money.transaction.enums.LedgerType;

import java.math.BigDecimal;

public record CreateLedgerEntry(
        String userPhone,
        BigDecimal amount,
        LedgerType type
) {
}
