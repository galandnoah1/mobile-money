package com.galandnoah.mobile_money.wallet.dto;

import java.math.BigDecimal;

public interface IWalletResponse {
    BigDecimal getBalance();
    String getCurrency();
}
