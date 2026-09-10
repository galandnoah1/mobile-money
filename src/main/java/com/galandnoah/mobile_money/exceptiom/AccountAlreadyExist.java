package com.galandnoah.mobile_money.exceptiom;

public class AccountAlreadyExist extends RuntimeException {
    public AccountAlreadyExist() {
        super("Ce numero de telephone est deja utilisé");
    }
}
