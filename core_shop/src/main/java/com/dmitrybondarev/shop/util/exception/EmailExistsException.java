package com.dmitrybondarev.shop.util.exception;

public class EmailExistsException extends Exception {

    public EmailExistsException(final String message) {
        super(message);
    }
}
