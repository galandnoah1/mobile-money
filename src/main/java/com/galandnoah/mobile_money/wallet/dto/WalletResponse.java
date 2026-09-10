package com.galandnoah.mobile_money.wallet.dto;

import java.math.BigDecimal;


public record WalletResponse(
        BigDecimal balance,
        String currency
) {
}
