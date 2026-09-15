package com.galandnoah.mobile_money.transaction.dto;

import com.galandnoah.mobile_money.transaction.enums.LedgerType;

import java.time.Instant;

public record LedgerEntryResponse(
        String userPhone,
        String amount,
        LedgerType type,
        Instant createdAt
) {
}
