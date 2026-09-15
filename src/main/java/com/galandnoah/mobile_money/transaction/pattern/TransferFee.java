package com.galandnoah.mobile_money.transaction.pattern;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TransferFee implements FeeStrategy {


    /**
     * Calcul les frais de transfert.
     * 1% du montant ne depassant jamais 5000 XAF
     * */
    @Override
    public BigDecimal getFees(BigDecimal amount) {

        BigDecimal transferFee = amount.multiply(new BigDecimal("0.01"))
                .setScale(2, RoundingMode.HALF_UP);

        return transferFee.doubleValue() >  5000 ? new BigDecimal("5000") : transferFee;
    }
}
