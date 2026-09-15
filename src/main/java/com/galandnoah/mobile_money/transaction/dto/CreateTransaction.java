package com.galandnoah.mobile_money.transaction.dto;

import com.galandnoah.mobile_money.transaction.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateTransaction(
        @NotBlank(message = "Le numero de telephone de l'initiateur de la transaction ne peut etre nul")
        String initiatorPhone,

        @NotBlank(message = "Le numero de telephone du bénéficiaire ne peut etre nul")
        String beneficiaryPhone,

        @NotBlank(message = "Veuillez entrer le montant de la transaction")
        BigDecimal amount,

        @NotNull(message = "Veuillez entrer le type de transaction")
        TransactionType type
) {
}
