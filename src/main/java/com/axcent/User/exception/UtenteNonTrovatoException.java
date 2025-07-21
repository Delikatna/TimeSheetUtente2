package com.axcent.User.exception;

public class UtenteNonTrovatoException extends RuntimeException {

    public UtenteNonTrovatoException() {
        super("Non ci sono utenti disponibili");
    }

    public UtenteNonTrovatoException(String message) {
        super(message);
    }
}
