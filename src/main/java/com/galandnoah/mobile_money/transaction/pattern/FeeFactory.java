package com.galandnoah.mobile_money.transaction.pattern;

import com.galandnoah.mobile_money.transaction.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class FeeFactory {

    private final Map<TransactionType, FeeStrategy> feeStrategies;

    public FeeFactory(TransferFee transferFee, WithdrawalFee withdrawalFee)
    {
        this.feeStrategies = Map.of(TransactionType.TRANSFER, transferFee,
                                    TransactionType.WITHDRAWAL, withdrawalFee);
    }

    public FeeStrategy getFeeStrategy(TransactionType type)
    {
        return this.feeStrategies.get(type);
    }
}
