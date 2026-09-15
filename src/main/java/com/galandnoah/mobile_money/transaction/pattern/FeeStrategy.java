package com.galandnoah.mobile_money.transaction.pattern;


import java.math.BigDecimal;

public interface FeeStrategy {
    BigDecimal getFees(BigDecimal amount);
}
