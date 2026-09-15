package com.galandnoah.mobile_money.exceptiom;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Désolé votre solde est insuffisant. Veuillez recharger votre solde");
    }
}
