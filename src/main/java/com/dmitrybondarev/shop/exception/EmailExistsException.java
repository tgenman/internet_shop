package com.dmitrybondarev.shop.exception;

public class EmailExistsException extends Exception {

    public EmailExistsException(final String message) {
        super(message);
    }
}
