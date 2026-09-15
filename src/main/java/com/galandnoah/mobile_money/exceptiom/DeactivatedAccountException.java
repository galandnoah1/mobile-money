package com.galandnoah.mobile_money.exceptiom;

public class DeactivatedAccountException extends RuntimeException {
    public DeactivatedAccountException() {
        super("Désolé, ce numero est associa a un compte suspendu. Veuillez contacter le service client");
    }
}
