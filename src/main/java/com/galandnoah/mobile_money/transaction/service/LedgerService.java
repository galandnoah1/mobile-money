package com.galandnoah.mobile_money.transaction.service;

import com.galandnoah.mobile_money.transaction.entity.LedgerEntry;
import com.galandnoah.mobile_money.transaction.enums.LedgerType;
import com.galandnoah.mobile_money.transaction.repository.LedgerEntryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class LedgerService {
    private final LedgerEntryRepository ledgerEntryRepository;

    /**
     * Create an entry in the ledger
     * */
    public LedgerEntry createEntry(String userPhone, BigDecimal amount, LedgerType type)
    {
        LedgerEntry entry = LedgerEntry.builder()
                .userPhone(userPhone)
                .amount(amount)
                .type(type)
                .build();

        return ledgerEntryRepository
                .save(entry);
    }
}
