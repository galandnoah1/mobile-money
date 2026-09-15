package com.galandnoah.mobile_money.exceptiom;

public class AccountAlreadyExistException extends RuntimeException {
    public AccountAlreadyExistException() {
        super("Ce numero de telephone est deja utilisé");
    }
}
