package com.galandnoah.mobile_money.exceptiom;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super("Désolé ce numero n'a pas de compte. Veuillez créer un compte, c'est gratuit et facile");
    }
}
