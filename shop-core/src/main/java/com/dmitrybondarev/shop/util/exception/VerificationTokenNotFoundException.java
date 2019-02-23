package com.dmitrybondarev.shop.util.exception;

public class VerificationTokenNotFoundException extends Exception {


    private static final long serialVersionUID = 5861315336628763L;

    public VerificationTokenNotFoundException() {
        super();
    }

    public VerificationTokenNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public VerificationTokenNotFoundException(final String message) {
        super(message);
    }

    public VerificationTokenNotFoundException(final Throwable cause) {
        super(cause);
    }
}
