package com.galandnoah.mobile_money.transaction.pattern;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class WithdrawalFee implements FeeStrategy{

    /**
     * Calcule les frais de retrait
     * 0% sur les retraits.
     * */
    @Override
    public BigDecimal getFees(BigDecimal amount) {
        return amount
                .multiply(new BigDecimal("0.00"))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
